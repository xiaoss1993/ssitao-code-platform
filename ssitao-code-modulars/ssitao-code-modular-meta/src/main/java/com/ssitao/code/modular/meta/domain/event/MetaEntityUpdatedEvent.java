package com.ssitao.code.modular.meta.domain.event;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 元数据实体更新事件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MetaEntityUpdatedEvent extends AbstractDomainEvent {

    /**
     * 实体ID
     */
    private String entityId;

    /**
     * 原实体名称
     */
    private String oldEntityName;

    /**
     * 新实体名称
     */
    private String newEntityName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 构造函数
     */
    public MetaEntityUpdatedEvent(String entityId, String oldEntityName, String newEntityName, String tenantId) {
        super(entityId, tenantId);
        this.entityId = entityId;
        this.oldEntityName = oldEntityName;
        this.newEntityName = newEntityName;
        this.tenantId = tenantId;
        this.setEventType("MetaEntityUpdatedEvent");
        this.setAggregateType("MetaEntity");
    }
}
