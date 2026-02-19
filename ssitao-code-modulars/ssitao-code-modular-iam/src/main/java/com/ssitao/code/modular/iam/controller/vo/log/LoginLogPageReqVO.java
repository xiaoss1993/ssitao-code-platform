package com.ssitao.code.modular.iam.controller.vo.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录日志分页查询请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 登录日志分页查询请求")
public class LoginLogPageReqVO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "登录类型：1-账号密码 2-短信验证码 3-社交登录")
    private Integer loginType;

    @Schema(description = "登录结果：1-成功 0-失败")
    private Integer result;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

}
