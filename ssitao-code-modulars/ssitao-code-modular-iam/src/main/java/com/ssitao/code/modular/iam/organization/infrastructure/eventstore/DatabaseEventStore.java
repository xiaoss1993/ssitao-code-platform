package com.ssitao.code.modular.iam.organization.infrastructure.eventstore;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import com.ssitao.code.frame.aggregate.eventstore.EventStore;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.organization.infrastructure.eventstore.dataobject.EventStoreDO;
import com.ssitao.code.modular.iam.organization.infrastructure.eventstore.mapper.EventStoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库事件存储实现
 * <p>
 * 将领域事件持久化到数据库，支持事件溯源
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@Repository
public class DatabaseEventStore implements EventStore {

    @Resource
    private EventStoreMapper eventStoreMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(List<AbstractDomainEvent> events) {
        if (events == null || events.isEmpty()) {
            return;
        }

        for (AbstractDomainEvent event : events) {
            EventStoreDO eventDO = convertToDO(event);
            eventStoreMapper.insert(eventDO);
        }

        log.debug("保存 {} 个领域事件到数据库", events.size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(AbstractDomainEvent event) {
        if (event == null) {
            return;
        }

        EventStoreDO eventDO = convertToDO(event);
        eventStoreMapper.insert(eventDO);

        log.debug("保存领域事件到数据库 - 事件ID: {}, 事件类型: {}",
                event.getEventId(), event.getEventType());
    }

    @Override
    public List<AbstractDomainEvent> getEvents(String aggregateId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("aggregate_id", aggregateId)
                .orderBy("occurred_time", true);

        List<EventStoreDO> eventDOs = eventStoreMapper.selectListByQuery(query);
        return convertToEvents(eventDOs);
    }

    @Override
    public List<AbstractDomainEvent> getEvents(String aggregateId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("aggregate_id", aggregateId)
                .eq("tenant_id", tenantId)
                .orderBy("occurred_time", true);

        List<EventStoreDO> eventDOs = eventStoreMapper.selectListByQuery(query);
        return convertToEvents(eventDOs);
    }

    @Override
    public List<AbstractDomainEvent> getEvents(String aggregateId, Long fromVersion, Long toVersion) {
        QueryWrapper query = QueryWrapper.create()
                .eq("aggregate_id", aggregateId)
                .ge("version", fromVersion)
                .le("version", toVersion)
                .orderBy("version", true);

        List<EventStoreDO> eventDOs = eventStoreMapper.selectListByQuery(query);
        return convertToEvents(eventDOs);
    }

    @Override
    public List<AbstractDomainEvent> getEventsByType(String eventType) {
        QueryWrapper query = QueryWrapper.create()
                .eq("event_type", eventType)
                .orderBy("occurred_time", true);

        List<EventStoreDO> eventDOs = eventStoreMapper.selectListByQuery(query);
        return convertToEvents(eventDOs);
    }

    /**
     * 转换为事件DO
     */
    private EventStoreDO convertToDO(AbstractDomainEvent event) {
        EventStoreDO eventDO = new EventStoreDO();
        eventDO.setEventId(event.getEventId());
        eventDO.setAggregateId(event.getAggregateId());
        eventDO.setAggregateType(event.getAggregateType());
        eventDO.setEventType(event.getEventType());
        eventDO.setEventData(toJson(event));
        eventDO.setVersion(event.getVersion());
        eventDO.setOccurredTime(event.getOccurredTime());
        eventDO.setTenantId(event.getTenantId());
        eventDO.setSource(event.getSource());
        return eventDO;
    }

    /**
     * 转换为领域事件列表
     */
    private List<AbstractDomainEvent> convertToEvents(List<EventStoreDO> eventDOs) {
        List<AbstractDomainEvent> events = new ArrayList<>();
        for (EventStoreDO eventDO : eventDOs) {
            events.add(convertToEvent(eventDO));
        }
        return events;
    }

    /**
     * 转换为领域事件
     */
    private AbstractDomainEvent convertToEvent(EventStoreDO eventDO) {
        return fromJson(eventDO.getEventData(), AbstractDomainEvent.class);
    }

    /**
     * 对象转JSON（简化实现）
     */
    private String toJson(Object obj) {
        // TODO: 使用 Jackson 或 FastJSON 实现序列化
        return com.alibaba.fastjson.JSON.toJSONString(obj);
    }

    /**
     * JSON转对象（简化实现）
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        // TODO: 使用 Jackson 或 FastJSON 实现反序列化
        return com.alibaba.fastjson.JSON.parseObject(json, clazz);
    }
}
