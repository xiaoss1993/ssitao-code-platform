package com.ssitao.code.common.util;

/**
 * Bean工厂接口
 *
 * @since 3.0
 */
public interface BeanFactory {

    <T> T newInstance(Class<T> beanType);
}
