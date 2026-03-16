package com.ssitao.code.modular.database.service;

import com.ssitao.code.modular.database.service.meta.ObjectMetadata;
import com.ssitao.code.modular.database.service.sql.SqlExecutor;
import com.ssitao.code.modular.database.service.sql.TransactionSqlExecutor;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface DatabaseManagerService extends SqlExecutor, TransactionSqlExecutor {
    Map<ObjectMetadata.ObjectType, List<? extends ObjectMetadata>> getMetas();
}
