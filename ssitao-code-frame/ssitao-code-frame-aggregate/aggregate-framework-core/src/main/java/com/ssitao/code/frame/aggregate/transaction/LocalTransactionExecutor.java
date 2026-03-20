package com.ssitao.code.frame.aggregate.transaction;

import com.ssitao.code.frame.aggregate.domainevent.EventMessage;

import java.util.List;

/**
 * Created by changming.xie on 12/20/17.
 */
public interface LocalTransactionExecutor {

    public LocalTransactionState executeLocalTransactionBranch(List<EventMessage> events);
}
