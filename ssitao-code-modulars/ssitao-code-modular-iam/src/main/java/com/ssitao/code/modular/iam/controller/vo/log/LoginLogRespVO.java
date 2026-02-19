package com.ssitao.code.modular.iam.controller.vo.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录日志响应VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 登录日志响应")
public class LoginLogRespVO {

    @Schema(description = "日志ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "登录类型：1-账号密码 2-短信验证码 3-社交登录")
    private Integer loginType;

    @Schema(description = "登录结果：1-成功 0-失败")
    private Integer result;

    @Schema(description = "登录消息")
    private String message;

    @Schema(description = "客户端IP")
    private String clientIp;

    @Schema(description = "用户代理")
    private String userAgent;

    @Schema(description = "登录时间")
    private LocalDateTime loginTime;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
