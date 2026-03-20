package com.ssitao.code.modular.iam.domain.repository;

import com.ssitao.code.frame.aggregate.repository.AggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysRoleAggregate;

/**
 * 角色仓储接口
 */
public interface SysRoleRepository extends AggregateRepository<SysRoleAggregate, Long> {

    /**
     * 根据角色Key查询角色
     */
    SysRoleAggregate findByRoleKey(String roleKey);

    /**
     * 检查角色Key是否存在
     */
    boolean existsByRoleKey(String roleKey);
}
