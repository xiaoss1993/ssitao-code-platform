


package com.ssitao.code.modular.oauth2.server.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 *
 *
 */
@Getter
@Setter
public class SimpleOAuth2AccessEntity implements OAuth2AccessEntity {

    private static final long serialVersionUID = 2090466474249489203L;
    private String clientId;

    private String ownerId;

    private String accessToken;

    private String refreshToken;

    private Integer expiresIn;

    private Long createTime;

    private Long updateTime;

    private Set<String> scope;

    private String grantType;

}
