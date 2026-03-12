package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaWorkflowDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaProcessInstanceDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaTaskInstanceDO;

import java.util.List;

/**
 * 工作流仓储接口
 * 负责工作流定义、流程实例和任务实例的持久化操作
 *
 * @author ssitao-code
 */
public interface MetaWorkflowRepository {

    // ==================== Workflow 操作 ====================

    /**
     * 保存流程定义
     *
     * @param workflow 流程定义
     * @return 流程ID
     */
    String saveWorkflow(MetaWorkflowDO workflow);

    /**
     * 更新流程定义
     *
     * @param workflow 流程定义
     */
    void updateWorkflow(MetaWorkflowDO workflow);

    /**
     * 删除流程定义
     *
     * @param id       流程ID
     * @param tenantId 租户ID
     */
    void deleteWorkflow(String id, String tenantId);

    /**
     * 根据ID查询流程定义
     *
     * @param id       流程ID
     * @param tenantId 租户ID
     * @return 流程定义
     */
    MetaWorkflowDO findWorkflowById(String id, String tenantId);

    /**
     * 根据编码查询流程定义
     *
     * @param workflowCode 流程编码
     * @param tenantId     租户ID
     * @return 流程定义
     */
    MetaWorkflowDO findWorkflowByCode(String workflowCode, String tenantId);

    /**
     * 查询所有流程定义
     *
     * @param tenantId 租户ID
     * @return 流程定义列表
     */
    List<MetaWorkflowDO> findWorkflows(String tenantId);

    // ==================== Process Instance 操作 ====================

    /**
     * 保存流程实例
     *
     * @param processInstance 流程实例
     * @return 流程实例ID
     */
    String saveProcessInstance(MetaProcessInstanceDO processInstance);

    /**
     * 更新流程实例
     *
     * @param processInstance 流程实例
     */
    void updateProcessInstance(MetaProcessInstanceDO processInstance);

    /**
     * 删除流程实例
     *
     * @param id       流程实例ID
     * @param tenantId 租户ID
     */
    void deleteProcessInstance(String id, String tenantId);

    /**
     * 根据ID查询流程实例
     *
     * @param id       流程实例ID
     * @param tenantId 租户ID
     * @return 流程实例
     */
    MetaProcessInstanceDO findProcessInstanceById(String id, String tenantId);

    /**
     * 根据业务键查询流程实例
     *
     * @param businessKey 业务键
     * @param tenantId    租户ID
     * @return 流程实例
     */
    MetaProcessInstanceDO findProcessInstanceByBusinessKey(String businessKey, String tenantId);

    /**
     * 根据流程定义查询流程实例
     *
     * @param workflowId 流程定义ID
     * @param tenantId   租户ID
     * @return 流程实例列表
     */
    List<MetaProcessInstanceDO> findProcessInstancesByWorkflowId(String workflowId, String tenantId);

    /**
     * 根据发起人查询流程实例
     *
     * @param initiator 发起人
     * @param tenantId  租户ID
     * @return 流程实例列表
     */
    List<MetaProcessInstanceDO> findProcessInstancesByInitiator(String initiator, String tenantId);

    // ==================== Task Instance 操作 ====================

    /**
     * 保存任务实例
     *
     * @param taskInstance 任务实例
     * @return 任务实例ID
     */
    String saveTaskInstance(MetaTaskInstanceDO taskInstance);

    /**
     * 更新任务实例
     *
     * @param taskInstance 任务实例
     */
    void updateTaskInstance(MetaTaskInstanceDO taskInstance);

    /**
     * 删除任务实例
     *
     * @param id       任务实例ID
     * @param tenantId 租户ID
     */
    void deleteTaskInstance(String id, String tenantId);

    /**
     * 根据ID查询任务实例
     *
     * @param id       任务实例ID
     * @param tenantId 租户ID
     * @return 任务实例
     */
    MetaTaskInstanceDO findTaskInstanceById(String id, String tenantId);

    /**
     * 根据流程实例查询任务实例
     *
     * @param processId 流程实例ID
     * @param tenantId  租户ID
     * @return 任务实例列表
     */
    List<MetaTaskInstanceDO> findTaskInstancesByProcessId(String processId, String tenantId);

    /**
     * 查询待办任务
     *
     * @param assignee  处理人
     * @param tenantId 租户ID
     * @return 待办任务列表
     */
    List<MetaTaskInstanceDO> findTodoTasks(String assignee, String tenantId);

    /**
     * 查询已办任务
     *
     * @param assignee 处理人
     * @param tenantId 租户ID
     * @return 已办任务列表
     */
    List<MetaTaskInstanceDO> findDoneTasks(String assignee, String tenantId);
}
