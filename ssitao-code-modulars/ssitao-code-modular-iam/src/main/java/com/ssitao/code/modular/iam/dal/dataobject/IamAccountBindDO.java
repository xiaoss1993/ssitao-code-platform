package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM账号绑定数据对象
 * 对应数据库表：tb_iam_accountbind
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_accountbind")
public class IamAccountBindDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamAccountbindId;

    /**
     * 账号ID
     */
    @Column(value = "accountbind_account_id")
    private String accountbindAccountId;

    /**
     * 绑定类型_code（openid/phone/email等）
     */
    @Column(value = "accountbind_bind_type_code")
    private String accountbindBindTypeCode;

    /**
     * 绑定类型_name
     */
    @Column(value = "accountbind_bind_type_name")
    private String accountbindBindTypeName;

    /**
     * 绑定标识（如openid、手机号、邮箱等）
     */
    @Column(value = "accountbind_bind_id")
    private String accountbindBindId;

    /**
     * 绑定平台（微信、支付宝等）
     */
    @Column(value = "accountbind_bind_platform")
    private String accountbindBindPlatform;

    /**
     * 绑定时间
     */
    @Column(value = "accountbind_bind_time")
    private String accountbindBindTime;

    /**
     * 绑定状态_code
     */
    @Column(value = "accountbind_status_code")
    private String accountbindStatusCode;

    /**
     * 绑定状态_name
     */
    @Column(value = "accountbind_status_name")
    private String accountbindStatusName;

    /**
     * 解绑时间
     */
    @Column(value = "accountbind_unbind_time")
    private String accountbindUnbindTime;

    /**
     * 备注
     */
    @Column(value = "accountbind_remark")
    private String accountbindRemark;

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
     * 修改人部门主键
     */
    @Column(value = "sy_modifyorgid")
    private String syModifyorgid;

    /**
     * 修改人部门
     */
    @Column(value = "sy_modifyorgname")
    private String syModifyorgname;

    /**
     * 修改人主键
     */
    @Column(value = "sy_modifyuserid")
    private String syModifyuserid;

    /**
     * 修改人
     */
    @Column(value = "sy_modifyusername")
    private String syModifyusername;

    /**
     * 修改时间
     */
    @Column(value = "sy_modifytime")
    private String syModifytime;

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
}
