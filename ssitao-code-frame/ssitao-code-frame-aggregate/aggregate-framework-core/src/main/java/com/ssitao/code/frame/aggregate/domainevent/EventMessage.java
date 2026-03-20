package com.ssitao.code.frame.aggregate.domainevent;

import java.io.Serializable;

/**
 * User: changming.xie
 * Date: 14-7-3
 * Time: 上午9:01
 */
public interface EventMessage extends Serializable {

    Class getPayloadType();

    Object getPayload();

    MessageType getMessageType();
}
