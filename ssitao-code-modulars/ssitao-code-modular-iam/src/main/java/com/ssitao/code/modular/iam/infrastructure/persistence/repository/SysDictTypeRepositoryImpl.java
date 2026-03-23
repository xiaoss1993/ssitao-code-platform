package com.ssitao.code.modular.iam.infrastructure.persistence.repository;

import com.ssitao.code.frame.aggregate.repository.AbstractAggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysDictTypeAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysDictTypeRepository;
import com.ssitao.code.modular.iam.infrastructure.persistence.mapper.SysDictTypeMapper;
import com.ssitao.code.common.core.domain.entity.SysDictType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 字典类型仓储实现
 */
@Repository
public class SysDictTypeRepositoryImpl extends AbstractAggregateRepository<SysDictTypeAggregate, Long> implements SysDictTypeRepository {

    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    public SysDictTypeRepositoryImpl() {
        super(SysDictTypeAggregate.class);
    }

    @Override
    protected Collection<SysDictTypeAggregate> doSave(Collection<SysDictTypeAggregate> aggregates) {
        List<SysDictTypeAggregate> saved = new ArrayList<>();
        for (SysDictTypeAggregate aggregate : aggregates) {
            if (aggregate.isNew()) {
                dictTypeMapper.insertDictType(toEntity(aggregate));
            } else {
                dictTypeMapper.updateDictType(toEntity(aggregate));
            }
            saved.add(aggregate);
        }
        return saved;
    }

    @Override
    protected void doRemove(Collection<SysDictTypeAggregate> aggregates) {
        List<Long> ids = new ArrayList<>();
        for (SysDictTypeAggregate aggregate : aggregates) {
            ids.add(aggregate.getDictId());
        }
        dictTypeMapper.deleteDictTypeByIds(ids.toArray(new Long[0]));
    }

    @Override
    protected SysDictTypeAggregate doFindOne(Long id) {
        SysDictType dictType = dictTypeMapper.selectDictTypeById(id);
        if (dictType == null) {
            return null;
        }
        return toAggregate(dictType);
    }

    @Override
    protected boolean doExists(Long id) {
        return dictTypeMapper.selectDictTypeById(id) != null;
    }

    @Override
    protected List<Long> doFindAllIds() {
        List<SysDictType> dictTypes = dictTypeMapper.selectDictTypeList(new SysDictType());
        List<Long> ids = new ArrayList<>();
        if (dictTypes != null) {
            for (SysDictType dictType : dictTypes) {
                ids.add(dictType.getDictId());
            }
        }
        return ids;
    }

    @Override
    protected List<SysDictTypeAggregate> doFindAll(Collection<Long> ids) {
        List<SysDictTypeAggregate> result = new ArrayList<>();
        for (Long id : ids) {
            SysDictTypeAggregate aggregate = doFindOne(id);
            if (aggregate != null) {
                result.add(aggregate);
            }
        }
        return result;
    }

    @Override
    protected long doCount() {
        return dictTypeMapper.selectDictTypeList(new SysDictType()).size();
    }

    @Override
    public SysDictTypeAggregate findByDictType(String dictType) {
        SysDictType type = dictTypeMapper.selectDictTypeByType(dictType);
        if (type == null) {
            return null;
        }
        return toAggregate(type);
    }

    @Override
    public boolean existsByDictType(String dictType) {
        return dictTypeMapper.selectDictTypeByType(dictType) != null;
    }

    private SysDictType toEntity(SysDictTypeAggregate aggregate) {
        SysDictType dictType = new SysDictType();
        dictType.setDictId(aggregate.getDictId());
        dictType.setDictName(aggregate.getDictName());
        dictType.setDictType(aggregate.getDictType());
        dictType.setStatus(aggregate.getStatus());
        dictType.setCreateBy(aggregate.getCreateBy());
        dictType.setCreateTime(aggregate.getCreateTime());
        dictType.setUpdateBy(aggregate.getUpdateBy());
        dictType.setUpdateTime(aggregate.getUpdateTime());
        dictType.setRemark(aggregate.getRemark());
        return dictType;
    }

    private SysDictTypeAggregate toAggregate(SysDictType dictType) {
        SysDictTypeAggregate aggregate = new SysDictTypeAggregate();
        aggregate.setDictId(dictType.getDictId());
        aggregate.setDictName(dictType.getDictName());
        aggregate.setDictType(dictType.getDictType());
        aggregate.setStatus(dictType.getStatus());
        aggregate.setCreateBy(dictType.getCreateBy());
        aggregate.setCreateTime(dictType.getCreateTime());
        aggregate.setUpdateBy(dictType.getUpdateBy());
        aggregate.setUpdateTime(dictType.getUpdateTime());
        aggregate.setRemark(dictType.getRemark());
        return aggregate;
    }
}
