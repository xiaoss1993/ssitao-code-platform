
package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.commons.entity.TreeSortSupportEntity;

import java.util.List;

/**
 * 权限菜单 实体
 *
 *
 */
public interface AuthorizationSettingMenuEntity extends TreeSortSupportEntity<String> {
 /*-------------------------------------------
    |               属性名常量               |
    ===========================================*/
    /**
     * 菜单id
     */
    String menuId    = "menuId";
    /**
     * 设置id
     */
    String settingId = "settingId";
    /**
     * 排序
     */
    String sortIndex = "sortIndex";
    /**
     * 上级id
     */
    String parentId  = "parentId";
    /**
     * 状态
     */
    String status     = "status";
    /**
     * 树路径
     */
    String path      = "path";
    /**
     * 树层级
     */
    String level     = "level";
    /**
     * 其他配置内容
     */
    String config    = "config";

    /**
     * @return 菜单id
     */
    String getMenuId();

    /**
     * 设置 菜单id
     */
    void setMenuId(String menuId);

    /**
     * @return 设置id
     */
    String getSettingId();

    /**
     * 设置 设置id
     */
    void setSettingId(String settingId);

    /**
     * @return 状态
     */
    Byte getStatus();

    /**
     * 设置 状态
     */
    void setStatus(Byte status);

    /**
     * @return 其他配置内容
     */
    String getConfig();

    /**
     * 设置 其他配置内容
     */
    void setConfig(String config);

    void setChildren(List<AuthorizationSettingMenuEntity> children);
}
