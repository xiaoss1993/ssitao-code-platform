package com.ssitao.code.frame.extra.idempotent.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 幂等控制注解
 * <p>
 * 用于标注需要幂等控制的方法，防止重复提交
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {

    /**
     * 幂等 Key 的前缀
     * <p>
     * 默认使用方法签名的 MD5 值
     */
    String keyPrefix() default "";

    /**
     * 幂等 Key 的生成表达式
     * <p>
     * 支持 SpEL 表达式，例如：#user.id
     */
    String keyExpression() default "";

    /**
     * 幂等时间（单位）
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 幂等时间值
     * <p>
     * 在此时间内重复请求会被拦截
     */
    long timeout() default 5;

    /**
     * 提示信息
     */
    String message() default "请勿重复提交";

}
