package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM部门用户数据对象
 * 对应数据库表：tb_iam_deptuser
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_deptuser")
public class IamDepartmentUserDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamDeptuserId;

    /**
     * 部门ID
     */
    @Column(value = "deptuser_dept_id")
    private String deptuserDeptId;

    /**
     * 用户ID
     */
    @Column(value = "deptuser_user_id")
    private String deptuserUserId;

    /**
     * 是否主部门_code
     */
    @Column(value = "deptuser_is_primary_code")
    private String deptuserIsPrimaryCode;

    /**
     * 是否主部门_name
     */
    @Column(value = "deptuser_is_primary_name")
    private String deptuserIsPrimaryName;

    /**
     * 职位
     */
    @Column(value = "deptuser_position")
    private String deptuserPosition;

    /**
     * 是否负责人_code
     */
    @Column(value = "deptuser_is_leader_code")
    private String deptuserIsLeaderCode;

    /**
     * 是否负责人_name
     */
    @Column(value = "deptuser_is_leader_name")
    private String deptuserIsLeaderName;

    /**
     * 排序号
     */
    @Column(value = "deptuser_sort_no")
    private Integer deptuserSortNo;

    /**
     * 生效日期
     */
    @Column(value = "deptuser_start_date")
    private String deptuserStartDate;

    /**
     * 失效日期
     */
    @Column(value = "deptuser_end_date")
    private String deptuserEndDate;

    /**
     * 备注
     */
    @Column(value = "deptuser_remark")
    private String deptuserRemark;

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
