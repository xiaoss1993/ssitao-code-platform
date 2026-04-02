package com.ssitao.code.common.utils;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * 枚举字典接口,使用枚举来实现数据字典
 *
 * @since 3.0
 */
public interface EnumDict<V> {

    /**
     * 获取枚举的值
     *
     * @return 枚举的值
     */
    V getValue();

    /**
     * 获取枚举的文本
     *
     * @return 枚举的文本
     */
    String getText();

    /**
     * 获取枚举序号
     *
     * @return 枚举序号
     */
    int ordinal();

    /**
     * 获取掩码
     *
     * @return 掩码
     */
    default long getMask() {
        return 1L << ordinal();
    }

    /**
     * 对比是否和value相等
     *
     * @param v value
     * @return 是否相等
     */
    @SuppressWarnings("all")
    default boolean eq(Object v) {
        if (v == null) {
            return false;
        }
        return this == v
                || getValue() == v
                || getValue().equals(v)
                || String.valueOf(getValue()).equalsIgnoreCase(String.valueOf(v))
                || getText().equalsIgnoreCase(String.valueOf(v));
    }

    /**
     * 检查是否在掩码内
     *
     * @param mask 掩码
     * @return 是否在掩码内
     */
    default boolean in(long mask) {
        return (mask & getMask()) != 0;
    }

    /**
     * 从指定的枚举类中查找想要的枚举
     *
     * @param type      枚举类
     * @param predicate 判断逻辑
     * @param <T>       枚举类型
     * @return 查找到的结果
     */
    static <T extends Enum & EnumDict> Optional<T> find(Class<T> type, Predicate<T> predicate) {
        if (type.isEnum()) {
            for (T enumDict : type.getEnumConstants()) {
                if (predicate.test(enumDict)) {
                    return Optional.of(enumDict);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * 根据枚举的getValue()来查找
     *
     * @param type  枚举类
     * @param value 值
     * @param <T>   枚举类型
     * @return 查找到的结果
     */
    static <T extends Enum & EnumDict<?>> Optional<T> findByValue(Class<T> type, Object value) {
        return find(type, e -> e.getValue() == value || e.getValue().equals(value) || String.valueOf(e.getValue()).equalsIgnoreCase(String.valueOf(value)));
    }
}
