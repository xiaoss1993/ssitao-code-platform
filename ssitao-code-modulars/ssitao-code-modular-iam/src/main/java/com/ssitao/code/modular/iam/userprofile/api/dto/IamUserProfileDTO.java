package com.ssitao.code.modular.iam.userprofile.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * IAM用户档案DTO
 * 基于 tb_iam_user 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "IAM用户档案DTO")
public class IamUserProfileDTO {

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "用户编码")
    private String userCode;

    @Schema(description = "性别代码")
    private String userSexCode;

    @Schema(description = "性别名称")
    private String userSexName;

    @Schema(description = "手机号")
    private String userPhone;

    @Schema(description = "邮箱")
    private String userMail;

    @Schema(description = "年龄")
    private Integer userAge;

    @Schema(description = "生日")
    private String userBirth;

    @Schema(description = "入职日期")
    private String userEntryDate;

    @Schema(description = "离职日期")
    private String userLeaveDate;

    @Schema(description = "头像")
    private String userAvatar;

    @Schema(description = "证件号")
    private String userCardnum;

    @Schema(description = "证件类型代码")
    private String userIdtypeCode;

    @Schema(description = "证件类型名称")
    private String userIdtypeName;

    @Schema(description = "籍贯")
    private String userNativePlace;

    @Schema(description = "文化程度代码")
    private String userEducationCode;

    @Schema(description = "文化程度名称")
    private String userEducationName;

    @Schema(description = "岗位代码")
    private String userPostCode;

    @Schema(description = "岗位名称")
    private String userPostName;

    @Schema(description = "监管部门ID")
    private String userMonitordeptId;

    @Schema(description = "监管部门名称")
    private String userMonitordeptName;

    @Schema(description = "角色ID")
    private String userRoleId;

    @Schema(description = "角色名称")
    private String userRoleName;

    @Schema(description = "备注信息")
    private String userRemark;

    @Schema(description = "通讯地址")
    private String userAddress;

    @Schema(description = "工位")
    private String userStation;

    @Schema(description = "工号")
    private String userJobnum;

    @Schema(description = "现居地址")
    private String userNowaddress;

    @Schema(description = "政治面貌代码")
    private String userPoliticalCode;

    @Schema(description = "政治面貌名称")
    private String userPoliticalName;

    @Schema(description = "紧急联系人名称")
    private String userContactorName;

    @Schema(description = "紧急联系人电话")
    private String userContactorPhone;

    @Schema(description = "座机")
    private String userTelephone;

    @Schema(description = "民族代码")
    private String userNationCode;

    @Schema(description = "民族名称")
    private String userNationName;

    @Schema(description = "国籍代码")
    private String userNationalityCode;

    @Schema(description = "国籍名称")
    private String userNationalityName;

    @Schema(description = "人员状态")
    private String userEmployeeStatus;

    @Schema(description = "数据状态")
    private String syStatus;

    @Schema(description = "排序字段")
    private Integer syOrderindex;

    @Schema(description = "所属机构ID")
    private String syOrgId;

    @Schema(description = "租户ID")
    private String syTenantId;

    @Schema(description = "租户名称")
    private String syTenantName;

    @Schema(description = "创建时间")
    private String syCreatetime;

    @Schema(description = "修改时间")
    private String syModifytime;

}
