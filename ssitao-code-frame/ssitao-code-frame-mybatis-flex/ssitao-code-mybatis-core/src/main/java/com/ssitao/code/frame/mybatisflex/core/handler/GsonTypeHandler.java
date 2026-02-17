
package com.ssitao.code.frame.mybatisflex.core.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author ssitao
 */
public class GsonTypeHandler extends BaseJsonTypeHandler<Object> {

    private static Gson gson;
    private final Class<?> propertyType;
    private Class<?> genericType;

    public GsonTypeHandler(Class<?> propertyType) {
        this.propertyType = propertyType;
    }

    public GsonTypeHandler(Class<?> propertyType, Class<?> genericType) {
        this.propertyType = propertyType;
        this.genericType = genericType;
    }

    @Override
    protected Object parseJson(String json) {
        if (genericType != null) {
            TypeToken<?> typeToken = TypeToken.getParameterized(propertyType, genericType);
            return getGson().fromJson(json, typeToken.getType());
        } else {
            return getGson().fromJson(json, propertyType);
        }
    }

    @Override
    protected String toJson(Object object) {
        return getGson().toJson(object);
    }


    public static Gson getGson() {
        if (null == gson) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setGson(Gson gson) {
        GsonTypeHandler.gson = gson;
    }

}
