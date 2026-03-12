package com.ssitao.code.frame.aggregate.domain;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 聚合根基类
 * <p>
 * 所有聚合根都应该继承此基类，提供领域事件管理能力
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public abstract class AbstractAggregateRoot implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 领域事件列表
     * 使用 transient 避免序列化
     */
    private transient List<AbstractDomainEvent> domainEvents = new ArrayList<>();

    /**
     * 聚合根版本号（用于乐观锁）
     */
    private Long version;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 租户ID（多租户支持）
     */
    private String tenantId;

    /**
     * 是否删除
     */
    private Boolean deleted = false;

    /**
     * 注册领域事件
     *
     * @param event 领域事件
     */
    protected void registerEvent(AbstractDomainEvent event) {
        if (this.domainEvents == null) {
            this.domainEvents = new ArrayList<>();
        }
        this.domainEvents.add(event);
    }

    /**
     * 获取并清空领域事件
     * <p>
     * 此方法在保存聚合根时调用，获取所有待发布的事件并清空列表
     *
     * @return 领域事件列表
     */
    public List<AbstractDomainEvent> getAndClearDomainEvents() {
        if (this.domainEvents == null) {
            return new ArrayList<>();
        }
        List<AbstractDomainEvent> events = this.domainEvents;
        this.domainEvents = new ArrayList<>();
        return events;
    }

    /**
     * 获取领域事件列表（不清空）
     *
     * @return 领域事件列表
     */
    public List<AbstractDomainEvent> getDomainEvents() {
        if (this.domainEvents == null) {
            return new ArrayList<>();
        }
        return domainEvents;
    }

    /**
     * 标记为已删除
     */
    public void markAsDeleted() {
        this.deleted = true;
        this.modifyTime = LocalDateTime.now();
    }

    /**
     * 判断是否已删除
     *
     * @return true if deleted
     */
    public boolean isDeleted() {
        return this.deleted != null && this.deleted;
    }

    /**
     * 增加版本号
     */
    public void incrementVersion() {
        if (this.version == null) {
            this.version = 1L;
        } else {
            this.version++;
        }
        this.modifyTime = LocalDateTime.now();
    }
}
