package com.ssitao.code.modular.organization.authorization.access;

import com.ssitao.code.modular.common.entity.Entity;

/**
 * 关联部门信息的实体,实现此接口,才能对相应的操作进行部门相关的权限控制
 *
 *
 * @since 3.0
 */
public interface DepartmentAttachEntity extends Entity {

    /*-------------------------------------------
     |               属性名常量               |
     ===========================================*/
    String departmentId = "departmentId";

    /**
     * @return 部门ID
     */
    String getDepartmentId();

    void setDepartmentId(String departmentId);

    default String getDepartmentIdProperty() {
        return departmentId;
    }
}
