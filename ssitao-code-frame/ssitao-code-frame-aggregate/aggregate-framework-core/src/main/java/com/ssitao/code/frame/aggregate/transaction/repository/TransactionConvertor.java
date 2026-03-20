package com.ssitao.code.frame.aggregate.transaction.repository;

import com.ssitao.code.frame.aggregate.transaction.Transaction;
import com.ssitao.code.frame.aggregate.transaction.serializer.TransactionSerializer;
import com.ssitao.code.frame.aggregate.storage.TransactionStore;

public final class TransactionConvertor {

    private TransactionConvertor() {
    }

    public static Transaction getTransaction(TransactionSerializer serializer, TransactionStore transactionStore) {

        Transaction transaction = serializer.deserialize(transactionStore.getContent());

        transaction.setVersion(transactionStore.getVersion());
        transaction.setLastUpdateTime(transactionStore.getLastUpdateTime());
        transaction.setRetriedCount(transactionStore.getRetriedCount());
        transaction.setRowId(transactionStore.getId());

        return transaction;
    }

    public static TransactionStore getTransactionStore(TransactionSerializer serializer, String domain, Transaction transaction, boolean needContent) {

        TransactionStore transactionStore = new TransactionStore();
        transactionStore.setXid(transaction.getXid());
        if (needContent) {
            transactionStore.setContent(serializer.serialize(transaction));
        }
        transactionStore.setVersion(transaction.getVersion());
        transactionStore.setLastUpdateTime(transaction.getLastUpdateTime());
        transactionStore.setRetriedCount(transaction.getRetriedCount());
        transactionStore.setCreateTime(transaction.getCreateTime());
        transactionStore.setDomain(domain);
        transactionStore.setId(transaction.getRowId());

        return transactionStore;
    }

}
