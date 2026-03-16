package com.ssitao.code.modular.database.service.simple;

import com.ssitao.code.modular.database.service.SqlExecuteRequest;
import com.ssitao.code.modular.database.service.SqlExecuteResult;

import java.util.List;

/**
 *
 */
public interface TransactionExecutor extends Runnable {
    String getTransactionId();

    String getDatasourceId();

    void commit();

    void rollback();

    boolean isRunning();

    List<SqlExecuteResult> execute(SqlExecuteRequest request)throws Exception;

}
