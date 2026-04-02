package com.ssitao.code.common.utils;

import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 默认的ToString操作器实现
 *
 * @since 3.0.0-RC
 */
public class DefaultToStringOperator<T> implements ToStringOperator<T> {

    private final Class<T> clazz;
    private final PropertyDescriptor[] propertyDescriptors;
    private final Map<String, Field> fieldMap = new HashMap<>();

    public DefaultToStringOperator(Class<T> clazz) {
        this.clazz = clazz;
        this.propertyDescriptors = org.springframework.beans.BeanUtils.getPropertyDescriptors(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
    }

    @Override
    public String toString(T target) {
        return toString(target, ToString.DEFAULT_FEATURE);
    }

    @Override
    public String toString(T target, String... ignoreProperty) {
        return toString(target, ToString.DEFAULT_FEATURE, ignoreProperty);
    }

    public String toString(T target, long features, String... ignoreProperty) {
        if (target == null) {
            return "null";
        }

        Set<String> ignoreSet = ignoreProperty != null ? new HashSet<>(Arrays.asList(ignoreProperty)) : Collections.emptySet();

        StringBuilder sb = new StringBuilder();
        boolean writeClassname = ToString.Feature.hasFeature(features, ToString.Feature.writeClassname);
        if (writeClassname) {
            sb.append(ClassUtils.getUserClass(target).getName());
            sb.append("{");
        } else {
            sb.append("{");
        }

        boolean first = true;
        for (PropertyDescriptor pd : propertyDescriptors) {
            if ("class".equals(pd.getName())) {
                continue;
            }

            if (ignoreSet.contains(pd.getName())) {
                continue;
            }

            try {
                Method readMethod = pd.getReadMethod();
                if (readMethod == null) {
                    continue;
                }

                Object value = readMethod.invoke(target);
                boolean isNull = value == null;

                if (ToString.Feature.hasFeature(features, ToString.Feature.ignoreNullProperty) && isNull) {
                    continue;
                }

                if (!first) {
                    sb.append(", ");
                }
                first = false;

                sb.append(pd.getName());
                sb.append("=");

                if (isNull) {
                    if (ToString.Feature.hasFeature(features, ToString.Feature.nullPropertyToEmpty)) {
                        sb.append("null");
                    } else {
                        sb.append("null");
                    }
                } else {
                    if (ToString.Feature.hasFeature(features, ToString.Feature.coverIgnoreProperty)) {
                        sb.append(coverValue(value, pd.getName()));
                    } else {
                        if (ToString.Feature.hasFeature(features, ToString.Feature.jsonFormat)) {
                            sb.append(value.toString());
                        } else {
                            sb.append(value.toString());
                        }
                    }
                }

            } catch (Exception e) {
                // 忽略反射异常
            }
        }

        sb.append("}");
        return sb.toString();
    }

    private String coverValue(Object value, String fieldName) {
        if (value == null) {
            return "null";
        }

        String str = value.toString();
        if (str.length() <= 2) {
            return str;
        }

        // 简单的遮盖逻辑
        if (fieldName.contains("name") || fieldName.contains("Name")) {
            if (str.length() >= 2) {
                return str.charAt(0) + "*";
            }
        }

        if (fieldName.contains("phone") || fieldName.contains("Phone") || fieldName.contains("mobile") || fieldName.contains("Mobile")) {
            if (str.length() >= 11) {
                return str.substring(0, 3) + "****" + str.substring(7);
            }
        }

        if (fieldName.contains("id") || fieldName.contains("Id")) {
            if (str.length() >= 8) {
                return str.substring(0, 4) + "****" + str.substring(str.length() - 4);
            }
        }

        return str;
    }
}
