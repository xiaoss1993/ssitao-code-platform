package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 列表配置聚合根
 * 用于定义实体的列表配置，包括列表列、排序、筛选、分页等
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaList {

    /**
     * 列表配置ID
     */
    private String id;

    /**
     * 实体ID
     */
    private String entityId;

    /**
     * 列表编码
     */
    private String listCode;

    /**
     * 列表名称
     */
    private String listName;

    /**
     * 列表类型：1-默认列表 2-树形列表 3-表格列表 4-分组列表
     */
    private Integer listType;

    /**
     * 列表行样式：1-默认 2-紧凑 3-舒适
     */
    private Integer rowStyle;

    /**
     * 是否显示斑马纹
     */
    private Boolean stripe;

    /**
     * 是否显示边框
     */
    private Boolean border;

    /**
     * 是否显示表头
     */
    private Boolean showHeader;

    /**
     * 是否开启高亮当前行
     */
    private Boolean highlightCurrentRow;

    /**
     * 排序方式：1-服务端排序 2-客户端排序
     */
    private Integer sortMode;

    /**
     * 默认排序字段
     */
    private String defaultSortField;

    /**
     * 默认排序方式：asc/desc
     */
    private String defaultSortOrder;

    /**
     * 默认分页大小
     */
    private Integer defaultPageSize;

    /**
     * 分页大小选项
     */
    private String pageSizeOptions;

    /**
     * 是否显示分页
     */
    private Boolean showPagination;

    /**
     * 是否显示刷新按钮
     */
    private Boolean showRefresh;

    /**
     * 是否显示列设置
     */
    private Boolean showColumnSetting;

    /**
     * 是否显示全屏按钮
     */
    private Boolean showFullscreen;

    /**
     * 是否显示搜索栏
     */
    private Boolean showSearchBar;

    /**
     * 搜索栏位置：1-顶部 2-右侧 3-隐藏
     */
    private Integer searchBarPosition;

    /**
     * 是否显示高级搜索
     */
    private Boolean showAdvancedSearch;

    /**
     * 是否显示工具栏
     */
    private Boolean showToolbar;

    /**
     * 是否显示新增按钮
     */
    private Boolean showAddButton;

    /**
     * 是否显示批量新增按钮
     */
    private Boolean showBatchAddButton;

    /**
     * 是否显示编辑按钮
     */
    private Boolean showEditButton;

    /**
     * 是否显示删除按钮
     */
    private Boolean showDeleteButton;

    /**
     * 是否显示导出按钮
     */
    private Boolean showExportButton;

    /**
     * 是否显示导入按钮
     */
    private Boolean showImportButton;

    /**
     * 扩展配置(JSON)
     */
    private String config;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态：1-启用 0-禁用
     */
    private Integer status;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 是否删除
     */
    private Boolean deleted;

    // ==================== 非持久化字段 ====================

    /**
     * 列表列配置
     */
    private List<MetaListColumn> listColumns;

    /**
     * 列表操作按钮配置
     */
    private List<MetaListButton> listButtons;

    /**
     * 列表查询条件配置
     */
    private List<MetaListQuery> listQueries;

    /**
     * 创建列表配置
     *
     * @param entityId 实体ID
     * @param listCode 列表编码
     * @param listName 列表名称
     * @param listType 列表类型
     * @return 列表配置
     */
    public static MetaList create(String entityId, String listCode, String listName, Integer listType) {
        MetaList list = new MetaList();
        list.setEntityId(entityId);
        list.setListCode(listCode);
        list.setListName(listName);
        list.setListType(listType);
        list.setRowStyle(3);
        list.setStripe(true);
        list.setBorder(true);
        list.setShowHeader(true);
        list.setHighlightCurrentRow(true);
        list.setSortMode(1);
        list.setDefaultPageSize(10);
        list.setPageSizeOptions("[10, 20, 50, 100]");
        list.setShowPagination(true);
        list.setShowRefresh(true);
        list.setShowColumnSetting(true);
        list.setShowFullscreen(true);
        list.setShowSearchBar(true);
        list.setSearchBarPosition(1);
        list.setShowAdvancedSearch(false);
        list.setShowToolbar(true);
        list.setShowAddButton(true);
        list.setShowBatchAddButton(false);
        list.setShowEditButton(true);
        list.setShowDeleteButton(true);
        list.setShowExportButton(false);
        list.setShowImportButton(false);
        list.setStatus(1);
        list.setDeleted(false);
        list.setCreateTime(LocalDateTime.now());
        return list;
    }

    /**
     * 启用列表
     */
    public void enable() {
        this.setStatus(1);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 禁用列表
     */
    public void disable() {
        this.setStatus(0);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 删除列表
     */
    public void delete() {
        this.setDeleted(true);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 列表列配置
     */
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetaListColumn {

        /**
         * 列ID
         */
        private String id;

        /**
         * 列表配置ID
         */
        private String listId;

        /**
         * 字段编码
         */
        private String fieldCode;

        /**
         * 字段名称
         */
        private String fieldName;

        /**
         * 列类型：1-普通列 2-排序列 3-图片列 4-标签列 5-开关列 6-按钮列 7-自定义列
         */
        private Integer columnType;

        /**
         * 列宽
         */
        private Integer width;

        /**
         * 最小列宽
         */
        private Integer minWidth;

        /**
         * 对齐方式：1-左对齐 2-居中 3-右对齐
         */
        private Integer align;

        /**
         * 是否固定到左侧
         */
        private Boolean fixedLeft;

        /**
         * 是否固定到右侧
         */
        private Boolean fixedRight;

        /**
         * 是否可排序
         */
        private Boolean sortable;

        /**
         * 排序字段
         */
        private String sortField;

        /**
         * 是否可筛选
         */
        private Boolean filterable;

        /**
         * 筛选类型：1-输入框 2-下拉框 3-日期范围 4-数字范围
         */
        private Integer filterType;

        /**
         * 筛选默认值
         */
        private String filterDefaultValue;

        /**
         * 字典类型编码
         */
        private String dictTypeCode;

        /**
         * 字典数据(JSON)
         */
        private String dictData;

        /**
         * 图片预览
         */
        private Boolean imagePreview;

        /**
         * 图片宽度
         */
        private Integer imageWidth;

        /**
         * 图片高度
         */
        private Integer imageHeight;

        /**
         * 日期格式化
         */
        private String dateFormat;

        /**
         * 数字格式化
         */
        private String numberFormat;

        /**
         * 是否显示在列表中
         */
        private Boolean showInList;

        /**
         * 是否在默认列表中显示
         */
        private Boolean showInDefault;

        /**
         * 扩展配置(JSON)
         */
        private String config;

        /**
         * 排序
         */
        private Integer sortOrder;

    }

    /**
     * 列表操作按钮配置
     */
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetaListButton {

        /**
         * 按钮ID
         */
        private String id;

        /**
         * 列表配置ID
         */
        private String listId;

        /**
         * 按钮编码
         */
        private String buttonCode;

        /**
         * 按钮名称
         */
        private String buttonName;

        /**
         * 按钮类型：1-新增 2-编辑 3-删除 4-导出 5-导入 6-自定义
         */
        private Integer buttonType;

        /**
         * 按钮样式：1-主要 2-默认 3-文本 4-链接 5-成功 6-警告 7-危险 8-信息
         */
        private Integer buttonStyle;

        /**
         * 图标
         */
        private String icon;

        /**
         * 排序
         */
        private Integer sortOrder;

        /**
         * 是否显示
         */
        private Boolean visible;

        /**
         * 是否禁用
         */
        private Boolean disabled;

        /**
         * 点击事件
         */
        private String clickEvent;

        /**
         * 权限编码
         */
        private String permissionCode;

        /**
         * 扩展配置(JSON)
         */
        private String config;

    }

    /**
     * 列表查询条件配置
     */
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetaListQuery {

        /**
         * 查询条件ID
         */
        private String id;

        /**
         * 列表配置ID
         */
        private String listId;

        /**
         * 字段编码
         */
        private String fieldCode;

        /**
         * 字段名称
         */
        private String fieldName;

        /**
         * 查询方式：1-精确查询 2-模糊查询 3-范围查询 4-日期范围 5-下拉框 6-多选
         */
        private Integer queryType;

        /**
         * 控件类型：1-输入框 2-下拉框 3-日期选择 4-日期范围 5-数字范围 6-树选择
         */
        private Integer controlType;

        /**
         * 控件宽度
         */
        private Integer controlWidth;

        /**
         * 占位符
         */
        private String placeholder;

        /**
         * 默认值
         */
        private String defaultValue;

        /**
         * 是否显示在高级搜索
         */
        private Boolean showInAdvanced;

        /**
         * 是否显示在快速搜索
         */
        private Boolean showInQuick;

        /**
         * 字典类型编码
         */
        private String dictTypeCode;

        /**
         * 排序
         */
        private Integer sortOrder;

    }

}
