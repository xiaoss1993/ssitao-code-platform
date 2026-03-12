package com.ssitao.code.modular.iam.organization.infrastructure.event;

import com.ssitao.code.frame.aggregate.annotation.EventHandler;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentChildAddedEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentCreatedEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentDeletedEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentDisabledEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentEnabledEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentLeaderUpdatedEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentUpdatedEvent;
import com.ssitao.code.modular.iam.organization.domain.repository.IamDepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门领域事件处理器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DepartmentEventHandler {

    private final IamDepartmentRepository departmentRepository;

    /**
     * 处理部门创建事件
     */
    @EventHandler
    public void handle(DepartmentCreatedEvent event) {
        log.info("处理部门创建事件 - 部门ID: {}, 部门编码: {}, 部门名称: {}, 租户ID: {}",
                event.getDeptId(), event.getDeptCode(), event.getDeptName(), event.getTenantId());

        // 1. 记录审计日志
        recordAuditLog(event.getTenantId(), "DEPARTMENT_CREATE",
                "创建部门: " + event.getDeptName() + "(" + event.getDeptCode() + ")");

        log.info("部门创建事件处理完成 - 部门ID: {}", event.getDeptId());
    }

    /**
     * 处理部门更新事件
     */
    @EventHandler
    public void handle(DepartmentUpdatedEvent event) {
        log.info("处理部门更新事件 - 部门ID: {}, 原名称: {}, 新名称: {}, 租户ID: {}",
                event.getDeptId(), event.getOldDeptName(), event.getNewDeptName(), event.getTenantId());

        // 1. 记录变更历史
        String changeInfo = buildChangeInfo(event);
        recordAuditLog(event.getTenantId(), "DEPARTMENT_UPDATE",
                "更新部门: " + event.getNewDeptName() + ", 变更内容: " + changeInfo);

        log.info("部门更新事件处理完成 - 部门ID: {}", event.getDeptId());
    }

    /**
     * 处理部门删除事件
     */
    @EventHandler
    public void handle(DepartmentDeletedEvent event) {
        log.info("处理部门删除事件 - 部门ID: {}, 部门编码: {}, 部门名称: {}, 租户ID: {}",
                event.getDeptId(), event.getDeptCode(), event.getDeptName(), event.getTenantId());

        // 1. 检查是否存在子部门
        List<?> childDepts = departmentRepository.findByParentId(event.getDeptId(), event.getTenantId());
        if (childDepts != null && !childDepts.isEmpty()) {
            log.warn("部门存在子部门，删除前需先处理子部门 - 部门ID: {}, 子部门数量: {}",
                    event.getDeptId(), childDepts.size());
        }

        // 2. 记录删除日志
        recordAuditLog(event.getTenantId(), "DEPARTMENT_DELETE",
                "删除部门: " + event.getDeptName() + "(" + event.getDeptCode() + ")");

        log.info("部门删除事件处理完成 - 部门ID: {}", event.getDeptId());
    }

    /**
     * 处理部门启用事件
     */
    @EventHandler
    public void handle(DepartmentEnabledEvent event) {
        log.info("处理部门启用事件 - 部门ID: {}, 部门编码: {}, 部门名称: {}, 租户ID: {}",
                event.getDeptId(), event.getDeptCode(), event.getDeptName(), event.getTenantId());

        recordAuditLog(event.getTenantId(), "DEPARTMENT_ENABLE",
                "启用部门: " + event.getDeptName() + "(" + event.getDeptCode() + ")");

        log.info("部门启用事件处理完成 - 部门ID: {}", event.getDeptId());
    }

    /**
     * 处理部门禁用事件
     */
    @EventHandler
    public void handle(DepartmentDisabledEvent event) {
        log.info("处理部门禁用事件 - 部门ID: {}, 部门编码: {}, 部门名称: {}, 租户ID: {}",
                event.getDeptId(), event.getDeptCode(), event.getDeptName(), event.getTenantId());

        recordAuditLog(event.getTenantId(), "DEPARTMENT_DISABLE",
                "禁用部门: " + event.getDeptName() + "(" + event.getDeptCode() + ")");

        log.info("部门禁用事件处理完成 - 部门ID: {}", event.getDeptId());
    }

    /**
     * 处理子部门添加事件
     */
    @EventHandler
    public void handle(DepartmentChildAddedEvent event) {
        log.info("处理子部门添加事件 - 父部门ID: {}, 子部门ID: {}, 租户ID: {}",
                event.getParentDeptId(), event.getChildDeptId(), event.getTenantId());

        recordAuditLog(event.getTenantId(), "DEPARTMENT_CHILD_ADD",
                "添加子部门: 父部门ID=" + event.getParentDeptId() + ", 子部门ID=" + event.getChildDeptId());

        log.info("子部门添加事件处理完成 - 父部门ID: {}, 子部门ID: {}",
                event.getParentDeptId(), event.getChildDeptId());
    }

    /**
     * 处理负责人更新事件
     */
    @EventHandler
    public void handle(DepartmentLeaderUpdatedEvent event) {
        log.info("处理负责人更新事件 - 部门ID: {}, 原负责人: {}, 新负责人: {}, 新负责人姓名: {}",
                event.getDeptId(), event.getOldLeaderId(), event.getNewLeaderId(), event.getNewLeaderName());

        String changeInfo = String.format("原负责人ID: %s -> 新负责人ID: %s (姓名: %s)",
                event.getOldLeaderId(), event.getNewLeaderId(), event.getNewLeaderName());
        recordAuditLog(event.getTenantId(), "DEPARTMENT_LEADER_UPDATE",
                "更新部门负责人: 部门ID=" + event.getDeptId() + ", " + changeInfo);

        log.info("负责人更新事件处理完成 - 部门ID: {}", event.getDeptId());
    }

    // ==================== 辅助方法 ====================

    /**
     * 构建变更信息
     */
    private String buildChangeInfo(DepartmentUpdatedEvent event) {
        StringBuilder sb = new StringBuilder();
        if (event.getOldDeptName() != null && event.getNewDeptName() != null
                && !event.getOldDeptName().equals(event.getNewDeptName())) {
            sb.append("部门名称:").append(event.getOldDeptName()).append("->").append(event.getNewDeptName());
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
