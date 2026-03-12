package org.tweb.mybatis.rdb.dialect;

import org.tweb.mybatis.core.dsl.condition.TermType;

/**
 * MySQL 数据库方言实现
 * <p>
 * 支持 MySQL 5.x 和 8.x 版本
 *
 * @author ssitao
 * @since 1.0.0
 */
public class MySQLDialect implements Dialect {

    @Override
    public String getName() {
        return "MySQL";
    }

    @Override
    public String getQuoteStart() {
        return "`";
    }

    @Override
    public String getQuoteEnd() {
        return "`";
    }

    @Override
    public String buildPagingSql(String sql, long offset, long limit) {
        return sql + " LIMIT " + offset + ", " + limit;
    }

    @Override
    public String renderCondition(String column, TermType termType, Object... params) {
        switch (termType) {
            case eq:
                return column + " = ?";
            case ne:
                return column + " != ?";
            case gt:
                return column + " > ?";
            case ge:
                return column + " >= ?";
            case lt:
                return column + " < ?";
            case le:
                return column + " <= ?";
            case like:
                return column + " LIKE CONCAT('%', ?, '%')";
            case likeLeft:
                return column + " LIKE CONCAT('%', ?)";
            case likeRight:
                return column + " LIKE CONCAT(?, '%')";
            case notLike:
                return column + " NOT LIKE CONCAT('%', ?, '%')";
            case in:
                return column + " IN (" + buildInParams(params.length) + ")";
            case notIn:
                return column + " NOT IN (" + buildInParams(params.length) + ")";
            case between:
                return column + " BETWEEN ? AND ?";
            case notBetween:
                return column + " NOT BETWEEN ? AND ?";
            case isNull:
                return column + " IS NULL";
            case isNotNull:
                return column + " IS NOT NULL";
            case sql:
                return params.length > 0 ? params[0].toString() : "";
            default:
                throw new UnsupportedOperationException("不支持的条件类型: " + termType);
        }
    }

    @Override
    public String getFunctionSql(String functionName, Object... params) {
        switch (functionName.toLowerCase()) {
            case "now":
                return "NOW()";
            case "curdate":
                return "CURDATE()";
            case "curtime":
                return "CURTIME()";
            case "date_format":
                if (params.length >= 2) {
                    return "DATE_FORMAT(" + params[0] + ", '" + params[1] + "')";
                }
                throw new IllegalArgumentException("DATE_FORMAT 需要两个参数");
            case "concat":
                return "CONCAT(" + String.join(", ", buildFunctionParams(params)) + ")";
            case "count":
                return "COUNT(" + (params.length > 0 ? params[0] : "*") + ")";
            case "sum":
                return "SUM(" + (params.length > 0 ? params[0] : "") + ")";
            case "avg":
                return "AVG(" + (params.length > 0 ? params[0] : "") + ")";
            case "max":
                return "MAX(" + (params.length > 0 ? params[0] : "") + ")";
            case "min":
                return "MIN(" + (params.length > 0 ? params[0] : "") + ")";
            case "length":
                return "LENGTH(" + (params.length > 0 ? params[0] : "") + ")";
            case "substring":
                if (params.length >= 3) {
                    return "SUBSTRING(" + params[0] + ", " + params[1] + ", " + params[2] + ")";
                }
                throw new IllegalArgumentException("SUBSTRING 需要三个参数");
            case "upper":
                return "UPPER(" + (params.length > 0 ? params[0] : "") + ")";
            case "lower":
                return "LOWER(" + (params.length > 0 ? params[0] : "") + ")";
            case "trim":
                return "TRIM(" + (params.length > 0 ? params[0] : "") + ")";
            case "ifnull":
                if (params.length >= 2) {
                    return "IFNULL(" + params[0] + ", " + params[1] + ")";
                }
                throw new IllegalArgumentException("IFNULL 需要两个参数");
            case "coalesce":
                return "COALESCE(" + String.join(", ", buildFunctionParams(params)) + ")";
            default:
                throw new UnsupportedOperationException("不支持的函数: " + functionName);
        }
    }

    @Override
    public boolean supportFunction(String functionName) {
        switch (functionName.toLowerCase()) {
            case "now":
            case "curdate":
            case "curtime":
            case "date_format":
            case "concat":
            case "count":
            case "sum":
            case "avg":
            case "max":
            case "min":
            case "length":
            case "substring":
            case "upper":
            case "lower":
            case "trim":
            case "ifnull":
            case "coalesce":
                return true;
            default:
                return false;
        }
    }

    /**
     * 构建 IN 参数占位符
     *
     * @param count 参数数量
     * @return 占位符字符串
     */
    private String buildInParams(int count) {
        if (count <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("?");
        }
        return sb.toString();
    }

    /**
     * 构建函数参数字符串
     *
     * @param params 参数数组
     * @return 参数字符串
     */
    private String[] buildFunctionParams(Object... params) {
        String[] result = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            result[i] = params[i] != null ? params[i].toString() : "";
        }
        return result;
    }
}
