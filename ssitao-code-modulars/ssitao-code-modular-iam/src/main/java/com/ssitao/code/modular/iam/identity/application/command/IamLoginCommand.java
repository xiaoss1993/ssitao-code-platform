package com.ssitao.code.modular.iam.identity.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * IAM登录命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "IAM登录命令")
public class IamLoginCommand {

    @NotBlank(message = "登录账号不能为空")
    @Schema(description = "登录账号", required = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码", required = true)
    private String password;

    @Schema(description = "登录类型：PASSWORD-密码 SMS-短信验证码 SOCIAL-第三方", defaultValue = "PASSWORD")
    private String loginType = "PASSWORD";

    @Schema(description = "验证码(短信/邮箱登录时使用)")
    private String verifyCode;

    @Schema(description = "第三方OpenID")
    private String openId;

    @Schema(description = "第三方类型：WECHAT-微信 QQ-支付宝 ALIPAY-支付宝")
    private String openType;

    @Schema(description = "租户ID")
    private String tenantId;

}
