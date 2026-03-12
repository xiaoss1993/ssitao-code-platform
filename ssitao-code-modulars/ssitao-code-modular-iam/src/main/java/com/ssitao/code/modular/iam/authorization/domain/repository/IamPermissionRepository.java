package com.ssitao.code.modular.iam.authorization.domain.repository;

import com.ssitao.code.modular.iam.authorization.domain.model.IamPermission;

import java.util.List;
import java.util.Optional;

/**
 * IAM权限仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamPermissionRepository {

    /**
     * 保存权限
     *
     * @param permission 权限聚合根
     * @return 权限ID
     */
    String save(IamPermission permission);

    /**
     * 批量保存权限
     *
     * @param permissions 权限列表
     * @return 权限ID列表
     */
    List<String> saveBatch(List<IamPermission> permissions);

    /**
     * 更新权限
     *
     * @param permission 权限聚合根
     */
    void update(IamPermission permission);

    /**
     * 根据ID删除权限
     *
     * @param id       权限ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据ID查找权限
     *
     * @param id       权限ID
     * @param tenantId 租户ID
     * @return 权限聚合根
     */
    Optional<IamPermission> findById(String id, String tenantId);

    /**
     * 根据权限编码查找权限
     *
     * @param permCode 权限编码
     * @param tenantId 租户ID
     * @return 权限聚合根
     */
    Optional<IamPermission> findByPermCode(String permCode, String tenantId);

    /**
     * 查找所有权限
     *
     * @param tenantId 租户ID
     * @return 权限列表
     */
    List<IamPermission> findAll(String tenantId);

    /**
     * 查找权限树
     *
     * @param tenantId 租户ID
     * @return 权限树列表
     */
    List<IamPermission> findTree(String tenantId);

    /**
     * 根据类型查找权限
     *
     * @param permType 权限类型
     * @param tenantId 租户ID
     * @return 权限列表
     */
    List<IamPermission> findByType(String permType, String tenantId);

    /**
     * 根据父ID查找子权限
     *
     * @param parentId 父权限ID
     * @param tenantId 租户ID
     * @return 权限列表
     */
    List<IamPermission> findByParentId(String parentId, String tenantId);

    /**
     * 根据角色ID查找权限列表
     *
     * @param roleId   角色ID
     * @param tenantId 租户ID
     * @return 权限列表
     */
    List<IamPermission> findByRoleId(String roleId, String tenantId);

    /**
     * 根据用户ID查找权限列表
     *
     * @param userId   用户ID
     * @param tenantId 租户ID
     * @return 权限列表
     */
    List<IamPermission> findByUserId(String userId, String tenantId);

    /**
     * 检查权限编码是否存在
     *
     * @param permCode  权限编码
     * @param tenantId  租户ID
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByPermCode(String permCode, String tenantId, String excludeId);

}
