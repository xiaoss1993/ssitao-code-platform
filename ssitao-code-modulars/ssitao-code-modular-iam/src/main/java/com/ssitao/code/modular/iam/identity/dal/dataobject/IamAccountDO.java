package com.ssitao.code.modular.iam.identity.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户账户数据对象
 * 对应表：iam_account
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_account")
public class IamAccountDO {

    /**
     * 账户ID
     */
    @Id(keyType = KeyType.None)
    private String accountId;

    /**
     * 账户编码
     */
    private String accountCode;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 账户密码(加密)
     */
    private String accountPassword;

    /**
     * 密码盐值
     */
    private String accountSalt;

    /**
     * 手机号码
     */
    private String accountPhone;

    /**
     * 邮箱地址
     */
    private String accountMail;

    /**
     * 头像URL
     */
    private String accountAvatar;

    /**
     * 账户类型: SYSTEM-系统, THIRD-第三方
     */
    private String accountType;

    /**
     * 账户来源: LOCAL-本地, DINGTALK-钉钉, WECHAT-微信
     */
    private String accountSource;

    /**
     * 账户状态: 0-禁用, 1-启用
     */
    private Integer accountStatus;

    /**
     * 是否管理员: 0-否, 1-是
     */
    private Integer accountIsAdmin;

    /**
     * 最后登录时间
     */
    private LocalDateTime accountLastLoginTime;

    /**
     * 最后登录IP
     */
    private String accountLastLoginIp;

    /**
     * 是否初始密码: 0-否, 1-是
     */
    private Integer accountInitPassword;

    /**
     * 初始密码重置时间
     */
    private LocalDateTime accountInitPasswordResetTime;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建组织ID
     */
    private String createOrgId;

    /**
     * 创建组织名称
     */
    private String createOrgName;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改组织ID
     */
    private String modifyOrgId;

    /**
     * 修改组织名称
     */
    private String modifyOrgName;

    /**
     * 修改人ID
     */
    private String modifyUserId;

    /**
     * 修改人姓名
     */
    private String modifyUserName;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer isDeleted;

    /**
     * 版本号(乐观锁)
     */
    private Integer version;

}
