
package com.ssitao.code.frame.mybatisflex.core.tenant;

import java.util.function.Supplier;

/**
 * 多租户管理器
 * <p>
 * 用于管理多租户功能，包括租户ID的获取和租户条件的控制。
 * 支持在SQL执行时自动注入租户条件，也支持临时忽略租户条件。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TenantManager {

    /**
     * 私有构造函数，防止实例化
     */
    private TenantManager() {
    }

    /**
     * 忽略租户条件的标志
     */
    private static final ThreadLocal<Boolean> ignoreFlags = new ThreadLocal<>();

    /**
     * 租户工厂
     */
    private static TenantFactory tenantFactory;

    /**
     * 获取租户工厂
     *
     * @return 租户工厂实例
     */
    public static TenantFactory getTenantFactory() {
        return tenantFactory;
    }

    /**
     * 设置租户工厂
     *
     * @param tenantFactory 租户工厂实例
     */
    public static void setTenantFactory(TenantFactory tenantFactory) {
        TenantManager.tenantFactory = tenantFactory;
    }

    /**
     * 在忽略租户条件的上下文中执行任务
     *
     * @param <T> 返回值类型
     * @param supplier 要执行的任务
     * @return 任务执行结果
     */
    public static <T> T withoutTenantCondition(Supplier<T> supplier) {
        try {
            ignoreTenantCondition();
            return supplier.get();
        } finally {
            restoreTenantCondition();
        }
    }

    /**
     * 在忽略租户条件的上下文中执行任务
     *
     * @param runnable 要执行的任务
     */
    public static void withoutTenantCondition(Runnable runnable) {
        try {
            ignoreTenantCondition();
            runnable.run();
        } finally {
            restoreTenantCondition();
        }
    }


    /**
     * 忽略租户条件
     * <p>
     * 设置忽略标志，使后续的SQL查询不自动添加租户条件
     */
    public static void ignoreTenantCondition() {
        ignoreFlags.set(Boolean.TRUE);
    }

    /**
     * 判断是否忽略租户条件
     *
     * @return 如果忽略租户条件返回true
     */
    public static boolean isIgnoreTenantCondition() {
        return Boolean.TRUE.equals(ignoreFlags.get());
    }

    /**
     * 恢复租户条件
     * <p>
     * 清除忽略标志，恢复正常的租户条件处理
     */
    public static void restoreTenantCondition() {
        ignoreFlags.remove();
    }

    /**
     * 获取当前租户ID
     *
     * @deprecated 使用 {@link #getTenantIds(String)} 代替
     * @return 租户ID数组
     */
    @Deprecated
    public static Object[] getTenantIds() {
        return getTenantIds(null);
    }

    /**
     * 获取指定表的租户ID
     *
     * @param tableName 表名
     * @return 租户ID数组，如果忽略租户或未配置租户工厂则返回null
     */
    public static Object[] getTenantIds(String tableName) {
        if (isIgnoreTenantCondition()) {
            return null;
        }
        return tenantFactory != null ? tenantFactory.getTenantIds(tableName) : null;
    }


}
