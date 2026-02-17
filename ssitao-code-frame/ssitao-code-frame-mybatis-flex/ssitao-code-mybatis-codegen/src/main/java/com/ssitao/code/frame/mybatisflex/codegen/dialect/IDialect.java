
package com.ssitao.code.frame.mybatisflex.codegen.dialect;

import com.ssitao.code.frame.mybatisflex.codegen.config.GlobalConfig;
import com.ssitao.code.frame.mybatisflex.codegen.dialect.impl.DefaultJdbcDialect;
import com.ssitao.code.frame.mybatisflex.codegen.dialect.impl.MySqlJdbcDialect;
import com.ssitao.code.frame.mybatisflex.codegen.dialect.impl.OracleJdbcDialect;
import com.ssitao.code.frame.mybatisflex.codegen.dialect.impl.SqliteDialect;
import com.ssitao.code.frame.mybatisflex.codegen.dialect.impl.PostgreSQLJdbcDialect;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 方言接口。
 * @author michael
 * @author Suomm
 */
public interface IDialect {

    /**
     * 默认方言。
     */
    IDialect DEFAULT = new DefaultJdbcDialect();

    /**
     * MySQL 方言。
     */
    IDialect MYSQL = new MySqlJdbcDialect();

    /**
     * Oracle 方言。
     */
    IDialect ORACLE = new OracleJdbcDialect();

    /**
     * Sqlite 方言。
     */
    IDialect SQLITE = new SqliteDialect();

    /**
     * PostgreSQL 方言。
     */
    IDialect POSTGRESQL = new PostgreSQLJdbcDialect();

    /**
     * 构建表和列的信息。
     *
     * @param schemaName
     * @param table        存入的表对象
     * @param globalConfig 全局配置
     * @param dbMeta       数据库元数据
     * @param conn         连接
     * @throws SQLException 发生 SQL 异常时抛出
     */
    void buildTableColumns(String schemaName, Table table, GlobalConfig globalConfig, DatabaseMetaData dbMeta, Connection conn) throws SQLException;

    /**
     * 获取表的描述信息。
     *
     * @param dbMeta 数据库元数据
     * @param conn   连接
     * @param schema 模式
     * @param types  结果集类型
     * @return 结果集
     * @throws SQLException 发生 SQL 异常时抛出
     */
    ResultSet getTablesResultSet(DatabaseMetaData dbMeta, Connection conn, String schema, String[] types) throws SQLException;

}
