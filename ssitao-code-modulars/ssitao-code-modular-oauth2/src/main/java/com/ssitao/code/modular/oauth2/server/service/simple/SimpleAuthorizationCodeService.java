


package com.ssitao.code.modular.oauth2.server.service.simple;

import com.tweb.commons.service.DefaultDSLDeleteService;
import com.tweb.commons.service.DefaultDSLQueryService;
import com.ssitao.code.modular.oauth2.server.entity.AuthorizationCodeEntity;
import com.tweb.frame.authorization.oauth2.server.support.code.AuthorizationCode;
import com.tweb.frame.authorization.oauth2.server.support.code.AuthorizationCodeRequest;
import com.tweb.frame.authorization.oauth2.server.support.code.AuthorizationCodeService;
import com.tweb.commons.entity.factory.EntityFactory;
import com.ssitao.code.modular.oauth2.server.dao.AuthorizationCodeDao;
import com.tweb.commons.utils.id.IDGenerator;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * TODO 完成注释
 *
 *
 */
public class SimpleAuthorizationCodeService implements AuthorizationCodeService {
    private AuthorizationCodeDao authorizationCodeDao;
    private EntityFactory        entityFactory;
    private CodeGenerator codeGenerator = IDGenerator.MD5::generate;

    public SimpleAuthorizationCodeService(AuthorizationCodeDao authorizationCodeDao, EntityFactory entityFactory) {
        this.authorizationCodeDao = authorizationCodeDao;
        this.entityFactory = entityFactory;
    }

    public SimpleAuthorizationCodeService setCodeGenerator(CodeGenerator codeGenerator) {
        if (codeGenerator != null) {
            this.codeGenerator = codeGenerator;
        }
        return this;
    }

    @Override
    public String createAuthorizationCode(AuthorizationCodeRequest request) {
        AuthorizationCodeEntity codeEntity = entityFactory.newInstance(AuthorizationCodeEntity.class);
        codeEntity.setClientId(request.getClientId());
        codeEntity.setRedirectUri(request.getRedirectUri());
        codeEntity.setCreateTime(System.currentTimeMillis());
        codeEntity.setUserId(request.getUserId());
        codeEntity.setScope(request.getScope());
        codeEntity.setCode(codeGenerator.generate());
        authorizationCodeDao.insert(codeEntity);
        return codeEntity.getCode();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public AuthorizationCode consumeAuthorizationCode(String code) {
        AuthorizationCodeEntity codeEntity = DefaultDSLQueryService
                .createQuery(authorizationCodeDao)
                .where("code", code).single();
        //delete
        DefaultDSLDeleteService.createDelete(authorizationCodeDao)
                .where("code", code).exec();
        return codeEntity;
    }
}
