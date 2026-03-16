


package com.ssitao.code.modular.oauth2.server.entity;


import java.util.Set;

/**
 *
 */
public class SimpleAuthorizationCodeEntity implements AuthorizationCodeEntity {
    private String clientId;

    private String userId;

    private String code;

    private Long createTime;

    private Set<String> scope;

    private String redirectUri;

    @Override
    public String getRedirectUri() {
        return redirectUri;
    }

    @Override
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Override
    public Set<String> getScope() {
        return scope;
    }

    @Override
    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Long getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
