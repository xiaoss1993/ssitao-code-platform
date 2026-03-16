package com.ssitao.code.modular.datasource.service.simple;

import com.ssitao.code.modular.datasource.dao.DataSourceConfigDao;
import com.tweb.commons.service.EnableCacheGenericEntityService;
import com.ssitao.code.modular.datasource.entity.DataSourceConfigEntity;
import com.tweb.commons.utils.id.IDGenerator;
import com.ssitao.code.modular.datasource.service.DataSourceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 默认的服务实现
 *
 *
 */
@Service("dataSourceConfigService")
@CacheConfig(cacheNames = "data-source")
public class SimpleDataSourceConfigService extends EnableCacheGenericEntityService<DataSourceConfigEntity, String>
        implements DataSourceConfigService {
    @Autowired
    private DataSourceConfigDao dataSourceConfigDao;

    @Override
    protected IDGenerator<String> getIDGenerator() {
        return IDGenerator.MD5;
    }

    @Override
    public DataSourceConfigDao getDao() {
        return dataSourceConfigDao;
    }

}
