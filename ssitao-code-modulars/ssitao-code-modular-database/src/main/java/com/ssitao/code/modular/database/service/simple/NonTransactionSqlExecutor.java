package com.ssitao.code.modular.database.service.simple;

import com.ssitao.code.modular.database.service.SqlExecuteRequest;
import com.ssitao.code.modular.database.service.SqlExecuteResult;
import com.ssitao.code.modular.database.service.SqlInfo;
import com.ssitao.code.modular.database.service.exception.SqlExecuteException;
import com.ssitao.code.modular.database.service.sql.SqlExecutor;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class NonTransactionSqlExecutor implements SqlExecutor {
    private com.tweb.frame.ezorm.rdb.executor.SqlExecutor executor;


    public NonTransactionSqlExecutor(com.tweb.frame.ezorm.rdb.executor.SqlExecutor executor) {
        this.executor = executor;
    }

    @Override
    public List<SqlExecuteResult> execute(SqlExecuteRequest request) throws Exception {
        return request.getSql().stream().map(this::doExecute).collect(Collectors.toList());
    }

    public SqlExecuteResult doExecute(SqlInfo sqlInfo) {
        SqlExecuteResult result = new SqlExecuteResult();
        Object executeResult = null;
        try {
            switch (sqlInfo.getType().toUpperCase()) {
                case "SELECT":
                    QueryResultWrapper wrapper = new QueryResultWrapper();
                    executor.list(sqlInfo.getSql(), wrapper);
                    executeResult = wrapper.getResult();
                    break;
                case "INSERT":
                case "UPDATE":
                    executeResult = executor.update(sqlInfo.getSql());
                    break;
                case "DELETE":
                    executeResult = executor.delete(sqlInfo.getSql());
                    break;
                default:
                    executor.exec(sqlInfo.getSql());
            }
            result.setSuccess(true);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage(), e, sqlInfo.getSql());
        }
        result.setResult(executeResult);
        result.setSqlInfo(sqlInfo);

        return result;
    }
}
