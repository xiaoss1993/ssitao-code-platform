package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.domain.model.MetaProcessInstance;
import com.ssitao.code.modular.meta.domain.model.MetaTaskInstance;
import com.ssitao.code.modular.meta.domain.model.MetaWorkflow;

import java.util.List;
import java.util.Map;

/**
 * 工作流服务接口
 * 定义工作流的核心操作，包括发起流程、审批、驳回、转办等
 *
 * @author ssitao-code
 */
public interface WorkflowService {

    // ==================== 流程定义相关 ====================

    /**
     * 创建流程定义
     *
     * @param workflowCode 流程编码
     * @param workflowName 流程名称
     * @param entityId     关联实体ID
     * @param category     分类
     * @param flowJson     流程JSON
     * @return 流程定义
     */
    MetaWorkflow createWorkflow(String workflowCode, String workflowName, String entityId,
                                 String category, String flowJson);

    /**
     * 更新流程定义
     *
     * @param workflowId   流程ID
     * @param workflowName 流程名称
     * @param category     分类
     * @param flowJson     流程JSON
     */
    void updateWorkflow(String workflowId, String workflowName, String category, String flowJson);

    /**
     * 发布流程（创建新版本）
     *
     * @param workflowId 流程ID
     * @param flowJson   流程JSON
     */
    void publishWorkflow(String workflowId, String flowJson);

    /**
     * 获取流程定义
     *
     * @param workflowId 流程ID
     * @return 流程定义
     */
    MetaWorkflow getWorkflow(String workflowId);

    /**
     * 根据编码获取流程定义
     *
     * @param workflowCode 流程编码
     * @return 流程定义
     */
    MetaWorkflow getWorkflowByCode(String workflowCode);

    // ==================== 流程实例相关 ====================

    /**
     * 发起流程
     *
     * @param workflowId 流程定义ID
     * @param businessKey 业务键
     * @param title      流程标题
     * @param initiator  发起人
     * @param variables  流程变量
     * @return 流程实例
     */
    MetaProcessInstance startProcess(String workflowId, String businessKey, String title,
                                      String initiator, Map<String, Object> variables);

    /**
     * 获取流程实例
     *
     * @param processId 流程实例ID
     * @return 流程实例
     */
    MetaProcessInstance getProcessInstance(String processId);

    /**
     * 根据业务键获取流程实例
     *
     * @param businessKey 业务键
     * @return 流程实例
     */
    MetaProcessInstance getProcessInstanceByBusinessKey(String businessKey);

    /**
     * 取消流程
     *
     * @param processId 流程实例ID
     * @param operator  操作人
     */
    void cancelProcess(String processId, String operator);

    // ==================== 任务操作相关 ====================

    /**
     * 签收任务
     *
     * @param taskId   任务ID
     * @param assignee 签收人
     */
    void signTask(String taskId, String assignee);

    /**
     * 审批通过
     *
     * @param taskId   任务ID
     * @param assignee 审批人
     * @param comment  审批意见
     * @return 下一任务列表
     */
    List<MetaTaskInstance> approveTask(String taskId, String assignee, String comment);

    /**
     * 驳回
     *
     * @param taskId   任务ID
     * @param assignee 审批人
     * @param comment  驳回意见
     */
    void rejectTask(String taskId, String assignee, String comment);

    /**
     * 转办
     *
     * @param taskId       任务ID
     * @param currentAssignee 当前处理人
     * @param newAssignee  新处理人
     * @param comment     转办意见
     */
    void delegateTask(String taskId, String currentAssignee, String newAssignee, String comment);

    /**
     * 获取任务
     *
     * @param taskId 任务ID
     * @return 任务实例
     */
    MetaTaskInstance getTask(String taskId);

    // ==================== 待办已办相关 ====================

    /**
     * 获取待办任务
     *
     * @param userId 用户ID
     * @return 待办任务列表
     */
    List<MetaTaskInstance> getTodoTasks(String userId);

    /**
     * 获取已办任务
     *
     * @param userId 用户ID
     * @return 已办任务列表
     */
    List<MetaTaskInstance> getDoneTasks(String userId);

    /**
     * 获取我发起的流程
     *
     * @param initiator 发起人
     * @return 流程实例列表
     */
    List<MetaProcessInstance> getMyProcesses(String initiator);

    // ==================== 流程历史相关 ====================

    /**
     * 获取流程历史
     *
     * @param processId 流程实例ID
     * @return 任务历史列表
     */
    List<MetaTaskInstance> getProcessHistory(String processId);

}
