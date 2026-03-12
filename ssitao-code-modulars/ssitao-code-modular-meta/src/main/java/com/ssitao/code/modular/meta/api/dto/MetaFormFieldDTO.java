package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 元数据表单字段配置DTO
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaFormFieldDTO {

    private String fieldId;
    private String formId;
    private String fieldCode;
    private String fieldName;
    private String fieldType;
    private Integer controlType;
    private Integer controlWidth;
    private String placeholder;
    private String defaultValue;
    private Integer required;
    private Integer editable;
    private Integer visible;
    private Integer copyable;
    private String rules;
    private String dictTypeCode;
    private String dictData;
    private String remoteUrl;
    private String labelField;
    private String valueField;
    private String groupName;
    private Integer sortOrder;
    private String config;
    private String tenantId;
}
