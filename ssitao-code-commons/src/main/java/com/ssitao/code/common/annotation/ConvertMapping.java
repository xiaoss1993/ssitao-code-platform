package com.ssitao.code.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 转换字段映射注解
 * <p>
 * 标注在字段上，用于指定源对象和目标对象之间字段名称的映射关系
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertMapping {

    /**
     * 源字段名称
     *
     * @return 源对象中的字段名
     */
    String source();
}
