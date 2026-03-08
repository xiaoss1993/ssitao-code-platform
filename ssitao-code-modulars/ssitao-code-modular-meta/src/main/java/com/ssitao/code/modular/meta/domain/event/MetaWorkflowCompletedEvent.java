package com.ssitao.code.modular.meta.domain.event;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 工作流完成事件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MetaWorkflowCompletedEvent extends AbstractDomainEvent {

    /**
     * 流程定义ID
     */
    private String workflowId;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 流程编码
     */
    private String workflowCode;

    /**
     * 流程标题
     */
    private String title;

    /**
     * 发起人
     */
    private String initiator;

    /**
     * 完成原因
     */
    private String reason;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 构造函数
     */
    public MetaWorkflowCompletedEvent(String workflowId, String processInstanceId, String workflowCode,
                                     String title, String initiator, String reason, String tenantId) {
        super(processInstanceId, tenantId);
        this.workflowId = workflowId;
        this.processInstanceId = processInstanceId;
        this.workflowCode = workflowCode;
        this.title = title;
        this.initiator = initiator;
        this.reason = reason;
        this.tenantId = tenantId;
        this.setEventType("MetaWorkflowCompletedEvent");
        this.setAggregateType("MetaWorkflow");
    }
}
