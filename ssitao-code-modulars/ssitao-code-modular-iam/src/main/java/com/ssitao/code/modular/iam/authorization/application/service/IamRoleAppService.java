package com.ssitao.code.modular.iam.authorization.application.service;

import com.ssitao.code.modular.iam.authorization.application.command.IamRoleCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleAssignPermissionCommand;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;

import java.util.List;

/**
 * IAM角色应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamRoleAppService {

    /**
     * 创建角色
     *
     * @param command 创建命令
     * @return 角色ID
     */
    Long createRole(IamRoleCreateCommand command);

    /**
     * 更新角色
     *
     * @param command 更新命令
     */
    void updateRole(IamRoleUpdateCommand command);

    /**
     * 删除角色
     *
     * @param id       角色ID
     * @param tenantId 租户ID
     */
    void deleteRole(Long id, String tenantId);

    /**
     * 根据ID获取角色
     *
     * @param id       角色ID
     * @param tenantId 租户ID
     * @return 角色DTO
     */
    IamRoleDTO getRoleById(Long id, String tenantId);

    /**
     * 获取角色列表
     *
     * @param tenantId 租户ID
     * @return 角色列表
     */
    List<IamRoleDTO> listRoles(String tenantId);

    /**
     * 获取角色树
     *
     * @param tenantId 租户ID
     * @return 角色树列表
     */
    List<IamRoleDTO> getRoleTree(String tenantId);

    /**
     * 分配权限给角色
     *
     * @param command 分配权限命令
     */
    void assignPermissions(IamRoleAssignPermissionCommand command);

    /**
     * 启用角色
     *
     * @param id       角色ID
     * @param tenantId 租户ID
     */
    void enableRole(Long id, String tenantId);

    /**
     * 禁用角色
     *
     * @param id       角色ID
     * @param tenantId
     */
    void disableRole(Long id, String tenantId);

}
