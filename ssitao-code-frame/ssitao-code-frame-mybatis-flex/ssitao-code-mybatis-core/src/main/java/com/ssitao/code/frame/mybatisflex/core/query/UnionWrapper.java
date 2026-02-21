
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.util.ObjectUtil;

public class UnionWrapper implements CloneSupport<UnionWrapper> {

    private String key;
    private QueryWrapper queryWrapper;

    static UnionWrapper union(QueryWrapper queryWrapper) {
        UnionWrapper unionWrapper = new UnionWrapper();
        unionWrapper.key = SqlConsts.UNION;
        unionWrapper.queryWrapper = queryWrapper;
        return unionWrapper;
    }

    static UnionWrapper unionAll(QueryWrapper queryWrapper) {
        UnionWrapper unionWrapper = new UnionWrapper();
        unionWrapper.key = SqlConsts.UNION_ALL;
        unionWrapper.queryWrapper = queryWrapper;
        return unionWrapper;
    }


    private UnionWrapper() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public QueryWrapper getQueryWrapper() {
        return queryWrapper;
    }

    public void setQueryWrapper(QueryWrapper queryWrapper) {
        this.queryWrapper = queryWrapper;
    }

    public void buildSql(StringBuilder sqlBuilder, IDialect dialect) {
        sqlBuilder.append(key)
            .append(SqlConsts.BRACKET_LEFT)
            .append(dialect.buildSelectSql(queryWrapper))
            .append(SqlConsts.BRACKET_RIGHT);
    }

    @Override
    public UnionWrapper clone() {
        try {
            UnionWrapper clone = (UnionWrapper) super.clone();
            // deep clone ...
            clone.queryWrapper = ObjectUtil.clone(this.queryWrapper);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw FlexExceptions.wrap(e);
        }
    }

}
