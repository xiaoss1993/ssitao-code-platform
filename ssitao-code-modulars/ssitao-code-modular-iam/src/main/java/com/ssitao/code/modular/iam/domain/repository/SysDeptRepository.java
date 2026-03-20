package com.ssitao.code.modular.iam.domain.repository;

import com.ssitao.code.frame.aggregate.repository.AggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysDeptAggregate;

import java.util.List;

/**
 * 部门仓储接口
 */
public interface SysDeptRepository extends AggregateRepository<SysDeptAggregate, Long> {

    /**
     * 根据父部门ID查询子部门列表
     */
    List<SysDeptAggregate> findByParentId(Long parentId);

    /**
     * 查询所有正常状态的部门
     */
    List<SysDeptAggregate> findAllNormal();

    /**
     * 检查部门名称是否存在
     */
    boolean existsByDeptNameAndParentId(String deptName, Long parentId);
}
