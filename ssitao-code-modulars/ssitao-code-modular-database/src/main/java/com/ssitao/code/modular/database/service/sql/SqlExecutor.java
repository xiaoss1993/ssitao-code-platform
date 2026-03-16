package com.ssitao.code.modular.database.service.sql;

import com.ssitao.code.modular.database.service.SqlExecuteRequest;
import com.ssitao.code.modular.database.service.SqlExecuteResult;

import java.util.List;

public interface SqlExecutor {
    List<SqlExecuteResult> execute(SqlExecuteRequest request)throws Exception;
}
