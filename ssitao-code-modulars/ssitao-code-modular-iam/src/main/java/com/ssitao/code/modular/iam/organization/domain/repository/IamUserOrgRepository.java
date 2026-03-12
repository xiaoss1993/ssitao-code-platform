package com.ssitao.code.modular.iam.organization.domain.repository;

import com.ssitao.code.modular.iam.organization.domain.model.IamUserOrg;

import java.util.List;
import java.util.Optional;

/**
 * IAM用户组织关联仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamUserOrgRepository {

    /**
     * 保存用户组织关联
     *
     * @param userOrg 用户组织关联
     * @return 保存后的用户组织关联ID
     */
    String save(IamUserOrg userOrg);

    /**
     * 根据用户ID删除用户组织关联
     *
     * @param userId 用户ID
     */
    void deleteByUserId(String userId);

    /**
     * 根据用户ID获取用户组织关联列表
     *
     * @param userId 用户ID
     * @return 用户组织关联列表
     */
    List<IamUserOrg> findByUserId(String userId);

    /**
     * 根据用户ID获取主组织
     *
     * @param userId 用户ID
     * @return 主组织
     */
    Optional<IamUserOrg> findMainByUserId(String userId);
}
