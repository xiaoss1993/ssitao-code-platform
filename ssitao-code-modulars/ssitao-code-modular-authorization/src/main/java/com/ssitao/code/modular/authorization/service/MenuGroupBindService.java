
package com.ssitao.code.modular.authorization.service;

import com.ssitao.code.commons.service.CrudService;
import com.ssitao.code.commons.service.TreeService;
import com.ssitao.code.modular.authorization.entity.MenuGroupBindEntity;

/**
 * 菜单分组关联 服务类
 *
 *
 */
public interface MenuGroupBindService extends
        CrudService<MenuGroupBindEntity, String>
        , TreeService<MenuGroupBindEntity, String> {

    int deleteByGroupId(String groupId);
}
