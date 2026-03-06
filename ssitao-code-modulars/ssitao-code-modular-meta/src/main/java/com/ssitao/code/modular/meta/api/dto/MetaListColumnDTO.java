package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 元数据列表列配置DTO
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaListColumnDTO {

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
}
