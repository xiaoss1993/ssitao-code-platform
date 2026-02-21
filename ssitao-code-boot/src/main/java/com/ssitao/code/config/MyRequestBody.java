package com.ssitao.code.config;

import java.lang.annotation.*;

/**
 * @author ssitao
 * 2023/3/14 16:09
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface MyRequestBody {

    /**
     * 是否必须出现的参数。
     */
    boolean required() default false;
    /**
     * 解析时用到的JSON的key。
     */
    String value() default "";
    /**
     * 集合元素的ClassType。只有在接口参数为List<E>的时候，需要把E的class传入。
     * 缺省值Class.class表示没有设置。
     */
    Class<?> elementType() default Class.class;
}
