

package com.ssitao.code.frame.mybatisflex.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * VO（View Object）类。
 *
 * @author 王帅
 * @since 2024-04-02
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableRef {

    /**
     * 对应的 <b>实体类</b> 引用。
     *
     * @return 实体类引用
     */
    Class<?> value();

    /**
     * 是否复制引用类 {@code @Table} 注解上的内容，默认为：{@code true}。
     *
     * @return 是否复制属性
     */
    boolean copyTableProps() default true;

}
