package com.ssitao.code.frame.aggregate.eventstore;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;

import java.util.List;

/**
 * 事件存储接口
 * <p>
 * 负责持久化领域事件，支持事件溯源
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface EventStore {

    /**
     * 保存事件
     *
     * @param events 事件列表
     */
    void save(List<AbstractDomainEvent> events);

    /**
     * 保存单个事件
     *
     * @param event 事件
     */
    void save(AbstractDomainEvent event);

    /**
     * 根据聚合根ID获取事件流
     *
     * @param aggregateId 聚合根ID
     * @return 事件列表
     */
    List<AbstractDomainEvent> getEvents(String aggregateId);

    /**
     * 根据聚合根ID和租户ID获取事件流
     *
     * @param aggregateId 聚合根ID
     * @param tenantId    租户ID
     * @return 事件列表
     */
    List<AbstractDomainEvent> getEvents(String aggregateId, String tenantId);

    /**
     * 根据聚合根ID和版本范围获取事件流
     *
     * @param aggregateId 聚合根ID
     * @param fromVersion 起始版本（包含）
     * @param toVersion   结束版本（包含）
     * @return 事件列表
     */
    List<AbstractDomainEvent> getEvents(String aggregateId, Long fromVersion, Long toVersion);

    /**
     * 根据事件类型获取事件
     *
     * @param eventType 事件类型
     * @return 事件列表
     */
    List<AbstractDomainEvent> getEventsByType(String eventType);
}
