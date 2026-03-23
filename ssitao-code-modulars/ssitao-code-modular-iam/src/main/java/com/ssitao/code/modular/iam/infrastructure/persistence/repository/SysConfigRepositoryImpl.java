package com.ssitao.code.modular.iam.infrastructure.persistence.repository;

import com.ssitao.code.frame.aggregate.repository.AbstractAggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysConfigAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysConfigRepository;
import com.ssitao.code.modular.iam.domain.SysConfig;
import com.ssitao.code.modular.iam.infrastructure.persistence.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 参数配置仓储实现
 */
@Repository
public class SysConfigRepositoryImpl extends AbstractAggregateRepository<SysConfigAggregate, Long> implements SysConfigRepository {

    @Autowired
    private SysConfigMapper configMapper;

    public SysConfigRepositoryImpl() {
        super(SysConfigAggregate.class);
    }

    @Override
    protected Collection<SysConfigAggregate> doSave(Collection<SysConfigAggregate> aggregates) {
        List<SysConfigAggregate> saved = new ArrayList<>();
        for (SysConfigAggregate aggregate : aggregates) {
            if (aggregate.isNew()) {
                configMapper.insertConfig(toEntity(aggregate));
            } else {
                configMapper.updateConfig(toEntity(aggregate));
            }
            saved.add(aggregate);
        }
        return saved;
    }

    @Override
    protected void doRemove(Collection<SysConfigAggregate> aggregates) {
        for (SysConfigAggregate aggregate : aggregates) {
            configMapper.deleteConfigById(aggregate.getConfigId());
        }
    }

    @Override
    protected SysConfigAggregate doFindOne(Long id) {
        SysConfig config = configMapper.selectConfigById(id);
        if (config == null) {
            return null;
        }
        return toAggregate(config);
    }

    @Override
    protected boolean doExists(Long id) {
        return configMapper.selectConfigById(id) != null;
    }

    @Override
    protected List<Long> doFindAllIds() {
        List<SysConfig> configs = configMapper.selectConfigList(new SysConfig());
        List<Long> ids = new ArrayList<>();
        if (configs != null) {
            for (SysConfig config : configs) {
                ids.add(config.getConfigId());
            }
        }
        return ids;
    }

    @Override
    protected List<SysConfigAggregate> doFindAll(Collection<Long> ids) {
        List<SysConfigAggregate> result = new ArrayList<>();
        for (Long id : ids) {
            SysConfigAggregate aggregate = doFindOne(id);
            if (aggregate != null) {
                result.add(aggregate);
            }
        }
        return result;
    }

    @Override
    protected long doCount() {
        return configMapper.selectConfigList(new SysConfig()).size();
    }

    @Override
    public SysConfigAggregate findByConfigKey(String configKey) {
        SysConfig config = configMapper.checkConfigKeyUnique(configKey);
        if (config == null) {
            return null;
        }
        return toAggregate(config);
    }

    @Override
    public boolean existsByConfigKey(String configKey) {
        return configMapper.checkConfigKeyUnique(configKey) != null;
    }

    /**
     * 将聚合根转换为实体
     */
    private SysConfig toEntity(SysConfigAggregate aggregate) {
        SysConfig config = new SysConfig();
        config.setConfigId(aggregate.getConfigId());
        config.setConfigName(aggregate.getConfigName());
        config.setConfigKey(aggregate.getConfigKey());
        config.setConfigValue(aggregate.getConfigValue());
        config.setConfigType(aggregate.getConfigType());
        config.setCreateBy(aggregate.getCreateBy());
        config.setCreateTime(aggregate.getCreateTime());
        config.setUpdateBy(aggregate.getUpdateBy());
        config.setUpdateTime(aggregate.getUpdateTime());
        config.setRemark(aggregate.getRemark());
        return config;
    }

    /**
     * 将实体转换为聚合根
     */
    private SysConfigAggregate toAggregate(SysConfig config) {
        SysConfigAggregate aggregate = new SysConfigAggregate();
        aggregate.setConfigId(config.getConfigId());
        aggregate.setConfigName(config.getConfigName());
        aggregate.setConfigKey(config.getConfigKey());
        aggregate.setConfigValue(config.getConfigValue());
        aggregate.setConfigType(config.getConfigType());
        aggregate.setCreateBy(config.getCreateBy());
        aggregate.setCreateTime(config.getCreateTime());
        aggregate.setUpdateBy(config.getUpdateBy());
        aggregate.setUpdateTime(config.getUpdateTime());
        aggregate.setRemark(config.getRemark());
        return aggregate;
    }
}
