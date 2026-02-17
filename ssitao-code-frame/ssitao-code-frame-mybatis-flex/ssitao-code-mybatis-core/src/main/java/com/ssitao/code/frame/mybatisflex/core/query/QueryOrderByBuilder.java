
package com.ssitao.code.frame.mybatisflex.core.query;


import com.ssitao.code.frame.mybatisflex.core.util.LambdaGetter;
import com.ssitao.code.frame.mybatisflex.core.util.LambdaUtil;

/**
 * 排序字段构建器
 * @author michael
 */
@SuppressWarnings("unchecked")
public class QueryOrderByBuilder<Wrapper extends QueryWrapper> {

    private Wrapper queryWrapper;
    private QueryColumn orderByColumn;

    public <T> QueryOrderByBuilder(Wrapper queryWrapper, LambdaGetter<T> getter) {
        this.queryWrapper = queryWrapper;
        this.orderByColumn = LambdaUtil.getQueryColumn(getter);
    }

    public Wrapper asc(){
        return (Wrapper) queryWrapper.orderBy(orderByColumn.asc());
    }

    public Wrapper desc(){
        return (Wrapper) queryWrapper.orderBy(orderByColumn.desc());
    }
}
