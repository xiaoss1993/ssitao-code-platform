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
 * 元数据列表查询条件配置数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_list_query")
public class MetaListQueryDO {

    @Id(keyType = KeyType.None)
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

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;
}
