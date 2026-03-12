

package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexAssert;

import java.util.List;

/**
 * {@link QueryColumn} 适配器，用于将 {@link QueryCondition} 转换为 {@link QueryColumn}。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class QueryColumnAdapter extends QueryColumn implements HasParamsColumn {

    private final QueryCondition condition;

    public QueryColumnAdapter(QueryCondition condition) {
        FlexAssert.notNull(condition, "condition");
        this.condition = condition;
    }

    public QueryCondition getCondition() {
        return condition;
    }

    @Override
    public Object[] getParamValues() {
        return WrapperUtil.getValues(condition);
    }

    @Override
    protected String toSelectSql(List<QueryTable> queryTables, IDialect dialect) {
        return condition.toSql(queryTables, dialect) + WrapperUtil.buildColumnAlias(alias, dialect);
    }

    @Override
    protected String toConditionSql(List<QueryTable> queryTables, IDialect dialect) {
        return condition.toSql(queryTables, dialect);
    }

}
