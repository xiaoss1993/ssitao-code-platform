package com.ssitao.code.modular.iam.controller.vo.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 操作日志响应VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 操作日志响应")
public class OperateLogRespVO {

    @Schema(description = "日志ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "模块名")
    private String module;

    @Schema(description = "操作名")
    private String name;

    @Schema(description = "操作类型：1-查询 2-新增 3-修改 4-删除 5-导出 6-导入")
    private Integer type;

    @Schema(description = "业务编号")
    private String businessNo;

    @Schema(description = "请求方法名")
    private String requestMethod;

    @Schema(description = "请求URL")
    private String requestUrl;

    @Schema(description = "请求参数")
    private String requestParams;

    @Schema(description = "返回值")
    private String returnValue;

    @Schema(description = "返回码：0-成功 其他-失败")
    private Integer returnCode;

    @Schema(description = "返回消息")
    private String returnMsg;

    @Schema(description = "执行时长（毫秒）")
    private Integer costTime;

    @Schema(description = "客户端IP")
    private String clientIp;

    @Schema(description = "用户代理")
    private String userAgent;

    @Schema(description = "操作时间")
    private LocalDateTime operateTime;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
