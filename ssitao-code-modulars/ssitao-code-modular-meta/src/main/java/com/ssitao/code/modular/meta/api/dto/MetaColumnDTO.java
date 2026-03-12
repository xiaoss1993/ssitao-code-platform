package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 元数据表字段DTO
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaColumnDTO {

    private String columnId;
    private String tableId;
    private String columnName;
    private String columnDesc;
    private String columnType;
    private String javaType;
    private String javaField;
    private Integer isPk;
    private Integer isIncrement;
    private Integer isRequired;
    private Integer isQuery;
    private Integer queryType;
    private Integer isDisplay;
    private Integer isList;
    private Integer isForm;
    private Integer formType;
    private String dictType;
    private String defaultValue;
    private Integer columnSort;
    private String remark;
    private String tenantId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
}
