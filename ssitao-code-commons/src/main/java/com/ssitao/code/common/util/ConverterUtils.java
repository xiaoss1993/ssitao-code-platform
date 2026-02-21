package com.ssitao.code.common.util;

import com.ssitao.code.common.annotation.ConvertIgnore;
import com.ssitao.code.common.annotation.ConvertMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 对象转换工具类
 * <p>
 * 提供对象之间的属性拷贝功能，支持：
 * <ul>
 * <li>同名同类型属性自动拷贝</li>
 * <li>忽略指定属性</li>
 * <li>自定义字段名映射</li>
 * <li>集合转换</li>
 * </ul>
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public final class ConverterUtils {

    private ConverterUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * 字段缓存
     */
    private static final Map<Class<?>, List<FieldInfo>> FIELD_CACHE = new ConcurrentHashMap<>();

    /**
     * 简单属性转换器（同名、同类型）
     *
     * @param source 源对象
     * @param target 目标类型
     * @param <S>    源类型
     * @param <T>    目标类型
     * @return 转换后的目标对象
     */
    public static <S, T> T convert(S source, Class<T> target) {
        if (source == null) {
            return null;
        }
        try {
            T instance = target.newInstance();
            copyProperties(source, instance);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Convert failed: " + e.getMessage(), e);
        }
    }

    /**
     * 简单属性转换器（带忽略字段）
     *
     * @param source        源对象
     * @param target        目标类型
     * @param ignoreFields 忽略的字段名
     * @param <S>           源类型
     * @param <T>           目标类型
     * @return 转换后的目标对象
     */
    public static <S, T> T convert(S source, Class<T> target, String... ignoreFields) {
        if (source == null) {
            return null;
        }
        try {
            T instance = target.newInstance();
            copyProperties(source, instance, ignoreFields);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Convert failed: " + e.getMessage(), e);
        }
    }

    /**
     * 拷贝属性
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <S>    源类型
     * @param <T>    目标类型
     */
    public static <S, T> void copyProperties(S source, T target) {
        copyProperties(source, target, new String[0]);
    }

    /**
     * 拷贝属性（带忽略字段）
     *
     * @param source        源对象
     * @param target        目标对象
     * @param ignoreFields 忀略的字段名
     * @param <S>           源类型
     * @param <T>           目标类型
     */
    public static <S, T> void copyProperties(S source, T target, String... ignoreFields) {
        if (source == null || target == null) {
            return;
        }

        Set<String> ignoreSet = new HashSet<>();
        if (ignoreFields != null) {
            for (String field : ignoreFields) {
                ignoreSet.add(field);
            }
        }

        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

        List<FieldInfo> sourceFields = getFields(sourceClass);
        List<FieldInfo> targetFields = getFields(targetClass);

        for (FieldInfo targetField : targetFields) {
            // 检查是否被忽略
            if (ignoreSet.contains(targetField.name) || Modifier.isFinal(targetField.modifiers) || targetField.ignore) {
                continue;
            }

            // 获取源字段名称（支持@ConvertMapping注解）
            String sourceFieldName = targetField.mappingName != null ? targetField.mappingName : targetField.name;

            FieldInfo sourceField = findField(sourceFields, sourceFieldName);
            if (sourceField != null && isAssignable(sourceField.type, targetField.type)) {
                Object value = getFieldValue(source, sourceField);
                if (value != null) {
                    setFieldValue(target, targetField, value);
                }
            }
        }
    }

    /**
     * 列表转换
     *
     * @param sources 源对象列表
     * @param target  目标类型
     * @param <S>     源类型
     * @param <T>     目标类型
     * @return 转换后的目标对象列表
     */
    public static <S, T> List<T> convertList(List<S> sources, Class<T> target) {
        if (sources == null || sources.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(sources.size());
        for (S source : sources) {
            result.add(convert(source, target));
        }
        return result;
    }

    /**
     * 列表转换（带忽略字段）
     *
     * @param sources       源对象列表
     * @param target        目标类型
     * @param ignoreFields  忽略的字段名
     * @param <S>           源类型
     * @param <T>           目标类型
     * @return 转换后的目标对象列表
     */
    public static <S, T> List<T> convertList(List<S> sources, Class<T> target, String... ignoreFields) {
        if (sources == null || sources.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(sources.size());
        for (S source : sources) {
            result.add(convert(source, target, ignoreFields));
        }
        return result;
    }

    /**
     * 使用自定义函数转换列表
     *
     * @param sources   源对象列表
     * @param converter 转换函数
     * @param <S>       源类型
     * @param <T>       目标类型
     * @return 转换后的目标对象列表
     */
    public static <S, T> List<T> convertList(List<S> sources, Function<S, T> converter) {
        if (sources == null || sources.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(sources.size());
        for (S source : sources) {
            result.add(converter.apply(source));
        }
        return result;
    }

    /**
     * 获取类的字段信息（带缓存）
     */
    private static List<FieldInfo> getFields(Class<?> type) {
        List<FieldInfo> fieldInfos = FIELD_CACHE.get(type);
        if (fieldInfos == null) {
            fieldInfos = new ArrayList<>();
            Class<?> current = type;
            while (current != null && current != Object.class) {
                Field[] fields = current.getDeclaredFields();
                for (Field field : fields) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.name = field.getName();
                    fieldInfo.type = field.getType();
                    fieldInfo.modifiers = field.getModifiers();
                    fieldInfo.field = field;

                    // 检查@ConvertIgnore注解
                    fieldInfo.ignore = field.isAnnotationPresent(ConvertIgnore.class);

                    // 检查@ConvertMapping注解
                    ConvertMapping mapping = field.getAnnotation(ConvertMapping.class);
                    if (mapping != null) {
                        fieldInfo.mappingName = mapping.source();
                    }

                    // 查找getter和setter
                    String fieldName = field.getName();
                    String getterName = "get" + capitalize(fieldName);
                    String setterName = "set" + capitalize(fieldName);
                    String isName = "is" + capitalize(fieldName);

                    try {
                        // 尝试查找getter
                        if (field.getType() == boolean.class) {
                            try {
                                fieldInfo.getter = current.getMethod(isName);
                            } catch (NoSuchMethodException e) {
                                fieldInfo.getter = current.getMethod(getterName);
                            }
                        } else {
                            fieldInfo.getter = current.getMethod(getterName);
                        }
                    } catch (NoSuchMethodException e) {
                        // 没有getter方法，直接使用字段访问
                    }

                    try {
                        fieldInfo.setter = current.getMethod(setterName, field.getType());
                    } catch (NoSuchMethodException e) {
                        // 没有setter方法，直接使用字段访问
                    }

                    fieldInfos.add(fieldInfo);
                }
                current = current.getSuperclass();
            }
            FIELD_CACHE.put(type, fieldInfos);
        }
        return fieldInfos;
    }

    /**
     * 判断源类型是否可以赋值给目标类型
     */
    private static boolean isAssignable(Class<?> sourceType, Class<?> targetType) {
        if (sourceType == null || targetType == null) {
            return false;
        }
        if (sourceType.equals(targetType)) {
            return true;
        }
        // 基本类型和包装类型的兼容处理
        return isPrimitiveWrapperMatch(sourceType, targetType);
    }

    /**
     * 判断是否是基本类型和包装类型的匹配
     */
    private static boolean isPrimitiveWrapperMatch(Class<?> sourceType, Class<?> targetType) {
        if (sourceType.isPrimitive() || targetType.isPrimitive()) {
            Class<?> sourceWrapped = sourceType.isPrimitive() ? getWrapperType(sourceType) : sourceType;
            Class<?> targetWrapped = targetType.isPrimitive() ? getWrapperType(targetType) : targetType;
            return sourceWrapped.equals(targetWrapped);
        }
        return false;
    }

    /**
     * 获取基本类型的包装类型
     */
    private static Class<?> getWrapperType(Class<?> primitiveType) {
        if (primitiveType == int.class) return Integer.class;
        if (primitiveType == long.class) return Long.class;
        if (primitiveType == double.class) return Double.class;
        if (primitiveType == float.class) return Float.class;
        if (primitiveType == boolean.class) return Boolean.class;
        if (primitiveType == byte.class) return Byte.class;
        if (primitiveType == short.class) return Short.class;
        if (primitiveType == char.class) return Character.class;
        return primitiveType;
    }

    /**
     * 查找字段
     */
    private static FieldInfo findField(List<FieldInfo> fields, String name) {
        for (FieldInfo field : fields) {
            if (field.name.equals(name)) {
                return field;
            }
        }
        return null;
    }

    /**
     * 获取字段值
     */
    private static Object getFieldValue(Object obj, FieldInfo fieldInfo) {
        try {
            if (fieldInfo.getter != null) {
                return fieldInfo.getter.invoke(obj);
            } else {
                Field field = fieldInfo.field;
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field.get(obj);
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 设置字段值
     */
    private static void setFieldValue(Object obj, FieldInfo fieldInfo, Object value) {
        try {
            if (fieldInfo.setter != null) {
                fieldInfo.setter.invoke(obj, value);
            } else {
                Field field = fieldInfo.field;
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(obj, value);
            }
        } catch (Exception e) {
            // 忽略设置失败
        }
    }

    /**
     * 首字母大写
     */
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 字段信息
     */
    private static class FieldInfo {
        String name;
        Class<?> type;
        int modifiers;
        Field field;
        Method getter;
        Method setter;
        boolean ignore;
        String mappingName;
    }
}
