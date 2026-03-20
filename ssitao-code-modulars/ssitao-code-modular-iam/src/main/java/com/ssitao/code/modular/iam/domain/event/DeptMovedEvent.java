package com.ssitao.code.modular.iam.domain.event;

import com.ssitao.code.frame.aggregate.domainevent.MessageType;
import com.ssitao.code.frame.aggregate.domainevent.SimpleEventMessage;
import lombok.Getter;

import java.util.Date;

/**
 * 部门迁移领域事件
 */
@Getter
public class DeptMovedEvent extends SimpleEventMessage<DeptMovedEvent> {

    private static final long serialVersionUID = 1L;

    private final Long deptId;
    private final String deptName;
    private final Long oldParentId;
    private final Long newParentId;
    private final String ancestors;
    private final String updateBy;
    private final Date updateTime;

    public DeptMovedEvent(Long deptId, String deptName, Long oldParentId, Long newParentId,
                          String ancestors, String updateBy, Date updateTime) {
        super(null, MessageType.DOMAIN_EVENT);
        this.deptId = deptId;
        this.deptName = deptName;
        this.oldParentId = oldParentId;
        this.newParentId = newParentId;
        this.ancestors = ancestors;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    @Override
    public Class<DeptMovedEvent> getPayloadType() {
        return DeptMovedEvent.class;
    }

    @Override
    public DeptMovedEvent getPayload() {
        return this;
    }
}
