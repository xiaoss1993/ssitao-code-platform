package com.ssitao.code.frame.security.tenant.utils;

import com.ssitao.code.frame.security.tenant.config.TenantProperties;
import com.ssitao.code.frame.security.tenant.core.TenantContextHolder;

/**
 * 租户工具类
 * <p>
 * 提供租户ID获取的工具方法，自动处理租户启用/禁用情况
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public class TenantUtils {

    private static TenantProperties tenantProperties;

    /**
     * 设置租户配置（由配置类调用）
     *
     * @param properties 租户配置
     */
    public static void setTenantProperties(TenantProperties properties) {
        TenantUtils.tenantProperties = properties;
    }

    /**
     * 获取当前租户ID
     * <p>
     * 优先从上下文中获取租户ID，如果上下文中没有且租户功能已启用，则使用默认租户ID。
     * 如果租户功能未启用，返回 null。
     *
     * @return 租户ID，如果租户功能未启用则返回 null
     */
    public static String getTenantId() {
        // 如果租户功能未启用或配置未初始化，返回 null
        if (tenantProperties == null || !tenantProperties.getEnabled()) {
            return null;
        }

        // 从上下文中获取租户ID
        String tenantId = TenantContextHolder.getTenantId();
        if (tenantId != null && !tenantId.isEmpty()) {
            return tenantId;
        }

        // 使用配置的默认租户ID
        return tenantProperties.getDefaultTenantId();
    }

    /**
     * 获取当前租户ID，如果不为空则返回
     * <p>
     * 无论租户功能是否启用，都会尝试获取租户ID。
     * 主要用于需要明确指定租户ID的场景。
     *
     * @return 租户ID，可能为 null 或空字符串
     */
    public static String getTenantIdOrNull() {
        String tenantId = TenantContextHolder.getTenantId();
        return (tenantId != null && !tenantId.isEmpty()) ? tenantId : null;
    }

    /**
     * 判断租户功能是否启用
     *
     * @return 如果启用返回 true，否则返回 false
     */
    public static boolean isTenantEnabled() {
        return tenantProperties != null && tenantProperties.getEnabled();
    }

    /**
     * 忽略租户条件执行操作
     * <p>
     * 在执行操作时临时忽略租户隔离
     *
     * @param runnable 要执行的操作
     */
    public static void withoutTenant(Runnable runnable) {
        // TODO: 集成 MyBatis-Flex 的租户忽略功能
        try {
            runnable.run();
        } finally {
            // 清除可能设置的忽略标志
        }
    }

}
