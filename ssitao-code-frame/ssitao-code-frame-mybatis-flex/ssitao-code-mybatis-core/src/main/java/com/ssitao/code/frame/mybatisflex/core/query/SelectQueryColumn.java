
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.util.ObjectUtil;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.List;

/**
 * 子查询列。
 *
 * @author ssitao
 */
public class SelectQueryColumn extends QueryColumn implements HasParamsColumn {

    private QueryWrapper queryWrapper;

    public SelectQueryColumn(QueryWrapper queryWrapper) {
        this.queryWrapper = queryWrapper;
    }

    public QueryWrapper getQueryWrapper() {
        return queryWrapper;
    }

    @Override
    protected String toConditionSql(List<QueryTable> queryTables, IDialect dialect) {
        return WrapperUtil.withBracket(dialect.forSelectByQuery(queryWrapper));
    }

    @Override
    protected String toSelectSql(List<QueryTable> queryTables, IDialect dialect) {
        String selectSql = dialect.forSelectByQuery(queryWrapper);
        if (StringUtil.hasText(selectSql) && StringUtil.hasText(alias)) {
            selectSql = WrapperUtil.withAlias(selectSql, alias, dialect);
        }
        return selectSql;
    }

    @Override
    public SelectQueryColumn clone() {
        SelectQueryColumn clone = (SelectQueryColumn) super.clone();
        // deep clone ...
        clone.queryWrapper = ObjectUtil.clone(this.queryWrapper);
        return clone;
    }

    @Override
    public Object[] getParamValues() {
        return queryWrapper.getAllValueArray();
    }

}
