package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 创建元数据实体配置命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "创建元数据实体配置命令")
public class MetaEntityCreateCommand {

    @Schema(description = "实体编码")
    private String entityCode;

    @Schema(description = "实体名称")
    private String entityName;

    @Schema(description = "物理表名")
    private String tableName;

    @Schema(description = "实体类型:SYSTEM-系统 USER-自定义")
    private String entityType = "USER";

    @Schema(description = "分类")
    private String category;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "包名")
    private String packageName;

    @Schema(description = "模块名")
    private String moduleName;

    @Schema(description = "业务名")
    private String businessName;

    @Schema(description = "模板类型:CRUD-增删改查 TREE-树形 MASTER_DETAIL-主明细")
    private String templateType = "CRUD";

    @Schema(description = "状态:1-启用 0-禁用")
    private Integer status = 1;
}
