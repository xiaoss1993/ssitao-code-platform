package com.ssitao.code.modular.meta.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 元数据列表配置数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_list")
public class MetaListDO {

    @Id(keyType = KeyType.None)
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

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;
}
