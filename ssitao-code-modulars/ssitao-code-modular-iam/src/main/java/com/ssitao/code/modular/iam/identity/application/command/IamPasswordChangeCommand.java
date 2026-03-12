package com.ssitao.code.modular.iam.identity.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * IAM账号修改密码命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "IAM账号修改密码命令")
public class IamPasswordChangeCommand {

    @NotBlank(message = "账号ID不能为空")
    @Schema(description = "账号ID", required = true)
    private String accountId;

    @NotBlank(message = "原密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20之间")
    @Schema(description = "原密码", required = true)
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20之间")
    @Schema(description = "新密码", required = true)
    private String newPassword;

}
