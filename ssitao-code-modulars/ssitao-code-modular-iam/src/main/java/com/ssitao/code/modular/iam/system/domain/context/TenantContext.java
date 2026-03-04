package com.ssitao.code.modular.iam.system.domain.context;

/**
 * 租户上下文
 * 使用ThreadLocal存储当前线程的租户信息
 */
public class TenantContext {

    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();
    private static final ThreadLocal<String> TENANT_CODE = new ThreadLocal<>();

    /**
     * 设置当前租户ID
     */
    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }

    /**
     * 获取当前租户ID
     */
    public static String getTenantId() {
        return TENANT_ID.get();
    }

    /**
     * 设置当前租户编码
     */
    public static void setTenantCode(String tenantCode) {
        TENANT_CODE.set(tenantCode);
    }

    /**
     * 获取当前租户编码
     */
    public static String getTenantCode() {
        return TENANT_CODE.get();
    }

    /**
     * 判断是否有租户上下文
     */
    public static boolean hasTenant() {
        return TENANT_ID.get() != null;
    }

    /**
     * 清除租户上下文
     */
    public static void clear() {
        TENANT_ID.remove();
        TENANT_CODE.remove();
    }
}
