package com.ssitao.code.frame.security.tenant.core;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 租户上下文持有者
 * <p>
 * 使用 TransmittableThreadLocal 解决线程池场景下的线程传递问题
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public class TenantContextHolder {

    /**
     * 租户ID 线程变量
     */
    private static final ThreadLocal<String> TENANT_ID = new TransmittableThreadLocal<>();

    /**
     * 是否忽略租户隔离 线程变量
     */
    private static final ThreadLocal<Boolean> IGNORE_TENANT = new TransmittableThreadLocal<>();

    /**
     * 获取当前租户ID
     *
     * @return 租户ID
     */
    public static String getTenantId() {
        return TENANT_ID.get();
    }

    /**
     * 设置当前租户ID
     *
     * @param tenantId 租户ID
     */
    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }

    /**
     * 清除当前租户ID
     */
    public static void clear() {
        TENANT_ID.remove();
    }

    /**
     * 判断当前是否为租户环境
     *
     * @return 是否为租户环境
     */
    public static boolean isTenant() {
        return TENANT_ID.get() != null;
    }

    /**
     * 获取是否忽略租户隔离
     *
     * @return 是否忽略租户隔离
     */
    public static boolean isIgnoreTenant() {
        Boolean ignore = IGNORE_TENANT.get();
        return ignore != null && ignore;
    }

    /**
     * 设置是否忽略租户隔离
     *
     * @param ignore 是否忽略租户隔离
     */
    public static void setIgnoreTenant(boolean ignore) {
        IGNORE_TENANT.set(ignore);
    }

    /**
     * 清除忽略租户隔离标志
     */
    public static void clearIgnoreTenant() {
        IGNORE_TENANT.remove();
    }

    /**
     * 清除所有租户上下文
     */
    public static void clearAll() {
        TENANT_ID.remove();
        IGNORE_TENANT.remove();
    }

}
