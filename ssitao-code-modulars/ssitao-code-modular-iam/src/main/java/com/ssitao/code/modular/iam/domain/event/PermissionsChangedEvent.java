package com.ssitao.code.modular.iam.domain.event;

import com.ssitao.code.frame.aggregate.domainevent.MessageType;
import com.ssitao.code.frame.aggregate.domainevent.SimpleEventMessage;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

/**
 * 角色权限变更领域事件
 */
@Getter
public class PermissionsChangedEvent extends SimpleEventMessage<PermissionsChangedEvent> {

    private static final long serialVersionUID = 1L;

    private final Long roleId;
    private final String roleKey;
    private final Set<String> permissions;
    private final String updateBy;
    private final Date updateTime;

    public PermissionsChangedEvent(Long roleId, String roleKey, Set<String> permissions, String updateBy, Date updateTime) {
        super(null, MessageType.DOMAIN_EVENT);
        this.roleId = roleId;
        this.roleKey = roleKey;
        this.permissions = permissions;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    @Override
    public Class<PermissionsChangedEvent> getPayloadType() {
        return PermissionsChangedEvent.class;
    }

    @Override
    public PermissionsChangedEvent getPayload() {
        return this;
    }
}
