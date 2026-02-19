package com.ssitao.code.modular.iam.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录请求 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "登录请求")
public class AuthLoginReqVO {

    /**
     * 用户名
     */
    @Schema(description = "用户名", required = true)
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码", required = true)
    private String password;

}
