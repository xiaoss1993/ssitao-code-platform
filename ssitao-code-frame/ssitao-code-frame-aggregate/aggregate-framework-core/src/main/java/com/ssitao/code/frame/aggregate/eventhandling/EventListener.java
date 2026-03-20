package com.ssitao.code.frame.aggregate.eventhandling;

import com.ssitao.code.frame.aggregate.domainevent.EventMessage;

import java.util.List;

/**
 * User: changming.xie
 * Date: 14-7-10
 * Time: 下午1:10
 */
public interface EventListener {

    List<EventInvokerEntry> matchHandler(List<EventMessage> event);
}
