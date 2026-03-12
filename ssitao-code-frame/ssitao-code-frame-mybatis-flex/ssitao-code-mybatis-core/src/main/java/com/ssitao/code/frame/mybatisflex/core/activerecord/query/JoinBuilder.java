

package com.ssitao.code.frame.mybatisflex.core.activerecord.query;

import com.ssitao.code.frame.mybatisflex.core.query.Join;
import com.ssitao.code.frame.mybatisflex.core.query.QueryCondition;
import com.ssitao.code.frame.mybatisflex.core.query.RawQueryCondition;

/**
 * Lambda joins 构建器。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class JoinBuilder<R extends QueryModel<R>> {

    private final R queryModel;
    private final Join join;

    public JoinBuilder(R queryModel, Join join) {
        this.queryModel = queryModel;
        this.join = join;
    }

    public R on(String on) {
        join.on(new RawQueryCondition(on));
        return queryModel;
    }

    public R on(QueryCondition on) {
        join.on(on);
        return queryModel;
    }

}
