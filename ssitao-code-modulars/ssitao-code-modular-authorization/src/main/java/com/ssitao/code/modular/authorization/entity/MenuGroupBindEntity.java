
package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.commons.entity.TreeSortSupportEntity;

import java.util.List;

/**
 * 菜单分组关联 实体
 *
 *
 */
public interface MenuGroupBindEntity extends TreeSortSupportEntity<String> {
 /*-------------------------------------------
    |               属性名常量               |
    ===========================================*/
    /**
     * 树结构编码
     */
    String path      = "path";
    /**
     * 父级id
     */
    String parentId  = "parentId";
    /**
     * 树层级
     */
    String level     = "level";
    /**
     * 排序序号
     */
    String sortIndex = "sortIndex";
    /**
     * 状态
     */
    String status     = "status";
    /**
     * 菜单id
     */
    String menuId    = "menuId";
    /**
     * 分组id
     */
    String groupId   = "groupId";

    /**
     * @return 状态
     */
    Byte getStatus();

    /**
     * 设置 状态
     */
    void setStatus(Byte status);

    /**
     * @return 菜单id
     */
    String getMenuId();

    /**
     * 设置 菜单id
     */
    void setMenuId(String menuId);

    /**
     * @return 分组id
     */
    String getGroupId();

    /**
     * 设置 分组id
     */
    void setGroupId(String groupId);


    void setChildren(List<MenuGroupBindEntity> children);
}
