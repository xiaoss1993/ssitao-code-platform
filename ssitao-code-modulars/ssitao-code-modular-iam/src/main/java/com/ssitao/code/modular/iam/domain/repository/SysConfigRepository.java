package com.ssitao.code.modular.iam.domain.repository;

import com.ssitao.code.frame.aggregate.repository.AggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysConfigAggregate;

/**
 * 参数配置仓储接口
 */
public interface SysConfigRepository extends AggregateRepository<SysConfigAggregate, Long> {

    /**
     * 根据参数键名查询
     */
    SysConfigAggregate findByConfigKey(String configKey);

    /**
     * 检查参数键名是否存在
     */
    boolean existsByConfigKey(String configKey);
}
