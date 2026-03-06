package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 元数据列表按钮配置DTO
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaListButtonDTO {

    private String buttonId;
    private String listId;
    private String buttonCode;
    private String buttonName;
    private Integer buttonType;
    private Integer buttonStyle;
    private String icon;
    private Integer sortOrder;
    private Integer visible;
    private Integer disabled;
    private String clickEvent;
    private String permissionCode;
    private String config;
    private String tenantId;
}
