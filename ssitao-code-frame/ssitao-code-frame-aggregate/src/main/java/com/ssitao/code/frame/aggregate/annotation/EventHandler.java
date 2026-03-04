package com.ssitao.code.frame.aggregate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事件处理器注解
 * <p>
 * 标注在方法上，表示该方法用于处理特定类型的领域事件
 * 方法参数应该是领域事件类型
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
}
