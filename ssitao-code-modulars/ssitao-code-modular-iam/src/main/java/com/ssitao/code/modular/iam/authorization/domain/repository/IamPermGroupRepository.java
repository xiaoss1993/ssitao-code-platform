package com.ssitao.code.modular.iam.authorization.domain.repository;

import com.ssitao.code.modular.iam.authorization.domain.model.IamPermGroup;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * IAM权限组仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamPermGroupRepository {

    /**
     * 保存权限组
     *
     * @param permGroup 权限组聚合根
     * @return 权限组ID
     */
    String save(IamPermGroup permGroup);

    /**
     * 更新权限组
     *
     * @param permGroup 权限组聚合根
     */
    void update(IamPermGroup permGroup);

    /**
     * 根据ID删除权限组
     *
     * @param id       权限组ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据ID查找权限组
     *
     * @param id       权限组ID
     * @param tenantId 租户ID
     * @return 权限组聚合根
     */
    Optional<IamPermGroup> findById(String id, String tenantId);

    /**
     * 根据权限组编码查找
     *
     * @param groupCode 权限组编码
     * @param tenantId  租户ID
     * @return 权限组聚合根
     */
    Optional<IamPermGroup> findByGroupCode(String groupCode, String tenantId);

    /**
     * 查找所有权限组
     *
     * @param tenantId 租户ID
     * @return 权限组列表
     */
    List<IamPermGroup> findAll(String tenantId);

    /**
     * 检查权限组编码是否存在
     *
     * @param groupCode 权限组编码
     * @param tenantId  租户ID
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByGroupCode(String groupCode, String tenantId, String excludeId);

    /**
     * 获取权限组的权限ID列表
     *
     * @param groupId  权限组ID
     * @param tenantId 租户ID
     * @return 权限ID集合
     */
    Set<String> getPermissionIds(String groupId, String tenantId);

    /**
     * 分配权限给权限组
     *
     * @param groupId      权限组ID
     * @param permissionIds 权限ID列表
     * @param tenantId     租户ID
     */
    void assignPermissions(String groupId, Set<String> permissionIds, String tenantId);

    /**
     * 移除权限组的权限
     *
     * @param groupId      权限组ID
     * @param permissionIds 权限ID列表
     * @param tenantId     租户ID
     */
    void removePermissions(String groupId, Set<String> permissionIds, String tenantId);

}
