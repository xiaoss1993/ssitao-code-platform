
package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.commons.entity.TreeSortSupportEntity;

import java.util.List;

/**
 * 菜单分组 实体
 *
 *
 */
public interface MenuGroupEntity extends TreeSortSupportEntity<String> {
 /*-------------------------------------------
    |               属性名常量               |
    ===========================================*/
    /**
     * 分组名称
     */
    String name         = "name";
    /**
     * 分组描述
     */
    String describe     = "describe";
    /**
     * 是否默认
     */
    String defaultGroup = "defaultGroup";
    /**
     * 树结构编码
     */
    String path         = "path";
    /**
     * 父级id
     */
    String parentId     = "parentId";
    /**
     * 树层级
     */
    String level        = "level";
    /**
     * 排序序号
     */
    String sortIndex    = "sortIndex";
    /**
     * 状态
     */
    String status       = "status";

    /**
     * @return 分组名称
     */
    String getName();

    /**
     * 设置 分组名称
     */
    void setName(String name);

    /**
     * @return 分组描述
     */
    String getDescribe();

    /**
     * 设置 分组描述
     */
    void setDescribe(String describe);

    /**
     * @return 是否默认
     */
    Boolean getDefaultGroup();

    /**
     * 设置 是否默认
     */
    void setDefaultGroup(Boolean defaultGroup);

    Byte getStatus();

    void setStatus(Byte status);

    List<MenuGroupBindEntity> getBindInfo();

    void setBindInfo(List<MenuGroupBindEntity> bindInfo);
}
