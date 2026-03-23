package com.ssitao.code.modular.iam.infrastructure.persistence.repository;

import com.ssitao.code.frame.aggregate.repository.AbstractAggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysDictDataAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysDictDataRepository;
import com.ssitao.code.modular.iam.infrastructure.persistence.mapper.SysDictDataMapper;
import com.ssitao.code.common.core.domain.entity.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 字典数据仓储实现
 */
@Repository
public class SysDictDataRepositoryImpl extends AbstractAggregateRepository<SysDictDataAggregate, Long> implements SysDictDataRepository {

    @Autowired
    private SysDictDataMapper dictDataMapper;

    public SysDictDataRepositoryImpl() {
        super(SysDictDataAggregate.class);
    }

    @Override
    protected Collection<SysDictDataAggregate> doSave(Collection<SysDictDataAggregate> aggregates) {
        List<SysDictDataAggregate> saved = new ArrayList<>();
        for (SysDictDataAggregate aggregate : aggregates) {
            if (aggregate.isNew()) {
                dictDataMapper.insertDictData(toEntity(aggregate));
            } else {
                dictDataMapper.updateDictData(toEntity(aggregate));
            }
            saved.add(aggregate);
        }
        return saved;
    }

    @Override
    protected void doRemove(Collection<SysDictDataAggregate> aggregates) {
        List<String> ids = new ArrayList<>();
        for (SysDictDataAggregate aggregate : aggregates) {
            ids.add(String.valueOf(aggregate.getDictCode()));
        }
        dictDataMapper.deleteDictDataByIds(ids.toArray(new String[0]));
    }

    @Override
    protected SysDictDataAggregate doFindOne(Long id) {
        SysDictData dictData = dictDataMapper.selectDictDataById(id);
        if (dictData == null) {
            return null;
        }
        return toAggregate(dictData);
    }

    @Override
    protected boolean doExists(Long id) {
        return dictDataMapper.selectDictDataById(id) != null;
    }

    @Override
    protected List<Long> doFindAllIds() {
        List<SysDictData> dictDatas = dictDataMapper.selectDictDataList(new SysDictData());
        List<Long> ids = new ArrayList<>();
        if (dictDatas != null) {
            for (SysDictData dictData : dictDatas) {
                ids.add(dictData.getDictCode());
            }
        }
        return ids;
    }

    @Override
    protected List<SysDictDataAggregate> doFindAll(Collection<Long> ids) {
        List<SysDictDataAggregate> result = new ArrayList<>();
        for (Long id : ids) {
            SysDictDataAggregate aggregate = doFindOne(id);
            if (aggregate != null) {
                result.add(aggregate);
            }
        }
        return result;
    }

    @Override
    protected long doCount() {
        return dictDataMapper.selectDictDataList(new SysDictData()).size();
    }

    @Override
    public List<SysDictDataAggregate> findByDictType(String dictType) {
        List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType);
        List<SysDictDataAggregate> aggregates = new ArrayList<>();
        if (dictDatas != null) {
            for (SysDictData dictData : dictDatas) {
                aggregates.add(toAggregate(dictData));
            }
        }
        return aggregates;
    }

    @Override
    public SysDictDataAggregate findByDictTypeAndDictValue(String dictType, String dictValue) {
        List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (dictDatas != null) {
            for (SysDictData dictData : dictDatas) {
                if (dictData.getDictValue().equals(dictValue)) {
                    return toAggregate(dictData);
                }
            }
        }
        return null;
    }

    private SysDictData toEntity(SysDictDataAggregate aggregate) {
        SysDictData dictData = new SysDictData();
        dictData.setDictCode(aggregate.getDictCode());
        dictData.setDictSort(aggregate.getDictSort());
        dictData.setDictLabel(aggregate.getDictLabel());
        dictData.setDictValue(aggregate.getDictValue());
        dictData.setDictType(aggregate.getDictType());
        dictData.setCssClass(aggregate.getCssClass());
        dictData.setListClass(aggregate.getListClass());
        dictData.setIsDefault(aggregate.getIsDefault());
        dictData.setStatus(aggregate.getStatus());
        dictData.setCreateBy(aggregate.getCreateBy());
        dictData.setCreateTime(aggregate.getCreateTime());
        dictData.setUpdateBy(aggregate.getUpdateBy());
        dictData.setUpdateTime(aggregate.getUpdateTime());
        dictData.setRemark(aggregate.getRemark());
        return dictData;
    }

    private SysDictDataAggregate toAggregate(SysDictData dictData) {
        SysDictDataAggregate aggregate = new SysDictDataAggregate();
        aggregate.setDictCode(dictData.getDictCode());
        aggregate.setDictSort(dictData.getDictSort());
        aggregate.setDictLabel(dictData.getDictLabel());
        aggregate.setDictValue(dictData.getDictValue());
        aggregate.setDictType(dictData.getDictType());
        aggregate.setCssClass(dictData.getCssClass());
        aggregate.setListClass(dictData.getListClass());
        aggregate.setIsDefault(dictData.getIsDefault());
        aggregate.setStatus(dictData.getStatus());
        aggregate.setCreateBy(dictData.getCreateBy());
        aggregate.setCreateTime(dictData.getCreateTime());
        aggregate.setUpdateBy(dictData.getUpdateBy());
        aggregate.setUpdateTime(dictData.getUpdateTime());
        aggregate.setRemark(dictData.getRemark());
        return aggregate;
    }
}
