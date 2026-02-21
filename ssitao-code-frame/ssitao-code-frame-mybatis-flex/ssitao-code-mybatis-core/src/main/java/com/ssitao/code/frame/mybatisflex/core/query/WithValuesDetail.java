
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.util.CollectionUtil;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class WithValuesDetail implements WithDetail {

    private List<Object> values;
    private QueryWrapper queryWrapper;

    public WithValuesDetail() {
    }

    public WithValuesDetail(List<Object> values, QueryWrapper queryWrapper) {
        this.values = values;
        this.queryWrapper = queryWrapper;
    }

    public QueryWrapper getQueryWrapper() {
        return queryWrapper;
    }

    public void setQueryWrapper(QueryWrapper queryWrapper) {
        this.queryWrapper = queryWrapper;
    }

    @Override
    public String toSql(IDialect dialect) {
        List<String> stringValues = new ArrayList<>(values.size());
        for (Object value : values) {
            stringValues.add(String.valueOf(value));
        }
        StringBuilder sql = new StringBuilder("VALUES (")
            .append(StringUtil.join(", ", stringValues)).append(") ");
        return sql.append(dialect.buildNoSelectSql(queryWrapper)).toString();
    }

    @Override
    public Object[] getParamValues() {
        return queryWrapper.getAllValueArray();
    }

    @Override
    public WithValuesDetail clone() {
        try {
            WithValuesDetail clone = (WithValuesDetail) super.clone();
            // deep clone ...
            clone.values = CollectionUtil.newArrayList(this.values);
            clone.queryWrapper = this.queryWrapper.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw FlexExceptions.wrap(e);
        }
    }

}
