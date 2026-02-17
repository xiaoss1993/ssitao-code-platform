package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
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
 * 部门人员 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_user")
public class TbIamUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamUserId;

    /**
     * 名称
     */
    private String userName;

    /**
     * 编码
     */
    private String userCode;

    /**
     * 性别_code
     */
    private String userSexCode;

    /**
     * 性别_name
     */
    private String userSexName;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 邮箱
     */
    private String userMail;

    /**
     * 已婚_code
     */
    private String userMarriedCode;

    /**
     * 已婚_name
     */
    private String userMarriedName;

    /**
     * 年龄
     */
    private Integer userAge;

    /**
     * 生日
     */
    private String userBirth;

    /**
     * 入职日期
     */
    private String userEntryDate;

    /**
     * 离职日期
     */
    private String userLeaveDate;

    /**
     * 已初始化_code
     */
    private String userInitedCode;

    /**
     * 已初始化_name
     */
    private String userInitedName;

    /**
     * 照片
     */
    private String userPhoto;

    /**
     * 头像
     */
    private String userAvatar;

    /**
     * 身份卡号
     */
    private String userCardnum;

    /**
     * 证件类型_code
     */
    private String userIdtypeCode;

    /**
     * 证件类型_name
     */
    private String userIdtypeName;

    /**
     * 籍贯
     */
    private String userNativePlace;

    /**
     * 文化程度_code
     */
    private String userEducationCode;

    /**
     * 文化程度_name
     */
    private String userEducationName;

    /**
     * 行政职务_code
     */
    private String userPostCode;

    /**
     * 行政职务_name
     */
    private String userPostName;

    /**
     * 监管部门_id
     */
    private String userMonitordeptId;

    /**
     * 监管部门_name
     */
    private String userMonitordeptName;

    /**
     * 角色_id
     */
    private String userRoleId;

    /**
     * 角色_name
     */
    private String userRoleName;

    /**
     * 备注信息
     */
    private String userRemark;

    /**
     * 通讯地址
     */
    private String userAddress;

    /**
     * 工位
     */
    private String userStation;

    /**
     * 工号
     */
    private String userJobnum;

    /**
     * 现居地址
     */
    private String userNowaddress;

    /**
     * 政治面貌_code
     */
    private String userPoliticalCode;

    /**
     * 政治面貌_name
     */
    private String userPoliticalName;

    /**
     * 紧急联系人名称
     */
    private String userContactorName;

    /**
     * 紧急联系人电话
     */
    private String userContactorPhone;

    /**
     * 座机
     */
    private String userTelephone;

    /**
     * 户籍性质_code
     */
    private String userHouseholdCode;

    /**
     * 户籍性质_name
     */
    private String userHouseholdName;

    /**
     * 民族_name
     */
    @Column("user_nation__name")
    private String userNation_name;

    /**
     * 民族_code
     */
    private String userNationCode;

    /**
     * 国籍_name
     */
    private String userNationalityName;

    /**
     * 国籍_code
     */
    private String userNationalityCode;

    /**
     * 人员状态
     */
    private String userEmployeeStatus;

    /**
     * 密级_code
     */
    private String sySecretCode;

    /**
     * 密级_name
     */
    private String sySecretName;

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
     * 是否允许
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 所属机构_id
     */
    private String syOrgId;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户_name
     */
    private String syTenantName;

}
