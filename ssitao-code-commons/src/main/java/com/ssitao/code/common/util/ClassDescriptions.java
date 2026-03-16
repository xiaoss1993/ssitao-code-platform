package com.ssitao.code.common.util;

import java.util.Map;

/**
 * 类描述缓存
 *
 * @since 3.0
 */
public class ClassDescriptions {

    private static final Map<Class<?>, ClassDescription> CACHE = new java.util.concurrent.ConcurrentHashMap<>();

    public static ClassDescription getDescription(Class<?> type) {
        return CACHE.computeIfAbsent(type, ClassDescription::new);
    }

}
