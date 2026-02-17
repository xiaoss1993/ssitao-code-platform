
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;

public class WithSelectDetail implements WithDetail {

    private QueryWrapper queryWrapper;

    public WithSelectDetail() {
    }

    public WithSelectDetail(QueryWrapper queryWrapper) {
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
        return dialect.buildSelectSql(queryWrapper);
    }

    @Override
    public Object[] getParamValues() {
        return queryWrapper.getAllValueArray();
    }

    @Override
    public WithSelectDetail clone() {
        try {
            WithSelectDetail clone = (WithSelectDetail) super.clone();
            // deep clone ...
            clone.queryWrapper = this.queryWrapper.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw FlexExceptions.wrap(e);
        }
    }

}
