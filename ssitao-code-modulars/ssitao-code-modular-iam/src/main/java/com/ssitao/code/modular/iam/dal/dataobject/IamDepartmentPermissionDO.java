package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM部门权限数据对象
 * 对应数据库表：tb_iam_deptperm
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_deptperm")
public class IamDepartmentPermissionDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamDeptpermId;

    /**
     * 部门ID
     */
    @Column(value = "deptperm_dept_id")
    private String deptpermDeptId;

    /**
     * 权限ID
     */
    @Column(value = "deptperm_perm_id")
    private String deptpermPermId;

    /**
     * 权限类型_code
     */
    @Column(value = "deptperm_type_code")
    private String deptpermTypeCode;

    /**
     * 权限类型_name
     */
    @Column(value = "deptperm_type_name")
    private String deptpermTypeName;

    /**
     * 授权范围_code（数据权限范围）
     */
    @Column(value = "deptperm_scope_code")
    private String deptpermScopeCode;

    /**
     * 授权范围_name
     */
    @Column(value = "deptperm_scope_name")
    private String deptpermScopeName;

    /**
     * 生效时间
     */
    @Column(value = "deptperm_start_time")
    private String deptpermStartTime;

    /**
     * 失效时间
     */
    @Column(value = "deptperm_end_time")
    private String deptpermEndTime;

    /**
     * 授权人ID
     */
    @Column(value = "deptperm_granter_id")
    private String deptpermGranterId;

    /**
     * 授权人姓名
     */
    @Column(value = "deptperm_granter_name")
    private String deptpermGranterName;

    /**
     * 备注
     */
    @Column(value = "deptperm_remark")
    private String deptpermRemark;

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
