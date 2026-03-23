package com.ssitao.code.modular.iam.domain.repository;

import com.ssitao.code.frame.aggregate.repository.AggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysDictDataAggregate;

import java.util.List;

/**
 * 字典数据仓储接口
 */
public interface SysDictDataRepository extends AggregateRepository<SysDictDataAggregate, Long> {

    /**
     * 根据字典类型查询
     */
    List<SysDictDataAggregate> findByDictType(String dictType);

    /**
     * 根据字典类型和字典键值查询
     */
    SysDictDataAggregate findByDictTypeAndDictValue(String dictType, String dictValue);
}
