package com.ssitao.code.modular.iam.identity.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * IAM账号聚合根
 * Identity领域的核心聚合根
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamAccount {

    /**
     * 账号ID
     */
    private String id;

    /**
     * 账号编码
     */
    private String accountCode;

    /**
     * 账号名称
     */
    private String accountName;

    /**
     * 密码(加密)
     */
    private String password;

    /**
     * 密码盐值
     */
    private String salt;

    /**
     * 第三方OpenID
     */
    private String openId;

    /**
     * 第三方类型
     */
    private String openType;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 状态：1-正常 0-禁用
     */
    private Boolean status;

    /**
     * 是否锁定：1-是 0-否
     */
    private Boolean locked;

    /**
     * 锁定过期时间
     */
    private LocalDateTime lockExpireTime;

    /**
     * 账号过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 密码是否已初始化：1-是 0-否
     */
    private Boolean passwordInited;

    /**
     * 是否永久账号：1-是 0-否
     */
    private Boolean permanent;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后密码修改时间
     */
    private LocalDateTime lastPasswordChangeTime;

    /**
     * 关联用户ID
     */
    private String userId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 组织ID
     */
    private String orgId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 是否删除：1-是 0-否
     */
    private Boolean deleted;

    /**
     * 登录记录列表
     */
    private List<IamLoginLog> loginLogs;

    /**
     * 创建账号
     *
     * @param accountCode 账号编码
     * @param accountName 账号名称
     * @param password    加密后的密码
     * @param salt        盐值
     * @return 账号聚合根
     */
    public static IamAccount create(String accountCode, String accountName, String password, String salt) {
        IamAccount account = new IamAccount();
        account.setAccountCode(accountCode);
        account.setAccountName(accountName);
        account.setPassword(password);
        account.setSalt(salt);
        account.setStatus(true);
        account.setLocked(false);
        account.setPasswordInited(false);
        account.setPermanent(false);
        account.setCreateTime(LocalDateTime.now());
        account.setLoginLogs(new ArrayList<>());
        return account;
    }

    /**
     * 验证密码
     *
     * @param inputPassword 输入的密码(加密后)
     * @return true-验证成功，false-验证失败
     */
    public boolean verifyPassword(String inputPassword) {
        return this.password != null && this.password.equals(inputPassword);
    }

    /**
     * 修改密码
     *
     * @param newPassword 新密码(加密后)
     * @param newSalt     新盐值
     */
    public void changePassword(String newPassword, String newSalt) {
        this.password = newPassword;
        this.salt = newSalt;
        this.passwordInited = true;
        this.lastPasswordChangeTime = LocalDateTime.now();
    }

    /**
     * 重置密码
     *
     * @param newPassword 新密码(加密后)
     * @param newSalt     新盐值
     */
    public void resetPassword(String newPassword, String newSalt) {
        this.password = newPassword;
        this.salt = newSalt;
        this.passwordInited = false;
        this.lastPasswordChangeTime = LocalDateTime.now();
    }

    /**
     * 锁定账号
     *
     * @param expireTime 锁定过期时间，null表示永久锁定
     */
    public void lock(LocalDateTime expireTime) {
        this.locked = true;
        this.lockExpireTime = expireTime;
    }

    /**
     * 解锁账号
     */
    public void unlock() {
        this.locked = false;
        this.lockExpireTime = null;
    }

    /**
     * 判断账号是否被锁定
     *
     * @return true-已锁定，false-未锁定
     */
    public boolean isLocked() {
        if (!this.locked) {
            return false;
        }
        // 检查是否超过锁定过期时间
        if (this.lockExpireTime != null && LocalDateTime.now().isAfter(this.lockExpireTime)) {
            this.locked = false;
            this.lockExpireTime = null;
            return false;
        }
        return true;
    }

    /**
     * 判断账号是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        return this.status && !isLocked() &&
               (this.expireTime == null || LocalDateTime.now().isBefore(this.expireTime));
    }

    /**
     * 判断账号是否过期
     *
     * @return true-已过期，false-未过期
     */
    public boolean isExpired() {
        return this.expireTime != null && LocalDateTime.now().isAfter(this.expireTime);
    }

    /**
     * 禁用账号
     */
    public void disable() {
        this.status = false;
    }

    /**
     * 启用账号
     */
    public void enable() {
        this.status = true;
    }

    /**
     * 记录登录
     *
     * @param loginType 登录类型
     * @param loginIp   登录IP
     * @param deviceInfo 设备信息
     * @param success   是否成功
     * @param errorMsg  错误信息
     */
    public void recordLogin(String loginType, String loginIp, String deviceInfo,
                           boolean success, String errorMsg) {
        if (this.loginLogs == null) {
            this.loginLogs = new ArrayList<>();
        }
        IamLoginLog log = IamLoginLog.create(this.id, this.accountCode, loginType,
                loginIp, deviceInfo, success, errorMsg);
        this.loginLogs.add(log);

        if (success) {
            this.lastLoginIp = loginIp;
            this.lastLoginTime = LocalDateTime.now();
        }
    }

}
