package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM角色数据对象
 * 对应数据库表：tb_iam_role
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_role")
public class IamRoleDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamRoleId;

    /**
     * 角色名称
     */
    @Column(value = "role_name")
    private String roleName;

    /**
     * 角色编码
     */
    @Column(value = "role_code")
    private String roleCode;

    /**
     * 基础角色_id
     */
    @Column(value = "role_base_id")
    private String roleBaseId;

    /**
     * 基础角色_name
     */
    @Column(value = "role_base_name")
    private String roleBaseName;

    /**
     * 备注信息
     */
    @Column(value = "role_remark")
    private String roleRemark;

    /**
     * 角色类型_code
     */
    @Column(value = "role_type_code")
    private String roleTypeCode;

    /**
     * 角色类型_name
     */
    @Column(value = "role_type_name")
    private String roleTypeName;

    /**
     * 节点图标
     */
    @Column(value = "role_iconcls")
    private String roleIconcls;

    /**
     * 开发
     */
    @Column(value = "role_develop")
    private String roleDevelop;

    /**
     * 所属权限组_id
     */
    @Column(value = "role_permgroup_id")
    private String rolePermgroupId;

    /**
     * 所属权限组_name
     */
    @Column(value = "role_permgroup_name")
    private String rolePermgroupName;

    /**
     * saas产品
     */
    @Column(value = "role_saas_pid")
    private String roleSaasPid;

    /**
     * 父节点id
     */
    @Column(value = "sy_parent")
    private String syParent;

    /**
     * 节点类型
     */
    @Column(value = "sy_nodetype")
    private String syNodetype;

    /**
     * 父节点路径
     */
    @Column(value = "sy_parentpath")
    private String syParentpath;

    /**
     * 层次
     */
    @Column(value = "sy_layer")
    private Integer syLayer;

    /**
     * 树形路径
     */
    @Column(value = "sy_path")
    private String syPath;

    /**
     * 是否启用
     */
    @Column(value = "sy_disabled")
    private String syDisabled;

    /**
     * 树形排序字段
     */
    @Column(value = "sy_treeorderindex")
    private String syTreeorderindex;

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
     * 登记人编码
     */
    @Column(value = "sy_createuser")
    private String syCreateuser;

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
     * 所属产品_id
     */
    @Column(value = "sy_product_id")
    private String syProductId;

    /**
     * 所属产品_name
     */
    @Column(value = "sy_product_name")
    private String syProductName;

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
     * 主键
     */
    @Column(value = "id")
    private String id;

}
