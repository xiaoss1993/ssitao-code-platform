package com.ssitao.code.modular.iam.identity.application.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.api.dto.IamLoginLogDTO;
import com.ssitao.code.modular.iam.identity.api.dto.IamLoginResultDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamLoginCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamLogoutCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamRefreshTokenCommand;
import com.ssitao.code.modular.iam.identity.application.query.IamLoginLogQuery;
import com.ssitao.code.modular.iam.identity.application.service.IamLoginAppService;
import com.ssitao.code.modular.iam.identity.domain.model.IamAccount;
import com.ssitao.code.modular.iam.identity.domain.model.IamLoginLog;
import com.ssitao.code.modular.iam.identity.domain.repository.IamAccountRepository;
import com.ssitao.code.modular.iam.identity.domain.repository.IamLoginLogRepository;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamAccountConverter;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamLoginLogConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM登录应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamLoginAppServiceImpl implements IamLoginAppService {

    @Resource
    private IamAccountRepository accountRepository;

    @Resource
    private IamLoginLogRepository loginLogRepository;

    @Resource
    private IamAccountConverter accountConverter;

    @Resource
    private IamLoginLogConverter loginLogConverter;

    @Resource
    private PasswordEncoder passwordEncoder;

    private static final long ACCESS_TOKEN_EXPIRE_SECONDS = 7200L; // 2 hours
    private static final long REFRESH_TOKEN_EXPIRE_DAYS = 7;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamLoginResultDTO login(IamLoginCommand command) {
        // 查找账号
        IamAccount account = accountRepository.findByAccountCode(command.getUsername(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("账号或密码错误"));

        // 验证密码 (支持 BCrypt 和 MD5 两种格式)
        boolean passwordMatch = false;
        if (account.getPassword() != null && account.getPassword().startsWith("$2")) {
            // BCrypt 格式密码，使用 PasswordEncoder 验证
            passwordMatch = passwordEncoder.matches(command.getPassword(), account.getPassword());
        } else {
            // MD5 格式密码，计算 MD5 后直接比较
            String inputPassword = command.getPassword() + (account.getSalt() != null ? account.getSalt() : "");
            String md5Hash = md5Hash(inputPassword);
            passwordMatch = md5Hash.equals(account.getPassword());
        }

        if (!passwordMatch) {
            // 记录登录失败日志
            recordLoginLog(account.getId(), command.getUsername(), command.getLoginType(),
                    command.getTenantId(), false, "密码错误", null, null);
            throw new IllegalArgumentException("账号或密码错误");
        }

        // 检查账号状态
        if (!account.isAvailable()) {
            throw new IllegalArgumentException("账号已被禁用或锁定");
        }

        // 使用 Sa-Token 进行登录，生成 Token
        // Sa-Token 会自动生成 UUID Token 并存储在 Session 中
        StpUtil.login(account.getId(), command.getTenantId());
        String accessToken = StpUtil.getTokenValue();
        // 获取 Token 有效期（默认使用配置的有效期）
        long expiresIn = ACCESS_TOKEN_EXPIRE_SECONDS;

        // 生成刷新令牌（使用 Sa-Token 的 Token 值）
        String refreshToken = StpUtil.getTokenValue();

        // 记录登录成功日志
        recordLoginLog(account.getId(), command.getUsername(), command.getLoginType(),
                command.getTenantId(), true, null, null, null);

        // 构建返回结果
        IamAccountDTO accountDTO = accountConverter.toDTOFromDomain(account);
        return IamLoginResultDTO.builder()
                .account(accountDTO)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(expiresIn)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout(String token, IamLogoutCommand command) {
        // 实现登出逻辑
        // 使用 Sa-Token 的登出功能
        try {
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            if (tokenInfo != null) {
                StpUtil.logout(tokenInfo.getLoginId());
            }
        } catch (Exception e) {
            // 忽略登出时的异常
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamLoginResultDTO refreshToken(IamRefreshTokenCommand command) {
        // 实现刷新Token逻辑
        // 使用 Sa-Token 的刷新功能
        try {
            Object loginId = StpUtil.getLoginIdByToken(command.getRefreshToken());
            if (loginId != null) {
                // 重新登录获取新token
                StpUtil.renewTimeout(StpUtil.getTokenValue(), ACCESS_TOKEN_EXPIRE_SECONDS);
                String newAccessToken = StpUtil.getTokenValue();

                // 获取账号信息
                IamAccount account = accountRepository.findById(loginId.toString())
                        .orElseThrow(() -> new IllegalArgumentException("账号不存在"));
                IamAccountDTO accountDTO = accountConverter.toDTOFromDomain(account);

                return IamLoginResultDTO.builder()
                        .account(accountDTO)
                        .accessToken(newAccessToken)
                        .refreshToken(command.getRefreshToken())
                        .tokenType("Bearer")
                        .expiresIn(ACCESS_TOKEN_EXPIRE_SECONDS)
                        .build();
            }
        } catch (Exception e) {
            // refresh token 无效
        }

        throw new IllegalArgumentException("刷新Token无效或已过期");
    }

    @Override
    public Boolean verifyToken(String token) {
        try {
            Object loginId = StpUtil.getLoginIdByToken(token);
            return loginId != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Object getCurrentUser(String token) {
        try {
            Object loginId = StpUtil.getLoginIdByToken(token);
            if (loginId != null) {
                // 获取账号信息
                IamAccount account = accountRepository.findById(loginId.toString())
                        .orElseThrow(() -> new IllegalArgumentException("账号不存在"));
                return accountConverter.toDTOFromDomain(account);
            }
        } catch (Exception e) {
            // token 无效或过期
        }
        return null;
    }

    @Override
    public Object getCurrentUserPermissions(String token) {
        // 获取当前登录用户的权限列表
        try {
            Object loginId = StpUtil.getLoginIdByToken(token);
            if (loginId != null) {
                // 通过 Sa-Token 的 StpInterface 获取权限列表
                return StpUtil.getPermissionList();
            }
        } catch (Exception e) {
            // token 无效或过期
        }
        return Collections.emptyList();
    }

    @Override
    public Object getCurrentUserRoles(String token) {
        // 获取当前登录用户的角色列表
        try {
            Object loginId = StpUtil.getLoginIdByToken(token);
            if (loginId != null) {
                // 通过 Sa-Token 的 StpInterface 获取角色列表
                return StpUtil.getRoleList();
            }
        } catch (Exception e) {
            // token 无效或过期
        }
        return Collections.emptyList();
    }

    @Override
    public List<IamLoginLogDTO> queryLoginLogs(IamLoginLogQuery query) {
        // 将loginStatus字符串转换为Boolean
        Boolean loginStatus = null;
        if (query.getLoginStatus() != null) {
            loginStatus = "success".equalsIgnoreCase(query.getLoginStatus());
        }

        List<IamLoginLog> logs = loginLogRepository.findByConditions(
                query.getUsername(),
                query.getLoginType(),
                loginStatus,
                query.getStartTime(),
                query.getEndTime(),
                query.getTenantId()
        );
        return logs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IamLoginLogDTO getLoginLog(String id) {
        IamLoginLog log = loginLogRepository.findById(id, null)
                .orElseThrow(() -> new IllegalArgumentException("登录日志不存在: " + id));
        return convertToDTO(log);
    }

    /**
     * 记录登录日志
     */
    private void recordLoginLog(String accountId, String username, String loginType,
                                String tenantId, boolean success, String errorMsg,
                                String loginIp, String deviceInfo) {
        IamLoginLog loginLog = IamLoginLog.create(
                accountId,
                username,
                loginType,
                loginIp,
                deviceInfo,
                success,
                errorMsg
        );
        loginLog.setTenantId(tenantId);
        loginLogRepository.save(loginLog);
    }

    /**
     * 生成新的访问令牌（用于刷新Token场景）
     * 使用 Sa-Token 的 Token 续期功能
     */
    private String generateNewAccessToken() {
        // 续期当前 Token
        StpUtil.renewTimeout(ACCESS_TOKEN_EXPIRE_SECONDS);
        return StpUtil.getTokenValue();
    }

    /**
     * 转换登录日志为DTO
     */
    private IamLoginLogDTO convertToDTO(IamLoginLog log) {
        return IamLoginLogDTO.builder()
                .id(log.getId())
                .username(log.getUsername())
                .userId(log.getUserId())
                .accountId(log.getAccountId())
                .loginType(log.getLoginType())
                .loginIp(log.getLoginIp())
                .loginLocation(log.getLoginLocation())
                .deviceInfo(log.getDeviceInfo())
                .loginStatus(log.getLoginStatus())
                .errorMessage(log.getErrorMessage())
                .loginTime(log.getLoginTime())
                .build();
    }

    /**
     * MD5哈希
     */
    private String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5 hash failed", e);
        }
    }
}
