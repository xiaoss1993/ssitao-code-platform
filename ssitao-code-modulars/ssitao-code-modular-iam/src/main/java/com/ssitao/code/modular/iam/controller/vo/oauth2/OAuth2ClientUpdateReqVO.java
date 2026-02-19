package com.ssitao.code.modular.iam.controller.vo.oauth2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * OAuth2客户端更新请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - OAuth2客户端更新请求")
public class OAuth2ClientUpdateReqVO {

    @Schema(description = "客户端ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "客户端ID不能为空")
    private Long id;

    @Schema(description = "客户端标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "客户端标识不能为空")
    private String clientId;

    @Schema(description = "客户端密钥", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "客户端密钥不能为空")
    private String clientSecret;

    @Schema(description = "客户端名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "客户端名称不能为空")
    private String name;

    @Schema(description = "客户端类型：1-Web应用 2-移动应用 3-桌面应用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "客户端类型不能为空")
    private Integer clientType;

    @Schema(description = "授权类型：1-授权码 2-密码 3-客户端凭证 4-刷新令牌（多个用逗号分隔）")
    private String grantTypes;

    @Schema(description = "重定向URI")
    private String redirectUris;

    @Schema(description = "授权范围")
    private String scopes;

    @Schema(description = "自动授权的授权范围")
    private String autoApproveScopes;

    @Schema(description = "访问令牌有效期（秒）")
    private Integer accessTokenValidity;

    @Schema(description = "刷新令牌有效期（秒）")
    private Integer refreshTokenValidity;

    @Schema(description = "状态：1-启用 0-禁用", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    @Schema(description = "备注")
    private String remark;

}
