package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM账号部门关联数据对象
 * 对应数据库表：tb_iam_accountdept
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_accountdept")
public class IamAccountDepartmentDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamAccountdeptId;

    /**
     * 账号ID
     */
    @Column(value = "accountdept_account_id")
    private String accountdeptAccountId;

    /**
     * 部门ID
     */
    @Column(value = "accountdept_dept_id")
    private String accountdeptDeptId;

    /**
     * 是否主部门_code
     */
    @Column(value = "accountdept_is_primary_code")
    private String accountdeptIsPrimaryCode;

    /**
     * 是否主部门_name
     */
    @Column(value = "accountdept_is_primary_name")
    private String accountdeptIsPrimaryName;

    /**
     * 职位
     */
    @Column(value = "accountdept_position")
    private String accountdeptPosition;

    /**
     * 是否负责人_code
     */
    @Column(value = "accountdept_is_leader_code")
    private String accountdeptIsLeaderCode;

    /**
     * 是否负责人_name
     */
    @Column(value = "accountdept_is_leader_name")
    private String accountdeptIsLeaderName;

    /**
     * 生效日期
     */
    @Column(value = "accountdept_start_date")
    private String accountdeptStartDate;

    /**
     * 失效日期
     */
    @Column(value = "accountdept_end_date")
    private String accountdeptEndDate;

    /**
     * 备注
     */
    @Column(value = "accountdept_remark")
    private String accountdeptRemark;

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
