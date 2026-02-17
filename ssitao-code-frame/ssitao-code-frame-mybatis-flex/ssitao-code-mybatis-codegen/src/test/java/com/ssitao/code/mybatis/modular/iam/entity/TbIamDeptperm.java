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
 * 部门权限关联 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_deptperm")
public class TbIamDeptperm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门主键_id
     */
    private String tbIamDepartmentId;

    /**
     * 权限主键_id
     */
    private String tbIamPermId;

    /**
     * 主键id
     */
    @Id
    private String tbIamDeptpermId;

    /**
     * 是否排他_code
     */
    private String deptpermExcludeCode;

    /**
     * 是否排他_name
     */
    private String deptpermExcludeName;

    /**
     * 授权方式_code
     */
    private String deptpermTypeCode;

    /**
     * 授权方式_name
     */
    private String deptpermTypeName;

    /**
     * 授权类型_code
     */
    private String deptpermGranttypeCode;

    /**
     * 授权类型_name
     */
    private String deptpermGranttypeName;

    /**
     * 是否不选中
     */
    private String deptpermNotChecked;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户_name
     */
    private String syTenantName;

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

}
