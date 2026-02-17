

package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.FlexConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;

import java.util.List;

/**
 * 取相反数（{@code -column}）。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class NegativeQueryColumn extends QueryColumn implements HasParamsColumn {

    private final QueryColumn queryColumn;

    public NegativeQueryColumn(QueryColumn queryColumn) {
        this.queryColumn = queryColumn;
    }

    @Override
    public Object[] getParamValues() {
        if (queryColumn instanceof HasParamsColumn) {
            return ((HasParamsColumn) queryColumn).getParamValues();
        }
        return FlexConsts.EMPTY_ARRAY;
    }

    @Override
    protected String toSelectSql(List<QueryTable> queryTables, IDialect dialect) {
        return toConditionSql(queryTables, dialect) + WrapperUtil.buildColumnAlias(alias, dialect);
    }

    @Override
    protected String toConditionSql(List<QueryTable> queryTables, IDialect dialect) {
        return "-" + queryColumn.toConditionSql(queryTables, dialect);
    }

}
