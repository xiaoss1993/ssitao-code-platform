package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 创建元数据列表配置命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "创建元数据列表配置命令")
public class MetaListCreateCommand {

    @Schema(description = "实体ID")
    @NotBlank(message = "实体ID不能为空")
    private String entityId;

    @Schema(description = "列表编码")
    @NotBlank(message = "列表编码不能为空")
    private String listCode;

    @Schema(description = "列表名称")
    @NotBlank(message = "列表名称不能为空")
    private String listName;

    @Schema(description = "列表类型:1-默认列表 2-树形列表 3-表格列表 4-分组列表")
    private Integer listType = 1;

    @Schema(description = "列表行样式:1-默认 2-紧凑 3-舒适")
    private Integer rowStyle = 3;

    @Schema(description = "是否显示斑马纹")
    private Integer stripe = 1;

    @Schema(description = "是否显示边框")
    private Integer border = 1;

    @Schema(description = "是否显示表头")
    private Integer showHeader = 1;

    @Schema(description = "是否高亮当前行")
    private Integer highlightCurrentRow = 1;

    @Schema(description = "排序方式:1-服务端排序 2-客户端排序")
    private Integer sortMode = 1;

    @Schema(description = "默认排序字段")
    private String defaultSortField;

    @Schema(description = "默认排序方式:asc/desc")
    private String defaultSortOrder = "asc";

    @Schema(description = "默认分页大小")
    private Integer defaultPageSize = 10;

    @Schema(description = "分页大小选项")
    private String pageSizeOptions = "[10,20,50,100]";

    @Schema(description = "是否显示分页")
    private Integer showPagination = 1;

    @Schema(description = "是否显示刷新按钮")
    private Integer showRefresh = 1;

    @Schema(description = "是否显示列设置")
    private Integer showColumnSetting = 1;

    @Schema(description = "是否显示全屏按钮")
    private Integer showFullscreen = 1;

    @Schema(description = "是否显示搜索栏")
    private Integer showSearchBar = 1;

    @Schema(description = "搜索栏位置:1-顶部 2-右侧 3-隐藏")
    private Integer searchBarPosition = 1;

    @Schema(description = "是否显示高级搜索")
    private Integer showAdvancedSearch = 0;

    @Schema(description = "是否显示工具栏")
    private Integer showToolbar = 1;

    @Schema(description = "是否显示新增按钮")
    private Integer showAddButton = 1;

    @Schema(description = "是否显示批量新增按钮")
    private Integer showBatchAddButton = 0;

    @Schema(description = "是否显示编辑按钮")
    private Integer showEditButton = 1;

    @Schema(description = "是否显示删除按钮")
    private Integer showDeleteButton = 1;

    @Schema(description = "是否显示导出按钮")
    private Integer showExportButton = 0;

    @Schema(description = "是否显示导入按钮")
    private Integer showImportButton = 0;

    @Schema(description = "扩展配置(JSON)")
    private String config;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态:1-启用 0-禁用")
    private Integer status = 1;

    // 列配置列表
    private List<MetaListColumnCreateCommand> columns;

    // 按钮配置列表
    private List<MetaListButtonCreateCommand> buttons;

    // 查询条件配置列表
    private List<MetaListQueryCreateCommand> queries;
}
