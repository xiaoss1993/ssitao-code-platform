package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM登录日志数据对象
 * 对应数据库表：tb_iam_loginlog
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_loginlog")
public class IamLoginLogDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.Generator, value = "uuid")
    private String tbIamLoginlogId;

    /**
     * 账号_name
     */
    @Column(value = "loginlog_account_name")
    private String loginlogAccountName;

    /**
     * 设备标识
     */
    @Column(value = "loginlog_device")
    private String loginlogDevice;

    /**
     * 账号_code
     */
    @Column(value = "loginlog_account_code")
    private String loginlogAccountCode;

    /**
     * 登录方式_code
     */
    @Column(value = "loginlog_type_code")
    private String loginlogTypeCode;

    /**
     * 登录方式_name
     */
    @Column(value = "loginlog_type_name")
    private String loginlogTypeName;

    /**
     * 操作类型_code
     */
    @Column(value = "loginlog_optype_code")
    private String loginlogOptypeCode;

    /**
     * 操作类型_name
     */
    @Column(value = "loginlog_optype_name")
    private String loginlogOptypeName;

    /**
     * 备注信息
     */
    @Column(value = "loginlog_bzxx")
    private String loginlogBzxx;

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
     * 账号_id
     */
    @Column(value = "sy_account_id")
    private String syAccountId;

    /**
     * 所属公司_id
     */
    @Column(value = "sy_company_id")
    private String syCompanyId;

    /**
     * 所属公司_name
     */
    @Column(value = "sy_company_name")
    private String syCompanyName;

    /**
     * 所属集团公司_id
     */
    @Column(value = "sy_group_company_id")
    private String syGroupCompanyId;

    /**
     * 所属集团公司_name
     */
    @Column(value = "sy_group_company_name")
    private String syGroupCompanyName;

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
     * 租户_id
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

    /**
     * 租户名称
     */
    @Column(value = "sy_tenant_name")
    private String syTenantName;

}
