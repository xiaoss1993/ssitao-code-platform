
package com.ssitao.code.modular.organization.entity;

import com.ssitao.code.modular.common.entity.TreeSortSupportEntity;
import com.ssitao.code.modular.organization.authorization.access.DepartmentAttachEntity;
import com.ssitao.code.modular.organization.authorization.access.OrgAttachEntity;

import java.util.List;

/**
 * 部门 实体
 *
 *
 */
public interface DepartmentEntity extends  TreeSortSupportEntity<String>, OrgAttachEntity, DepartmentAttachEntity {
 /*-------------------------------------------
    |               属性名常量               |
    ===========================================*/
    /**
     * 名称
     */
    String name      = "name";
    /**
     * 所在组织id
     */
    String orgId     = "orgId";
    /**
     * 部门编码
     */
    String code      = "code";
    /**
     * 父级id
     */
    String parentId  = "parentId";
    /**
     * 树结构编码
     */
    String path      = "path";
    /**
     * 排序序号
     */
    String sortIndex = "sortIndex";
    /**
     * 状态
     */
    String status    = "status";
    /**
     * 级别
     */
    String level     = "level";

    /**
     * @return 名称
     */
    String getName();

    /**
     * 设置 名称
     */
    void setName(String name);

    /**
     * @return 部门编码
     */
    String getCode();

    /**
     * 设置 部门编码
     */
    void setCode(String code);

    /**
     * @return 状态
     */
    Byte getStatus();

    /**
     * 设置 状态
     */
    void setStatus(Byte status);

    void setChildren(List<DepartmentEntity> children);

    @Override
    default String getDepartmentId() {
        return getId();
    }

    @Override
    default void setDepartmentId(String departmentId) {
        setId(departmentId);
    }
}
