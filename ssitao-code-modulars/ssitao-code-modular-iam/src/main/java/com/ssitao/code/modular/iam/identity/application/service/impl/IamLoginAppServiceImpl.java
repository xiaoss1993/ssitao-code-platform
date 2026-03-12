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
import com.ssitao.code.modular.iam.identity.application.service.CaptchaService;
import com.ssitao.code.modular.iam.identity.application.service.IamLoginAppService;
import com.ssitao.code.modular.iam.identity.domain.model.IamAccount;
import com.ssitao.code.modular.iam.identity.domain.model.IamLoginLog;
import com.ssitao.code.modular.iam.identity.domain.repository.IamAccountRepository;
import com.ssitao.code.modular.iam.identity.domain.repository.IamLoginLogRepository;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamAccountConverter;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamLoginLogConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * IAM登录应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
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

    @Resource
    private CaptchaService captchaService;

    private static final long ACCESS_TOKEN_EXPIRE_SECONDS = 7200L; // 2 hours
    private static final long REFRESH_TOKEN_EXPIRE_DAYS = 7;

    /**
     * 模拟用户数据（开发环境使用）
     */
    private static final Map<String, MockUser> MOCK_USERS = new HashMap<>();

    static {
        // admin用户
        MOCK_USERS.put("admin", new MockUser(
                "1",
                "admin",
                "admin123",
                "管理员",
                "https://avatars.githubusercontent.com/u/1?v=4",
                "admin@example.com",
                "13800138000",
                "技术部",
                "超级管理员",
                Arrays.asList("admin", "super-admin"),
                Arrays.asList("*:*:*")
        ));

        // user用户
        MOCK_USERS.put("user", new MockUser(
                "2",
                "user",
                "user123",
                "普通用户",
                "https://avatars.githubusercontent.com/u/2?v=4",
                "user@example.com",
                "13800138001",
                "业务部",
                "普通用户",
                Arrays.asList("user"),
                Arrays.asList("user:view", "user:edit")
        ));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamLoginResultDTO login(IamLoginCommand command) {
        // 校验图形验证码
        if (command.getCaptchaId() != null && command.getCaptchaCode() != null) {
            boolean captchaValid = captchaService.verifyCaptcha(command.getCaptchaId(), command.getCaptchaCode());
            if (!captchaValid) {
                throw new IllegalArgumentException("图形验证码错误");
            }
        }

        String tenantId = command.getTenantId() != null ? command.getTenantId() : "1";

        // 首先尝试从数据库查找账号
        Optional<IamAccount> accountOpt = accountRepository.findByAccountCode(command.getUsername(), tenantId);

        IamAccount account;
        boolean passwordMatch = false;
        MockUser mockUser = null;

        if (accountOpt.isPresent()) {
            // 数据库中存在账号，验证密码
            account = accountOpt.get();

            // 验证密码 - 简化处理，直接用BCrypt验证
            if (account.getPassword() != null && account.getPassword().startsWith("$2")) {
                passwordMatch = passwordEncoder.matches(command.getPassword(), account.getPassword());
            } else {
                // 不再支持明文或MD5密码
                log.warn("用户[{}]的密码格式不正确", command.getUsername());
                passwordMatch = false;
            }

            if (!passwordMatch) {
                recordLoginLog(account.getId(), command.getUsername(), command.getLoginType(),
                        tenantId, false, "密码错误", null, null);
                throw new IllegalArgumentException("账号或密码错误");
            }

            // 检查账号状态
            if (!account.isAvailable()) {
                throw new IllegalArgumentException("账号已被禁用或锁定");
            }
        } else {
            // 数据库中不存在，使用模拟账号验证
            mockUser = MOCK_USERS.get(command.getUsername());
            if (mockUser == null) {
                throw new IllegalArgumentException("账号或密码错误");
            }

            if (!mockUser.password.equals(command.getPassword())) {
                throw new IllegalArgumentException("账号或密码错误");
            }

            // 使用模拟账号信息
            account = createMockAccount(mockUser, tenantId);
            passwordMatch = true;
        }

        // 使用 Sa-Token 进行登录，生成 Token
        // 登录ID格式：accountId_tenantId，用于区分不同租户的同一账号
        String loginId = account.getId() + "_" + tenantId;
        // 根据 keepLogin 设置 token 有效期：记住登录=30天，普通登录=24小时
        long tokenTimeout = Boolean.TRUE.equals(command.getKeepLogin()) ? 2592000L : 86400L;
        StpUtil.login(loginId, tokenTimeout);
        String accessToken = StpUtil.getTokenValue();
        long expiresIn = ACCESS_TOKEN_EXPIRE_SECONDS;

        // 生成刷新令牌
        String refreshToken = UUID.randomUUID().toString().replace("-", "");

        // 记录登录成功日志
        recordLoginLog(account.getId(), command.getUsername(), command.getLoginType(),
                tenantId, true, null, null, null);

        // 转换账号DTO
        IamAccountDTO accountDTO = accountConverter.toDTOFromDomain(account);

        // 构建用户信息（前端格式）
        IamLoginResultDTO.UserInfo userInfo;
        if (mockUser != null) {
            userInfo = buildUserInfoFromMock(mockUser);
        } else {
            userInfo = buildUserInfoFromAccount(account);
        }

        // 构建返回结果
        return IamLoginResultDTO.builder()
                .account(accountDTO)
                .userInfo(userInfo)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(expiresIn)
                .tenantId(tenantId)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout(String token, IamLogoutCommand command) {
        try {
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            if (tokenInfo != null) {
                StpUtil.logout(tokenInfo.getLoginId());
            }
        } catch (Exception e) {
            log.warn("登出时异常: {}", e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamLoginResultDTO refreshToken(IamRefreshTokenCommand command) {
        try {
            Object loginId = StpUtil.getLoginIdByToken(command.getRefreshToken());
            if (loginId != null) {
                StpUtil.renewTimeout(StpUtil.getTokenValue(), ACCESS_TOKEN_EXPIRE_SECONDS);
                String newAccessToken = StpUtil.getTokenValue();

                IamAccount account = accountRepository.findById(loginId.toString())
                        .orElseThrow(() -> new IllegalArgumentException("账号不存在"));
                IamAccountDTO accountDTO = accountConverter.toDTOFromDomain(account);

                IamLoginResultDTO.UserInfo userInfo = buildUserInfoFromAccount(account);

                return IamLoginResultDTO.builder()
                        .account(accountDTO)
                        .userInfo(userInfo)
                        .accessToken(newAccessToken)
                        .refreshToken(command.getRefreshToken())
                        .tokenType("Bearer")
                        .expiresIn(ACCESS_TOKEN_EXPIRE_SECONDS)
                        .build();
            }
        } catch (Exception e) {
            log.warn("刷新Token失败: {}", e.getMessage());
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
                // 从 loginId 中提取真正的 accountId（格式：accountId_tenantId）
                String accountId = extractAccountId(loginId);

                // 先从数据库查找
                Optional<IamAccount> accountOpt = accountRepository.findById(accountId);
                if (accountOpt.isPresent()) {
                    return accountConverter.toDTOFromDomain(accountOpt.get());
                }

                // 如果数据库没有，从模拟数据查找
                for (MockUser mockUser : MOCK_USERS.values()) {
                    if (mockUser.id.equals(accountId)) {
                        String tenantId = extractTenantId(loginId);
                        IamAccount mockAccount = createMockAccount(mockUser, tenantId);
                        return accountConverter.toDTOFromDomain(mockAccount);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("获取当前用户失败: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public Object getCurrentUserPermissions(String token) {
        try {
            Object loginId = StpUtil.getLoginIdByToken(token);
            if (loginId != null) {
                return StpUtil.getPermissionList();
            }
        } catch (Exception e) {
            log.warn("获取用户权限失败: {}", e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public Object getCurrentUserRoles(String token) {
        try {
            Object loginId = StpUtil.getLoginIdByToken(token);
            if (loginId != null) {
                return StpUtil.getRoleList();
            }
        } catch (Exception e) {
            log.warn("获取用户角色失败: {}", e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<IamLoginLogDTO> queryLoginLogs(IamLoginLogQuery query) {
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
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public IamLoginLogDTO getLoginLog(String id) {
        IamLoginLog log = loginLogRepository.findById(id, null)
                .orElseThrow(() -> new IllegalArgumentException("登录日志不存在: " + id));
        return convertToDTO(log);
    }

    /**
     * 从模拟用户构建用户信息
     */
    private IamLoginResultDTO.UserInfo buildUserInfoFromMock(MockUser mockUser) {
        return IamLoginResultDTO.UserInfo.builder()
                .id(mockUser.id)
                .username(mockUser.username)
                .realName(mockUser.realName)
                .nickname(mockUser.realName)
                .avatar(mockUser.avatar)
                .email(mockUser.email)
                .phone(mockUser.phone)
                .deptName(mockUser.deptName)
                .postName(mockUser.postName)
                .roles(mockUser.roles)
                .permissions(mockUser.permissions)
                .build();
    }

    /**
     * 从账号构建用户信息
     */
    private IamLoginResultDTO.UserInfo buildUserInfoFromAccount(IamAccount account) {
        return IamLoginResultDTO.UserInfo.builder()
                .id(account.getId())
                .username(account.getAccountCode())
                .realName(account.getAccountName())
                .nickname(account.getAccountName())
                .avatar(account.getAvatar())
                .email(account.getEmail())
                .phone(account.getPhone())
                .deptName("默认部门")
                .postName("默认岗位")
                .roles(Collections.singletonList("user"))
                .permissions(Collections.emptyList())
                .dashboard("0")
                .tenantId(account.getTenantId())
                .build();
    }

    /**
     * 创建模拟账号对象
     */
    private IamAccount createMockAccount(MockUser mockUser, String tenantId) {
        return IamAccount.create(mockUser.username, mockUser.realName, mockUser.password, null)
                .toBuilder()
                .id(mockUser.id)
                .phone(mockUser.phone)
                .email(mockUser.email)
                .avatar(mockUser.avatar)
                .userId(mockUser.id)
                .tenantId(tenantId)
                .status(true)
                .locked(false)
                .build();
    }

    /**
     * 记录登录日志
     */
    private void recordLoginLog(String accountId, String username, String loginType,
                                String tenantId, boolean success, String errorMsg,
                                String loginIp, String deviceInfo) {
        try {
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
        } catch (Exception e) {
            log.warn("记录登录日志失败: {}", e.getMessage());
        }
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
     * 模拟用户
     */
    private static class MockUser {
        String id;
        String username;
        String password;
        String realName;
        String avatar;
        String email;
        String phone;
        String deptName;
        String postName;
        List<String> roles;
        List<String> permissions;

        MockUser(String id, String username, String password, String realName, String avatar,
                 String email, String phone, String deptName, String postName,
                 List<String> roles, List<String> permissions) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.realName = realName;
            this.avatar = avatar;
            this.email = email;
            this.phone = phone;
            this.deptName = deptName;
            this.postName = postName;
            this.roles = roles;
            this.permissions = permissions;
        }
    }

    /**
     * 从 loginId 中提取账号ID
     * loginId 格式：accountId_tenantId
     *
     * @param loginId 登录ID
     * @return 账号ID
     */
    public static String extractAccountId(Object loginId) {
        if (loginId == null) {
            return null;
        }
        String loginIdStr = loginId.toString();
        int underscoreIndex = loginIdStr.lastIndexOf("_");
        if (underscoreIndex > 0) {
            return loginIdStr.substring(0, underscoreIndex);
        }
        return loginIdStr;
    }

    /**
     * 从 loginId 中提取租户ID
     * loginId 格式：accountId_tenantId
     *
     * @param loginId 登录ID
     * @return 租户ID
     */
    public static String extractTenantId(Object loginId) {
        if (loginId == null) {
            return null;
        }
        String loginIdStr = loginId.toString();
        int underscoreIndex = loginIdStr.lastIndexOf("_");
        if (underscoreIndex > 0 && underscoreIndex < loginIdStr.length() - 1) {
            return loginIdStr.substring(underscoreIndex + 1);
        }
        return "default";
    }
}
