package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM用户数据对象
 * 对应数据库表：tb_iam_user
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_user")
public class IamUserDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamUserId;

    /**
     * 名称
     */
    @Column(value = "user_name")
    private String userName;

    /**
     * 编码
     */
    @Column(value = "user_code")
    private String userCode;

    /**
     * 性别_code
     */
    @Column(value = "user_sex_code")
    private String userSexCode;

    /**
     * 性别_name
     */
    @Column(value = "user_sex_name")
    private String userSexName;

    /**
     * 手机号
     */
    @Column(value = "user_phone")
    private String userPhone;

    /**
     * 邮箱
     */
    @Column(value = "user_mail")
    private String userMail;

    /**
     * 已婚_code
     */
    @Column(value = "user_married_code")
    private String userMarriedCode;

    /**
     * 已婚_name
     */
    @Column(value = "user_married_name")
    private String userMarriedName;

    /**
     * 年龄
     */
    @Column(value = "user_age")
    private Integer userAge;

    /**
     * 生日
     */
    @Column(value = "user_birth")
    private String userBirth;

    /**
     * 入职日期
     */
    @Column(value = "user_entry_date")
    private String userEntryDate;

    /**
     * 离职日期
     */
    @Column(value = "user_leave_date")
    private String userLeaveDate;

    /**
     * 已初始化_code
     */
    @Column(value = "user_inited_code")
    private String userInitedCode;

    /**
     * 已初始化_name
     */
    @Column(value = "user_inited_name")
    private String userInitedName;

    /**
     * 照片
     */
    @Column(value = "user_photo")
    private String userPhoto;

    /**
     * 头像
     */
    @Column(value = "user_avatar")
    private String userAvatar;

    /**
     * 身份卡号
     */
    @Column(value = "user_cardnum")
    private String userCardnum;

    /**
     * 证件类型_code
     */
    @Column(value = "user_idtype_code")
    private String userIdtypeCode;

    /**
     * 证件类型_name
     */
    @Column(value = "user_idtype_name")
    private String userIdtypeName;

    /**
     * 籍贯
     */
    @Column(value = "user_native_place")
    private String userNativePlace;

    /**
     * 文化程度_code
     */
    @Column(value = "user_education_code")
    private String userEducationCode;

    /**
     * 文化程度_name
     */
    @Column(value = "user_education_name")
    private String userEducationName;

    /**
     * 行政职务_code
     */
    @Column(value = "user_post_code")
    private String userPostCode;

    /**
     * 行政职务_name
     */
    @Column(value = "user_post_name")
    private String userPostName;

    /**
     * 监管部门_id
     */
    @Column(value = "user_monitordept_id")
    private String userMonitordeptId;

    /**
     * 监管部门_name
     */
    @Column(value = "user_monitordept_name")
    private String userMonitordeptName;

    /**
     * 角色_id
     */
    @Column(value = "user_role_id")
    private String userRoleId;

    /**
     * 角色_name
     */
    @Column(value = "user_role_name")
    private String userRoleName;

    /**
     * 备注信息
     */
    @Column(value = "user_remark")
    private String userRemark;

    /**
     * 通讯地址
     */
    @Column(value = "user_address")
    private String userAddress;

    /**
     * 工位
     */
    @Column(value = "user_station")
    private String userStation;

    /**
     * 工号
     */
    @Column(value = "user_jobnum")
    private String userJobnum;

    /**
     * 现居地址
     */
    @Column(value = "user_nowaddress")
    private String userNowaddress;

    /**
     * 政治面貌_code
     */
    @Column(value = "user_political_code")
    private String userPoliticalCode;

    /**
     * 政治面貌_name
     */
    @Column(value = "user_political_name")
    private String userPoliticalName;

    /**
     * 紧急联系人名称
     */
    @Column(value = "user_contactor_name")
    private String userContactorName;

    /**
     * 紧急联系人电话
     */
    @Column(value = "user_contactor_phone")
    private String userContactorPhone;

    /**
     * 座机
     */
    @Column(value = "user_telephone")
    private String userTelephone;

    /**
     * 户籍性质_code
     */
    @Column(value = "user_household_code")
    private String userHouseholdCode;

    /**
     * 户籍性质_name
     */
    @Column(value = "user_household_name")
    private String userHouseholdName;

    /**
     * 民族_name
     */
    @Column(value = "user_nation__name")
    private String userNationName;

    /**
     * 民族_code
     */
    @Column(value = "user_nation_code")
    private String userNationCode;

    /**
     * 国籍_name
     */
    @Column(value = "user_nationality_name")
    private String userNationalityName;

    /**
     * 国籍_code
     */
    @Column(value = "user_nationality_code")
    private String userNationalityCode;

    /**
     * 人员状态
     */
    @Column(value = "user_employee_status")
    private String userEmployeeStatus;

    /**
     * 密级_code
     */
    @Column(value = "sy_secret_code")
    private String sySecretCode;

    /**
     * 密级_name
     */
    @Column(value = "sy_secret_name")
    private String sySecretName;

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
     * 是否允许
     */
    @Column(value = "sy_status")
    private String syStatus;

    /**
     * 排序字段
     */
    @Column(value = "sy_orderindex")
    private Integer syOrderindex;

    /**
     * 所属机构_id
     */
    @Column(value = "sy_org_id")
    private String syOrgId;

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

}
