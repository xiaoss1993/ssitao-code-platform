package com.ssitao.code.modular.iam.authorization.application.service;

import com.ssitao.code.modular.iam.authorization.application.command.IamPermGroupCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermGroupUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermGroupAssignPermissionCommand;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermGroupDTO;

import java.util.List;

/**
 * IAM权限组应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamPermGroupAppService {

    /**
     * 创建权限组
     *
     * @param command 创建命令
     * @return 权限组ID
     */
    Long createPermGroup(IamPermGroupCreateCommand command);

    /**
     * 更新权限组
     *
     * @param command 更新命令
     */
    void updatePermGroup(IamPermGroupUpdateCommand command);

    /**
     * 删除权限组
     *
     * @param id       权限组ID
     * @param tenantId 租户ID
     */
    void deletePermGroup(Long id, String tenantId);

    /**
     * 根据ID获取权限组
     *
     * @param id       权限组ID
     * @param tenantId 租户ID
     * @return 权限组DTO
     */
    IamPermGroupDTO getPermGroupById(Long id, String tenantId);

    /**
     * 获取权限组列表
     *
     * @param tenantId 租户ID
     * @return 权限组列表
     */
    List<IamPermGroupDTO> listPermGroups(String tenantId);

    /**
     * 分配权限给权限组
     *
     * @param command 分配权限命令
     */
    void assignPermissions(IamPermGroupAssignPermissionCommand command);

    /**
     * 启用权限组
     *
     * @param id       权限组ID
     * @param tenantId 租户ID
     */
    void enablePermGroup(Long id, String tenantId);

    /**
     * 禁用权限组
     *
     * @param id       权限组ID
     * @param tenantId 租户ID
     */
    void disablePermGroup(Long id, String tenantId);

}
