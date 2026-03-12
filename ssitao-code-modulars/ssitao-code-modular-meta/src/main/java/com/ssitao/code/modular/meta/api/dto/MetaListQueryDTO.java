package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 元数据列表查询条件配置DTO
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaListQueryDTO {

    private String queryId;
    private String listId;
    private String fieldCode;
    private String fieldName;
    private Integer queryType;
    private Integer controlType;
    private Integer controlWidth;
    private String placeholder;
    private String defaultValue;
    private Integer showInAdvanced;
    private Integer showInQuick;
    private String dictTypeCode;
    private Integer sortOrder;
    private String tenantId;
}
