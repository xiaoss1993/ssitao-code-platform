package com.ssitao.code.modular.authorization.service.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.ssitao.code.modular.authorization.entity.UserEntity;

/**
 *
 * @since 3.0.4
 */
@Getter
@AllArgsConstructor
public class TotpTwoFactorCreatedEvent {
    private UserEntity userEntity;

    private String totpUrl;
}
