package com.ssitao.code.modular.iam.domain.repository;

import com.ssitao.code.frame.aggregate.repository.AggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysMenuAggregate;

import java.util.List;

/**
 * 菜单仓储接口
 */
public interface SysMenuRepository extends AggregateRepository<SysMenuAggregate, Long> {

    /**
     * 根据父菜单ID查询子菜单
     */
    List<SysMenuAggregate> findByParentId(Long parentId);

    /**
     * 检查菜单名称是否存在
     */
    boolean existsByMenuNameAndParentId(String menuName, Long parentId);
}
