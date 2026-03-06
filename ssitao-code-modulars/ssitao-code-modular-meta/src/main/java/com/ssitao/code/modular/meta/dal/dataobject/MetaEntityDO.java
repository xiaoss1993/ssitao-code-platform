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
 * 元数据实体配置数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_entity")
public class MetaEntityDO {

    @Id(keyType = KeyType.None)
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

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;

    private String tenantId;
}
