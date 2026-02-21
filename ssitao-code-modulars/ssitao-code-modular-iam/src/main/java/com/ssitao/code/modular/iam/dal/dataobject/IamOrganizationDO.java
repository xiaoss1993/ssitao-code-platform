package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM组织数据对象
 * 对应数据库表：tb_iam_org
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_org")
public class IamOrganizationDO {

    /**
     * 资源表_id
     */
    @Column(value = "tb_core_resourcetable_id")
    private String tbCoreResourcetableId;

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamOrgId;

    /**
     * 名称
     */
    @Column(value = "org_name")
    private String orgName;

    /**
     * 编码
     */
    @Column(value = "org_code")
    private String orgCode;

    /**
     * 资源表_code
     */
    @Column(value = "org_resourcetable_code")
    private String orgResourcetableCode;

    /**
     * 资源表_name
     */
    @Column(value = "org_resourcetable_name")
    private String orgResourcetableName;

    /**
     * 账号手机字段
     */
    @Column(value = "org_account_phone")
    private String orgAccountPhone;

    /**
     * 账号邮箱字段
     */
    @Column(value = "org_account_mail")
    private String orgAccountMail;

    /**
     * 账号头像字段
     */
    @Column(value = "org_account_avatar")
    private String orgAccountAvatar;

    /**
     * 备注信息
     */
    @Column(value = "org_remark")
    private String orgRemark;

    /**
     * 角色字段_id
     */
    @Column(value = "org_rolefield_id")
    private String orgRolefieldId;

    /**
     * 角色字段_name
     */
    @Column(value = "org_rolefield_name")
    private String orgRolefieldName;

    /**
     * 账户主键字段
     */
    @Column(value = "org_field_pk")
    private String orgFieldPk;

    /**
     * 账户名称字段
     */
    @Column(value = "org_account_name")
    private String orgAccountName;

    /**
     * 账户编码字段
     */
    @Column(value = "org_account_code")
    private String orgAccountCode;

    /**
     * 账号性别字段
     */
    @Column(value = "org_account_sex")
    private String orgAccountSex;

    /**
     * 账号真实用户状态
     */
    @Column(value = "org_account_status")
    private String orgAccountStatus;

    /**
     * 账号访问状态
     */
    @Column(value = "org_accountaccess_status")
    private String orgAccountaccessStatus;

    /**
     * 账号密级编码字段
     */
    @Column(value = "org_secret_code")
    private String orgSecretCode;

    /**
     * 账号密级名称字段
     */
    @Column(value = "org_secret_name")
    private String orgSecretName;

    /**
     * 租户_id
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

    /**
     * 租户_name
     */
    @Column(value = "sy_tenant_name")
    private String syTenantName;

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

}
