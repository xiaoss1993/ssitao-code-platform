
package com.ssitao.code.frame.mybatisflex.core.activerecord.query;


import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.util.LambdaGetter;
import com.ssitao.code.frame.mybatisflex.core.util.LambdaUtil;

/**
 * Lambda 排序构建器。
 *
 * @author 王帅
 * @since 2023-07-25
 */
public class OrderByBuilder<R extends QueryModel<R>> {

    private final R queryModel;
    private final QueryColumn queryColumn;

    public <T> OrderByBuilder(R queryModel, LambdaGetter<T> getter) {
        this.queryModel = queryModel;
        this.queryColumn = LambdaUtil.getQueryColumn(getter);
    }

    public R asc() {
        return queryModel.orderBy(queryColumn.asc());
    }

    public R desc() {
        return queryModel.orderBy(queryColumn.desc());
    }

}
