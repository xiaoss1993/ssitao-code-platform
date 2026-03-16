package com.ssitao.code.modular.authorization.service.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.ssitao.code.modular.authorization.entity.UserEntity;

/**
 * 用户密码发生修改时事件
 *
 *
 * @see org.springframework.context.event.EventListener
 * @see org.springframework.context.ApplicationEventPublisher
 * @since 3.0
 */
@AllArgsConstructor
@Getter
public class UserModifiedEvent {
    private UserEntity userEntity;

    private boolean passwordModified;

    private boolean roleModified;
}
