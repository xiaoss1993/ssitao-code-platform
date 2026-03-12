package com.ssitao.code.modular.iam.authorization.application.service;

import com.ssitao.code.modular.iam.authorization.application.command.IamPermissionCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermissionUpdateCommand;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;

import java.util.List;

/**
 * IAM权限应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamPermissionAppService {

    /**
     * 创建权限
     *
     * @param command 创建命令
     * @return 权限ID
     */
    Long createPermission(IamPermissionCreateCommand command);

    /**
     * 批量创建权限
     *
     * @param commands 创建命令列表
     * @return 权限ID列表
     */
    List<Long> batchCreatePermissions(List<IamPermissionCreateCommand> commands);

    /**
     * 更新权限
     *
     * @param command 更新命令
     */
    void updatePermission(IamPermissionUpdateCommand command);

    /**
     * 删除权限
     *
     * @param id       权限ID
     * @param tenantId 租户ID
     */
    void deletePermission(Long id, String tenantId);

    /**
     * 根据ID获取权限
     *
     * @param id       权限ID
     * @param tenantId 租户ID
     * @return 权限DTO
     */
    IamPermissionDTO getPermissionById(Long id, String tenantId);

    /**
     * 获取权限列表
     *
     * @param tenantId 租户ID
     * @return 权限列表
     */
    List<IamPermissionDTO> listPermissions(String tenantId);

    /**
     * 获取权限树
     *
     * @param tenantId 租户ID
     * @return 权限树列表
     */
    List<IamPermissionDTO> getPermissionTree(String tenantId);

    /**
     * 根据类型获取权限列表
     *
     * @param permType 权限类型
     * @param tenantId 租户ID
     * @return 权限列表
     */
    List<IamPermissionDTO> listPermissionsByType(String permType, String tenantId);

    /**
     * 根据用户ID获取权限列表
     *
     * @param userId   用户ID
     * @param tenantId 租户ID
     * @return 权限列表
     */
    List<IamPermissionDTO> listPermissionsByUserId(Long userId, String tenantId);

    /**
     * 启用权限
     *
     * @param id       权限ID
     * @param tenantId 租户ID
     */
    void enablePermission(Long id, String tenantId);

    /**
     * 禁用权限
     *
     * @param id       权限ID
     * @param tenantId 租户ID
     */
    void disablePermission(Long id, String tenantId);

}
