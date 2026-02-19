package com.ssitao.code.modular.iam.controller.vo;

import com.ssitao.code.frame.satoken.api.LoginUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录响应 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "登录响应")
public class AuthLoginRespVO {

    /**
     * Token
     */
    @Schema(description = "访问令牌")
    private String token;

    /**
     * 登录用户信息
     */
    @Schema(description = "登录用户信息")
    private LoginUser user;

    public AuthLoginRespVO(String token, LoginUser user) {
        this.token = token;
        this.user = user;
    }

}
