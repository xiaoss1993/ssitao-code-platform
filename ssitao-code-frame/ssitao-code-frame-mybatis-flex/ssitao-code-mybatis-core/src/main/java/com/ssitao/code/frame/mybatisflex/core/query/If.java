
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.Collection;
import java.util.Map;

public class If {

    private If() {
    }

    /**
     * 判断对象是否为空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 判断对象是否非空
     */
    public static boolean notNull(Object object) {
        return object != null;
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    @Deprecated
    public static <T> boolean isNotEmpty(T[] array) {
        return notEmpty(array);
    }

    public static <T> boolean notEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    @Deprecated
    public static boolean isNotEmpty(Map<?, ?> map) {
        return notEmpty(map);
    }

    public static boolean notEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    @Deprecated
    public static boolean isNotEmpty(Collection<?> collection) {
        return notEmpty(collection);
    }

    public static boolean notEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean hasText(String string) {
        return StringUtil.hasText(string);
    }

    public static boolean noText(String string) {
        return StringUtil.noText(string);
    }

}
