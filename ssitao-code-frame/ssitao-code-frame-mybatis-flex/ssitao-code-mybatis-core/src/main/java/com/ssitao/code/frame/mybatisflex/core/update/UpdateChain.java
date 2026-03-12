

package com.ssitao.code.frame.mybatisflex.core.update;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.frame.mybatisflex.core.dialect.DialectFactory;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.mybatis.Mappers;
import com.ssitao.code.frame.mybatisflex.core.query.CPI;
import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapperAdapter;
import com.ssitao.code.frame.mybatisflex.core.table.TableInfo;
import com.ssitao.code.frame.mybatisflex.core.table.TableInfoFactory;
import com.ssitao.code.frame.mybatisflex.core.util.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 用于数据更新、删除的链式操作
 *
 * @author ssitao
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class UpdateChain<T> extends QueryWrapperAdapter<UpdateChain<T>> implements PropertySetter<UpdateChain<T>> {

    private final BaseMapper<T> baseMapper;
    private final T entity;
    private final UpdateWrapper entityWrapper;

    public static <T> UpdateChain<T> of(Class<T> entityClass) {
        BaseMapper<T> baseMapper = Mappers.ofEntityClass(entityClass);
        return new UpdateChain<>(baseMapper);
    }

    public static <T> UpdateChain<T> of(BaseMapper<T> baseMapper) {
        return new UpdateChain<>(baseMapper);
    }

    public static <T> UpdateChain<T> of(T entityObject) {
        Class<T> entityClass = (Class<T>) ClassUtil.getUsefulClass(entityObject.getClass());
        BaseMapper<T> baseMapper = Mappers.ofEntityClass(entityClass);
        return new UpdateChain<>(baseMapper, entityObject);
    }

    public static <T> UpdateChain<T> of(T entityObject, BaseMapper<T> baseMapper) {
        return new UpdateChain<>(baseMapper, entityObject);
    }


    public UpdateChain(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
        this.entity = createEntity(ClassUtil.getUsefulClass(baseMapper.getClass()));
        this.entityWrapper = (UpdateWrapper) entity;
    }


    public UpdateChain(BaseMapper<T> baseMapper, T entityObject) {
        this.baseMapper = baseMapper;
        entityObject = (T) UpdateWrapper.of(entityObject);
        this.entity = entityObject;
        this.entityWrapper = (UpdateWrapper) entityObject;
    }

    private T createEntity(Class<?> mapperClass) {
        Type type = mapperClass.getGenericInterfaces()[0];
        if (type instanceof ParameterizedType) {
            Class<T> modelClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
            return UpdateEntity.of(modelClass);
        }
        throw FlexExceptions.wrap("Can not get entity class from mapper: " + mapperClass.getName());
    }

    public static <E> UpdateChain<E> create(BaseMapper<E> baseMapper) {
        return new UpdateChain<>(baseMapper);
    }

    @Override
    public UpdateChain<T> set(String property, Object value, boolean isEffective) {
        entityWrapper.set(property, value, isEffective);
        return this;
    }

    @Override
    public UpdateChain<T> set(QueryColumn queryColumn, Object value, boolean isEffective) {
        entityWrapper.set(queryColumn, value, isEffective);
        return this;
    }

    @Override
    public <L> UpdateChain<T> set(LambdaGetter<L> getter, Object value, boolean isEffective) {
        entityWrapper.set(getter, value, isEffective);
        return this;
    }


    @Override
    public UpdateChain<T> setRaw(String property, Object value, boolean isEffective) {
        entityWrapper.setRaw(property, value, isEffective);
        return this;
    }

    @Override
    public UpdateChain<T> setRaw(QueryColumn queryColumn, Object value, boolean isEffective) {
        entityWrapper.setRaw(queryColumn, value, isEffective);
        return this;
    }

    @Override
    public <L> UpdateChain<T> setRaw(LambdaGetter<L> getter, Object value, boolean isEffective) {
        entityWrapper.setRaw(getter, value, isEffective);
        return this;
    }


    public boolean remove() {
        return SqlUtil.toBool(baseMapper.deleteByQuery(this));
    }


    public boolean update() {
        return SqlUtil.toBool(baseMapper.updateByQuery(entity, this));
    }


    @Override
    public String toSQL() {
        TableInfo tableInfo = TableInfoFactory.ofMapperClass(baseMapper.getClass());
        CPI.setFromIfNecessary(this, tableInfo.getSchema(), tableInfo.getTableName());
        String sql = DialectFactory.getDialect().forUpdateEntityByQuery(tableInfo, entity, true, this);

        Object[] values = tableInfo.buildUpdateSqlArgs(entity, true, true);
        values = ArrayUtil.concat(values, CPI.getValueArray(this));

        return SqlUtil.replaceSqlParams(sql, values);
    }

}
