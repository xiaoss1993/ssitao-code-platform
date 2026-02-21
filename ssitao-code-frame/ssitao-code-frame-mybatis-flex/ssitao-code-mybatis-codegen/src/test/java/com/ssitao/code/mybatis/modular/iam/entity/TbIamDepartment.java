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
 * 部门管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_department")
public class TbIamDepartment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamDepartmentId;

    /**
     * 部门简称
     */
    private String departmentSimpleName;

    /**
     * 主管_id
     */
    private String departmentMajorId;

    /**
     * 主管_name
     */
    private String departmentMajorName;

    /**
     * 职能描述
     */
    private String departmentFuncDesc;

    /**
     * 备注信息
     */
    private String departmentRemark;

    /**
     * 监管部门_id
     */
    private String departmentMonitordeptId;

    /**
     * 监管部门_name
     */
    private String departmentMonitordeptName;

    /**
     * 图标
     */
    private String departmentIcon;

    /**
     * 部门级别_code
     */
    private String departmentLevelCode;

    /**
     * 部门级别_name
     */
    private String departmentLevelName;

    /**
     * 部门电话
     */
    private String departmentTelephone;

    /**
     * 办公地点
     */
    private String departmentAddress;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门编码
     */
    private String departmentCode;

    /**
     * 上级部门
     */
    private String superiorDepartment;

    /**
     * 部门人员排序
     */
    private String departmentUserOrderindex;

    /**
     * 上级
     */
    private String departmentParent;

    /**
     * 父级节点类型
     */
    private String parentNodetype;

    /**
     * 所属公司_id
     */
    private String syCompanyId;

    /**
     * 所属公司_name
     */
    private String syCompanyName;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户_name
     */
    private String syTenantName;

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
     * 所属集团公司_id
     */
    private String syGroupCompanyId;

    /**
     * 所属集团公司_name
     */
    private String syGroupCompanyName;

    /**
     * 所属机构_id
     */
    private String syOrgId;

    /**
     * 修改人部门主键
     */
    private String syModifyorgid;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改人主键
     */
    private String syModifyuserid;

    /**
     * 修改人
     */
    private String syModifyusername;

    /**
     * 修改时间
     */
    private String syModifytime;

}
