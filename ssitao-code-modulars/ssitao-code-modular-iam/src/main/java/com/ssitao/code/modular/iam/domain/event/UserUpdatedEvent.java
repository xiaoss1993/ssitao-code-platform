package com.ssitao.code.modular.iam.domain.event;

import com.ssitao.code.frame.aggregate.domainevent.MessageType;
import com.ssitao.code.frame.aggregate.domainevent.SimpleEventMessage;
import lombok.Getter;

import java.util.Date;

/**
 * 用户更新领域事件
 */
@Getter
public class UserUpdatedEvent extends SimpleEventMessage<UserUpdatedEvent> {

    private static final long serialVersionUID = 1L;

    private final Long userId;
    private final String loginName;
    private final String userName;
    private final String updateBy;
    private final Date updateTime;

    public UserUpdatedEvent(Long userId, String loginName, String userName, String updateBy, Date updateTime) {
        super(null, MessageType.DOMAIN_EVENT);
        this.userId = userId;
        this.loginName = loginName;
        this.userName = userName;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    @Override
    public Class<UserUpdatedEvent> getPayloadType() {
        return UserUpdatedEvent.class;
    }

    @Override
    public UserUpdatedEvent getPayload() {
        return this;
    }
}
