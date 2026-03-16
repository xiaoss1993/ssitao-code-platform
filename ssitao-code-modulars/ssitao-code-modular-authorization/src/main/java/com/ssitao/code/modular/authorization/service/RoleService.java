package com.ssitao.code.modular.authorization.service;

import com.ssitao.code.commons.entity.DataStatus;
import com.ssitao.code.commons.service.CrudService;
import com.ssitao.code.modular.authorization.entity.RoleEntity;

/**
 * 角色服务,就是一个简单的crud
 *
 *
 * @since 3.0
 */
public interface RoleService extends CrudService<RoleEntity, String> {
    /**
     * 启用角色
     *
     * @param roleId 角色ID
     * @see RoleEntity#setStatus(Byte)
     * @see DataStatus#STATUS_ENABLED
     */
    void enable(String roleId);

    /**
     * 禁用角色
     *
     * @param roleId 角色ID
     * @see RoleEntity#setStatus(Byte)
     * @see DataStatus#STATUS_DISABLED
     */
    void disable(String roleId);
}
