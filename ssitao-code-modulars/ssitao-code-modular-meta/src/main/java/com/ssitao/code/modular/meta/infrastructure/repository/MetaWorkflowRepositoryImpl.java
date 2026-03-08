package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.meta.dal.dataobject.MetaProcessInstanceDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaTaskInstanceDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaWorkflowDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaProcessInstanceMapper;
import com.ssitao.code.modular.meta.dal.mapper.MetaTaskInstanceMapper;
import com.ssitao.code.modular.meta.dal.mapper.MetaWorkflowMapper;
import com.ssitao.code.modular.meta.domain.repository.MetaWorkflowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 工作流仓储实现（数据库持久化）
 *
 * @author ssitao-code
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class MetaWorkflowRepositoryImpl implements MetaWorkflowRepository {

    private final MetaWorkflowMapper workflowMapper;
    private final MetaProcessInstanceMapper processInstanceMapper;
    private final MetaTaskInstanceMapper taskInstanceMapper;

    // ==================== Workflow 操作 ====================

    @Override
    public String saveWorkflow(MetaWorkflowDO workflow) {
        if (workflow.getId() == null || workflow.getId().isEmpty()) {
            workflow.setId(UUID.randomUUID().toString().replace("-", ""));
        }
        workflow.setCreateTime(LocalDateTime.now());
        workflow.setUpdateTime(LocalDateTime.now());
        workflow.setDeleted(0);
        workflowMapper.insert(workflow);
        log.info("保存流程定义: workflowCode={}, tenantId={}", workflow.getWorkflowCode(), workflow.getTenantId());
        return workflow.getId();
    }

    @Override
    public void updateWorkflow(MetaWorkflowDO workflow) {
        workflow.setUpdateTime(LocalDateTime.now());
        workflowMapper.update(workflow);
        log.info("更新流程定义: workflowCode={}, tenantId={}", workflow.getWorkflowCode(), workflow.getTenantId());
    }

    @Override
    public void deleteWorkflow(String id, String tenantId) {
        // 逻辑删除
        MetaWorkflowDO workflow = new MetaWorkflowDO();
        workflow.setId(id);
        workflow.setDeleted(1);
        workflow.setUpdateTime(LocalDateTime.now());
        workflowMapper.update(workflow);
        log.info("删除流程定义: id={}, tenantId={}", id, tenantId);
    }

    @Override
    public MetaWorkflowDO findWorkflowById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("id", id)
                .eq("tenant_id", tenantId)
                .eq("deleted", 0);
        return workflowMapper.selectOneByQuery(query);
    }

    @Override
    public MetaWorkflowDO findWorkflowByCode(String workflowCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("workflow_code", workflowCode)
                .eq("tenant_id", tenantId)
                .eq("deleted", 0);
        return workflowMapper.selectOneByQuery(query);
    }

    @Override
    public List<MetaWorkflowDO> findWorkflows(String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tenant_id", tenantId)
                .eq("deleted", 0);
        return workflowMapper.selectListByQuery(query);
    }

    // ==================== Process Instance 操作 ====================

    @Override
    public String saveProcessInstance(MetaProcessInstanceDO processInstance) {
        if (processInstance.getId() == null || processInstance.getId().isEmpty()) {
            processInstance.setId(UUID.randomUUID().toString().replace("-", ""));
        }
        processInstance.setCreateTime(LocalDateTime.now());
        processInstance.setUpdateTime(LocalDateTime.now());
        processInstance.setDeleted(0);
        processInstanceMapper.insert(processInstance);
        log.info("保存流程实例: processInstanceId={}, tenantId={}", processInstance.getId(), processInstance.getTenantId());
        return processInstance.getId();
    }

    @Override
    public void updateProcessInstance(MetaProcessInstanceDO processInstance) {
        processInstance.setUpdateTime(LocalDateTime.now());
        processInstanceMapper.update(processInstance);
        log.info("更新流程实例: processInstanceId={}, tenantId={}", processInstance.getId(), processInstance.getTenantId());
    }

    @Override
    public void deleteProcessInstance(String id, String tenantId) {
        // 逻辑删除流程实例
        MetaProcessInstanceDO processInstance = new MetaProcessInstanceDO();
        processInstance.setId(id);
        processInstance.setDeleted(1);
        processInstance.setUpdateTime(LocalDateTime.now());
        processInstanceMapper.update(processInstance);

        // 逻辑删除关联的任务实例
        QueryWrapper taskQuery = QueryWrapper.create()
                .eq("process_id", id)
                .eq("tenant_id", tenantId);
        List<MetaTaskInstanceDO> tasks = taskInstanceMapper.selectListByQuery(taskQuery);
        for (MetaTaskInstanceDO task : tasks) {
            task.setDeleted(1);
            task.setUpdateTime(LocalDateTime.now());
            taskInstanceMapper.update(task);
        }

        log.info("删除流程实例: id={}, tenantId={}", id, tenantId);
    }

    @Override
    public MetaProcessInstanceDO findProcessInstanceById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("id", id)
                .eq("tenant_id", tenantId)
                .eq("deleted", 0);
        return processInstanceMapper.selectOneByQuery(query);
    }

    @Override
    public MetaProcessInstanceDO findProcessInstanceByBusinessKey(String businessKey, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("business_key", businessKey)
                .eq("tenant_id", tenantId)
                .eq("deleted", 0);
        return processInstanceMapper.selectOneByQuery(query);
    }

    @Override
    public List<MetaProcessInstanceDO> findProcessInstancesByWorkflowId(String workflowId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("workflow_id", workflowId)
                .eq("tenant_id", tenantId)
                .eq("deleted", 0);
        return processInstanceMapper.selectListByQuery(query);
    }

    @Override
    public List<MetaProcessInstanceDO> findProcessInstancesByInitiator(String initiator, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("initiator", initiator)
                .eq("tenant_id", tenantId)
                .eq("deleted", 0);
        return processInstanceMapper.selectListByQuery(query);
    }

    // ==================== Task Instance 操作 ====================

    @Override
    public String saveTaskInstance(MetaTaskInstanceDO taskInstance) {
        if (taskInstance.getId() == null || taskInstance.getId().isEmpty()) {
            taskInstance.setId(UUID.randomUUID().toString().replace("-", ""));
        }
        taskInstance.setCreateTime(LocalDateTime.now());
        taskInstance.setUpdateTime(LocalDateTime.now());
        taskInstance.setDeleted(0);
        taskInstanceMapper.insert(taskInstance);
        log.info("保存任务实例: taskInstanceId={}, tenantId={}", taskInstance.getId(), taskInstance.getTenantId());
        return taskInstance.getId();
    }

    @Override
    public void updateTaskInstance(MetaTaskInstanceDO taskInstance) {
        taskInstance.setUpdateTime(LocalDateTime.now());
        taskInstanceMapper.update(taskInstance);
        log.info("更新任务实例: taskInstanceId={}, tenantId={}", taskInstance.getId(), taskInstance.getTenantId());
    }

    @Override
    public void deleteTaskInstance(String id, String tenantId) {
        MetaTaskInstanceDO taskInstance = new MetaTaskInstanceDO();
        taskInstance.setId(id);
        taskInstance.setDeleted(1);
        taskInstance.setUpdateTime(LocalDateTime.now());
        taskInstanceMapper.update(taskInstance);
        log.info("删除任务实例: id={}", id);
    }

    @Override
    public MetaTaskInstanceDO findTaskInstanceById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("id", id)
                .eq("tenant_id", tenantId)
                .eq("deleted", 0);
        return taskInstanceMapper.selectOneByQuery(query);
    }

    @Override
    public List<MetaTaskInstanceDO> findTaskInstancesByProcessId(String processId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("process_id", processId)
                .eq("tenant_id", tenantId)
                .eq("deleted", 0);
        return taskInstanceMapper.selectListByQuery(query);
    }

    @Override
    public List<MetaTaskInstanceDO> findTodoTasks(String assignee, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("assignee", assignee)
                .eq("tenant_id", tenantId)
                .eq("status", "PENDING")
                .eq("deleted", 0);
        return taskInstanceMapper.selectListByQuery(query);
    }

    @Override
    public List<MetaTaskInstanceDO> findDoneTasks(String assignee, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("assignee", assignee)
                .eq("tenant_id", tenantId)
                .in("status", Arrays.asList("COMPLETED", "REJECTED"))
                .eq("deleted", 0);
        return taskInstanceMapper.selectListByQuery(query);
    }
}
