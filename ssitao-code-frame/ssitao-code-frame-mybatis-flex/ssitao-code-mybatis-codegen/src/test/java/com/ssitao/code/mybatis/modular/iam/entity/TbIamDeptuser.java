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
 * 部门人员关联 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_deptuser")
public class TbIamDeptuser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门主键
     */
    private String tbIamDepartmentId;

    /**
     * 用户主键
     */
    private String tbIamUserId;

    /**
     * 主键id
     */
    @Id
    private String tbIamDeptuserId;

    /**
     * 是否主部门_code
     */
    private String deptuserMainCode;

    /**
     * 是否主部门_name
     */
    private String deptuserMainName;

    /**
     * 直接领导_id
     */
    private String deptuserDirectleaderId;

    /**
     * 直接领导_name
     */
    private String deptuserDirectleaderName;

    /**
     * 主管领导_id
     */
    private String deptuserMajorId;

    /**
     * 主管领导_name
     */
    private String deptuserMajorName;

    /**
     * 是否主管_code
     */
    private String deptuserSfzgCode;

    /**
     * 是否主管_name
     */
    private String deptuserSfzgName;

    /**
     * 是否开通账号_code
     */
    private String deptuserOpenaccountCode;

    /**
     * 是否开通账号_name
     */
    private String deptuserOpenaccountName;

    /**
     * 所属公司_code
     */
    private String syCompanyCode;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
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

    /**
     * 所属公司_id
     */
    private String syCompanyId;

    /**
     * 所属公司_name
     */
    private String syCompanyName;

    /**
     * 所属集团公司_id
     */
    private String syGroupCompanyId;

    /**
     * 所属集团公司_name
     */
    private String syGroupCompanyName;

}
