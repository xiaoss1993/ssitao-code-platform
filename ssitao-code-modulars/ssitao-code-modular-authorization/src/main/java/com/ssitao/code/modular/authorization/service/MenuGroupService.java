
package com.ssitao.code.modular.authorization.service;

import com.ssitao.code.commons.service.CrudService;
import com.ssitao.code.modular.authorization.entity.MenuEntity;
import com.ssitao.code.modular.authorization.entity.MenuGroupEntity;
import com.ssitao.code.commons.service.TreeService;

import java.util.List;

/**
 * 菜单分组 服务类
 *
 *
 */
public interface MenuGroupService extends
        CrudService<MenuGroupEntity, String>
        , TreeService<MenuGroupEntity, String> {

    List<MenuEntity> getMenuByGroupId(List<String> groupId);

    void enable(String id);

    void disable(String id);
}
