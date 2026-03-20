package com.ssitao.code.modular.iam.domain.event;

import com.ssitao.code.frame.aggregate.domainevent.MessageType;
import com.ssitao.code.frame.aggregate.domainevent.SimpleEventMessage;
import lombok.Getter;

import java.util.Date;

/**
 * 部门创建领域事件
 */
@Getter
public class DeptCreatedEvent extends SimpleEventMessage<DeptCreatedEvent> {

    private static final long serialVersionUID = 1L;

    private final Long deptId;
    private final String deptName;
    private final Long parentId;
    private final String createBy;
    private final Date createTime;

    public DeptCreatedEvent(Long deptId, String deptName, Long parentId, String createBy, Date createTime) {
        super(null, MessageType.DOMAIN_EVENT);
        this.deptId = deptId;
        this.deptName = deptName;
        this.parentId = parentId;
        this.createBy = createBy;
        this.createTime = createTime;
    }

    @Override
    public Class<DeptCreatedEvent> getPayloadType() {
        return DeptCreatedEvent.class;
    }

    @Override
    public DeptCreatedEvent getPayload() {
        return this;
    }
}
