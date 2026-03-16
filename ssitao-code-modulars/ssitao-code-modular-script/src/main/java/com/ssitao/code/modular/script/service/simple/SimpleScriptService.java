package com.ssitao.code.modular.script.service.simple;

import com.ssitao.code.modular.script.dao.ScriptDao;
import com.tweb.commons.service.EnableCacheGenericEntityService;
import com.ssitao.code.modular.script.entity.ScriptEntity;
import com.tweb.commons.utils.id.IDGenerator;
import com.ssitao.code.modular.script.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 默认的服务实现
 *
 *
 */
@Service("scriptService")
@CacheConfig(cacheNames = "dynamic-script")
public class SimpleScriptService extends EnableCacheGenericEntityService<ScriptEntity, String>
        implements ScriptService {
    @Autowired
    private ScriptDao scriptDao;

    @Override
    protected IDGenerator<String> getIDGenerator() {
        return IDGenerator.MD5;
    }

    @Override
    public ScriptDao getDao() {
        return scriptDao;
    }

}
