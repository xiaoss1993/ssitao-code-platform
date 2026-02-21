package com.ssitao.code.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 转换忽略注解
 * <p>
 * 标注在字段上，表示在进行对象转换时忽略该字段
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertIgnore {
}
