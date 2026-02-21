package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM账号数据对象
 * 对应数据库表：tb_iam_account
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_account")
public class IamAccountDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamAccountId;

    /**
     * 名称
     */
    @Column(value = "account_name")
    private String accountName;

    /**
     * 编码
     */
    @Column(value = "account_code")
    private String accountCode;

    /**
     * 开放id
     */
    @Column(value = "account_openid")
    private String accountOpenid;

    /**
     * 密码
     */
    @Column(value = "account_password")
    private String accountPassword;

    /**
     * 电话
     */
    @Column(value = "account_phone")
    private String accountPhone;

    /**
     * 邮件
     */
    @Column(value = "account_mail")
    private String accountMail;

    /**
     * 头像
     */
    @Column(value = "account_avatar")
    private String accountAvatar;

    /**
     * 用户关联_id
     */
    @Column(value = "user_association_id")
    private String userAssociationId;

    /**
     * 过期时间
     */
    @Column(value = "account_expire_time")
    private String accountExpireTime;

    /**
     * 是否锁定_code
     */
    @Column(value = "account_locked_code")
    private String accountLockedCode;

    /**
     * 是否锁定_name
     */
    @Column(value = "account_locked_name")
    private String accountLockedName;

    /**
     * 卡号
     */
    @Column(value = "account_cardnum")
    private String accountCardnum;

    /**
     * 备注信息
     */
    @Column(value = "account_remark")
    private String accountRemark;

    /**
     * 盐值
     */
    @Column(value = "account_salt")
    private String accountSalt;

    /**
     * 锁定超时时间
     */
    @Column(value = "account_lock_expire")
    private String accountLockExpire;

    /**
     * 是否初始化
     */
    @Column(value = "account_inited")
    private String accountInited;

    /**
     * 是否永久使用_code
     */
    @Column(value = "account_permanent_code")
    private String accountPermanentCode;

    /**
     * 是否永久使用_name
     */
    @Column(value = "account_permanent_name")
    private String accountPermanentName;

    /**
     * 是否锁定
     */
    @Column(value = "account_locked_status")
    private String accountLockedStatus;

    /**
     * 用户性别
     */
    @Column(value = "account_sex")
    private String accountSex;

    /**
     * 用户状态
     */
    @Column(value = "user_status")
    private String userStatus;

    /**
     * 租户_id
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

    /**
     * 租户名称
     */
    @Column(value = "sy_tenant_name")
    private String syTenantName;

    /**
     * 登记部门主键
     */
    @Column(value = "sy_createorgid")
    private String syCreateorgid;

    /**
     * 登记部门
     */
    @Column(value = "sy_createorgname")
    private String syCreateorgname;

    /**
     * 登记时间
     */
    @Column(value = "sy_createtime")
    private String syCreatetime;

    /**
     * 登记人主键
     */
    @Column(value = "sy_createuserid")
    private String syCreateuserid;

    /**
     * 登记人
     */
    @Column(value = "sy_createusername")
    private String syCreateusername;

    /**
     * 数据状态
     */
    @Column(value = "sy_status")
    private String syStatus;

    /**
     * 排序字段
     */
    @Column(value = "sy_orderindex")
    private Integer syOrderindex;

    /**
     * 所属机构_id
     */
    @Column(value = "sy_org_id")
    private String syOrgId;

    /**
     * 所属机构_name
     */
    @Column(value = "sy_org_name")
    private String syOrgName;

    /**
     * 密级_code
     */
    @Column(value = "sy_secret_code")
    private String sySecretCode;

    /**
     * 密级_name
     */
    @Column(value = "sy_secret_name")
    private String sySecretName;

}
