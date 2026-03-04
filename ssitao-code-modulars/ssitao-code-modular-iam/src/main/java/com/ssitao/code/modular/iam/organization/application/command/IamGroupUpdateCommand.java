package com.ssitao.code.modular.iam.organization.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * IAM集团更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IAM集团更新命令")
public class IamGroupUpdateCommand {

    @NotBlank(message = "集团ID不能为空")
    @Schema(description = "集团ID", required = true)
    private String id;

    @NotBlank(message = "集团编码不能为空")
    @Schema(description = "集团编码", required = true)
    private String groupCode;

    @NotBlank(message = "集团名称不能为空")
    @Schema(description = "集团名称", required = true)
    private String groupName;

    @Schema(description = "负责人")
    private String leader;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "排序")
    private Integer sortOrder;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "更新人")
    private String updater;
}
