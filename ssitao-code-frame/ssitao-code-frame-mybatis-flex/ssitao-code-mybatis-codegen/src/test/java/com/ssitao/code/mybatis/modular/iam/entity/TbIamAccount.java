package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 账号管理 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_account")
public class TbIamAccount extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamAccountId;

    /**
     * 名称
     */
    private String accountName;

    /**
     * 编码
     */
    private String accountCode;

    /**
     * 开放id
     */
    private String accountOpenid;

    /**
     * 密码
     */
    private String accountPassword;

    /**
     * 电话
     */
    private String accountPhone;

    /**
     * 邮件
     */
    private String accountMail;

    /**
     * 头像
     */
    private String accountAvatar;

    /**
     * 用户关联_id
     */
    private String userAssociationId;

    /**
     * 过期时间
     */
    private String accountExpireTime;

    /**
     * 是否锁定_code
     */
    private String accountLockedCode;

    /**
     * 是否锁定_name
     */
    private String accountLockedName;

    /**
     * 卡号
     */
    private String accountCardnum;

    /**
     * 备注信息
     */
    private String accountRemark;

    /**
     * 盐值
     */
    private String accountSalt;

    /**
     * 锁定超时时间
     */
    private String accountLockExpire;

    /**
     * 是否初始化
     */
    private String accountInited;

    /**
     * 是否永久使用_code
     */
    private String accountPermanentCode;

    /**
     * 是否永久使用_name
     */
    private String accountPermanentName;

    /**
     * 是否锁定
     */
    private String accountLockedStatus;

    /**
     * 用户性别
     */
    private String accountSex;

    /**
     * 用户状态
     */
    private String userStatus;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 登记部门主键
     */
    private String syCreateorgid;

    /**
     * 登记部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人主键
     */
    private String syCreateuserid;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 所属机构_id
     */
    private String syOrgId;

    /**
     * 所属机构_name
     */
    private String syOrgName;

    /**
     * 密级_code
     */
    private String sySecretCode;

    /**
     * 密级_name
     */
    private String sySecretName;

}
