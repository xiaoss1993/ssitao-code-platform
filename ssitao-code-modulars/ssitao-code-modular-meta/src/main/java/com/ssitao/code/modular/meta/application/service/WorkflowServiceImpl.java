package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.dal.dataobject.MetaProcessInstanceDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaTaskInstanceDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaWorkflowDO;
import com.ssitao.code.modular.meta.domain.model.MetaProcessInstance;
import com.ssitao.code.modular.meta.domain.model.MetaTaskInstance;
import com.ssitao.code.modular.meta.domain.model.MetaWorkflow;
import com.ssitao.code.modular.meta.domain.repository.MetaWorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工作流服务默认实现
 * 提供工作流的核心操作实现
 *
 * @author ssitao-code
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private MetaWorkflowRepository workflowRepository;

    @Override
    public MetaWorkflow createWorkflow(String workflowCode, String workflowName, String entityId,
                                        String category, String flowJson) {
        String id = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        MetaWorkflow workflow = MetaWorkflow.builder()
                .id(id)
                .workflowCode(workflowCode)
                .workflowName(workflowName)
                .entityId(entityId)
                .category(category)
                .flowJson(flowJson)
                .version(1)
                .status(1)
                .tenantId("default")
                .deleted(false)
                .createTime(now)
                .build();

        // 转换为DO并保存到数据库
        MetaWorkflowDO workflowDO = convertToWorkflowDO(workflow);
        workflowRepository.saveWorkflow(workflowDO);

        return workflow;
    }

    @Override
    public void updateWorkflow(String workflowId, String workflowName, String category, String flowJson) {
        MetaWorkflowDO workflowDO = workflowRepository.findWorkflowById(workflowId, "default");
        if (workflowDO != null) {
            workflowDO.setWorkflowName(workflowName);
            workflowDO.setCategory(category);
            workflowDO.setFlowJson(flowJson);
            workflowDO.setUpdateTime(LocalDateTime.now());
            workflowRepository.updateWorkflow(workflowDO);
        }
    }

    @Override
    public void publishWorkflow(String workflowId, String flowJson) {
        MetaWorkflowDO workflowDO = workflowRepository.findWorkflowById(workflowId, "default");
        if (workflowDO != null) {
            workflowDO.setVersion(workflowDO.getVersion() + 1);
            workflowDO.setFlowJson(flowJson);
            workflowDO.setStatus(1);
            workflowDO.setUpdateTime(LocalDateTime.now());
            workflowRepository.updateWorkflow(workflowDO);
        }
    }

    @Override
    public MetaWorkflow getWorkflow(String workflowId) {
        MetaWorkflowDO workflowDO = workflowRepository.findWorkflowById(workflowId, "default");
        return convertToWorkflow(workflowDO);
    }

    @Override
    public MetaWorkflow getWorkflowByCode(String workflowCode) {
        MetaWorkflowDO workflowDO = workflowRepository.findWorkflowByCode(workflowCode, "default");
        return convertToWorkflow(workflowDO);
    }

    @Override
    public MetaProcessInstance startProcess(String workflowId, String businessKey, String title,
                                             String initiator, Map<String, Object> variables) {
        MetaWorkflowDO workflowDO = workflowRepository.findWorkflowById(workflowId, "default");
        if (workflowDO == null) {
            throw new IllegalArgumentException("流程定义不存在: " + workflowId);
        }

        // 解析流程JSON获取开始节点
        String startNodeId = "start";
        if (workflowDO.getFlowJson() != null && workflowDO.getFlowJson().contains("startNodeId")) {
            // 实际应该解析JSON获取，这里简化处理
            startNodeId = "node_1";
        }

        String processId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        MetaProcessInstance instance = MetaProcessInstance.builder()
                .id(processId)
                .workflowId(workflowId)
                .businessKey(businessKey)
                .title(title)
                .initiator(initiator)
                .currentNodeId(startNodeId)
                .currentNodeName("开始")
                .status("RUNNING")
                .tenantId(workflowDO.getTenantId())
                .startTime(now)
                .createTime(now)
                .build();

        // 保存流程实例到数据库
        MetaProcessInstanceDO processDO = convertToProcessInstanceDO(instance);
        workflowRepository.saveProcessInstance(processDO);

        // 创建第一个任务
        createNextTask(instance, startNodeId, initiator);

        return instance;
    }

    @Override
    public MetaProcessInstance getProcessInstance(String processId) {
        MetaProcessInstanceDO processDO = workflowRepository.findProcessInstanceById(processId, "default");
        return convertToProcessInstance(processDO);
    }

    @Override
    public MetaProcessInstance getProcessInstanceByBusinessKey(String businessKey) {
        MetaProcessInstanceDO processDO = workflowRepository.findProcessInstanceByBusinessKey(businessKey, "default");
        return convertToProcessInstance(processDO);
    }

    @Override
    public void cancelProcess(String processId, String operator) {
        MetaProcessInstanceDO processDO = workflowRepository.findProcessInstanceById(processId, "default");
        if (processDO != null) {
            processDO.setStatus("CANCELLED");
            processDO.setEndTime(LocalDateTime.now());
            processDO.setUpdateTime(LocalDateTime.now());
            workflowRepository.updateProcessInstance(processDO);

            // 取消所有待处理任务
            List<MetaTaskInstanceDO> tasks = workflowRepository.findTaskInstancesByProcessId(processId, "default");
            tasks.stream()
                    .filter(t -> "PENDING".equals(t.getStatus()))
                    .forEach(t -> {
                        t.setStatus("CANCELLED");
                        t.setCompleteTime(LocalDateTime.now());
                        t.setUpdateTime(LocalDateTime.now());
                        workflowRepository.updateTaskInstance(t);
                    });
        }
    }

    @Override
    public void signTask(String taskId, String assignee) {
        MetaTaskInstanceDO taskDO = workflowRepository.findTaskInstanceById(taskId, "default");
        if (taskDO != null) {
            taskDO.setAssignee(assignee);
            taskDO.setStatus("SIGNED");
            taskDO.setClaimTime(LocalDateTime.now());
            taskDO.setUpdateTime(LocalDateTime.now());
            workflowRepository.updateTaskInstance(taskDO);
        }
    }

    @Override
    public List<MetaTaskInstance> approveTask(String taskId, String assignee, String comment) {
        MetaTaskInstanceDO taskDO = workflowRepository.findTaskInstanceById(taskId, "default");
        if (taskDO == null) {
            return Collections.emptyList();
        }

        // 更新任务状态
        taskDO.setStatus("APPROVED");
        taskDO.setComment(comment);
        taskDO.setCompleteTime(LocalDateTime.now());
        taskDO.setUpdateTime(LocalDateTime.now());
        workflowRepository.updateTaskInstance(taskDO);

        MetaProcessInstanceDO processDO = workflowRepository.findProcessInstanceById(taskDO.getProcessId(), "default");
        if (processDO == null) {
            return Collections.emptyList();
        }

        MetaProcessInstance process = convertToProcessInstance(processDO);

        // 查找下一个任务
        return createNextTask(process, taskDO.getTaskId(), assignee);
    }

    @Override
    public void rejectTask(String taskId, String assignee, String comment) {
        MetaTaskInstanceDO taskDO = workflowRepository.findTaskInstanceById(taskId, "default");
        if (taskDO == null) {
            return;
        }

        // 更新任务状态
        taskDO.setStatus("REJECTED");
        taskDO.setComment(comment);
        taskDO.setCompleteTime(LocalDateTime.now());
        taskDO.setUpdateTime(LocalDateTime.now());
        workflowRepository.updateTaskInstance(taskDO);

        // 驳回到发起人
        MetaProcessInstanceDO processDO = workflowRepository.findProcessInstanceById(taskDO.getProcessId(), "default");
        if (processDO != null) {
            // 更新流程实例状态
            processDO.setStatus("RUNNING");
            processDO.setCurrentNodeId("start");
            processDO.setCurrentNodeName("开始");
            processDO.setUpdateTime(LocalDateTime.now());
            workflowRepository.updateProcessInstance(processDO);

            MetaProcessInstance process = convertToProcessInstance(processDO);

            // 重新创建第一个任务给发起人
            createNextTask(process, "start", process.getInitiator());
        }
    }

    @Override
    public void delegateTask(String taskId, String currentAssignee, String newAssignee, String comment) {
        MetaTaskInstanceDO taskDO = workflowRepository.findTaskInstanceById(taskId, "default");
        if (taskDO != null && currentAssignee.equals(taskDO.getAssignee())) {
            taskDO.setAssignee(newAssignee);
            taskDO.setComment(comment);
            taskDO.setStatus("DELEGATED");
            taskDO.setUpdateTime(LocalDateTime.now());
            workflowRepository.updateTaskInstance(taskDO);
        }
    }

    @Override
    public MetaTaskInstance getTask(String taskId) {
        MetaTaskInstanceDO taskDO = workflowRepository.findTaskInstanceById(taskId, "default");
        return convertToTaskInstance(taskDO);
    }

    @Override
    public List<MetaTaskInstance> getTodoTasks(String userId) {
        List<MetaTaskInstanceDO> taskDOs = workflowRepository.findTodoTasks(userId, "default");
        return taskDOs.stream()
                .map(this::convertToTaskInstance)
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaTaskInstance> getDoneTasks(String userId) {
        List<MetaTaskInstanceDO> taskDOs = workflowRepository.findDoneTasks(userId, "default");
        return taskDOs.stream()
                .map(this::convertToTaskInstance)
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaProcessInstance> getMyProcesses(String initiator) {
        List<MetaProcessInstanceDO> processDOs = workflowRepository.findProcessInstancesByInitiator(initiator, "default");
        return processDOs.stream()
                .map(this::convertToProcessInstance)
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaTaskInstance> getProcessHistory(String processId) {
        List<MetaTaskInstanceDO> taskDOs = workflowRepository.findTaskInstancesByProcessId(processId, "default");
        return taskDOs.stream()
                .filter(t -> "APPROVED".equals(t.getStatus()) || "REJECTED".equals(t.getStatus()))
                .sorted(Comparator.comparing(MetaTaskInstanceDO::getCreateTime))
                .map(this::convertToTaskInstance)
                .collect(Collectors.toList());
    }

    /**
     * 创建下一个任务
     *
     * @param process         流程实例
     * @param currentTaskId   当前任务ID
     * @param nextAssignee    下一个处理人
     * @return 创建的任务列表
     */
    private List<MetaTaskInstance> createNextTask(MetaProcessInstance process, String currentTaskId, String nextAssignee) {
        List<MetaTaskInstance> tasks = new ArrayList<>();

        // 简化实现：创建下一个审批任务
        // 实际应该根据流程图解析确定下一个节点
        String nextNodeId = "node_2";
        String nextNodeName = "审批";

        LocalDateTime now = LocalDateTime.now();

        MetaTaskInstance task = MetaTaskInstance.builder()
                .id(UUID.randomUUID().toString())
                .processId(process.getId())
                .taskId(nextNodeId)
                .taskName(nextNodeName)
                .taskType("APPROVAL")
                .assignee(nextAssignee)
                .status("PENDING")
                .priority(2)
                .tenantId(process.getTenantId())
                .createTime(now)
                .build();

        // 保存任务到数据库
        MetaTaskInstanceDO taskDO = convertToTaskInstanceDO(task);
        workflowRepository.saveTaskInstance(taskDO);

        tasks.add(task);

        // 更新流程实例当前节点
        MetaProcessInstanceDO processDO = workflowRepository.findProcessInstanceById(process.getId(), "default");
        if (processDO != null) {
            processDO.setCurrentNodeId(nextNodeId);
            processDO.setCurrentNodeName(nextNodeName);
            processDO.setUpdateTime(now);
            workflowRepository.updateProcessInstance(processDO);
        }

        return tasks;
    }

    // ==================== 转换方法 ====================

    /**
     * 将领域模型转换为DO对象
     */
    private MetaWorkflowDO convertToWorkflowDO(MetaWorkflow workflow) {
        if (workflow == null) {
            return null;
        }
        return MetaWorkflowDO.builder()
                .id(workflow.getId())
                .workflowCode(workflow.getWorkflowCode())
                .workflowName(workflow.getWorkflowName())
                .entityId(workflow.getEntityId())
                .category(workflow.getCategory())
                .version(workflow.getVersion())
                .flowJson(workflow.getFlowJson())
                .status(workflow.getStatus())
                .tenantId(workflow.getTenantId())
                .creator(workflow.getCreator())
                .createTime(workflow.getCreateTime())
                .updater(workflow.getUpdater())
                .updateTime(workflow.getUpdateTime())
                .deleted(workflow.getDeleted() ? 1 : 0)
                .build();
    }

    /**
     * 将DO对象转换为领域模型
     */
    private MetaWorkflow convertToWorkflow(MetaWorkflowDO workflowDO) {
        if (workflowDO == null) {
            return null;
        }
        return MetaWorkflow.builder()
                .id(workflowDO.getId())
                .workflowCode(workflowDO.getWorkflowCode())
                .workflowName(workflowDO.getWorkflowName())
                .entityId(workflowDO.getEntityId())
                .category(workflowDO.getCategory())
                .version(workflowDO.getVersion())
                .flowJson(workflowDO.getFlowJson())
                .status(workflowDO.getStatus())
                .tenantId(workflowDO.getTenantId())
                .creator(workflowDO.getCreator())
                .createTime(workflowDO.getCreateTime())
                .updater(workflowDO.getUpdater())
                .updateTime(workflowDO.getUpdateTime())
                .deleted(workflowDO.getDeleted() != null && workflowDO.getDeleted() == 1)
                .build();
    }

    /**
     * 将流程实例领域模型转换为DO对象
     */
    private MetaProcessInstanceDO convertToProcessInstanceDO(MetaProcessInstance instance) {
        if (instance == null) {
            return null;
        }
        return MetaProcessInstanceDO.builder()
                .id(instance.getId())
                .workflowId(instance.getWorkflowId())
                .businessKey(instance.getBusinessKey())
                .title(instance.getTitle())
                .initiator(instance.getInitiator())
                .currentNodeId(instance.getCurrentNodeId())
                .currentNodeName(instance.getCurrentNodeName())
                .status(instance.getStatus())
                .tenantId(instance.getTenantId())
                .startTime(instance.getStartTime())
                .endTime(instance.getEndTime())
                .createTime(instance.getCreateTime())
                .updateTime(instance.getUpdateTime())
                .deleted(0)
                .build();
    }

    /**
     * 将流程实例DO对象转换为领域模型
     */
    private MetaProcessInstance convertToProcessInstance(MetaProcessInstanceDO processDO) {
        if (processDO == null) {
            return null;
        }
        return MetaProcessInstance.builder()
                .id(processDO.getId())
                .workflowId(processDO.getWorkflowId())
                .businessKey(processDO.getBusinessKey())
                .title(processDO.getTitle())
                .initiator(processDO.getInitiator())
                .currentNodeId(processDO.getCurrentNodeId())
                .currentNodeName(processDO.getCurrentNodeName())
                .status(processDO.getStatus())
                .tenantId(processDO.getTenantId())
                .startTime(processDO.getStartTime())
                .endTime(processDO.getEndTime())
                .createTime(processDO.getCreateTime())
                .updateTime(processDO.getUpdateTime())
                .build();
    }

    /**
     * 将任务实例领域模型转换为DO对象
     */
    private MetaTaskInstanceDO convertToTaskInstanceDO(MetaTaskInstance task) {
        if (task == null) {
            return null;
        }
        return MetaTaskInstanceDO.builder()
                .id(task.getId())
                .processId(task.getProcessId())
                .taskId(task.getTaskId())
                .taskName(task.getTaskName())
                .taskType(task.getTaskType())
                .assignee(task.getAssignee())
                .status(task.getStatus())
                .priority(task.getPriority())
                .comment(task.getComment())
                .tenantId(task.getTenantId())
                .claimTime(task.getSignTime())
                .completeTime(task.getCompleteTime())
                .createTime(task.getCreateTime())
                .updateTime(task.getUpdateTime())
                .deleted(0)
                .build();
    }

    /**
     * 将任务实例DO对象转换为领域模型
     */
    private MetaTaskInstance convertToTaskInstance(MetaTaskInstanceDO taskDO) {
        if (taskDO == null) {
            return null;
        }
        return MetaTaskInstance.builder()
                .id(taskDO.getId())
                .processId(taskDO.getProcessId())
                .taskId(taskDO.getTaskId())
                .taskName(taskDO.getTaskName())
                .taskType(taskDO.getTaskType())
                .assignee(taskDO.getAssignee())
                .status(taskDO.getStatus())
                .priority(taskDO.getPriority())
                .comment(taskDO.getComment())
                .tenantId(taskDO.getTenantId())
                .signTime(taskDO.getClaimTime())
                .completeTime(taskDO.getCompleteTime())
                .createTime(taskDO.getCreateTime())
                .updateTime(taskDO.getUpdateTime())
                .build();
    }
}
