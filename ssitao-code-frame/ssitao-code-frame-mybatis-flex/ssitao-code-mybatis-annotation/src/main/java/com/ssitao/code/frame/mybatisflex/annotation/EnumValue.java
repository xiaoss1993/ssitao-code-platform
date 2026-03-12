
package com.ssitao.code.frame.mybatisflex.annotation;

import java.lang.annotation.*;

/**
 * 枚举属性注解。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EnumValue {

}
