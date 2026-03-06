package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 创建元数据列表查询条件配置命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "创建元数据列表查询条件配置命令")
public class MetaListQueryCreateCommand {

    @Schema(description = "字段编码")
    private String fieldCode;

    @Schema(description = "字段名称")
    private String fieldName;

    @Schema(description = "查询方式:1-精确查询 2-模糊查询 3-范围查询 4-日期范围 5-下拉框 6-多选")
    private Integer queryType = 1;

    @Schema(description = "控件类型:1-输入框 2-下拉框 3-日期选择 4-日期范围 5-数字范围 6-树选择")
    private Integer controlType = 1;

    @Schema(description = "控件宽度")
    private Integer controlWidth = 200;

    @Schema(description = "占位符")
    private String placeholder;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "是否显示在高级搜索")
    private Integer showInAdvanced = 1;

    @Schema(description = "是否显示在快速搜索")
    private Integer showInQuick = 1;

    @Schema(description = "字典类型编码")
    private String dictTypeCode;

    @Schema(description = "排序")
    private Integer sortOrder = 0;
}
