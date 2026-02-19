package com.ssitao.code.frame.security.datapermission.annotation;

import java.lang.annotation.*;

/**
 * 数据权限注解
 * <p>
 * 用于标注需要进行数据权限控制的方法或类
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {

    /**
     * 是否启用数据权限
     */
    boolean enable() default true;

    /**
     * 包含的权限规则
     * <p>
     * 仅执行指定的权限规则
     */
    String[] includeRules() default {};

    /**
     * 排除的权限规则
     * <p>
     * 不执行指定的权限规则
     */
    String[] excludeRules() default {};

    /**
     * 是否忽略租户权限
     */
    boolean ignoreTenant() default false;

}
