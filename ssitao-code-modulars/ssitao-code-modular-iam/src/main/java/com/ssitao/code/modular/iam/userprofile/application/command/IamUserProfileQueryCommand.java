package com.ssitao.code.modular.iam.userprofile.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * IAM用户档案查询命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "IAM用户档案查询命令")
public class IamUserProfileQueryCommand {

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "用户编码")
    private String userCode;

    @Schema(description = "用户名称（模糊查询）")
    private String userName;

    @Schema(description = "手机号")
    private String userPhone;

    @Schema(description = "邮箱")
    private String userMail;

    @Schema(description = "岗位代码")
    private String userPostCode;

    @Schema(description = "角色ID")
    private String userRoleId;

    @Schema(description = "监管部门ID")
    private String userMonitordeptId;

    @Schema(description = "人员状态")
    private String userEmployeeStatus;

    @Schema(description = "数据状态")
    private String syStatus;

    @Schema(description = "租户ID")
    private String syTenantId;

    @Schema(description = "所属机构ID")
    private String syOrgId;

    @Schema(description = "关键字搜索（用户名、手机号、邮箱）")
    private String keyword;

}
