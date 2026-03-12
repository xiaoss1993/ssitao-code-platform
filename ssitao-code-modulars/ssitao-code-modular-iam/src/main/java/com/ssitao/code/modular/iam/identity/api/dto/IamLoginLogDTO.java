package com.ssitao.code.modular.iam.identity.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM登录日志DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IAM登录日志DTO")
public class IamLoginLogDTO {

    @Schema(description = "日志ID")
    private String id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "账号ID")
    private String accountId;

    @Schema(description = "登录类型")
    private String loginType;

    @Schema(description = "登录IP")
    private String loginIp;

    @Schema(description = "登录地点")
    private String loginLocation;

    @Schema(description = "设备信息")
    private String deviceInfo;

    @Schema(description = "登录状态：1-成功 0-失败")
    private Boolean loginStatus;

    @Schema(description = "错误信息")
    private String errorMessage;

    @Schema(description = "登录时间")
    private LocalDateTime loginTime;

}
