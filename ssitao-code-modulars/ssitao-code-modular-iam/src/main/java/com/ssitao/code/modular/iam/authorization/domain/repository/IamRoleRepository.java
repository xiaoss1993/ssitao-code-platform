package com.ssitao.code.modular.iam.authorization.domain.repository;

import com.ssitao.code.modular.iam.authorization.domain.model.IamRole;

import java.util.List;
import java.util.Optional;

/**
 * IAM角色仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamRoleRepository {

    /**
     * 保存角色
     *
     * @param role 角色聚合根
     * @return 角色ID
     */
    String save(IamRole role);

    /**
     * 更新角色
     *
     * @param role 角色聚合根
     */
    void update(IamRole role);

    /**
     * 根据ID删除角色
     *
     * @param id      角色ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据ID查找角色
     *
     * @param id       角色ID
     * @param tenantId 租户ID
     * @return 角色聚合根
     */
    Optional<IamRole> findById(String id, String tenantId);

    /**
     * 根据角色编码查找角色
     *
     * @param roleCode 角色编码
     * @param tenantId 租户ID
     * @return 角色聚合根
     */
    Optional<IamRole> findByRoleCode(String roleCode, String tenantId);

    /**
     * 查找所有角色
     *
     * @param tenantId 租户ID
     * @return 角色列表
     */
    List<IamRole> findAll(String tenantId);

    /**
     * 查找角色树
     *
     * @param tenantId 租户ID
     * @return 角色树列表
     */
    List<IamRole> findTree(String tenantId);

    /**
     * 根据用户ID查找角色列表
     *
     * @param userId   用户ID
     * @param tenantId 租户ID
     * @return 角色列表
     */
    List<IamRole> findByUserId(String userId, String tenantId);

    /**
     * 检查角色编码是否存在
     *
     * @param roleCode  角色编码
     * @param tenantId  租户ID
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByRoleCode(String roleCode, String tenantId, String excludeId);

    /**
     * 分配权限给角色
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID列表
     * @param tenantId      租户ID
     */
    void assignPermissions(String roleId, List<String> permissionIds, String tenantId);

    /**
     * 移除角色的权限
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID列表
     * @param tenantId      租户ID
     */
    void removePermissions(String roleId, List<String> permissionIds, String tenantId);

}
