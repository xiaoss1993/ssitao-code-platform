package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 更新元数据字段命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "更新元数据字段命令")
public class MetaColumnUpdateCommand {

    @Schema(description = "字段ID")
    @NotBlank(message = "字段ID不能为空")
    private String columnId;

    @Schema(description = "字段名称")
    private String columnName;

    @Schema(description = "字段描述")
    private String columnDesc;

    @Schema(description = "字段类型")
    private String columnType;

    @Schema(description = "Java类型")
    private String javaType;

    @Schema(description = "Java字段名")
    private String javaField;

    @Schema(description = "是否主键")
    private Integer isPk;

    @Schema(description = "是否自增")
    private Integer isIncrement;

    @Schema(description = "是否必填")
    private Integer isRequired;

    @Schema(description = "是否为查询条件")
    private Integer isQuery;

    @Schema(description = "查询方式:1-精确 2-模糊 3-范围")
    private Integer queryType;

    @Schema(description = "是否为显示字段")
    private Integer isDisplay;

    @Schema(description = "是否为列表显示")
    private Integer isList;

    @Schema(description = "是否为表单字段")
    private Integer isForm;

    @Schema(description = "表单类型")
    private Integer formType;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "排序")
    private Integer columnSort;

    @Schema(description = "备注")
    private String remark;
}
