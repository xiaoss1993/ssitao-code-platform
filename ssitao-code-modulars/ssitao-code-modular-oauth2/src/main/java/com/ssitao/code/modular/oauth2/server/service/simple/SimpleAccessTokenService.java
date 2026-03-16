


package com.ssitao.code.modular.oauth2.server.service.simple;


import com.tweb.commons.service.DefaultDSLQueryService;
import com.tweb.commons.service.DefaultDSLUpdateService;
import com.ssitao.code.modular.oauth2.server.entity.OAuth2AccessEntity;
import com.tweb.frame.authorization.oauth2.server.OAuth2AccessToken;
import com.tweb.frame.authorization.oauth2.server.token.AccessTokenService;
import com.tweb.commons.entity.factory.EntityFactory;
import com.ssitao.code.modular.oauth2.server.dao.OAuth2AccessDao;
import com.tweb.commons.utils.id.IDGenerator;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 */
public class SimpleAccessTokenService implements AccessTokenService {

    private TokenGenerator tokenGenerator = IDGenerator.MD5::generate;

    private OAuth2AccessDao oAuth2AccessDao;

    private EntityFactory entityFactory;

    public SimpleAccessTokenService(OAuth2AccessDao oAuth2AccessDao, EntityFactory entityFactory) {
        this.oAuth2AccessDao = oAuth2AccessDao;
        this.entityFactory = entityFactory;
    }

    public SimpleAccessTokenService setTokenGenerator(TokenGenerator tokenGenerator) {
        if (tokenGenerator != null) {
            this.tokenGenerator = tokenGenerator;
        }
        return this;
    }

    @Override
    @Cacheable(cacheNames = "oauth2-access-token", key = "'cgo'+#token.clientId+#token.grantType+#token.ownerId")
    public OAuth2AccessToken tryGetOldToken(OAuth2AccessToken token) {
        OAuth2AccessToken old = DefaultDSLQueryService
                .createQuery(oAuth2AccessDao)
                .where("clientId", token.getClientId())
                .and("grantType", token.getGrantType())
                .and("ownerId", token.getOwnerId())
                .single();
        return old;
    }

    @Override
    public OAuth2AccessToken createToken() {
        OAuth2AccessEntity accessEntity = entityFactory.newInstance(OAuth2AccessEntity.class);
        accessEntity.setAccessToken(tokenGenerator.generate());
        accessEntity.setRefreshToken(tokenGenerator.generate());
        accessEntity.setCreateTime(System.currentTimeMillis());
        return accessEntity;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Cacheable(cacheNames = "oauth2-access-token", key = "'refresh:'+#refreshToken")
    public OAuth2AccessToken getTokenByRefreshToken(String refreshToken) {
        Assert.notNull(refreshToken, "refreshToken can not be null!");
        return DefaultDSLQueryService.createQuery(oAuth2AccessDao)
                .where("refreshToken", refreshToken).single();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Cacheable(cacheNames = "oauth2-access-token", key = "'token:'+#accessToken")
    public OAuth2AccessToken getTokenByAccessToken(String accessToken) {
        Assert.notNull(accessToken, "accessToken can not be null!");
        return DefaultDSLQueryService.createQuery(oAuth2AccessDao)
                .where("accessToken", accessToken).single();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Caching(put = {
            @CachePut(cacheNames = "oauth2-access-token", key = "'refresh:'+#result.refreshToken"),
            @CachePut(cacheNames = "oauth2-access-token", key = "'token:'+#result.accessToken"),
            @CachePut(cacheNames = "oauth2-access-token", key = "'cgo'+#result.clientId+#result.grantType+#result.ownerId")
    })
    public OAuth2AccessToken saveOrUpdateToken(OAuth2AccessToken token) {
        Assert.notNull(token, "token can not be null!");
        int total = DefaultDSLQueryService
                .createQuery(oAuth2AccessDao)
                .where("clientId", token.getClientId())
                .and("grantType", token.getGrantType())
                .and("ownerId", token.getOwnerId()).total();
        token.setUpdateTime(System.currentTimeMillis());
        if (total > 0) {
            DefaultDSLUpdateService
                    .createUpdate(oAuth2AccessDao, token)
                    .where("clientId", token.getClientId())
                    .and("grantType", token.getGrantType())
                    .and("ownerId", token.getOwnerId())
                    .exec();
        } else {
            token.setCreateTime(System.currentTimeMillis());
            oAuth2AccessDao.insert(((OAuth2AccessEntity) token));
        }

        return token;
    }
}
