package com.ssitao.code.common.utils;

@FunctionalInterface
public interface Converter {
    <T> T convert(Object source, Class<T> targetClass, Class[] genericType);
}
