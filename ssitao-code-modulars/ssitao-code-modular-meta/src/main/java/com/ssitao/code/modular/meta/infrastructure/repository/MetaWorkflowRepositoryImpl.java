package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaWorkflowDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaProcessInstanceDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaTaskInstanceDO;
import com.ssitao.code.modular.meta.domain.repository.MetaWorkflowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 工作流仓储实现（内存存储）
 * TODO: 后续需要实现持久化到数据库
 *
 * @author ssitao-code
 */
@Slf4j
@Repository
public class MetaWorkflowRepositoryImpl implements MetaWorkflowRepository {

    /**
     * 流程定义存储（内存）
     * key: tenantId_workflowId
     */
    private final Map<String, MetaWorkflowDO> workflowStore = new ConcurrentHashMap<>();

    /**
     * 流程实例存储（内存）
     * key: tenantId_processInstanceId
     */
    private final Map<String, MetaProcessInstanceDO> processInstanceStore = new ConcurrentHashMap<>();

    /**
     * 任务实例存储（内存）
     * key: tenantId_taskInstanceId
     */
    private final Map<String, MetaTaskInstanceDO> taskInstanceStore = new ConcurrentHashMap<>();

    // ==================== Workflow 操作 ====================

    @Override
    public String saveWorkflow(MetaWorkflowDO workflow) {
        String key = getWorkflowKey(workflow.getTenantId(), workflow.getId());
        workflowStore.put(key, workflow);
        log.info("保存流程定义: workflowCode={}, tenantId={}", workflow.getWorkflowCode(), workflow.getTenantId());
        return workflow.getId();
    }

    @Override
    public void updateWorkflow(MetaWorkflowDO workflow) {
        String key = getWorkflowKey(workflow.getTenantId(), workflow.getId());
        workflowStore.put(key, workflow);
        log.info("更新流程定义: workflowCode={}, tenantId={}", workflow.getWorkflowCode(), workflow.getTenantId());
    }

    @Override
    public void deleteWorkflow(String id, String tenantId) {
        workflowStore.entrySet().removeIf(entry -> {
            MetaWorkflowDO workflow = entry.getValue();
            return workflow.getId().equals(id) &&
                    (tenantId == null || tenantId.equals(workflow.getTenantId()));
        });
        log.info("删除流程定义: id={}, tenantId={}", id, tenantId);
    }

