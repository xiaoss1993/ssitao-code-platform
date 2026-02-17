
package com.ssitao.code.frame.mybatisflex.core.exception;

import com.ssitao.code.frame.mybatisflex.core.exception.locale.LocalizedFormats;

import java.util.Collection;
import java.util.Map;

/**
 * 断言。
 *
 * @author ssitao
 *
 * @since 1.0.0
 */
public final class FlexAssert {

    private FlexAssert() {
    }

    /**
     * 断言对象不为空，如果为空抛出异常，并指明哪个对象为空。
     *
     * @param obj   对象
     * @param param 错误消息参数
     * @throws MybatisFlexException 如果对象为空，抛出此异常。
     */
    public static void notNull(Object obj, String param) {
        if (obj == null) {
            throw FlexExceptions.wrap(LocalizedFormats.OBJECT_NULL, param);
        }
    }


    /**
     * 断言 Map 集合不为 {@code null} 或者空集合，如果为空则抛出异常，并指明为什么不允许为空集合。
     *
     * @param map   Map 集合
     * @param param 错误消息参数
     * @throws MybatisFlexException 如果集合为空，抛出此异常。
     */
    public static void notEmpty(Map<?, ?> map, String param) {
        if (map == null || map.isEmpty()) {
            throw FlexExceptions.wrap(LocalizedFormats.MAP_NULL_OR_EMPTY, param);
        }
    }

    /**
     * 断言集合不为 {@code null} 或者空集合，如果为空则抛出异常，并指明为什么不允许为空集合。
     *
     * @param collection 集合
     * @param param      错误消息参数
     * @throws MybatisFlexException 如果集合为空，抛出此异常。
     */
    public static void notEmpty(Collection<?> collection, String param) {
        if (collection == null || collection.isEmpty()) {
            throw FlexExceptions.wrap(LocalizedFormats.MAP_NULL_OR_EMPTY, param);
        }
    }

    /**
     * 断言数组不为 {@code null} 或者空数组，如果为空则抛出异常，并指明为什么不允许为空数组。
     *
     * @param array 数组
     * @param param 错误消息参数
     * @throws MybatisFlexException 如果数组为空，抛出此异常。
     */
    public static <T> void notEmpty(T[] array, String param) {
        if (array == null || array.length == 0) {
            throw FlexExceptions.wrap(LocalizedFormats.ARRAY_NULL_OR_EMPTY, param);
        }
    }

    /**
     * 断言传入的数组内容不能为 null 或者 空
     */
    public static <T> void assertAreNotNull(T[] elements, String msg, Object params) {
        if (elements == null || elements.length == 0) {
            throw FlexExceptions.wrap(msg, params);
        }
        for (T element : elements) {
            if (element == null) {
                throw FlexExceptions.wrap(msg, params);
            }
        }
    }

}
