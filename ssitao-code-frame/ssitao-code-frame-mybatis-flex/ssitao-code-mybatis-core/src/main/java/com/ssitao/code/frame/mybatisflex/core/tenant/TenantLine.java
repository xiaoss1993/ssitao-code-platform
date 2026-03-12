
package com.ssitao.code.frame.mybatisflex.core.tenant;

/**
 * 租户行级权限拦截器接口
 * <p>
 * 用于实现多租户数据隔离，在执行SQL时自动添加租户条件。
 * 此接口定义了获取租户ID、忽略表和租户字段名的规范。
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface TenantLine {

    /**
     * 获取当前租户ID
     * <p>
     * 在SQL执行时，此方法返回的租户ID会被自动添加到WHERE条件中。
     *
     * @return 租户ID，可以是String、Number等类型
     */
    Object getTenantId();

    /**
     * 判断指定表是否需要忽略租户隔离
     * <p>
     * 某些系统表（如租户表、权限表等）不需要进行租户隔离，
     * 可以通过此方法返回true来忽略这些表。
     *
     * @param tableName 表名
     * @return 如果忽略租户隔离返回true，否则返回false
     */
    boolean ignoreTable(String tableName);

    /**
     * 获取租户字段名
     * <p>
     * 默认返回 "tenant_id"，子类可以重写此方法来使用不同的字段名。
     *
     * @return 租户字段名
     */
    default String getTenantIdColumn() {
        return "tenant_id";
    }

}
