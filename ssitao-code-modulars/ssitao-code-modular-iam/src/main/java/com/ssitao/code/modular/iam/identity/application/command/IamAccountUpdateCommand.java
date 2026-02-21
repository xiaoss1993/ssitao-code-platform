package com.ssitao.code.modular.iam.identity.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * IAM账号更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "IAM账号更新命令")
public class IamAccountUpdateCommand {

    @NotBlank(message = "账号ID不能为空")
    @Schema(description = "账号ID", required = true)
    private String id;

    @Size(max = 128, message = "账号名称长度不能超过128")
    @Schema(description = "账号名称")
    private String accountName;

    @Schema(description = "手机号")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "头像URL")
    private String avatar;

    @Schema(description = "账号过期时间")
    private LocalDateTime expireTime;

    @Size(max = 500, message = "备注长度不能超过500")
    @Schema(description = "备注")
    private String remark;

    @Schema(description = "更新人")
    private String updater;

}
