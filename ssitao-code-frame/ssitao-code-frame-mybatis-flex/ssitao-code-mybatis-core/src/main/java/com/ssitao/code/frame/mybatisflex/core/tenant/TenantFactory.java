
package com.ssitao.code.frame.mybatisflex.core.tenant;

/**
 * 租户工厂接口
 * <p>
 * 用于定义租户ID的获取策略。
 * 实现此接口可以自定义租户ID的来源，如从用户上下文、请求头等地方获取。
 *
 * @author ssitao
 * @since 1.0.0
 */
public interface TenantFactory {

    /**
     * 获取当前租户ID
     *
     * @deprecated 使用 {@link #getTenantIds(String)} 代替
     * @return 租户ID数组
     */
    @Deprecated
    Object[] getTenantIds();

    /**
     * 获取指定表的租户ID
     * <p>
     * 默认实现调用无参方法，子类可以重写此方法为不同表返回不同的租户ID
     *
     * @param tableName 表名
     * @return 租户ID数组
     */
    default Object[] getTenantIds(String tableName) {
        return getTenantIds();
    }

}
