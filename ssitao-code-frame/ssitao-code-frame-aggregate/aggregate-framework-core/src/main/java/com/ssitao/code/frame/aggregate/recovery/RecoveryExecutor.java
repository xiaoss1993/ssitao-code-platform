package com.ssitao.code.frame.aggregate.recovery;

import com.ssitao.code.frame.aggregate.storage.TransactionStore;

public interface RecoveryExecutor {

    void recover(TransactionStore transactionStore);

    byte[] transactionVisualize(String domain, byte[] content);
}
