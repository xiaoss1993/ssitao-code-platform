package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 创建元数据列表列配置命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "创建元数据列表列配置命令")
public class MetaListColumnCreateCommand {

    @Schema(description = "字段编码")
    private String fieldCode;

    @Schema(description = "字段名称")
    private String fieldName;

    @Schema(description = "列类型:1-普通列 2-排序列 3-图片列 4-标签列 5-开关列 6-按钮列 7-自定义列")
    private Integer columnType = 1;

    @Schema(description = "列宽")
    private Integer width;

    @Schema(description = "最小列宽")
    private Integer minWidth;

    @Schema(description = "对齐方式:1-左对齐 2-居中 3-右对齐")
    private Integer align = 1;

    @Schema(description = "是否固定到左侧")
    private Integer fixedLeft = 0;

    @Schema(description = "是否固定到右侧")
    private Integer fixedRight = 0;

    @Schema(description = "是否可排序")
    private Integer sortable = 0;

    @Schema(description = "排序字段")
    private String sortField;

    @Schema(description = "是否可筛选")
    private Integer filterable = 0;

    @Schema(description = "筛选类型:1-输入框 2-下拉框 3-日期范围 4-数字范围")
    private Integer filterType;

    @Schema(description = "筛选默认值")
    private String filterDefaultValue;

    @Schema(description = "字典类型编码")
    private String dictTypeCode;

    @Schema(description = "字典数据(JSON)")
    private String dictData;

    @Schema(description = "图片预览")
    private Integer imagePreview = 0;

    @Schema(description = "图片宽度")
    private Integer imageWidth;

    @Schema(description = "图片高度")
    private Integer imageHeight;

    @Schema(description = "日期格式化")
    private String dateFormat;

    @Schema(description = "数字格式化")
    private String numberFormat;

    @Schema(description = "是否显示在列表中")
    private Integer showInList = 1;

    @Schema(description = "是否在默认列表中显示")
    private Integer showInDefault = 1;

    @Schema(description = "扩展配置(JSON)")
    private String config;

    @Schema(description = "排序")
    private Integer sortOrder = 0;
}
