package com.ssitao.code.modular.iam.organization.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * IAM用户组织分配命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IAM用户组织分配命令")
public class IamUserOrgCreateCommand {

    @NotBlank(message = "用户ID不能为空")
    @Schema(description = "用户ID", required = true)
    private String userId;

    @Schema(description = "集团ID")
    private String groupId;

    @NotBlank(message = "公司ID不能为空")
    @Schema(description = "公司ID", required = true)
    private String companyId;

    @NotBlank(message = "部门ID不能为空")
    @Schema(description = "部门ID", required = true)
    private String deptId;

    @Schema(description = "岗位ID")
    private String postId;

    @Schema(description = "是否主组织(true是/false否)")
    private Boolean isMain;

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID", required = true)
    private String tenantId;

    @Schema(description = "创建人")
    private String creator;
}
