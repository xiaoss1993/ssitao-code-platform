package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM部门数据对象
 * 对应数据库表：tb_iam_department
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_department")
public class IamDepartmentDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamDepartmentId;

    /**
     * 部门简称
     */
    @Column(value = "department_simple_name")
    private String departmentSimpleName;

    /**
     * 主管_id
     */
    @Column(value = "department_major_id")
    private String departmentMajorId;

    /**
     * 主管_name
     */
    @Column(value = "department_major_name")
    private String departmentMajorName;

    /**
     * 职能描述
     */
    @Column(value = "department_func_desc")
    private String departmentFuncDesc;

    /**
     * 备注信息
     */
    @Column(value = "department_remark")
    private String departmentRemark;

    /**
     * 监管部门_id
     */
    @Column(value = "department_monitordept_id")
    private String departmentMonitordeptId;

    /**
     * 监管部门_name
     */
    @Column(value = "department_monitordept_name")
    private String departmentMonitordeptName;

    /**
     * 图标
     */
    @Column(value = "department_icon")
    private String departmentIcon;

    /**
     * 部门级别_code
     */
    @Column(value = "department_level_code")
    private String departmentLevelCode;

    /**
     * 部门级别_name
     */
    @Column(value = "department_level_name")
    private String departmentLevelName;

    /**
     * 部门电话
     */
    @Column(value = "department_telephone")
    private String departmentTelephone;

    /**
     * 办公地点
     */
    @Column(value = "department_address")
    private String departmentAddress;

    /**
     * 部门名称
     */
    @Column(value = "department_name")
    private String departmentName;

    /**
     * 部门编码
     */
    @Column(value = "department_code")
    private String departmentCode;

    /**
     * 上级部门
     */
    @Column(value = "superior_department")
    private String superiorDepartment;

    /**
     * 部门人员排序
     */
    @Column(value = "department_user_orderindex")
    private String departmentUserOrderindex;

    /**
     * 上级
     */
    @Column(value = "department_parent")
    private String departmentParent;

    /**
     * 父级节点类型
     */
    @Column(value = "parent_nodetype")
    private String parentNodetype;

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

}
