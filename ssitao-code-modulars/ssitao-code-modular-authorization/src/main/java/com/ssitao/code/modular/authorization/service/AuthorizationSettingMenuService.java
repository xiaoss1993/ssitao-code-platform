package com.ssitao.code.modular.authorization.service;

import com.ssitao.code.commons.service.CrudService;
import com.ssitao.code.modular.authorization.entity.AuthorizationSettingEntity;
import com.ssitao.code.modular.authorization.entity.AuthorizationSettingMenuEntity;
import com.ssitao.code.commons.service.TreeService;

import java.util.List;

/**
 * 权限菜单设置
 *
 *
 * @see AuthorizationSettingService
 */
public interface AuthorizationSettingMenuService extends
        CrudService<AuthorizationSettingMenuEntity, String>
        , TreeService<AuthorizationSettingMenuEntity, String> {

    /**
     * 根据设置id删除菜单配置
     *
     * @param settingId 设置id  {@link AuthorizationSettingEntity#id}
     * @return 删除的数量
     */
    int deleteBySettingId(String settingId);

    /**
     * 获取设置id对应的所有权限菜单配置
     *
     * @param settingId 设置id {@link AuthorizationSettingEntity#id}
     * @return 永远不为nul .权限菜单设置,如果没有则返回空集合
     */
    List<AuthorizationSettingMenuEntity> selectBySettingId(String settingId);

    /**
     * 获取多个设置id对应的所有权限菜单配置
     *
     * @param settingId 设置id {@link AuthorizationSettingEntity#id}
     * @return 永远不为nul .权限菜单设置,如果没有则返回空集合
     */
    List<AuthorizationSettingMenuEntity> selectBySettingId(List<String> settingId);
}
