package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM角色权限关联数据对象
 * 对应数据库表：tb_iam_roleperm
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_roleperm")
public class IamRolePermissionDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamRolepermId;

    /**
     * 角色主键
     */
    @Column(value = "tb_iam_role_id")
    private String tbIamRoleId;

    /**
     * 权限主键
     */
    @Column(value = "tb_iam_perm_id")
    private String tbIamPermId;

    /**
     * 是否排他_code
     */
    @Column(value = "roleperm_exclude_code")
    private String rolepermExcludeCode;

    /**
     * 是否排他_name
     */
    @Column(value = "roleperm_exclude_name")
    private String rolepermExcludeName;

    /**
     * 授权方式_code
     */
    @Column(value = "roleperm_type_code")
    private String rolepermTypeCode;

    /**
     * 授权方式_name
     */
    @Column(value = "roleperm_type_name")
    private String rolepermTypeName;

    /**
     * 授权类型_code
     */
    @Column(value = "roleperm_granttype_code")
    private String rolepermGranttypeCode;

    /**
     * 授权类型_name
     */
    @Column(value = "roleperm_granttype_name")
    private String rolepermGranttypeName;

    /**
     * 是否不选中
     */
    @Column(value = "roleperm_not_checked")
    private String rolepermNotChecked;

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
