package com.ssitao.code.modular.meta.domain.event;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 元数据实体删除事件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MetaEntityDeletedEvent extends AbstractDomainEvent {

    /**
     * 实体ID
     */
    private String entityId;

    /**
     * 实体编码
     */
    private String entityCode;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 构造函数
     */
    public MetaEntityDeletedEvent(String entityId, String entityCode, String entityName, String tenantId) {
        super(entityId, tenantId);
        this.entityId = entityId;
        this.entityCode = entityCode;
        this.entityName = entityName;
        this.tenantId = tenantId;
        this.setEventType("MetaEntityDeletedEvent");
        this.setAggregateType("MetaEntity");
    }
}
