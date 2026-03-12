package com.ssitao.code.modular.iam.organization.domain.event;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 部门添加子节点事件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartmentChildAddedEvent extends AbstractDomainEvent {

    /**
     * 父部门ID
     */
    private String parentDeptId;

    /**
     * 子部门ID
     */
    private String childDeptId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 构造函数
     */
    public DepartmentChildAddedEvent(String parentDeptId, String childDeptId, String tenantId) {
        super(parentDeptId, tenantId);
        this.parentDeptId = parentDeptId;
        this.childDeptId = childDeptId;
        this.tenantId = tenantId;
        this.setEventType("DepartmentChildAddedEvent");
        this.setAggregateType("IamDepartment");
    }
}
