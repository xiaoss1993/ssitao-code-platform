package com.ssitao.code.modular.iam.domain.repository;

import com.ssitao.code.frame.aggregate.repository.AggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysDictTypeAggregate;

import java.util.List;

/**
 * 字典类型仓储接口
 */
public interface SysDictTypeRepository extends AggregateRepository<SysDictTypeAggregate, Long> {

    /**
     * 根据字典类型查询
     */
    SysDictTypeAggregate findByDictType(String dictType);

    /**
     * 检查字典类型是否存在
     */
    boolean existsByDictType(String dictType);
}
