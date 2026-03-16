package com.ssitao.code.common.util;

/**
 * ToString操作器接口
 *
 * @since 3.0.0-RC
 */
public interface ToStringOperator<T> {

    /**
     * 将对象转换为字符串
     *
     * @param target 目标对象
     * @return 字符串表示
     */
    String toString(T target);

    /**
     * 将对象转换为字符串，排除指定属性
     *
     * @param target          目标对象
     * @param ignoreProperty  排除的属性
     * @return 字符串表示
     */
    String toString(T target, String... ignoreProperty);
}
