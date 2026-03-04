package com.ssitao.code.modular.iam.organization.domain.event;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 部门启用事件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartmentEnabledEvent extends AbstractDomainEvent {

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 构造函数
     */
    public DepartmentEnabledEvent(String deptId, String deptCode, String deptName, String tenantId) {
        super(deptId, tenantId);
        this.deptId = deptId;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.tenantId = tenantId;
        this.setEventType("DepartmentEnabledEvent");
        this.setAggregateType("IamDepartment");
    }
}
