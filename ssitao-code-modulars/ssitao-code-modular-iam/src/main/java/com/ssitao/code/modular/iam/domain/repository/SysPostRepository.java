package com.ssitao.code.modular.iam.domain.repository;

import com.ssitao.code.frame.aggregate.repository.AggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysPostAggregate;

/**
 * 岗位仓储接口
 */
public interface SysPostRepository extends AggregateRepository<SysPostAggregate, Long> {

    /**
     * 根据岗位编码查询
     */
    SysPostAggregate findByPostCode(String postCode);

    /**
     * 根据岗位名称查询
     */
    SysPostAggregate findByPostName(String postName);

    /**
     * 检查岗位编码是否存在
     */
    boolean existsByPostCode(String postCode);

    /**
     * 检查岗位名称是否存在
     */
    boolean existsByPostName(String postName);
}
