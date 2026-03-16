package com.ssitao.code.modular.oauth2.server.starter;

import lombok.AllArgsConstructor;
import com.tweb.frame.authorization.oauth2.server.event.OAuth2GrantEvent;
import com.tweb.frame.authorization.token.UserTokenManager;
import org.springframework.context.event.EventListener;

/**
 *
 * @since 1.0
 */
@AllArgsConstructor
public class OAuth2GrantEventListener {

    private UserTokenManager userTokenManager;

    @EventListener
    public void handleOAuth2GrantEvent(OAuth2GrantEvent event) {
        userTokenManager.signIn(
                event.getAccessToken().getAccessToken(),
                "oauth2-access-token",
                event.getAccessToken().getOwnerId(),
                event.getAccessToken().getExpiresIn() * 1000L);

    }
}
