
package com.ssitao.code.frame.mybatisflex.core.update;

import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.query.QueryCondition;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.mybatisflex.core.util.LambdaGetter;
import com.ssitao.code.frame.mybatisflex.core.util.LambdaUtil;
import com.ssitao.code.frame.mybatisflex.core.util.UpdateEntity;
import org.apache.ibatis.javassist.util.proxy.Proxy;
import org.apache.ibatis.javassist.util.proxy.ProxyFactory;
import org.apache.ibatis.javassist.util.proxy.ProxyObject;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ssitao
 */
public interface UpdateWrapper<T> extends PropertySetter<UpdateWrapper<T>>, Serializable {

    default Map<String, Object> getUpdates() {
        ModifyAttrsRecordHandler handler = null;
        if (this instanceof ProxyObject) {
            handler = (ModifyAttrsRecordHandler) ((ProxyObject) this).getHandler();
        } else if (this instanceof Proxy) {
            handler = (ModifyAttrsRecordHandler) ProxyFactory.getHandler((Proxy) this);
        } else {
            throw FlexExceptions.wrap("获取实体类代理对象 %s 的字段更新处理器时出错，该对象既不是 ProxyObject 的实例，也不是 Proxy 的实例", this.getClass().getName());
        }
        return handler.getUpdates();
    }

    @Override
    default UpdateWrapper<T> set(String property, Object value, boolean isEffective) {
        if (isEffective) {
            if (value instanceof QueryWrapper
                    || value instanceof QueryColumn
                    || value instanceof QueryCondition) {
                getUpdates().put(property, new RawValue(value));
            } else {
                getUpdates().put(property, value);
            }
        }
        return this;
    }

    @Override
    default UpdateWrapper<T> set(QueryColumn property, Object value, boolean isEffective) {
        if (isEffective) {
            if (value instanceof QueryWrapper
                    || value instanceof QueryColumn
                    || value instanceof QueryCondition) {
                getUpdates().put(property.getName(), new RawValue(value));
            } else {
                getUpdates().put(property.getName(), value);
            }
        }
        return this;
    }

    @Override
    default <E> UpdateWrapper<T> set(LambdaGetter<E> property, Object value, boolean isEffective) {
        if (isEffective) {
            if (value instanceof QueryWrapper
                    || value instanceof QueryColumn
                    || value instanceof QueryCondition) {
                getUpdates().put(LambdaUtil.getFieldName(property), new RawValue(value));
            } else {
                getUpdates().put(LambdaUtil.getFieldName(property), value);
            }
        }
        return this;
    }

    @Override
    default UpdateWrapper<T> setRaw(String property, Object value, boolean isEffective) {
        if (isEffective) {
            getUpdates().put(property, new RawValue(value));
        }
        return this;
    }


    @Override
    default UpdateWrapper<T> setRaw(QueryColumn property, Object value, boolean isEffective) {
        if (isEffective) {
            getUpdates().put(property.getName(), new RawValue(value));
        }
        return this;
    }

    @Override
    default <E> UpdateWrapper<T> setRaw(LambdaGetter<E> property, Object value, boolean isEffective) {
        if (isEffective) {
            getUpdates().put(LambdaUtil.getFieldName(property), new RawValue(value));
        }
        return this;
    }


    static <T> UpdateWrapper<T> of(T entity) {
        if (entity instanceof UpdateWrapper) {
            return (UpdateWrapper<T>) entity;
        } else {
            return (UpdateWrapper<T>) UpdateEntity.ofNotNull(entity);
        }
    }

    static <T> UpdateWrapper<T> of(Class<T> tClass) {
        return (UpdateWrapper<T>) UpdateEntity.of(tClass);
    }


    default T toEntity() {
        return (T) this;
    }

}
