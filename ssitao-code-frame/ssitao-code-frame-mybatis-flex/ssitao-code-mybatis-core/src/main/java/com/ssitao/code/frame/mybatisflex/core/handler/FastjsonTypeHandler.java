
package com.ssitao.code.frame.mybatisflex.core.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * @author ssitao
 */
public class FastjsonTypeHandler extends BaseJsonTypeHandler<Object> {

    private final Class<?> propertyType;
    private Class<?> genericType;
    private Type type;

    public FastjsonTypeHandler(Class<?> propertyType) {
        this.propertyType = propertyType;
    }

    public FastjsonTypeHandler(Class<?> propertyType, Class<?> genericType) {
        this.propertyType = propertyType;
        this.genericType = genericType;
        this.type = new ParameterizedTypeImpl(propertyType, genericType);
    }

    @Override
    protected Object parseJson(String json) {
        if (genericType != null && Collection.class.isAssignableFrom(propertyType)) {
            return JSON.parseObject(json, type);
        } else {
            return JSON.parseObject(json, propertyType);
        }
    }

    @Override
    protected String toJson(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
    }


    public static class ParameterizedTypeImpl implements ParameterizedType {

        private final Type[] actualTypeArguments;
        private final Type ownerType;
        private final Type rawType;

        public ParameterizedTypeImpl(Type rawType, Type... actualTypeArguments) {
            this.rawType = rawType;
            this.actualTypeArguments = actualTypeArguments;
            this.ownerType = null;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return this.actualTypeArguments;
        }

        @Override
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override
        public Type getRawType() {
            return this.rawType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o != null && this.getClass() == o.getClass()) {
                ParameterizedTypeImpl that = (ParameterizedTypeImpl) o;
                if (!Arrays.equals(this.actualTypeArguments, that.actualTypeArguments)) {
                    return false;
                } else {
                    if (this.ownerType != null) {
                        if (this.ownerType.equals(that.ownerType)) {
                            return Objects.equals(this.rawType, that.rawType);
                        }
                    } else if (that.ownerType == null) {
                        return Objects.equals(this.rawType, that.rawType);
                    }

                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int result = this.actualTypeArguments != null ? Arrays.hashCode(this.actualTypeArguments) : 0;
            result = 31 * result + (this.ownerType != null ? this.ownerType.hashCode() : 0);
            result = 31 * result + (this.rawType != null ? this.rawType.hashCode() : 0);
            return result;
        }
    }


}
