


package com.ssitao.code.modular.oauth2.server.service.simple;

import com.tweb.commons.service.DefaultDSLQueryService;
import com.tweb.commons.service.DefaultDSLUpdateService;
import com.tweb.frame.authorization.oauth2.server.client.OAuth2Client;
import com.tweb.frame.authorization.oauth2.server.client.OAuth2ClientConfigRepository;
import com.ssitao.code.modular.oauth2.server.entity.SimpleOAuth2ClientEntity;
import com.tweb.commons.entity.DataStatus;
import com.tweb.commons.entity.param.QueryParamEntity;
import com.ssitao.code.modular.oauth2.server.dao.OAuth2ClientDao;
import com.tweb.commons.utils.id.IDGenerator;
import org.springframework.cache.annotation.*;

import java.util.List;

/**
 *
 */
@CacheConfig(cacheNames = "oauth2-client-config")
public class SimpleClientConfigRepository implements OAuth2ClientConfigRepository {
    private OAuth2ClientDao oAuth2ClientDao;

    public SimpleClientConfigRepository(OAuth2ClientDao oAuth2ClientDao) {
        this.oAuth2ClientDao = oAuth2ClientDao;
    }

    @Override
    @Cacheable(key = "'id:'+#id")
    public OAuth2Client getClientById(String id) {
        return DefaultDSLQueryService.createQuery(oAuth2ClientDao).where("id", id).single();
    }

    @Override
    @Cacheable(key = "'ownerId:'+#ownerId")
    public OAuth2Client getClientByOwnerId(String ownerId) {
        return DefaultDSLQueryService.createQuery(oAuth2ClientDao).where("ownerId", ownerId).single();
    }

    @Override
    @Caching(put = {
            @CachePut(key = "'ownerId:'+#result.ownerId"),
            @CachePut(key = "'id:'+#result.id")
    })
    public OAuth2Client save(OAuth2Client oAuth2Client) {
        OAuth2Client old = getClientById(oAuth2Client.getId());
        if (old != null) {
            DefaultDSLUpdateService
                    .createUpdate(oAuth2ClientDao, oAuth2Client)
                    .excludes("id", "createTime")
                    .where("id", oAuth2Client.getId()).exec();
        } else {
            oAuth2ClientDao.insert(((SimpleOAuth2ClientEntity) oAuth2Client));
        }
        return oAuth2Client;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "'ownerId:'+#result.ownerId", condition = "#result!=null"),
            @CacheEvict(key = "'id:'+#result.id", condition = "#result!=null")
    })
    public OAuth2Client remove(String id) {
        OAuth2Client old = getClientById(id);
        oAuth2ClientDao.deleteByPk(id);
        return old;
    }

    @Override
    public OAuth2Client newClient() {
        SimpleOAuth2ClientEntity clientEntity = SimpleOAuth2ClientEntity.builder()
                .build();
        clientEntity.setId(IDGenerator.MD5.generate());
        clientEntity.setSecret(IDGenerator.MD5.generate());
        clientEntity.setStatus(DataStatus.STATUS_ENABLED);
        clientEntity.setCreateTimeNow();
        return clientEntity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OAuth2Client> getAll() {
        QueryParamEntity entity = new QueryParamEntity();
        entity.setPaging(false);
        return (List) oAuth2ClientDao.query(entity);
    }
}
