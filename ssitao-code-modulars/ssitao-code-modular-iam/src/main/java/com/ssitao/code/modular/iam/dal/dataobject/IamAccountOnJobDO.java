package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM账号在岗数据对象
 * 对应数据库表：tb_iam_accountonjob
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_accountonjob")
public class IamAccountOnJobDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamAccountonjobId;

    /**
     * 账号ID
     */
    @Column(value = "accountonjob_account_id")
    private String accountonjobAccountId;

    /**
     * 员工档案ID
     */
    @Column(value = "accountonjob_staff_id")
    private String accountonjobStaffId;

    /**
     * 在岗状态_code
     */
    @Column(value = "accountonjob_status_code")
    private String accountonjobStatusCode;

    /**
     * 在岗状态_name
     */
    @Column(value = "accountonjob_status_name")
    private String accountonjobStatusName;

    /**
     * 在岗开始时间
     */
    @Column(value = "accountonjob_start_time")
    private String accountonjobStartTime;

    /**
     * 在岗结束时间
     */
    @Column(value = "accountonjob_end_time")
    private String accountonjobEndTime;

    /**
     * 工作岗位
     */
    @Column(value = "accountonjob_position")
    private String accountonjobPosition;

    /**
     * 工作地点
     */
    @Column(value = "accountonjob_location")
    private String accountonjobLocation;

    /**
     * 备注
     */
    @Column(value = "accountonjob_remark")
    private String accountonjobRemark;

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
