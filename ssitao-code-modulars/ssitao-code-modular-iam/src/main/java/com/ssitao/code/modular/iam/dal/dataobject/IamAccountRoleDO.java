package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM账号角色关联数据对象
 * 对应数据库表：tb_iam_accountrole
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_accountrole")
public class IamAccountRoleDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamAccountroleId;

    /**
     * 角色_id
     */
    @Column(value = "accountrole_role_id")
    private String accountroleRoleId;

    /**
     * 账号_id
     */
    @Column(value = "accountrole_account_id")
    private String accountroleAccountId;

    /**
     * 角色_name
     */
    @Column(value = "accountrole_role_name")
    private String accountroleRoleName;

    /**
     * 所属部门_id
     */
    @Column(value = "accountrole_dept_id")
    private String accountroleDeptId;

    /**
     * 所属部门_name
     */
    @Column(value = "accountrole_dept_name")
    private String accountroleDeptName;

    /**
     * 是否主部门_code
     */
    @Column(value = "accountrole_main_code")
    private String accountroleMainCode;

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
    private String syOrderindex;

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
     * 登记人
     */
    @Column(value = "sy_createusername")
    private String syCreateusername;

}
