package com.ssitao.code.modular.iam.authorization.application.service;

import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;

import java.util.List;

/**
 * IAM角色权限关联服务接口
 * 管理 tb_iam_role_permission 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamRolePermissionService {

    /**
     * 为角色分配权限
     */
    void assignPermissions(String roleId, List<String> permissionIds, String tenantId);

    /**
     * 撤销角色权限
     */
    void revokePermissions(String roleId, List<String> permissionIds, String tenantId);

    /**
     * 撤销角色所有权限
     */
    void revokeAllPermissions(String roleId, String tenantId);

    /**
     * 获取角色的权限列表
     */
    List<IamPermissionDTO> getRolePermissions(String roleId, String tenantId);

    /**
     * 获取权限下的角色列表
     */
    List<IamRoleDTO> getPermissionRoles(String permissionId, String tenantId);

    /**
     * 检查角色是否有指定权限
     */
    boolean checkPermission(String roleId, String permissionId, String tenantId);

    /**
     * 批量为多个角色分配权限
     */
    void batchAssignPermissions(List<String> roleIds, List<String> permissionIds, String tenantId);

}
