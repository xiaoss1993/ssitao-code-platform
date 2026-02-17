
package com.ssitao.code.frame.mybatisflex.codegen.dialect.impl;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.ssitao.code.frame.mybatisflex.codegen.dialect.AbstractJdbcDialect;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;
import com.ssitao.code.frame.mybatisflex.core.util.ClassUtil;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;
import com.zaxxer.hikari.pool.HikariProxyConnection;
import oracle.jdbc.driver.OracleConnection;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ssitao
 */
public class OracleJdbcDialect extends AbstractJdbcDialect {

    @Override
    public String forBuildColumnsSql(String schema, String tableName) {
        return "SELECT * FROM \"" + (StringUtil.hasText(schema) ? schema + "\".\"" : "") + tableName + "\" WHERE rownum < 1";
    }

    @Override
    public ResultSet getTablesResultSet(DatabaseMetaData dbMeta, Connection conn, String schema, String[] types) throws SQLException {
        return dbMeta.getTables(conn.getCatalog(), StringUtil.hasText(schema) ? schema : dbMeta.getUserName(), null, types);
    }

    @Override
    protected ResultSet forRemarks(String schema, Table table, DatabaseMetaData dbMeta, Connection conn) throws SQLException {
        if (conn instanceof OracleConnection) {
            ((OracleConnection) conn).setRemarksReporting(true);
            return dbMeta.getColumns(conn.getCatalog(), StringUtil.hasText(schema) ? schema : dbMeta.getUserName(), table.getName(), null);
        } else if ("com.zaxxer.hikari.pool.HikariProxyConnection".equals(conn.getClass().getName())) {
            return forRemarks(schema, table, dbMeta, getOriginalConn(HikariProxyConnection.class, "delegate", conn));
        } else if ("com.alibaba.druid.pool.DruidPooledConnection".equals(conn.getClass().getName())) {
            return forRemarks(schema, table, dbMeta, getOriginalConn(DruidPooledConnection.class, "conn", conn));
        }
        return null;
    }

    private Connection getOriginalConn(Class<?> clazz, String attr, Connection conn) {
        Field delegate = ClassUtil.getFirstField(clazz, field -> field.getName().equals(attr));
        try {
            delegate.setAccessible(true);
            return (Connection) delegate.get(conn);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
