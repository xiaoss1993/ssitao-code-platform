package com.ssitao.code.frame.aggregate.transaction.repository;

import com.ssitao.code.frame.aggregate.transaction.Transaction;
import com.ssitao.code.frame.aggregate.storage.Page;
import com.ssitao.code.frame.aggregate.xid.Xid;

import java.io.Closeable;
import java.util.Date;

/**
 * Created by changmingxie on 11/12/15.
 */
public interface TransactionRepository extends Closeable {

    String getDomain();

    int create(Transaction transaction);

    int update(Transaction transaction);

    int delete(Transaction transaction);

    Transaction findByXid(Xid xid);

    boolean supportRecovery();

    Page<Transaction> findAllUnmodifiedSince(Date date, String offset, int pageSize);

    @Override
    default void close() {

    }
}
