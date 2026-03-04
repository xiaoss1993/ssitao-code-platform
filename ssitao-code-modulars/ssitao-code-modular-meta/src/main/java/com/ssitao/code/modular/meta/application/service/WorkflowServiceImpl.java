package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.domain.model.MetaProcessInstance;
import com.ssitao.code.modular.meta.domain.model.MetaTaskInstance;
import com.ssitao.code.modular.meta.domain.model.MetaWorkflow;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 工作流服务默认实现
 * 提供工作流的核心操作实现
 *
 * @author ssitao-code
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    /**
     * 流程定义存储（实际项目中应使用数据库）
     */
    private final Map<String, MetaWorkflow> workflowStore = new ConcurrentHashMap<>();

    /**
     * 流程实例存储
     */
    private final Map<String, MetaProcessInstance> processStore = new ConcurrentHashMap<>();

    /**
     * 任务实例存储
     */
    private final Map<String, MetaTaskInstance> taskStore = new ConcurrentHashMap<>();

    @Override
    public MetaWorkflow createWorkflow(String workflowCode, String workflowName, String entityId,
                                        String category, String flowJson) {
        String id = UUID.randomUUID().toString();
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
                .createTime(LocalDateTime.now())
                .build();
        workflowStore.put(id, workflow);
        return workflow;
    }

    @Override
    public void updateWorkflow(String workflowId, String workflowName, String category, String flowJson) {
        MetaWorkflow workflow = workflowStore.get(workflowId);
        if (workflow != null) {
            workflow.setWorkflowName(workflowName);
            workflow.setCategory(category);
            workflow.setFlowJson(flowJson);
            workflow.setUpdateTime(LocalDateTime.now());
        }
    }

    @Override
    public void publishWorkflow(String workflowId, String flowJson) {
        MetaWorkflow workflow = workflowStore.get(workflowId);
        if (workflow != null) {
            workflow.setVersion(workflow.getVersion() + 1);
            workflow.setFlowJson(flowJson);
            workflow.setStatus(1);
            workflow.setUpdateTime(LocalDateTime.now());
        }
    }

    @Override
    public MetaWorkflow getWorkflow(String workflowId) {
        return workflowStore.get(workflowId);
    }

    @Override
    public MetaWorkflow getWorkflowByCode(String workflowCode) {
        return workflowStore.values().stream()
                .filter(w -> workflowCode.equals(w.getWorkflowCode()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MetaProcessInstance startProcess(String workflowId, String businessKey, String title,
                                             String initiator, Map<String, Object> variables) {
        MetaWorkflow workflow = workflowStore.get(workflowId);
        if (workflow == null) {
            throw new IllegalArgumentException("流程定义不存在: " + workflowId);
        }

        // 解析流程JSON获取开始节点
        String startNodeId = "start";
        if (workflow.getFlowJson() != null && workflow.getFlowJson().contains("startNodeId")) {
            // 实际应该解析JSON获取，这里简化处理
            startNodeId = "node_1";
        }

        String processId = UUID.randomUUID().toString();
        MetaProcessInstance instance = MetaProcessInstance.builder()
                .id(processId)
                .workflowId(workflowId)
                .businessKey(businessKey)
                .title(title)
                .initiator(initiator)
                .currentNodeId(startNodeId)
                .currentNodeName("开始")
                .status("RUNNING")
                .tenantId(workflow.getTenantId())
                .startTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .build();

        processStore.put(processId, instance);

        // 创建第一个任务
        createNextTask(instance, startNodeId, initiator);

        return instance;
    }

    @Override
    public MetaProcessInstance getProcessInstance(String processId) {
        return processStore.get(processId);
    }

    @Override
    public MetaProcessInstance getProcessInstanceByBusinessKey(String businessKey) {
        return processStore.values().stream()
                .filter(p -> businessKey.equals(p.getBusinessKey()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void cancelProcess(String processId, String operator) {
        MetaProcessInstance instance = processStore.get(processId);
        if (instance != null) {
            instance.setStatus("CANCELLED");
            instance.setEndTime(LocalDateTime.now());
            instance.setUpdateTime(LocalDateTime.now());

            // 取消所有待处理任务
            taskStore.values().stream()
                    .filter(t -> processId.equals(t.getProcessId()) && "PENDING".equals(t.getStatus()))
                    .forEach(t -> t.cancel());
        }
    }

    @Override
    public void signTask(String taskId, String assignee) {
        MetaTaskInstance task = taskStore.get(taskId);
        if (task != null) {
            task.sign(assignee);
        }
    }

    @Override
    public List<MetaTaskInstance> approveTask(String taskId, String assignee, String comment) {
        MetaTaskInstance task = taskStore.get(taskId);
        if (task == null) {
            return Collections.emptyList();
        }

        task.approve(comment);

        MetaProcessInstance process = processStore.get(task.getProcessId());
        if (process == null) {
            return Collections.emptyList();
        }

        // 查找下一个任务
        return createNextTask(process, task.getTaskId(), assignee);
    }

    @Override
    public void rejectTask(String taskId, String assignee, String comment) {
        MetaTaskInstance task = taskStore.get(taskId);
        if (task == null) {
            return;
        }

        task.reject(comment);

        // 驳回到发起人
        MetaProcessInstance process = processStore.get(task.getProcessId());
        if (process != null) {
            // 更新流程实例状态
            process.setStatus("RUNNING");
            process.setCurrentNodeId("start");
            process.setCurrentNodeName("开始");
            process.setUpdateTime(LocalDateTime.now());

            // 重新创建第一个任务给发起人
            createNextTask(process, "start", process.getInitiator());
        }
    }

    @Override
    public void delegateTask(String taskId, String currentAssignee, String newAssignee, String comment) {
        MetaTaskInstance task = taskStore.get(taskId);
        if (task != null && currentAssignee.equals(task.getAssignee())) {
            task.delegate(newAssignee, comment);
        }
    }

    @Override
    public MetaTaskInstance getTask(String taskId) {
        return taskStore.get(taskId);
    }

    @Override
    public List<MetaTaskInstance> getTodoTasks(String userId) {
        return taskStore.values().stream()
                .filter(t -> userId.equals(t.getAssignee()))
                .filter(t -> "PENDING".equals(t.getStatus()) || "SIGNED".equals(t.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaTaskInstance> getDoneTasks(String userId) {
        return taskStore.values().stream()
                .filter(t -> userId.equals(t.getAssignee()))
                .filter(t -> t.isCompleted())
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaProcessInstance> getMyProcesses(String initiator) {
        return processStore.values().stream()
                .filter(p -> initiator.equals(p.getInitiator()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaTaskInstance> getProcessHistory(String processId) {
        return taskStore.values().stream()
                .filter(t -> processId.equals(t.getProcessId()))
                .filter(MetaTaskInstance::isCompleted)
                .sorted(Comparator.comparing(MetaTaskInstance::getCreateTime))
                .collect(Collectors.toList());
    }

    /**
     * 创建下一个任务
     *
     * @param process   流程实例
     * @param currentTaskId 当前任务ID
     * @param nextAssignee 下一个处理人
     * @return 创建的任务列表
     */
    private List<MetaTaskInstance> createNextTask(MetaProcessInstance process, String currentTaskId, String nextAssignee) {
        List<MetaTaskInstance> tasks = new ArrayList<>();

        // 简化实现：创建下一个审批任务
        // 实际应该根据流程图解析确定下一个节点
        String nextNodeId = "node_2";
        String nextNodeName = "审批";

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
                .createTime(LocalDateTime.now())
                .build();

        taskStore.put(task.getId(), task);
        tasks.add(task);

        // 更新流程实例当前节点
        process.setCurrentNodeId(nextNodeId);
        process.setCurrentNodeName(nextNodeName);
        process.setUpdateTime(LocalDateTime.now());

        return tasks;
    }
}
