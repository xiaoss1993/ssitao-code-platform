package com.ssitao.code.common.utils;

/**
 * Bean工厂接口
 *
 * @since 3.0
 */
public interface BeanFactory {

    <T> T newInstance(Class<T> beanType);
}
