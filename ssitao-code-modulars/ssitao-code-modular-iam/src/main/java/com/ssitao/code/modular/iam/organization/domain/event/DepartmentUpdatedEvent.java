package com.ssitao.code.modular.iam.organization.domain.event;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 部门更新事件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartmentUpdatedEvent extends AbstractDomainEvent {

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 原部门名称
     */
    private String oldDeptName;

    /**
     * 新部门名称
     */
    private String newDeptName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 构造函数
     */
    public DepartmentUpdatedEvent(String deptId, String oldDeptName, String newDeptName, String tenantId) {
        super(deptId, tenantId);
        this.deptId = deptId;
        this.oldDeptName = oldDeptName;
        this.newDeptName = newDeptName;
        this.tenantId = tenantId;
        this.setEventType("DepartmentUpdatedEvent");
        this.setAggregateType("IamDepartment");
    }
}
