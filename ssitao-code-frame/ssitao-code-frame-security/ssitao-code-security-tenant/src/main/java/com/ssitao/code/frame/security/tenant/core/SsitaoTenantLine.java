package com.ssitao.code.frame.security.tenant.core;

import com.mybatis.flex.core.handler.TenantLine;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * SSITAO 租户拦截器
 * <p>
 * 实现 MyBatis-Flex 的 TenantLine 接口，实现多租户 SQL 自动拼接
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
public class SsitaoTenantLine implements TenantLine {

    /**
     * 需要忽略租户隔离的表
     */
    private static final Set<String> IGNORE_TABLES = new HashSet<>(Arrays.asList(
            "iam_tenant",
            "iam_permission",
            "iam_role_permission",
            "system_dict_type",
            "system_dict_data"
    ));

    /**
     * 需要忽略租户隔离的表前缀
     */
    private static final Set<String> IGNORE_TABLE_PREFIXES = new HashSet<>(Arrays.asList(
            "system_config",
            "system_log",
            "system_notice"
    ));

    @Override
    public Object getTenantId() {
        String tenantId = TenantContextHolder.getTenantId();
        if (tenantId == null) {
            log.warn("当前请求未指定租户ID，可能导致数据安全问题");
            return "0";
        }
        return tenantId;
    }

    @Override
    public boolean ignoreTable(String tableName) {
        if (IGNORE_TABLES.contains(tableName)) {
            return true;
        }
        // 检查是否以忽略前缀开头
        for (String prefix : IGNORE_TABLE_PREFIXES) {
            if (tableName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取租户字段名
     *
     * @return 租户字段名
     */
    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

}
