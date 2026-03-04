package com.ssitao.code.modular.iam.organization.domain.repository;

import com.ssitao.code.modular.iam.organization.domain.model.IamGroup;

import java.util.List;
import java.util.Optional;

/**
 * IAM集团仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamGroupRepository {

    /**
     * 保存集团
     *
     * @param group 集团聚合根
     * @return 保存后的集团ID
     */
    String save(IamGroup group);

    /**
     * 更新集团
     *
     * @param group 集团聚合根
     */
    void update(IamGroup group);

    /**
     * 根据ID删除集团
     *
     * @param id 集团ID
     */
    void deleteById(String id);

    /**
     * 根据ID获取集团
     *
     * @param id 集团ID
     * @return 集团聚合根
     */
    Optional<IamGroup> findById(String id);

    /**
     * 根据租户ID获取集团列表
     *
     * @param tenantId 租户ID
     * @return 集团列表
     */
    List<IamGroup> findByTenantId(String tenantId);

    /**
     * 获取所有集团
     *
     * @return 集团列表
     */
    List<IamGroup> findAll();
}
