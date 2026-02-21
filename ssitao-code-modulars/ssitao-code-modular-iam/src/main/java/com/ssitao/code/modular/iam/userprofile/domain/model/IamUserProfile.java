package com.ssitao.code.modular.iam.userprofile.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * IAM用户档案领域模型
 * 基于 tb_iam_user 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamUserProfile {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 性别代码
     */
    private String userSexCode;

    /**
     * 性别名称
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
     * 头像
     */
    private String userAvatar;

    /**
     * 证件号
     */
    private String userCardnum;

    /**
     * 证件类型代码
     */
    private String userIdtypeCode;

    /**
     * 证件类型名称
     */
    private String userIdtypeName;

    /**
     * 籍贯
     */
    private String userNativePlace;

    /**
     * 文化程度代码
     */
    private String userEducationCode;

    /**
     * 文化程度名称
     */
    private String userEducationName;

    /**
     * 岗位代码
     */
    private String userPostCode;

    /**
     * 岗位名称
     */
    private String userPostName;

    /**
     * 监管部门ID
     */
    private String userMonitordeptId;

    /**
     * 监管部门名称
     */
    private String userMonitordeptName;

    /**
     * 角色ID
     */
    private String userRoleId;

    /**
     * 角色名称
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
     * 政治面貌代码
     */
    private String userPoliticalCode;

    /**
     * 政治面貌名称
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
     * 民族代码
     */
    private String userNationCode;

    /**
     * 民族名称
     */
    private String userNationName;

    /**
     * 国籍代码
     */
    private String userNationalityCode;

    /**
     * 国籍名称
     */
    private String userNationalityName;

    /**
     * 人员状态
     */
    private String userEmployeeStatus;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 所属机构ID
     */
    private String syOrgId;

    /**
     * 租户ID
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 创建时间
     */
    private String syCreatetime;

    /**
     * 修改时间
     */
    private String syModifytime;

    // ==================== 业务方法 ====================

    /**
     * 启用用户档案
     */
    public void enable() {
        this.syStatus = "1";
    }

    /**
     * 禁用用户档案
     */
    public void disable() {
        this.syStatus = "0";
    }

    /**
     * 检查是否启用
     */
    public boolean isEnabled() {
        return "1".equals(this.syStatus);
    }

    /**
     * 分配部门
     */
    public void assignDepartment(String deptId, String deptName) {
        this.userMonitordeptId = deptId;
        this.userMonitordeptName = deptName;
    }

    /**
     * 分配岗位
     */
    public void assignPost(String postCode, String postName) {
        this.userPostCode = postCode;
        this.userPostName = postName;
    }

    /**
     * 分配角色
     */
    public void assignRole(String roleId, String roleName) {
        this.userRoleId = roleId;
        this.userRoleName = roleName;
    }

}
