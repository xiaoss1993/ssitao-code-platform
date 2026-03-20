package com.ssitao.code.modular.iam.domain.event;

import com.ssitao.code.frame.aggregate.domainevent.MessageType;
import com.ssitao.code.frame.aggregate.domainevent.SimpleEventMessage;
import lombok.Getter;

import java.util.Date;

/**
 * 用户创建领域事件
 */
@Getter
public class UserCreatedEvent extends SimpleEventMessage<UserCreatedEvent> {

    private static final long serialVersionUID = 1L;

    private final Long userId;
    private final String loginName;
    private final String userName;
    private final Long deptId;
    private final String createBy;
    private final Date createTime;

    public UserCreatedEvent(Long userId, String loginName, String userName, Long deptId, String createBy, Date createTime) {
        super(null, MessageType.DOMAIN_EVENT);
        this.userId = userId;
        this.loginName = loginName;
        this.userName = userName;
        this.deptId = deptId;
        this.createBy = createBy;
        this.createTime = createTime;
    }

    @Override
    public Class<UserCreatedEvent> getPayloadType() {
        return UserCreatedEvent.class;
    }

    @Override
    public UserCreatedEvent getPayload() {
        return this;
    }
}
