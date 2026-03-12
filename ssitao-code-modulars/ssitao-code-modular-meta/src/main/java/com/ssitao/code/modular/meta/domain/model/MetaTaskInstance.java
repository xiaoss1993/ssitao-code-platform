package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 任务实例
 * 工作流中的任务节点实例，记录任务的执行信息
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaTaskInstance {

    /**
     * 任务实例ID
     */
    private String id;

    /**
     * 流程实例ID
     */
    private String processId;

    /**
     * 任务标识
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务类型：
     * APPROVAL - 审批
     * CONDITION - 条件
     * PARALLEL - 并行
     */
    private String taskType;

    /**
     * 审批人/处理人
     */
    private String assignee;

    /**
     * 候选人
     */
    private String candidateUsers;

    /**
     * 任务状态：
     * PENDING - 待处理
     * SIGNED - 已签收
     * APPROVED - 已通过
     * REJECTED - 已驳回
     * DELEGATED - 已转办
     * CANCELLED - 已取消
     */
    private String status;

    /**
     * 审批意见
     */
    private String comment;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 签收时间
     */
    private LocalDateTime signTime;

    /**
     * 完成时间
     */
    private LocalDateTime completeTime;

    /**
     * 到期时间
     */
    private LocalDateTime dueTime;

    /**
     * 优先级：1-低 2-中 3-高
     */
    private Integer priority;

    /**
     * 节点配置JSON
     */
    private String nodeConfigJson;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建任务实例
     *
     * @param processId   流程实例ID
     * @param taskId     任务标识
     * @param taskName   任务名称
     * @param taskType   任务类型
     * @param assignee   处理人
     * @param tenantId   租户ID
     * @return 任务实例
     */
    public static MetaTaskInstance create(String processId, String taskId, String taskName,
                                          String taskType, String assignee, String tenantId) {
        MetaTaskInstance task = new MetaTaskInstance();
        task.setProcessId(processId);
        task.setTaskId(taskId);
        task.setTaskName(taskName);
        task.setTaskType(taskType);
        task.setAssignee(assignee);
        task.setStatus("PENDING");
        task.setPriority(2);
        task.setTenantId(tenantId);
        task.setCreateTime(LocalDateTime.now());
        return task;
    }

    /**
     * 签收任务
     *
     * @param assignee 签收人
     */
    public void sign(String assignee) {
        this.setAssignee(assignee);
        this.setStatus("SIGNED");
        this.setSignTime(LocalDateTime.now());
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 通过任务
     *
     * @param comment 审批意见
     */
    public void approve(String comment) {
        this.setStatus("APPROVED");
        this.setComment(comment);
        this.setCompleteTime(LocalDateTime.now());
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 驳回任务
     *
     * @param comment 驳回意见
     */
    public void reject(String comment) {
        this.setStatus("REJECTED");
        this.setComment(comment);
        this.setCompleteTime(LocalDateTime.now());
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 转办任务
     *
     * @param newAssignee 新处理人
     * @param comment     转办意见
     */
    public void delegate(String newAssignee, String comment) {
        this.setAssignee(newAssignee);
        this.setStatus("DELEGATED");
        this.setComment(comment);
        this.setCompleteTime(LocalDateTime.now());
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 取消任务
     */
    public void cancel() {
        this.setStatus("CANCELLED");
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 判断任务是否待处理
     *
     * @return 是否待处理
     */
    public boolean isPending() {
        return "PENDING".equals(status);
    }

    /**
     * 判断任务是否已完成
     *
     * @return 是否已完成
     */
    public boolean isCompleted() {
        return "APPROVED".equals(status) || "REJECTED".equals(status) || "DELEGATED".equals(status);
    }
}
