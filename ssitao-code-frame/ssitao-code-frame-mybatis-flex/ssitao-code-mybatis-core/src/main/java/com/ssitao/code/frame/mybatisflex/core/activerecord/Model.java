

package com.ssitao.code.frame.mybatisflex.core.activerecord;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.frame.mybatisflex.core.activerecord.query.FieldsQuery;
import com.ssitao.code.frame.mybatisflex.core.activerecord.query.QueryModel;
import com.ssitao.code.frame.mybatisflex.core.activerecord.query.RelationsQuery;
import com.ssitao.code.frame.mybatisflex.core.query.MapperQueryChain;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.mybatisflex.core.query.RelationsBuilder;
import com.ssitao.code.frame.mybatisflex.core.relation.RelationManager;
import com.ssitao.code.frame.mybatisflex.core.util.LambdaGetter;
import com.ssitao.code.frame.mybatisflex.core.util.LambdaUtil;
import com.ssitao.code.frame.mybatisflex.core.util.SqlUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

/**
 * Active Record 模型。
 *
 * @param <T> 实体类类型
 * @author ssitao 
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "unchecked"})
public abstract class Model<T extends Model<T>>
    extends QueryModel<T>
    implements MapperModel<T>, MapperQueryChain<T>, Serializable {

    /**
     * 根据实体类构建的条件删除数据。
     *
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    public boolean remove() {
        return SqlUtil.toBool(baseMapper().deleteByQuery(queryWrapper()));
    }

    /**
     * 根据实体类构建的条件删除数据，结果使用 {@link Optional} 返回源对象回调，删除成功返回
     * {@code Optional.of(this)}，删除失败返回 {@code Optional.empty()}。
     *
     * @return {@link Optional} 链式调用
     */
    public Optional<T> removeOpt() {
        return remove() ? Optional.of((T) this) : Optional.empty();
    }

    /**
     * 根据实体类构建的条件更新数据（自动忽略 {@code null} 值）。
     *
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    public boolean update() {
        return update(true);
    }

    /**
     * 根据实体类构建的条件更新数据（自动忽略 {@code null} 值），结果使用 {@link Optional}
     * 返回源对象回调，更新成功返回 {@code Optional.of(this)}，更新失败返回
     * {@code Optional.empty()}。
     *
     * @return {@link Optional} 链式调用
     */
    public Optional<T> updateOpt() {
        return updateOpt(true);
    }

    /**
     * 根据实体类构建的条件更新数据，并设置是否忽略 {@code null} 值。
     *
     * @param ignoreNulls 是否忽略 {@code null} 值
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    public boolean update(boolean ignoreNulls) {
        return SqlUtil.toBool(baseMapper().updateByQuery((T) this, ignoreNulls, queryWrapper()));
    }

    /**
     * 根据实体类构建的条件更新数据，并设置是否忽略 {@code null} 值，结果使用 {@link Optional}
     * 返回源对象回调，更新成功返回 {@code Optional.of(this)}，更新失败返回
     * {@code Optional.empty()}。
     *
     * @param ignoreNulls 是否忽略 {@code null} 值
     * @return {@link Optional} 链式调用
     */
    public Optional<T> updateOpt(boolean ignoreNulls) {
        return update(ignoreNulls) ? Optional.of((T) this) : Optional.empty();
    }

    @Override
    public BaseMapper<T> baseMapper() {
        return MapperModel.super.baseMapper();
    }

    @Override
    public QueryWrapper toQueryWrapper() {
        return queryWrapper();
    }

    @Override
    public FieldsQuery<T> withFields() {
        return new FieldsQuery<>(this);
    }

    @Override
    public RelationsQuery<T> withRelations() {
        return new RelationsQuery<>(this);
    }

    @Override
    public RelationsBuilder<T> withRelations(LambdaGetter<T>... columns) {
        if(columns != null && columns.length > 0) {
            String[] array = Arrays.stream(columns)
                .map(LambdaUtil::getFieldName)
                .toArray(String[]::new);
            RelationManager.addQueryRelations(array);
        }
        return new RelationsBuilder<>(this);
    }

}
