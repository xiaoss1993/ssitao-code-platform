package com.ssitao.code.modular.iam.identity.application.service.impl;

import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountCreateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountUpdateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamPasswordChangeCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamPasswordResetCommand;
import com.ssitao.code.modular.iam.identity.application.query.IamAccountQuery;
import com.ssitao.code.modular.iam.identity.application.service.IamAccountAppService;
import com.ssitao.code.modular.iam.identity.domain.model.IamAccount;
import com.ssitao.code.modular.iam.identity.domain.repository.IamAccountRepository;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamAccountConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM账号应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamAccountAppServiceImpl implements IamAccountAppService {

    @Resource
    private IamAccountRepository accountRepository;

    @Resource
    private IamAccountConverter accountConverter;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createAccount(IamAccountCreateCommand command) {
        // 检查账号编码是否已存在
        if (accountRepository.existsByAccountCode(command.getAccountCode(),
                command.getTenantId(), null)) {
            throw new IllegalArgumentException("账号编码已存在: " + command.getAccountCode());
        }

        // 检查手机号是否已存在
        if (command.getPhone() != null &&
            accountRepository.existsByPhone(command.getPhone(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("手机号已被使用: " + command.getPhone());
        }

        // 加密密码
        String salt = UUID.randomUUID().toString().replace("-", "");
        String encodedPassword = passwordEncoder.encode(command.getPassword() + salt);

        // 创建账号聚合根
        IamAccount account = IamAccount.create(
                command.getAccountCode(),
                command.getAccountName(),
                encodedPassword,
                salt
        );

        // 设置账号ID
        account.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置其他属性
        account.setPhone(command.getPhone());
        account.setEmail(command.getEmail());
        account.setAvatar(command.getAvatar());
        account.setExpireTime(command.getExpireTime());
        account.setTenantId(command.getTenantId());
        account.setOrgId(command.getOrgId());
        account.setRemark(command.getRemark());

        // 保存
        return accountRepository.save(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAccount(IamAccountUpdateCommand command) {
        IamAccount account = accountRepository.findById(command.getId())
                .orElseThrow(() -> new IllegalArgumentException("账号不存在: " + command.getId()));

        // 检查手机号是否已被其他账号使用
        if (command.getPhone() != null &&
            accountRepository.existsByPhone(command.getPhone(), account.getTenantId(), command.getId())) {
            throw new IllegalArgumentException("手机号已被使用: " + command.getPhone());
        }

        // 更新属性
        if (command.getAccountName() != null) {
            account.setAccountName(command.getAccountName());
        }
        account.setPhone(command.getPhone());
        account.setEmail(command.getEmail());
        account.setAvatar(command.getAvatar());
        account.setExpireTime(command.getExpireTime());
        account.setRemark(command.getRemark());
        account.setUpdateTime(LocalDateTime.now());
        account.setUpdater(command.getUpdater());

        accountRepository.update(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAccount(String id, String tenantId) {
        accountRepository.delete(id);
    }

    @Override
    public IamAccountDTO getAccount(String id, String tenantId) {
        IamAccount account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在: " + id));
        return accountConverter.toDTOFromDomain(account);
    }

    @Override
    public IamAccountDTO getAccountByCode(String accountCode, String tenantId) {
        IamAccount account = accountRepository.findByAccountCode(accountCode, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在: " + accountCode));
        return accountConverter.toDTOFromDomain(account);
    }

    @Override
    public List<IamAccountDTO> listAccounts(IamAccountQuery query) {
        List<IamAccount> accounts = accountRepository.findByConditions(
                query.getAccountName(),
                query.getPhone(),
                query.getStatus(),
                query.getTenantId()
        );
        return accounts.stream()
                .map(accountConverter::toDTOFromDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<IamAccountDTO> pageAccounts(IamAccountQuery query, int page, int size) {
        // 简单分页实现
        List<IamAccount> accounts = accountRepository.findByConditions(
                query.getAccountName(),
                query.getPhone(),
                query.getStatus(),
                query.getTenantId()
        );
        return accounts.stream()
                .skip((long) page * size)
                .limit(size)
                .map(accountConverter::toDTOFromDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void lockAccount(String id, Integer expireDays, String tenantId) {
        IamAccount account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在"));

        LocalDateTime expireTime = null;
        if (expireDays != null && expireDays > 0) {
            expireTime = LocalDateTime.now().plusDays(expireDays);
        }

        account.lock(expireTime);
        accountRepository.update(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unlockAccount(String id, String tenantId) {
        IamAccount account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在"));

        account.unlock();
        accountRepository.update(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableAccount(String id, String tenantId) {
        IamAccount account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在"));

        account.disable();
        accountRepository.update(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableAccount(String id, String tenantId) {
        IamAccount account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在"));

        account.enable();
        accountRepository.update(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(IamPasswordChangeCommand command) {
        IamAccount account = accountRepository.findById(command.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("账号不存在"));

        // 验证原密码
        String oldEncodedPassword = passwordEncoder.encode(command.getOldPassword() + account.getSalt());
        if (!account.verifyPassword(oldEncodedPassword)) {
            throw new IllegalArgumentException("原密码不正确");
        }

        // 生成新盐值和加密密码
        String newSalt = UUID.randomUUID().toString().replace("-", "");
        String newEncodedPassword = passwordEncoder.encode(command.getNewPassword() + newSalt);

        account.changePassword(newEncodedPassword, newSalt);
        accountRepository.update(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(IamPasswordResetCommand command) {
        IamAccount account = accountRepository.findById(command.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("账号不存在"));

        // 生成新盐值和加密密码
        String newSalt = UUID.randomUUID().toString().replace("-", "");
        String newEncodedPassword = passwordEncoder.encode(command.getNewPassword() + newSalt);

        account.resetPassword(newEncodedPassword, newSalt);
        accountRepository.update(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPasswordById(String id, String newPassword, String tenantId) {
        IamAccount account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在"));

        // 生成新盐值和加密密码
        String newSalt = UUID.randomUUID().toString().replace("-", "");
        String newEncodedPassword = passwordEncoder.encode(newPassword + newSalt);

        account.resetPassword(newEncodedPassword, newSalt);
        accountRepository.update(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindUser(String accountId, String userId, String tenantId) {
        IamAccount account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在"));

        // 设置关联的用户ID
        account.setUserId(userId);
        account.setUpdateTime(LocalDateTime.now());

        accountRepository.update(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unbindUser(String accountId, String tenantId) {
        IamAccount account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在: " + accountId));

        // 清空关联的用户ID，实现解绑
        account.setUserId(null);
        account.setUpdateTime(LocalDateTime.now());

        accountRepository.update(account);
    }

    @Override
    public Object getBoundUser(String accountId, String tenantId) {
        // 获取账号绑定的用户信息
        IamAccount account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("账号不存在"));

        // 账号中的 userId 就是关联的用户ID
        String userId = account.getUserId();
        if (userId == null || userId.isEmpty()) {
            return null;
        }

        // 返回用户ID，可以通过用户服务获取完整的用户信息
        return userId;
    }
}
