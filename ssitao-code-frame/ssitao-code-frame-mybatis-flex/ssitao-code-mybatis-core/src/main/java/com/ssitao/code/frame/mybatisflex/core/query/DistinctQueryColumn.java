
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.FlexConsts;
import com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.util.CollectionUtil;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistinctQueryColumn extends QueryColumn implements HasParamsColumn {

    private List<QueryColumn> queryColumns;

    public DistinctQueryColumn(QueryColumn... queryColumns) {
        this.queryColumns = CollectionUtil.newArrayList(queryColumns);
    }

    public List<QueryColumn> getQueryColumns() {
        return queryColumns;
    }

    public void setQueryColumns(List<QueryColumn> queryColumns) {
        this.queryColumns = queryColumns;
    }

    @Override
    protected String toConditionSql(List<QueryTable> queryTables, IDialect dialect) {
        if (CollectionUtil.isEmpty(queryTables)) {
            return SqlConsts.EMPTY;
        }

        return SqlConsts.DISTINCT + StringUtil.join(
            SqlConsts.DELIMITER,
            queryColumns,
            queryColumn -> queryColumn.toSelectSql(queryTables, dialect)
        );
    }

    @Override
    public String toSelectSql(List<QueryTable> queryTables, IDialect dialect) {
        if (CollectionUtil.isEmpty(queryTables)) {
            return SqlConsts.EMPTY;
        }

        String sql = SqlConsts.DISTINCT + StringUtil.join(
            SqlConsts.DELIMITER,
            queryColumns,
            queryColumn -> queryColumn.toSelectSql(queryTables, dialect)
        );

        return sql + WrapperUtil.buildColumnAlias(alias, dialect);
    }

    @Override
    public DistinctQueryColumn clone() {
        DistinctQueryColumn clone = (DistinctQueryColumn) super.clone();
        // deep clone ...
        clone.queryColumns = CollectionUtil.cloneArrayList(this.queryColumns);

        return clone;
    }

    @Override
    public Object[] getParamValues() {
        if (CollectionUtil.isEmpty(queryColumns)) {
            return FlexConsts.EMPTY_ARRAY;
        }

        List<Object> params = new ArrayList<>();

        for (QueryColumn queryColumn : queryColumns) {
            if (queryColumn instanceof HasParamsColumn) {
                Object[] paramValues = ((HasParamsColumn) queryColumn).getParamValues();

                params.addAll(Arrays.asList(paramValues));
            }
        }

        return params.toArray();
    }
}
