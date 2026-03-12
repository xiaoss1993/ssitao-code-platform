package com.ssitao.code.modular.meta.application.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 元数据表查询条件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "元数据表查询条件")
public class MetaTableQuery {

    @Schema(description = "表名称(模糊查询)")
    private String tableName;

    @Schema(description = "表描述(模糊查询)")
    private String tableComment;

    @Schema(description = "实体ID")
    private String entityId;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "状态：1-启用 0-禁用")
    private Integer status;

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页大小", example = "20")
    private Integer pageSize = 20;
}
