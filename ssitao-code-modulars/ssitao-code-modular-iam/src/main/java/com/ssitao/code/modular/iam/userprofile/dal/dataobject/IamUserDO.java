package com.ssitao.code.modular.iam.userprofile.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户信息数据对象
 * 对应表：iam_user
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_user")
public class IamUserDO {

    /**
     * 用户ID
     */
    @Id
    private String userId;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 性别: MALE-男, FEMALE-女, UNKNOWN-未知
     */
    private String userSex;

    /**
     * 生日
     */
    private LocalDate userBirthday;

    /**
     * 身份证号
     */
    private String userIdCard;

    /**
     * 手机号码
     */
    private String userPhone;

    /**
     * 邮箱地址
     */
    private String userMail;

    /**
     * 照片URL
     */
    private String userPhoto;

    /**
     * 居住地址
     */
    private String userAddress;

    /**
     * 籍贯
     */
    private String userNativePlace;

    /**
     * 民族
     */
    private String userNation;

    /**
     * 婚姻状况
     */
    private String userMaritalStatus;

    /**
     * 政治面貌
     */
    private String userPoliticalStatus;

    /**
     * 工号
     */
    private String userWorkNumber;

    /**
     * 入职日期
     */
    private LocalDate userEntryDate;

    /**
     * 试用期结束日期
     */
    private LocalDate userProbationEndDate;

    /**
     * 用工性质: FULL_TIME-全职, PART_TIME-兼职, INTERNSHIP-实习, OUTSOURCING-外包
     */
    private String userEmploymentType;

    /**
     * 学历
     */
    private String userEducation;

    /**
     * 员工状态: ON_JOB-在职, RESIGN-离职, probation-试用期
     */
    private String userStatus;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建组织ID
     */
    private String createOrgId;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人ID
     */
    private String modifyUserId;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer isDeleted;

    /**
     * 版本号
     */
    private Integer version;

}
