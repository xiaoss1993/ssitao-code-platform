package com.ssitao.code.modular.iam.identity.application.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * IAM登录日志查询条件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "IAM登录日志查询条件")
public class IamLoginLogQuery {

    @Schema(description = "账号编码")
    private String accountCode;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "登录类型")
    private String loginType;

    @Schema(description = "登录结果：success-成功 fail-失败")
    private String loginResult;

    @Schema(description = "登录状态：success-成功 fail-失败")
    private String loginStatus;

    @Schema(description = "登录IP")
    private String loginIp;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页大小", example = "20")
    private Integer pageSize = 20;

}
