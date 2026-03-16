

package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.commons.entity.TreeSortSupportEntity;

import java.util.List;

/**
 *
 */
public interface MenuEntity
        extends TreeSortSupportEntity<String> {

    String getName();

    void setName(String name);

    String getDescribe();

    void setDescribe(String describe);

    String getPermissionId();

    void setPermissionId(String permissionId);

    String getUrl();

    void setUrl(String url);

    String getIcon();

    void setIcon(String icon);

    Byte getStatus();

    void setStatus(Byte status);

    void setChildren(List<MenuEntity> children);

    @Override
    MenuEntity clone();
}
