
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.FlexConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;

import java.util.List;

/**
 * CAST函数查询列
 */
public class CastQueryColumn extends QueryColumn implements HasParamsColumn {

    private QueryColumn column;
    private final String dataType;

    public CastQueryColumn(QueryColumn column, String dataType) {
        this.column = column;
        this.dataType = dataType;
    }

    public CastQueryColumn(String column, String dataType) {
        this.column = new QueryColumn(column);
        this.dataType = dataType;
    }

    @Override
    protected String toConditionSql(List<QueryTable> queryTables, IDialect dialect) {
        return " CAST(" + column.toConditionSql(queryTables, dialect) + " AS " + dataType + ") ";
    }

    @Override
    protected String toSelectSql(List<QueryTable> queryTables, IDialect dialect) {
        return " CAST(" + column.toSelectSql(queryTables, dialect) + " AS " + dataType + ") " + WrapperUtil.buildColumnAlias(alias, dialect);
    }

    @Override
    public CastQueryColumn clone() {
        CastQueryColumn clone = (CastQueryColumn) super.clone();
        clone.column = column.clone();
        return clone;
    }

    @Override
    public String toString() {
        return "CastQueryColumn{" +
            "column=" + column +
            ", dataType='" + dataType + '\'' +
            ", alias='" + alias + '\'' +
            '}';
    }

    @Override
    public Object[] getParamValues() {
        if (column instanceof HasParamsColumn) {
            return ((HasParamsColumn) column).getParamValues();
        }
        return FlexConsts.EMPTY_ARRAY;
    }
}
