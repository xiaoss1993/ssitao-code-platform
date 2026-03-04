package com.ssitao.code.modular.iam.organization.domain.event;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 部门负责人更新事件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartmentLeaderUpdatedEvent extends AbstractDomainEvent {

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 原负责人ID
     */
    private String oldLeaderId;

    /**
     * 新负责人ID
     */
    private String newLeaderId;

    /**
     * 新负责人姓名
     */
    private String newLeaderName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 构造函数
     */
    public DepartmentLeaderUpdatedEvent(String deptId, String oldLeaderId, String newLeaderId, String newLeaderName, String tenantId) {
        super(deptId, tenantId);
        this.deptId = deptId;
        this.oldLeaderId = oldLeaderId;
        this.newLeaderId = newLeaderId;
        this.newLeaderName = newLeaderName;
        this.tenantId = tenantId;
        this.setEventType("DepartmentLeaderUpdatedEvent");
        this.setAggregateType("IamDepartment");
    }
}
