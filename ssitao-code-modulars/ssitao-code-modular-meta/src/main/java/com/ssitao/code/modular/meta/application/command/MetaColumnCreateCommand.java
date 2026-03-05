package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建元数据字段命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "创建元数据字段命令")
public class MetaColumnCreateCommand {

    @Schema(description = "表ID")
    @NotBlank(message = "表ID不能为空")
    private String tableId;

    @Schema(description = "字段名称")
    @NotBlank(message = "字段名称不能为空")
    private String columnName;

    @Schema(description = "字段描述")
    private String columnDesc;

    @Schema(description = "字段类型")
    @NotBlank(message = "字段类型不能为空")
    private String columnType;

    @Schema(description = "Java类型")
    @NotBlank(message = "Java类型不能为空")
    private String javaType;

    @Schema(description = "Java字段名")
    @NotBlank(message = "Java字段名不能为空")
    private String javaField;

    @Schema(description = "是否主键")
    private Integer isPk = 0;

    @Schema(description = "是否自增")
    private Integer isIncrement = 0;

    @Schema(description = "是否必填")
    private Integer isRequired = 0;

    @Schema(description = "是否为查询条件")
    private Integer isQuery = 0;

    @Schema(description = "查询方式:1-精确 2-模糊 3-范围")
    private Integer queryType = 1;

    @Schema(description = "是否为显示字段")
    private Integer isDisplay = 0;

    @Schema(description = "是否为列表显示")
    private Integer isList = 0;

    @Schema(description = "是否为表单字段")
    private Integer isForm = 0;

    @Schema(description = "表单类型")
    private Integer formType = 1;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "排序")
    private Integer columnSort = 0;

    @Schema(description = "备注")
    private String remark;
}
