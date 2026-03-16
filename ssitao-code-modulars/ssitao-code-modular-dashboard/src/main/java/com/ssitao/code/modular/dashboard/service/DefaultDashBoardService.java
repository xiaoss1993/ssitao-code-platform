package com.ssitao.code.modular.dashboard.service;

import com.ssitao.code.commons.dao.CrudDao;
import com.ssitao.code.modular.dashboard.service.dao.DashBoardConfigDao;
import com.ssitao.code.commons.utils.id.IDGenerator;
import com.ssitao.code.commons.service.EnableCacheAllEvictGenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "hsweb:dashboard-conf")
public class DefaultDashBoardService extends EnableCacheAllEvictGenericEntityService<DashBoardConfigEntity, String> implements DashBoardService {

    @Autowired
    private DashBoardConfigDao dashBoardConfigDao;

    @Override
    protected IDGenerator<String> getIDGenerator() {
        return IDGenerator.MD5;
    }

    @Override
    public CrudDao<DashBoardConfigEntity, String> getDao() {
        return dashBoardConfigDao;
    }

    @Cacheable(key = "'all-defaults'")
    public List<DashBoardConfigEntity> selectAllDefaults() {
        return createQuery().where("defaultConfig", true).or().isNull("defaultConfig").listNoPaging();
    }
}
