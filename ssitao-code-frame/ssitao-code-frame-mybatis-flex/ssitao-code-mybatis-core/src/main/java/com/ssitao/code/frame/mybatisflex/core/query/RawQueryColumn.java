
package com.ssitao.code.frame.mybatisflex.core.query;


import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;

import java.util.Arrays;
import java.util.List;

/**
 * 原生列。
 *
 * @author ssitao
 */
public class RawQueryColumn extends QueryColumn implements HasParamsColumn {

    protected String content;
    protected Object[] params;

    public RawQueryColumn(Object content, Object... params) {
        this.content = String.valueOf(content);
        this.params = params;
    }

    @Override
    protected String toConditionSql(List<QueryTable> queryTables, IDialect dialect) {
        return content;
    }

    @Override
    protected String toSelectSql(List<QueryTable> queryTables, IDialect dialect) {
        return content + WrapperUtil.buildColumnAlias(alias, dialect);
    }

    @Override
    public String toString() {
        return "RawQueryColumn{" +
            "content='" + content + '\'' +
            ", params='" + Arrays.toString(params) + '\'' +
            '}';
    }

    public String getContent() {
        return content;
    }

    public Object[] getParams() {
        return params;
    }

    @Override
    public RawQueryColumn clone() {
        return (RawQueryColumn) super.clone();
    }

    @Override
    public Object[] getParamValues() {
        return params;
    }

}
