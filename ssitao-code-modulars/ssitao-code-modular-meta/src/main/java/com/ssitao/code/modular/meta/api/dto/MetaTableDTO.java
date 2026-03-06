package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 元数据表配置DTO
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaTableDTO {

    private String id;
    private String tableName;
    private String tableDesc;
    private Integer tableType;
    private String packageName;
    private String moduleName;
    private String className;
    private String classDesc;
    private String entityName;
    private String author;
    private Integer enabled;
    private Integer generated;
    private String genPath;
    private String remark;
    private String tenantId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
}
