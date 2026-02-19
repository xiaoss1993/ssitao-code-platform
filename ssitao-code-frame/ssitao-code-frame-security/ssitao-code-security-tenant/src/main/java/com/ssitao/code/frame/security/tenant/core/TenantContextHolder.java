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

}
