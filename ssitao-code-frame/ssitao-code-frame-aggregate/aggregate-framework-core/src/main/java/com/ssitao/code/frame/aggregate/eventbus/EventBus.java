package com.ssitao.code.frame.aggregate.eventbus;

import com.ssitao.code.frame.aggregate.domainevent.EventMessage;
import com.ssitao.code.frame.aggregate.eventhandling.EventListener;
import com.ssitao.code.frame.aggregate.transaction.LocalTransactionExecutor;

import java.util.List;

/**
 * User: changming.xie
 * Date: 14-7-10
 * Time: 下午5:14
 */
public interface EventBus {

    public void subscribe(EventListener eventListener);

    void publishInTransaction(List<EventMessage> messages, LocalTransactionExecutor localTransactionExecutor);
}
