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
 * 元数据表字段数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_column")
public class MetaColumnDO {

    @Id(keyType = KeyType.AssignId)
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

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;
}
