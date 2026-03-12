package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 元数据列表配置DTO
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaListDTO {

    private String listId;
    private String entityId;
    private String listCode;
    private String listName;
    private Integer listType;
    private Integer rowStyle;
    private Integer stripe;
    private Integer border;
    private Integer showHeader;
    private Integer highlightCurrentRow;
    private Integer sortMode;
    private String defaultSortField;
    private String defaultSortOrder;
    private Integer defaultPageSize;
    private String pageSizeOptions;
    private Integer showPagination;
    private Integer showRefresh;
    private Integer showColumnSetting;
    private Integer showFullscreen;
    private Integer showSearchBar;
    private Integer searchBarPosition;
    private Integer showAdvancedSearch;
    private Integer showToolbar;
    private Integer showAddButton;
    private Integer showBatchAddButton;
    private Integer showEditButton;
    private Integer showDeleteButton;
    private Integer showExportButton;
    private Integer showImportButton;
    private String config;
    private String remark;
    private Integer status;
    private String tenantId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    // 关联的配置
    private List<MetaListColumnDTO> columns;
    private List<MetaListButtonDTO> buttons;
    private List<MetaListQueryDTO> queries;
}
