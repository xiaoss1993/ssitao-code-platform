package com.ssitao.code.modular.meta.infrastructure.event;

import com.ssitao.code.frame.aggregate.annotation.EventHandler;
import com.ssitao.code.modular.meta.domain.event.MetaEntityCreatedEvent;
import com.ssitao.code.modular.meta.domain.event.MetaEntityDeletedEvent;
import com.ssitao.code.modular.meta.domain.event.MetaEntityUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 元数据实体领域事件处理器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@Component
public class MetaEntityEventHandler {

    /**
     * 处理实体创建事件
     */
    @EventHandler
    public void handle(MetaEntityCreatedEvent event) {
        log.info("处理实体创建事件 - entityId: {}, entityCode: {}, entityName: {}, tenantId: {}",
                event.getEntityId(), event.getEntityCode(), event.getEntityName(), event.getTenantId());

        // 1. 记录审计日志
        recordAuditLog(event.getTenantId(), "META_ENTITY_CREATE",
                "创建实体: " + event.getEntityName() + "(" + event.getEntityCode() + ")");

        // 2. 可以在这里添加其他业务逻辑：
        // - 同步创建物理表
        // - 初始化默认配置
        // - 发送通知

        log.info("实体创建事件处理完成 - entityId: {}", event.getEntityId());
    }

    /**
     * 处理实体更新事件
     */
    @EventHandler
    public void handle(MetaEntityUpdatedEvent event) {
        log.info("处理实体更新事件 - entityId: {}, oldName: {}, newName: {}, tenantId: {}",
                event.getEntityId(), event.getOldEntityName(), event.getNewEntityName(), event.getTenantId());

        // 1. 记录变更日志
        String changeInfo = buildChangeInfo(event);
        recordAuditLog(event.getTenantId(), "META_ENTITY_UPDATE",
                "更新实体: " + event.getNewEntityName() + ", 变更内容: " + changeInfo);

        // 2. 可以在这里添加其他业务逻辑：
        // - 同步更新物理表结构
        // - 清除相关缓存

        log.info("实体更新事件处理完成 - entityId: {}", event.getEntityId());
    }

    /**
     * 处理实体删除事件
     */
    @EventHandler
    public void handle(MetaEntityDeletedEvent event) {
        log.info("处理实体删除事件 - entityId: {}, entityCode: {}, entityName: {}, tenantId: {}",
                event.getEntityId(), event.getEntityCode(), event.getEntityName(), event.getTenantId());

        // 1. 记录删除日志
        recordAuditLog(event.getTenantId(), "META_ENTITY_DELETE",
                "删除实体: " + event.getEntityName() + "(" + event.getEntityCode() + ")");

        // 2. 可以在这里添加其他业务逻辑：
        // - 删除物理表
        // - 清除相关缓存

        log.info("实体删除事件处理完成 - entityId: {}", event.getEntityId());
    }

    // ==================== 辅助方法 ====================

    /**
     * 构建变更信息
     */
    private String buildChangeInfo(MetaEntityUpdatedEvent event) {
        StringBuilder sb = new StringBuilder();
        if (event.getOldEntityName() != null && event.getNewEntityName() != null
                && !event.getOldEntityName().equals(event.getNewEntityName())) {
            sb.append("实体名称:").append(event.getOldEntityName()).append("->").append(event.getNewEntityName());
        }
        return sb.length() > 0 ? sb.toString() : "无变更";
    }

    /**
     * 记录审计日志
     */
    private void recordAuditLog(String tenantId, String operation, String detail) {
        log.debug("审计日志 - 租户:{}, 操作:{}, 详情:{}, 时间:{}",
                tenantId, operation, detail, LocalDateTime.now());
    }
}
