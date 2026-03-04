package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 流程实例
 * 工作流运行时实例，记录流程的执行状态和基本信息
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaProcessInstance {

    /**
     * 流程实例ID
     */
    private String id;

    /**
     * 流程定义ID
     */
    private String workflowId;

    /**
     * 业务键（关联业务数据）
     */
    private String businessKey;

    /**
     * 流程标题
     */
    private String title;

    /**
     * 发起人
     */
    private String initiator;

    /**
     * 当前节点ID
     */
    private String currentNodeId;

    /**
     * 当前节点名称
     */
    private String currentNodeName;

    /**
     * 流程状态：
     * RUNNING - 运行中
     * COMPLETED - 已完成
     * CANCELLED - 已取消
     * SUSPENDED - 已暂停
     */
    private String status;

    /**
     * 发起时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 流程变量JSON
     */
    private String variablesJson;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建流程实例
     *
     * @param workflowId    流程定义ID
     * @param businessKey  业务键
     * @param title        流程标题
     * @param initiator    发起人
     * @param currentNodeId 当前节点ID
     * @param tenantId     租户ID
     * @return 流程实例
     */
    public static MetaProcessInstance create(String workflowId, String businessKey, String title,
                                              String initiator, String currentNodeId, String tenantId) {
        MetaProcessInstance instance = new MetaProcessInstance();
        instance.setWorkflowId(workflowId);
        instance.setBusinessKey(businessKey);
        instance.setTitle(title);
        instance.setInitiator(initiator);
        instance.setCurrentNodeId(currentNodeId);
        instance.setStatus("RUNNING");
        instance.setTenantId(tenantId);
        instance.setStartTime(LocalDateTime.now());
        instance.setCreateTime(LocalDateTime.now());
        return instance;
    }

    /**
     * 签收任务（更新当前节点）
     *
     * @param nodeId   节点ID
     * @param nodeName 节点名称
     */
    public void signTask(String nodeId, String nodeName) {
        this.setCurrentNodeId(nodeId);
        this.setCurrentNodeName(nodeName);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 完成流程
     */
    public void complete() {
        this.setStatus("COMPLETED");
        this.setEndTime(LocalDateTime.now());
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 取消流程
     */
    public void cancel() {
        this.setStatus("CANCELLED");
        this.setEndTime(LocalDateTime.now());
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 暂停流程
     */
    public void suspend() {
        this.setStatus("SUSPENDED");
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 恢复流程
     */
    public void resume() {
        this.setStatus("RUNNING");
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 判断流程是否运行中
     *
     * @return 是否运行中
     */
    public boolean isRunning() {
        return "RUNNING".equals(status);
    }

    /**
     * 判断流程是否已完成
     *
     * @return 是否已完成
     */
    public boolean isCompleted() {
        return "COMPLETED".equals(status);
    }
}
