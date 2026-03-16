package com.ssitao.code.modular.template.service.simple;

import com.ssitao.code.modular.template.dao.TemplateDao;
import com.ssitao.code.modular.template.service.TemplateService;
import com.ssitao.code.modular.template.entity.TemplateEntity;
import com.tweb.commons.service.EnableCacheGenericEntityService;
import com.tweb.commons.utils.id.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 默认的服务实现
 *
 *
 */
@Service("templateService")
@CacheConfig(cacheNames = "template")
public class SimpleTemplateService extends EnableCacheGenericEntityService<TemplateEntity, String>
        implements TemplateService {
    @Autowired
    private TemplateDao templateDao;

    @Override
    protected IDGenerator<String> getIDGenerator() {
        return IDGenerator.MD5;
    }

    @Override
    public TemplateDao getDao() {
        return templateDao;
    }

    @Override
    public int updateByPk(String id, TemplateEntity entity) {
        TemplateEntity old = selectByPk(id);
        assertNotNull(old);
        entity.setVersion(old.getVersion() + 1);
        return super.updateByPk(id, entity);
    }

    @Override
    public String insert(TemplateEntity entity) {
        entity.setVersion(1L);
        return super.insert(entity);
    }
}
