package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.modular.meta.application.service.WorkflowService;
import com.ssitao.code.modular.meta.domain.model.MetaProcessInstance;
import com.ssitao.code.modular.meta.domain.model.MetaTaskInstance;
import com.ssitao.code.modular.meta.domain.model.MetaWorkflow;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流管理 Controller
 * 提供工作流相关的REST API接口
 *
 * @author ssitao-code
 */
@RestController
@RequestMapping("/workflow")
@Tag(name = "工作流管理", description = "工作流定义、流程实例、任务处理相关接口")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    // ==================== 流程定义接口 ====================

    @PostMapping("/definition")
    @Operation(summary = "创建流程定义")
    public Map<String, Object> createWorkflow(@RequestBody Map<String, String> request) {
        String workflowCode = request.get("workflowCode");
        String workflowName = request.get("workflowName");
        String entityId = request.get("entityId");
        String category = request.get("category");
        String flowJson = request.get("flowJson");

        MetaWorkflow workflow = workflowService.createWorkflow(workflowCode, workflowName, entityId, category, flowJson);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", workflow);
        return result;
    }

    @PutMapping("/definition/{workflowId}")
    @Operation(summary = "更新流程定义")
    public Map<String, Object> updateWorkflow(@Parameter(description = "流程定义ID") @PathVariable String workflowId,
                                                @RequestBody Map<String, String> request) {
        String workflowName = request.get("workflowName");
        String category = request.get("category");
        String flowJson = request.get("flowJson");

        workflowService.updateWorkflow(workflowId, workflowName, category, flowJson);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/definition/{workflowId}/publish")
    @Operation(summary = "发布流程")
    public Map<String, Object> publishWorkflow(@Parameter(description = "流程定义ID") @PathVariable String workflowId,
                                                 @RequestBody Map<String, String> request) {
        String flowJson = request.get("flowJson");
        workflowService.publishWorkflow(workflowId, flowJson);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @GetMapping("/definition/{workflowId}")
    @Operation(summary = "获取流程定义")
    public Map<String, Object> getWorkflow(@Parameter(description = "流程定义ID") @PathVariable String workflowId) {
        MetaWorkflow workflow = workflowService.getWorkflow(workflowId);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", workflow);
        return result;
    }

    @GetMapping("/definition/code/{workflowCode}")
    @Operation(summary = "根据编码获取流程定义")
    public Map<String, Object> getWorkflowByCode(@Parameter(description = "流程编码") @PathVariable String workflowCode) {
        MetaWorkflow workflow = workflowService.getWorkflowByCode(workflowCode);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", workflow);
        return result;
    }

    // ==================== 流程实例接口 ====================

    @PostMapping("/process/start")
    @Operation(summary = "发起流程")
    public Map<String, Object> startProcess(@RequestBody Map<String, Object> request) {
        String workflowId = (String) request.get("workflowId");
        String businessKey = (String) request.get("businessKey");
        String title = (String) request.get("title");
        String initiator = (String) request.get("initiator");
        @SuppressWarnings("unchecked")
        Map<String, Object> variables = (Map<String, Object>) request.get("variables");

        MetaProcessInstance instance = workflowService.startProcess(workflowId, businessKey, title, initiator, variables);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", instance);
        return result;
    }

    @GetMapping("/process/{processId}")
    @Operation(summary = "获取流程实例")
    public Map<String, Object> getProcessInstance(@Parameter(description = "流程实例ID") @PathVariable String processId) {
        MetaProcessInstance instance = workflowService.getProcessInstance(processId);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", instance);
        return result;
    }

    @GetMapping("/process/business/{businessKey}")
    @Operation(summary = "根据业务键获取流程实例")
    public Map<String, Object> getProcessInstanceByBusinessKey(@Parameter(description = "业务键") @PathVariable String businessKey) {
        MetaProcessInstance instance = workflowService.getProcessInstanceByBusinessKey(businessKey);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", instance);
        return result;
    }

    @PostMapping("/process/{processId}/cancel")
    @Operation(summary = "取消流程")
    public Map<String, Object> cancelProcess(@Parameter(description = "流程实例ID") @PathVariable String processId,
                                              @RequestBody Map<String, String> request) {
        String operator = request.get("operator");
        workflowService.cancelProcess(processId, operator);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    // ==================== 任务操作接口 ====================

    @PostMapping("/task/{taskId}/sign")
    @Operation(summary = "签收任务")
    public Map<String, Object> signTask(@Parameter(description = "任务ID") @PathVariable String taskId,
                                         @RequestBody Map<String, String> request) {
        String assignee = request.get("assignee");
        workflowService.signTask(taskId, assignee);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/task/{taskId}/approve")
    @Operation(summary = "审批通过")
    public Map<String, Object> approveTask(@Parameter(description = "任务ID") @PathVariable String taskId,
                                            @RequestBody Map<String, String> request) {
        String assignee = request.get("assignee");
        String comment = request.get("comment");

        List<MetaTaskInstance> nextTasks = workflowService.approveTask(taskId, assignee, comment);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("nextTasks", nextTasks);
        return result;
    }

    @PostMapping("/task/{taskId}/reject")
    @Operation(summary = "驳回")
    public Map<String, Object> rejectTask(@Parameter(description = "任务ID") @PathVariable String taskId,
                                           @RequestBody Map<String, String> request) {
        String assignee = request.get("assignee");
        String comment = request.get("comment");

        workflowService.rejectTask(taskId, assignee, comment);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/task/{taskId}/delegate")
    @Operation(summary = "转办")
    public Map<String, Object> delegateTask(@Parameter(description = "任务ID") @PathVariable String taskId,
                                              @RequestBody Map<String, String> request) {
        String currentAssignee = request.get("currentAssignee");
        String newAssignee = request.get("newAssignee");
        String comment = request.get("comment");

        workflowService.delegateTask(taskId, currentAssignee, newAssignee, comment);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @GetMapping("/task/{taskId}")
    @Operation(summary = "获取任务")
    public Map<String, Object> getTask(@Parameter(description = "任务ID") @PathVariable String taskId) {
        MetaTaskInstance task = workflowService.getTask(taskId);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", task);
        return result;
    }

    // ==================== 待办已办接口 ====================

    @GetMapping("/todo/{userId}")
    @Operation(summary = "获取待办任务")
    public Map<String, Object> getTodoTasks(@Parameter(description = "用户ID") @PathVariable String userId) {
        List<MetaTaskInstance> tasks = workflowService.getTodoTasks(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", tasks);
        return result;
    }

    @GetMapping("/done/{userId}")
    @Operation(summary = "获取已办任务")
    public Map<String, Object> getDoneTasks(@Parameter(description = "用户ID") @PathVariable String userId) {
        List<MetaTaskInstance> tasks = workflowService.getDoneTasks(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", tasks);
        return result;
    }

    @GetMapping("/my/{initiator}")
    @Operation(summary = "获取我发起的流程")
    public Map<String, Object> getMyProcesses(@Parameter(description = "发起人") @PathVariable String initiator) {
        List<MetaProcessInstance> processes = workflowService.getMyProcesses(initiator);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", processes);
        return result;
    }

    // ==================== 流程历史接口 ====================

    @GetMapping("/history/{processId}")
    @Operation(summary = "获取流程历史")
    public Map<String, Object> getProcessHistory(@Parameter(description = "流程实例ID") @PathVariable String processId) {
        List<MetaTaskInstance> history = workflowService.getProcessHistory(processId);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", history);
        return result;
    }

}
