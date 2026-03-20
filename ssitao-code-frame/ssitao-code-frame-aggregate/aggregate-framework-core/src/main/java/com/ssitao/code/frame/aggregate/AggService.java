package com.ssitao.code.frame.aggregate;

import com.ssitao.code.frame.aggregate.recovery.TransactionStoreRecovery;

public interface AggService {

    void start() throws Exception;

    void shutdown() throws Exception;

    TransactionStoreRecovery getTransactionStoreRecovery();
}
