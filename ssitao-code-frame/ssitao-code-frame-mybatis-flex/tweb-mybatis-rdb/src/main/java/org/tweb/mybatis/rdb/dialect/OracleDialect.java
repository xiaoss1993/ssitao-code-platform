package org.tweb.mybatis.rdb.dialect;

import org.tweb.mybatis.core.dsl.condition.TermType;

/**
 * Oracle 数据库方言实现
 * <p>
 * 支持 Oracle 11g 及以上版本
 *
 * @author ssitao
 * @since 1.0.0
 */
public class OracleDialect implements Dialect {

    @Override
    public String getName() {
        return "Oracle";
    }

    @Override
    public String getQuoteStart() {
        return "\"";
    }

    @Override
    public String getQuoteEnd() {
        return "\"";
    }

    @Override
    public String buildPagingSql(String sql, long offset, long limit) {
        // Oracle 使用 ROWNUM 或 ROW_NUMBER() 进行分页
        return "SELECT * FROM (SELECT a.*, ROWNUM rn FROM (" + sql + ") a WHERE ROWNUM <= " +
                (offset + limit) + ") WHERE rn > " + offset;
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
                return column + " LIKE '%' || ? || '%'";
            case likeLeft:
                return column + " LIKE '%' || ?";
            case likeRight:
                return column + " LIKE ? || '%'";
            case notLike:
                return column + " NOT LIKE '%' || ? || '%'";
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
                return "SYSDATE";
            case "curdate":
                return "TRUNC(SYSDATE)";
            case "curtime":
                return "TO_CHAR(SYSDATE, 'HH24:MI:SS')";
            case "date_format":
                if (params.length >= 2) {
                    return "TO_CHAR(" + params[0] + ", '" + params[1] + "')";
                }
                throw new IllegalArgumentException("DATE_FORMAT 需要两个参数");
            case "concat":
                return params.length > 0 ? params[0] + " || " + String.join(" || ", buildFunctionParams(params)) : "";
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
                    return "SUBSTR(" + params[0] + ", " + params[1] + ", " + params[2] + ")";
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
                    return "NVL(" + params[0] + ", " + params[1] + ")";
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

    private String[] buildFunctionParams(Object... params) {
        String[] result = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            result[i] = params[i] != null ? params[i].toString() : "";
        }
        return result;
    }
}
