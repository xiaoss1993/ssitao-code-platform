package org.tweb.mybatis.rdb.dialect;

import org.tweb.mybatis.core.dsl.condition.TermType;

/**
 * 数据库方言接口
 * <p>
 * 定义了不同数据库的差异处理，包括：
 * <ul>
 *   <li>标识符引用（MySQL: `, Oracle: ", SQL Server: [）</li>
 *   <li>分页语句（LIMIT/OFFSET, ROWNUM, ROW_NUMBER()）</li>
 *   <li>函数调用（NOW(), SYSDATE, GETDATE）</li>
 *   <li>条件渲染（LIKE, IN, BETWEEN 等）</li>
 * </ul>
 *
 * @author ssitao
 * @since 1.0.0
 */
public interface Dialect {

    /**
     * 获取方言名称
     *
     * @return 方言名称（如 MySQL, Oracle, PostgreSQL）
     */
    String getName();

    /**
     * 获取标识符引用开始符
     * <p>
     * MySQL: `
     * Oracle: "
     * SQL Server: [
     * PostgreSQL: "
     * H2: "
     *
     * @return 引用开始符
     */
    String getQuoteStart();

    /**
     * 获取标识符引用结束符
     *
     * @return 引用结束符
     */
    String getQuoteEnd();

    /**
     * 给标识符（表名、列名）加引用
     * <p>
     * 示例：quote("user_name") -> `user_name` (MySQL)
     *
     * @param identifier 标识符
     * @return 加引用后的标识符
     */
    default String quote(String identifier) {
        return getQuoteStart() + identifier + getQuoteEnd();
    }

    /**
     * 生成分页 SQL
     *
     * @param sql 原始 SQL
     * @param offset 偏移量
     * @param limit 限制数量
     * @return 分页 SQL
     */
    String buildPagingSql(String sql, long offset, long limit);

    /**
     * 是否支持分页参数
     * <p>
     * 某些数据库可能不支持预编译分页参数
     *
     * @return true 表示支持
     */
    default boolean supportPagingParameter() {
        return true;
    }

    /**
     * 渲染条件为 SQL 片段
     *
     * @param column 列名
     * @param termType 条件类型
     * @param params 参数值
     * @return SQL 片段
     */
    String renderCondition(String column, TermType termType, Object... params);

    /**
     * 渲染条件为 SQL 片段（带表别名）
     *
     * @param tableAlias 表别名
     * @param column 列名
     * @param termType 条件类型
     * @param params 参数值
     * @return SQL 片段
     */
    default String renderCondition(String tableAlias, String column, TermType termType, Object... params) {
        String fullColumn = (tableAlias != null && !tableAlias.isEmpty())
                ? tableAlias + "." + column
                : column;
        return renderCondition(fullColumn, termType, params);
    }

    /**
     * 获取函数 SQL
     *
     * @param functionName 函数名
     * @param params 函数参数
     * @return SQL 函数调用
     */
    String getFunctionSql(String functionName, Object... params);

    /**
     * 是否支持该函数
     *
     * @param functionName 函数名
     * @return true 表示支持
     */
    boolean supportFunction(String functionName);
}
