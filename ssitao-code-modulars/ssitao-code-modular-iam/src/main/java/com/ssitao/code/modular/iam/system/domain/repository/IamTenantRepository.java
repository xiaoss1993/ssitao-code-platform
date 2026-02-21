package com.ssitao.code.modular.iam.system.domain.repository;

import com.ssitao.code.modular.iam.system.domain.model.IamTenant;

import java.util.List;
import java.util.Optional;

/**
 * IAM租户仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamTenantRepository {

    /**
     * 保存租户
     *
     * @param tenant 租户聚合根
     * @return 租户ID
     */
    String save(IamTenant tenant);

    /**
     * 更新租户
     *
     * @param tenant 租户聚合根
     */
    void update(IamTenant tenant);

    /**
     * 根据ID删除租户
     *
     * @param id 租户ID
     */
    void deleteById(String id);

    /**
     * 根据ID查找租户
     *
     * @param id 租户ID
     * @return 租户聚合根
     */
    Optional<IamTenant> findById(String id);

    /**
     * 根据租户编码查找租户
     *
     * @param tenantCode 租户编码
     * @return 租户聚合根
     */
    Optional<IamTenant> findByTenantCode(String tenantCode);

    /**
     * 根据域名查找租户
     *
     * @param domain 域名
     * @return 租户聚合根
     */
    Optional<IamTenant> findByDomain(String domain);

    /**
     * 查找所有租户
     *
     * @return 租户列表
     */
    List<IamTenant> findAll();

    /**
     * 根据状态查找租户
     *
     * @param tenantStatus 租户状态
     * @return 租户列表
     */
    List<IamTenant> findByStatus(String tenantStatus);

    /**
     * 查找过期租户
     *
     * @return 租户列表
     */
    List<IamTenant> findExpired();

    /**
     * 检查租户编码是否存在
     *
     * @param tenantCode 租户编码
     * @param excludeId  排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByTenantCode(String tenantCode, String excludeId);

    /**
     * 检查域名是否存在
     *
     * @param domain    域名
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByDomain(String domain, String excludeId);

}
