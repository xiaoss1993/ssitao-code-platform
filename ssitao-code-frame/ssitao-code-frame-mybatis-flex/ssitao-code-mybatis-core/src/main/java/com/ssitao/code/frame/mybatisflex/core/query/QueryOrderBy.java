
package com.ssitao.code.frame.mybatisflex.core.query;


import com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.util.ObjectUtil;

import java.util.List;

/**
 * 排序字段
 * @author michael
 */
public class QueryOrderBy implements CloneSupport<QueryOrderBy> {

    QueryColumn queryColumn;

    /**
     * asc, desc
     */
    private String orderType = SqlConsts.ASC;

    private boolean nullsFirst = false;
    private boolean nullsLast = false;

    protected QueryOrderBy() {
    }

    public QueryOrderBy(QueryColumn queryColumn, String orderType) {
        this.queryColumn = queryColumn;
        this.orderType = orderType;
    }


    public QueryOrderBy(QueryColumn queryColumn) {
        this.queryColumn = queryColumn;
    }


    public QueryOrderBy nullsFirst() {
        this.nullsFirst = true;
        this.nullsLast = false;
        return this;
    }


    public QueryOrderBy nullsLast() {
        this.nullsFirst = false;
        this.nullsLast = true;
        return this;
    }

    public QueryColumn getQueryColumn() {
        return this.queryColumn;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public String toSql(List<QueryTable> queryTables, IDialect dialect) {
        String sql = queryColumn.toConditionSql(queryTables, dialect) + orderType;
        if (nullsFirst) {
            sql = sql + SqlConsts.NULLS_FIRST;
        } else if (nullsLast) {
            sql = sql + SqlConsts.NULLS_LAST;
        }
        return sql;
    }


    @Override
    public QueryOrderBy clone() {
        try {
            QueryOrderBy clone = (QueryOrderBy) super.clone();
            // deep clone ...
            clone.queryColumn = ObjectUtil.clone(this.queryColumn);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw FlexExceptions.wrap(e);
        }
    }

}
