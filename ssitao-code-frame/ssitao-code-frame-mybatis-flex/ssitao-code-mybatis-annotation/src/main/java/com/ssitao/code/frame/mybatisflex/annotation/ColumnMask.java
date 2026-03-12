
package com.ssitao.code.frame.mybatisflex.annotation;

import java.lang.annotation.*;

/**
 * 数据脱敏注解。
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ColumnMask {

    /**
     * 脱敏方式。
     */
    String value();

}
