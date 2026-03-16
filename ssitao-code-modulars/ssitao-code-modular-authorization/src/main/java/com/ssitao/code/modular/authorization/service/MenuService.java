

package com.ssitao.code.modular.authorization.service;


import com.ssitao.code.commons.service.CrudService;
import com.ssitao.code.commons.service.TreeService;
import com.ssitao.code.modular.authorization.entity.MenuEntity;

/**
 * 菜单服务类
 *
 *
 */
public interface MenuService
        extends CrudService<MenuEntity, String>
        , TreeService<MenuEntity, String> {

}
