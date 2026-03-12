

package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;

/**
 * 抽象关联查询。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public abstract class AbstractQueryBuilder<T> implements ChainQuery<T> {

    protected final MapperQueryChain<T> delegate;

    protected AbstractQueryBuilder(MapperQueryChain<T> delegate) {
        this.delegate = delegate;
    }

    /**
     * @return BaseMapper
     */
    protected BaseMapper<T> baseMapper() {
        return delegate.baseMapper();
    }

    /**
     * @return QueryWrapper
     */
    protected QueryWrapper queryWrapper() {
        return delegate.toQueryWrapper();
    }

}
