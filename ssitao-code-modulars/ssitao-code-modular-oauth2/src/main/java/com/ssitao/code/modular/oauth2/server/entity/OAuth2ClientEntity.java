


package com.ssitao.code.modular.oauth2.server.entity;

import com.tweb.frame.authorization.User;
import com.tweb.frame.authorization.oauth2.server.client.OAuth2Client;
import com.tweb.commons.entity.GenericEntity;
import com.tweb.commons.entity.RecordCreationEntity;

import java.util.Set;

/**
 *
 */
public interface OAuth2ClientEntity extends GenericEntity<String>, OAuth2Client, RecordCreationEntity {

    // client_id
    @Override
    String getId();

    @Override
    String getName();

    void setName(String name);

    // client_secret
    @Override
    String getSecret();

    void setSecret(String secret);

    //redirect_uri
    @Override
    String getRedirectUri();

    void setRedirectUri(String redirectUri);

    /**
     * @return 客户端所有者
     * @see User#getId()
     */
    @Override
    String getOwnerId();

    void setOwnerId(String ownerId);

    String getDescribe();

    void setDescribe(String describe);

    String getType();

    void setType(String type);

    @Override
    Set<String> getSupportGrantTypes();

    @Override
    Set<String> getDefaultGrantScope();

    void setDefaultGrantScope(Set<String> defaultGrantScope);

    void setSupportGrantTypes(Set<String> supportGrantType);

    void setStatus(Byte status);
}
