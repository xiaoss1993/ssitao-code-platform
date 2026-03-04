package com.ssitao.code.modular.iam.organization.infrastructure.eventstore.dataobject;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 事件存储数据对象
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class EventStoreDO {

    /**
     * 事件ID
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
     * 事件数据（JSON格式）
     */
    private String eventData;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 事件发生时间
     */
    private LocalDateTime occurredTime;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 事件来源
     */
    private String source;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
