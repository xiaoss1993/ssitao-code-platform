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
 * 角色管理 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_role")
public class TbIamRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamRoleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 基础角色_id
     */
    private String roleBaseId;

    /**
     * 基础角色_name
     */
    private String roleBaseName;

    /**
     * 备注信息
     */
    private String roleRemark;

    /**
     * 角色类型_code
     */
    private String roleTypeCode;

    /**
     * 角色类型_name
     */
    private String roleTypeName;

    /**
     * 节点图标
     */
    private String roleIconcls;

    /**
     * 开发
     */
    private String roleDevelop;

    /**
     * 所属权限组_id
     */
    private String rolePermgroupId;

    /**
     * 所属权限组_name
     */
    private String rolePermgroupName;

    /**
     * saas产品
     */
    private String roleSaasPid;

    /**
     * 父节点id
     */
    private String syParent;

    /**
     * 节点类型
     */
    private String syNodetype;

    /**
     * 父节点路径
     */
    private String syParentpath;

    /**
     * 层次
     */
    private Integer syLayer;

    /**
     * 树形路径
     */
    private String syPath;

    /**
     * 是否启用
     */
    private String syDisabled;

    /**
     * 树形排序字段
     */
    private String syTreeorderindex;

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
     * 登记人编码
     */
    private String syCreateuser;

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
     * 所属产品_id
     */
    private String syProductId;

    /**
     * 所属产品_name
     */
    private String syProductName;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户_name
     */
    private String syTenantName;

    /**
     * 主键
     */
    private String id;

}
