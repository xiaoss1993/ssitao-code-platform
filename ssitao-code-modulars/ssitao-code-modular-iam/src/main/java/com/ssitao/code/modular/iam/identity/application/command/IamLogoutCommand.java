package com.ssitao.code.modular.iam.identity.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * IAM登出命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "IAM登出命令")
public class IamLogoutCommand {

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "设备ID")
    private String deviceId;

}
