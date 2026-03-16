


package com.ssitao.code.modular.oauth2.server.entity;

import lombok.*;
import com.tweb.commons.entity.SimpleGenericEntity;

import java.util.Set;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleOAuth2ClientEntity extends SimpleGenericEntity<String> implements OAuth2ClientEntity {
    private static final long serialVersionUID = -8370400980996896599L;
    private String name;

    private String secret;

    private String redirectUri;

    private String ownerId;

    private String creatorId;

    private Long createTime;

    private String type;

    private String describe;

    private Set<String> supportGrantTypes;

    private Set<String> defaultGrantScope;

    private Byte status;

}
