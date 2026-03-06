package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 元数据实体配置DTO
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaEntityDTO {

    private String id;
    private String entityCode;
    private String entityName;
    private String tableName;
    private String entityType;
    private String category;
    private String description;
    private String packageName;
    private String moduleName;
    private String businessName;
    private String templateType;
    private Integer status;
    private String tenantId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
}
