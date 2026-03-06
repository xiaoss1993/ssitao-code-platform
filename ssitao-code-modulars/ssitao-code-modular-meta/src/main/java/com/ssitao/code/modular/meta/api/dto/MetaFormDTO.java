package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 元数据表单 * @author ss配置DTO
 *
itao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaFormDTO {

    private String formId;
    private String entityId;
    private String formCode;
    private String formName;
    private Integer formType;
    private Integer layout;
    private Integer width;
    private Integer labelPosition;
    private Integer labelWidth;
    private Integer showButtons;
    private Integer showResetButton;
    private Integer showCancelButton;
    private String submitButtonText;
    private String resetButtonText;
    private String cancelButtonText;
    private Integer submitAction;
    private String redirectPath;
    private String config;
    private String remark;
    private Integer status;
    private String tenantId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    // 关联的字段配置列表
    private List<MetaFormFieldDTO> fields;
}
