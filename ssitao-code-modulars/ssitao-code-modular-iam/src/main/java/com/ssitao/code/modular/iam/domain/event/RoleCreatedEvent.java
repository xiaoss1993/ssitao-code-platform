package com.ssitao.code.modular.iam.domain.event;

import com.ssitao.code.frame.aggregate.domainevent.MessageType;
import com.ssitao.code.frame.aggregate.domainevent.SimpleEventMessage;
import lombok.Getter;

import java.util.Date;

/**
 * 角色创建领域事件
 */
@Getter
public class RoleCreatedEvent extends SimpleEventMessage<RoleCreatedEvent> {

    private static final long serialVersionUID = 1L;

    private final Long roleId;
    private final String roleName;
    private final String roleKey;
    private final String createBy;
    private final Date createTime;

    public RoleCreatedEvent(Long roleId, String roleName, String roleKey, String createBy, Date createTime) {
        super(null, MessageType.DOMAIN_EVENT);
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.createBy = createBy;
        this.createTime = createTime;
    }

    @Override
    public Class<RoleCreatedEvent> getPayloadType() {
        return RoleCreatedEvent.class;
    }

    @Override
    public RoleCreatedEvent getPayload() {
        return this;
    }
}
