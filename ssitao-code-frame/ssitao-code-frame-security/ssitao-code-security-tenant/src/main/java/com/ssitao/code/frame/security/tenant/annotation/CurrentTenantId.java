package com.ssitao.code.frame.security.tenant.annotation;

import java.lang.annotation.*;

/**
 * 当前租户ID注解
 * <p>
 * 用于Controller方法参数上，自动注入当前租户ID。
 * 如果租户功能未启用，则注入配置的默认租户ID。
 *
 * 使用示例：
 * <pre>
 * &#64;PostMapping("/create")
 * public CommonResult&lt;Void&gt; create(@RequestBody CreateCommand command,
 *                                       &#64;CurrentTenantId String tenantId) {
 *     // tenantId 会自动注入当前租户ID
 *     service.create(command, tenantId);
 * }
 * </pre>
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentTenantId {

    /**
     * 是否必需
     * <p>
     * 如果为 true，当租户功能未启用时，会抛出异常。
     * 如果为 false，当租户功能未启用时，会传入 null。
     */
    boolean required() default true;

}
