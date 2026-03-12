package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 创建元数据表单字段配置命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "创建元数据表单字段配置命令")
public class MetaFormFieldCreateCommand {

    @Schema(description = "字段编码")
    private String fieldCode;

    @Schema(description = "字段名称")
    private String fieldName;

    @Schema(description = "字段类型")
    private String fieldType;

    @Schema(description = "控件类型:1-输入框 2-文本域 3-下拉框 4-单选框 5-复选框 6-开关 7-日期选择 8-时间选择 9-日期时间选择 10-文件上传 11-图片上传 12-富文本 13-评分 14-滑块 15-颜色选择 16-穿梭框 17-树选择")
    private Integer controlType = 1;

    @Schema(description = "控件宽度(百分比)")
    private Integer controlWidth = 100;

    @Schema(description = "占位符")
    private String placeholder;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "是否必填")
    private Integer required = 0;

    @Schema(description = "是否可编辑")
    private Integer editable = 1;

    @Schema(description = "是否可见")
    private Integer visible = 1;

    @Schema(description = "是否可复制")
    private Integer copyable = 0;

    @Schema(description = "验证规则(JSON)")
    private String rules;

    @Schema(description = "字典类型编码")
    private String dictTypeCode;

    @Schema(description = "字典数据(JSON)")
    private String dictData;

    @Schema(description = "远程数据URL")
    private String remoteUrl;

    @Schema(description = "显示字段")
    private String labelField;

    @Schema(description = "值字段")
    private String valueField;

    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "排序")
    private Integer sortOrder = 0;

    @Schema(description = "扩展配置(JSON)")
    private String config;
}
