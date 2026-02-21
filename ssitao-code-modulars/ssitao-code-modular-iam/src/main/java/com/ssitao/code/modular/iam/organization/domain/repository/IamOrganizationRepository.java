package com.ssitao.code.modular.iam.organization.domain.repository;

import com.ssitao.code.modular.iam.organization.domain.model.IamOrganization;

import java.util.List;
import java.util.Optional;

/**
 * IAM组织仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamOrganizationRepository {

    /**
     * 保存组织
     *
     * @param organization 组织聚合根
     * @return 组织ID
     */
    String save(IamOrganization organization);

    /**
     * 批量保存组织
     *
     * @param organizations 组织列表
     * @return 组织ID列表
     */
    List<String> saveBatch(List<IamOrganization> organizations);

    /**
     * 更新组织
     *
     * @param organization 组织聚合根
     */
    void update(IamOrganization organization);

    /**
     * 根据ID删除组织
     *
     * @param id       组织ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据ID查找组织
     *
     * @param id       组织ID
     * @param tenantId 租户ID
     * @return 组织聚合根
     */
    Optional<IamOrganization> findById(String id, String tenantId);

    /**
     * 根据组织编码查找组织
     *
     * @param orgCode  组织编码
     * @param tenantId 租户ID
     * @return 组织聚合根
     */
    Optional<IamOrganization> findByOrgCode(String orgCode, String tenantId);

    /**
     * 查找所有组织
     *
     * @param tenantId 租户ID
     * @return 组织列表
     */
    List<IamOrganization> findAll(String tenantId);

    /**
     * 查找组织树
     *
     * @param tenantId 租户ID
     * @return 组织树列表
     */
    List<IamOrganization> findTree(String tenantId);

    /**
     * 根据类型查找组织
     *
     * @param orgType  组织类型
     * @param tenantId 租户ID
     * @return 组织列表
     */
    List<IamOrganization> findByType(String orgType, String tenantId);

    /**
     * 根据父ID查找子组织
     *
     * @param parentId  父组织ID
     * @param tenantId 租户ID
     * @return 组织列表
     */
    List<IamOrganization> findByParentId(String parentId, String tenantId);

    /**
     * 检查组织编码是否存在
     *
     * @param orgCode   组织编码
     * @param tenantId  租户ID
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByOrgCode(String orgCode, String tenantId, String excludeId);

}