    @Override
    public MetaWorkflowDO findWorkflowById(String id, String tenantId) {
        return workflowStore.values().stream()
                .filter(w -> w.getId().equals(id) &&
                        (tenantId == null || tenantId.equals(w.getTenantId())))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MetaWorkflowDO findWorkflowByCode(String workflowCode, String tenantId) {
        return workflowStore.values().stream()
                .filter(w -> workflowCode.equals(w.getWorkflowCode()) &&
                        (tenantId == null || tenantId.equals(w.getTenantId())))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MetaWorkflowDO> findWorkflows(String tenantId) {
        return workflowStore.values().stream()
                .filter(w -> tenantId == null || tenantId.equals(w.getTenantId()))
                .collect(Collectors.toList());
    }

    // ==================== Process Instance 操作 ====================

    @Override
    public String saveProcessInstance(MetaProcessInstanceDO processInstance) {
        String key = getProcessInstanceKey(processInstance.getTenantId(), processInstance.getId());
        processInstanceStore.put(key, processInstance);
        log.info("保存流程实例: processInstanceId={}, tenantId={}", processInstance.getId(), processInstance.getTenantId());
        return processInstance.getId();
    }

    @Override
    public void updateProcessInstance(MetaProcessInstanceDO processInstance) {
        String key = getProcessInstanceKey(processInstance.getTenantId(), processInstance.getId());
        processInstanceStore.put(key, processInstance);
        log.info("更新流程实例: processInstanceId={}, tenantId={}", processInstance.getId(), processInstance.getTenantId());
    }

    @Override
    public void deleteProcessInstance(String id, String tenantId) {
        // 删除流程实例
        processInstanceStore.entrySet().removeIf(entry -> {
            MetaProcessInstanceDO processInstance = entry.getValue();
            boolean match = processInstance.getId().equals(id) &&
                    (tenantId == null || tenantId.equals(processInstance.getTenantId()));
            if (match) {
                // 删除关联的任务实例
                deleteTaskInstancesByProcessId(processInstance.getId(), tenantId);
            }
            return match;
        });
        log.info("删除流程实例: id={}, tenantId={}", id, tenantId);
    }

    @Override
    public MetaProcessInstanceDO findProcessInstanceById(String id, String tenantId) {
        return processInstanceStore.values().stream()
                .filter(p -> p.getId().equals(id) &&
                        (tenantId == null || tenantId.equals(p.getTenantId())))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MetaProcessInstanceDO findProcessInstanceByBusinessKey(String businessKey, String tenantId) {
        return processInstanceStore.values().stream()
                .filter(p -> businessKey.equals(p.getBusinessKey()) &&
                        (tenantId == null || tenantId.equals(p.getTenantId())))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MetaProcessInstanceDO> findProcessInstancesByWorkflowId(String workflowId, String tenantId) {
        return processInstanceStore.values().stream()
                .filter(p -> workflowId.equals(p.getWorkflowId()) &&
                        (tenantId == null || tenantId.equals(p.getTenantId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaProcessInstanceDO> findProcessInstancesByInitiator(String initiator, String tenantId) {
        return processInstanceStore.values().stream()
                .filter(p -> initiator.equals(p.getInitiator()) &&
                        (tenantId == null || tenantId.equals(p.getTenantId())))
                .collect(Collectors.toList());
    }

    // ==================== Task Instance 操作 ====================

    @Override
    public String saveTaskInstance(MetaTaskInstanceDO taskInstance) {
        String key = getTaskInstanceKey(taskInstance.getTenantId(), taskInstance.getId());
        taskInstanceStore.put(key, taskInstance);
        log.info("保存任务实例: taskInstanceId={}, tenantId={}", taskInstance.getId(), taskInstance.getTenantId());
        return taskInstance.getId();
    }

    @Override
    public void updateTaskInstance(MetaTaskInstanceDO taskInstance) {
        String key = getTaskInstanceKey(taskInstance.getTenantId(), taskInstance.getId());
        taskInstanceStore.put(key, taskInstance);
        log.info("更新任务实例: taskInstanceId={}, tenantId={}", taskInstance.getId(), taskInstance.getTenantId());
    }

    @Override
    public void deleteTaskInstance(String id, String tenantId) {
        taskInstanceStore.entrySet().removeIf(entry -> {
            MetaTaskInstanceDO taskInstance = entry.getValue();
            return taskInstance.getId().equals(id) &&
                    (tenantId == null || tenantId.equals(taskInstance.getTenantId()));
        });
        log.info("删除任务实例: id={}", id);
    }

    private void deleteTaskInstancesByProcessId(String processId, String tenantId) {
        taskInstanceStore.entrySet().removeIf(entry -> {
            MetaTaskInstanceDO taskInstance = entry.getValue();
            return taskInstance.getProcessId().equals(processId) &&
                    (tenantId == null || tenantId.equals(taskInstance.getTenantId()));
        });
        log.info("删除流程实例所有任务: processId={}", processId);
    }

    @Override
    public MetaTaskInstanceDO findTaskInstanceById(String id, String tenantId) {
        return taskInstanceStore.values().stream()
                .filter(t -> t.getId().equals(id) &&
                        (tenantId == null || tenantId.equals(t.getTenantId())))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MetaTaskInstanceDO> findTaskInstancesByProcessId(String processId, String tenantId) {
        return taskInstanceStore.values().stream()
                .filter(t -> processId.equals(t.getProcessId()) &&
                        (tenantId == null || tenantId.equals(t.getTenantId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaTaskInstanceDO> findTodoTasks(String assignee, String tenantId) {
        return taskInstanceStore.values().stream()
                .filter(t -> assignee.equals(t.getAssignee()) &&
                        (tenantId == null || tenantId.equals(t.getTenantId())) &&
                        "PENDING".equals(t.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaTaskInstanceDO> findDoneTasks(String assignee, String tenantId) {
        return taskInstanceStore.values().stream()
                .filter(t -> assignee.equals(t.getAssignee()) &&
                        (tenantId == null || tenantId.equals(t.getTenantId())) &&
                        ("COMPLETED".equals(t.getStatus()) || "REJECTED".equals(t.getStatus())))
                .collect(Collectors.toList());
    }

    // ==================== Key 生成方法 ====================

    private String getWorkflowKey(String tenantId, String workflowId) {
        return (tenantId == null ? "default" : tenantId) + "_" + workflowId;
    }

    private String getProcessInstanceKey(String tenantId, String processInstanceId) {
        return (tenantId == null ? "default" : tenantId) + "_" + processInstanceId;
    }

    private String getTaskInstanceKey(String tenantId, String taskInstanceId) {
        return (tenantId == null ? "default" : tenantId) + "_" + taskInstanceId;
    }
}
