package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 创建元数据表单配置命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "创建元数据表单配置命令")
public class MetaFormCreateCommand {

    @Schema(description = "实体ID")
    @NotBlank(message = "实体ID不能为空")
    private String entityId;

    @Schema(description = "表单编码")
    @NotBlank(message = "表单编码不能为空")
    private String formCode;

    @Schema(description = "表单名称")
    @NotBlank(message = "表单名称不能为空")
    private String formName;

    @Schema(description = "表单类型:1-默认表单 2-弹窗表单 3-抽屉表单 4-步骤表单")
    private Integer formType = 1;

    @Schema(description = "表单布局:1-单列 2-双列 3-三列 4-四列")
    private Integer layout = 1;

    @Schema(description = "表单宽度")
    private Integer width = 600;

    @Schema(description = "表单标签位置:1-左侧 2-上方 3-右侧")
    private Integer labelPosition = 1;

    @Schema(description = "表单标签宽度")
    private Integer labelWidth = 100;

    @Schema(description = "是否显示操作按钮")
    private Integer showButtons = 1;

    @Schema(description = "是否显示重置按钮")
    private Integer showResetButton = 1;

    @Schema(description = "是否显示取消按钮")
    private Integer showCancelButton = 1;

    @Schema(description = "提交按钮文本")
    private String submitButtonText = "提交";

    @Schema(description = "重置按钮文本")
    private String resetButtonText = "重置";

    @Schema(description = "取消按钮文本")
    private String cancelButtonText = "取消";

    @Schema(description = "提交后行为:1-关闭弹窗 2-刷新列表 3-跳转页面")
    private Integer submitAction = 1;

    @Schema(description = "跳转页面路径")
    private String redirectPath;

    @Schema(description = "扩展配置(JSON)")
    private String config;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态:1-启用 0-禁用")
    private Integer status = 1;

    // 字段配置列表
    private List<MetaFormFieldCreateCommand> fields;
}
