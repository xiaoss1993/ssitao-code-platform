
package com.ssitao.code.modular.organization.entity;

import com.ssitao.code.modular.common.entity.TreeSortSupportEntity;
import com.ssitao.code.modular.organization.authorization.access.DepartmentAttachEntity;
import com.ssitao.code.modular.organization.authorization.access.PositionAttachEntity;

import java.util.List;

/**
 * 职位 实体
 *
 *
 */
public interface PositionEntity extends TreeSortSupportEntity<String>, DepartmentAttachEntity, PositionAttachEntity {
 /*-------------------------------------------
    |               属性名常量               |
    ===========================================*/
    /**
     * 职位名称
     */
    String name         = "name";
    /**
     * 部门id
     */
    String departmentId = "departmentId";
    /**
     * 持有的角色
     */
    String roles        = "roles";
    /**
     * 备注
     */
    String remark       = "remark";
    /**
     * 父级id
     */
    String parentId     = "parentId";
    /**
     * 树结构编码
     */
    String path         = "path";
    /**
     * 排序索引
     */
    String sortIndex    = "sortIndex";
    /**
     * 级别
     */
    String level        = "level";

    /**
     * @return 职位名称
     */
    String getName();

    /**
     * 设置 职位名称
     */
    void setName(String name);

    /**
     * @return 持有的角色
     */
    List<String> getRoles();

    /**
     * 设置 持有的角色
     */
    void setRoles(List<String> roles);

    /**
     * @return 备注
     */
    String getRemark();

    /**
     * 设置 备注
     */
    void setRemark(String remark);

    void setChildren(List<PositionEntity> children);

    @Override
    default String getPositionId() {
        return getId();
    }

    @Override
    default void setPositionId(String positionId) {
        setId(positionId);
    }
}
