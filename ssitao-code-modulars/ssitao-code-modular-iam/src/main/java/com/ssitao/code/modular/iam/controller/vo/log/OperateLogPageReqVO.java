package com.ssitao.code.modular.iam.controller.vo.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 操作日志分页查询请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 操作日志分页查询请求")
public class OperateLogPageReqVO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "模块名")
    private String module;

    @Schema(description = "操作名")
    private String name;

    @Schema(description = "操作类型：1-查询 2-新增 3-修改 4-删除 5-导出 6-导入")
    private Integer type;

    @Schema(description = "返回码：0-成功 其他-失败")
    private Integer returnCode;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

}
