package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 更新元数据表配置命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "更新元数据表配置命令")
public class MetaTableUpdateCommand {

    @Schema(description = "表ID")
    private String id;

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "表描述")
    private String tableDesc;

    @Schema(description = "表类型:1-业务表 2-字典表 3-树形表 4-关联表")
    private Integer tableType;

    @Schema(description = "包名")
    private String packageName;

    @Schema(description = "模块名")
    private String moduleName;

    @Schema(description = "类名")
    private String className;

    @Schema(description = "类描述")
    private String classDesc;

    @Schema(description = "实体类名称")
    private String entityName;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "是否启用")
    private Integer enabled;

    @Schema(description = "生成路径")
    private String genPath;

    @Schema(description = "备注")
    private String remark;
}
