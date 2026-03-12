
package com.ssitao.code.frame.mybatisflex.codegen.config;

public interface ColumnConfigFactory {

    ColumnConfig getColumnConfig(String tableName, String columnName);
}
