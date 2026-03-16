package com.ssitao.code.common.util;

@FunctionalInterface
public interface Converter {
    <T> T convert(Object source, Class<T> targetClass, Class[] genericType);
}
