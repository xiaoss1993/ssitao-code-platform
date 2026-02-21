

package com.ssitao.code.frame.mybatisflex.annotation;

import java.lang.annotation.*;

/**
 * 别名注解，用于解决列名重复。
 *
 * @author ssitao 
 * @since 1.0.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ColumnAlias {

    /**
     * 列的别名，在查询的时候，查询 sql 会自动添加 as ...
     *
     * @return 别名
     */
    String[] value();

}
