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
 * 元数据列表列配置数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_list_column")
public class MetaListColumnDO {

    @Id(keyType = KeyType.None)
    private String columnId;

    private String listId;

    private String fieldCode;

    private String fieldName;

    private Integer columnType;

    private Integer width;

    private Integer minWidth;

    private Integer align;

    private Integer fixedLeft;

    private Integer fixedRight;

    private Integer sortable;

    private String sortField;

    private Integer filterable;

    private Integer filterType;

    private String filterDefaultValue;

    private String dictTypeCode;

    private String dictData;

    private Integer imagePreview;

    private Integer imageWidth;

    private Integer imageHeight;

    private String dateFormat;

    private String numberFormat;

    private Integer showInList;

    private Integer showInDefault;

    private String config;

    private Integer sortOrder;

    private String tenantId;

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;
}
