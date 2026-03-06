package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 创建元数据列表按钮配置命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "创建元数据列表按钮配置命令")
public class MetaListButtonCreateCommand {

    @Schema(description = "按钮编码")
    private String buttonCode;

    @Schema(description = "按钮名称")
    private String buttonName;

    @Schema(description = "按钮类型:1-新增 2-编辑 3-删除 4-导出 5-导入 6-自定义")
    private Integer buttonType = 6;

    @Schema(description = "按钮样式:1-主要 2-默认 3-文本 4-链接 5-成功 6-警告 7-危险 8-信息")
    private Integer buttonStyle = 1;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "排序")
    private Integer sortOrder = 0;

    @Schema(description = "是否显示")
    private Integer visible = 1;

    @Schema(description = "是否禁用")
    private Integer disabled = 0;

    @Schema(description = "点击事件")
    private String clickEvent;

    @Schema(description = "权限编码")
    private String permissionCode;

    @Schema(description = "扩展配置(JSON)")
    private String config;
}
