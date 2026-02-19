package com.ssitao.code.modular.iam.controller.vo.sms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 短信日志分页查询请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 短信日志分页查询请求")
public class SmsLogPageReqVO {

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "短信类型：1-验证码 2-通知 3-营销")
    private Integer type;

    @Schema(description = "发送结果：1-成功 0-失败")
    private Integer result;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

}
