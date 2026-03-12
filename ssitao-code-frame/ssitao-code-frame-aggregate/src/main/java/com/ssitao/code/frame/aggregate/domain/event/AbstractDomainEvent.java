package com.ssitao.code.frame.aggregate.domain.event;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 领域事件基类
 * <p>
 * 所有领域事件都应该继承此基类，提供统一的字段定义
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public abstract class AbstractDomainEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事件唯一标识
     */
    private String eventId;

    /**
     * 聚合根ID
     */
    private String aggregateId;

    /**
     * 聚合根类型
     */
    private String aggregateType;

    /**
     * 事件类型
     */
    private String eventType;

    /**
     * 事件发生时间
     */
    private LocalDateTime occurredTime;

    /**
     * 事件版本号
     */
    private Long version;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 事件来源
     */
    private String source;

    /**
     * 构造函数
     */
    public AbstractDomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.occurredTime = LocalDateTime.now();
    }

    /**
     * 构造函数
     *
     * @param aggregateId 聚合根ID
     */
    public AbstractDomainEvent(String aggregateId) {
        this();
        this.aggregateId = aggregateId;
        this.aggregateType = this.getClass().getSimpleName();
        this.eventType = this.getClass().getSimpleName();
    }

    /**
     * 构造函数
     *
     * @param aggregateId 聚合根ID
     * @param tenantId    租户ID
     */
    public AbstractDomainEvent(String aggregateId, String tenantId) {
        this(aggregateId);
        this.tenantId = tenantId;
    }
}
