package com.ssitao.code.modular.iam.domain.event;

import com.ssitao.code.frame.aggregate.domainevent.MessageType;
import com.ssitao.code.frame.aggregate.domainevent.SimpleEventMessage;
import lombok.Getter;

import java.util.Date;

/**
 * 密码变更领域事件
 */
@Getter
public class PasswordChangedEvent extends SimpleEventMessage<PasswordChangedEvent> {

    private static final long serialVersionUID = 1L;

    private final Long userId;
    private final String loginName;
    private final Date changeTime;

    public PasswordChangedEvent(Long userId, String loginName, Date changeTime) {
        super(null, MessageType.DOMAIN_EVENT);
        this.userId = userId;
        this.loginName = loginName;
        this.changeTime = changeTime;
    }

    @Override
    public Class<PasswordChangedEvent> getPayloadType() {
        return PasswordChangedEvent.class;
    }

    @Override
    public PasswordChangedEvent getPayload() {
        return this;
    }
}
