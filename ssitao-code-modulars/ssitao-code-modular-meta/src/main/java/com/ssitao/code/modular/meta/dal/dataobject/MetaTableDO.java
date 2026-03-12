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
 * 元数据表配置数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_table")
public class MetaTableDO {

    @Id(keyType = KeyType.None)
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

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;

    private String tenantId;
}
