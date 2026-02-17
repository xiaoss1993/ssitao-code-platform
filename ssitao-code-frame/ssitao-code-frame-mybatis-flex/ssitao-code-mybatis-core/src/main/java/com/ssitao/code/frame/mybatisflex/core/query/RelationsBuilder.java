

package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.relation.RelationManager;
import com.ssitao.code.frame.mybatisflex.core.util.LambdaGetter;

import java.util.List;

/**
 * 使用 {@code Relations Query} 的方式进行关联查询。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class RelationsBuilder<T> extends AbstractQueryBuilder<T> {

    public RelationsBuilder(MapperQueryChain<T> delegate) {
        super(delegate);
    }

    /**
     * 忽略查询部分 {@code Relations} 注解标记的属性。
     *
     * @param fields 属性
     * @return {@code Relations} 查询构建
     */
    public RelationsBuilder<T> ignoreRelations(String... fields) {
        RelationManager.addIgnoreRelations(fields);
        return this;
    }

    /**
     * 忽略查询部分 {@code Relations} 注解标记的属性。
     *
     * @param fields 属性
     * @return {@code Relations} 查询构建
     */
    public <L> RelationsBuilder<T> ignoreRelations(LambdaGetter<L>... fields) {
        RelationManager.addIgnoreRelations(fields);
        return this;
    }

    /**
     * 设置父子关系查询中，默认的递归查询深度。
     *
     * @param maxDepth 查询深度
     * @return {@code Relations} 查询构建
     */
    public RelationsBuilder<T> maxDepth(int maxDepth) {
        RelationManager.setMaxDepth(maxDepth);
        return this;
    }

    /**
     * 添加额外的 {@code Relations} 查询条件。
     *
     * @param key   键
     * @param value 值
     * @return {@code Relations} 查询构建
     */
    public RelationsBuilder<T> extraConditionParam(String key, Object value) {
        RelationManager.addExtraConditionParam(key, value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T one() {
        return baseMapper().selectOneWithRelationsByQuery(queryWrapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R oneAs(Class<R> asType) {
        return baseMapper().selectOneWithRelationsByQueryAs(queryWrapper(), asType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> list() {
        return baseMapper().selectListWithRelationsByQuery(queryWrapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> List<R> listAs(Class<R> asType) {
        return baseMapper().selectListWithRelationsByQueryAs(queryWrapper(), asType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<T> page(Page<T> page) {
        return baseMapper().paginateWithRelations(page, queryWrapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> Page<R> pageAs(Page<R> page, Class<R> asType) {
        return baseMapper().paginateWithRelationsAs(page, queryWrapper(), asType);
    }

}
