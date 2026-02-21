package com.ssitao.code.modular.iam.identity.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * IAM账号创建命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "IAM账号创建命令")
public class IamAccountCreateCommand {

    @NotBlank(message = "账号编码不能为空")
    @Size(max = 64, message = "账号编码长度不能超过64")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "账号编码只能包含字母、数字和下划线")
    @Schema(description = "账号编码", required = true)
    private String accountCode;

    @NotBlank(message = "账号名称不能为空")
    @Size(max = 128, message = "账号名称长度不能超过128")
    @Schema(description = "账号名称", required = true)
    private String accountName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20之间")
    @Schema(description = "密码", required = true)
    private String password;

    @Schema(description = "手机号")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "头像URL")
    private String avatar;

    @Schema(description = "关联用户ID")
    private String userId;

    @Schema(description = "账号过期时间")
    private LocalDateTime expireTime;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "组织ID")
    private String orgId;

    @Size(max = 500, message = "备注长度不能超过500")
    @Schema(description = "备注")
    private String remark;

}
