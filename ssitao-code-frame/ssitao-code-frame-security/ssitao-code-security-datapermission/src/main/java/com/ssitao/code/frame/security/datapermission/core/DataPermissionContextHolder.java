package com.ssitao.code.frame.security.datapermission.core;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

/**
 * 数据权限上下文持有者
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@UtilityClass
public class DataPermissionContextHolder {

    /**
     * 包含的权限规则
     */
    private static final ThreadLocal<Set<String>> INCLUDE_RULES = new ThreadLocal<>();

    /**
     * 排除的权限规则
     */
    private static final ThreadLocal<Set<String>> EXCLUDE_RULES = new ThreadLocal<>();

    /**
     * 是否忽略租户权限
     */
    private static final ThreadLocal<Boolean> IGNORE_TENANT = new ThreadLocal<>();

    /**
     * 获取包含的权限规则
     *
     * @return 包含的权限规则
     */
    public Set<String> getIncludeRules() {
        return INCLUDE_RULES.get();
    }

    /**
     * 设置包含的权限规则
     *
     * @param rules 规则集合
     */
    public void setIncludeRules(Set<String> rules) {
        if (rules == null || rules.isEmpty()) {
            INCLUDE_RULES.remove();
        } else {
            INCLUDE_RULES.set(rules);
        }
    }

    /**
     * 获取排除的权限规则
     *
     * @return 排除的权限规则
     */
    public Set<String> getExcludeRules() {
        return EXCLUDE_RULES.get();
    }

    /**
     * 设置排除的权限规则
     *
     * @param rules 规则集合
     */
    public void setExcludeRules(Set<String> rules) {
        if (rules == null || rules.isEmpty()) {
            EXCLUDE_RULES.remove();
        } else {
            EXCLUDE_RULES.set(rules);
        }
    }

    /**
     * 获取是否忽略租户权限
     *
     * @return 是否忽略租户权限
     */
    public Boolean getIgnoreTenant() {
        return IGNORE_TENANT.get();
    }

    /**
     * 设置是否忽略租户权限
     *
     * @param ignoreTenant 是否忽略租户权限
     */
    public void setIgnoreTenant(Boolean ignoreTenant) {
        if (ignoreTenant == null || !ignoreTenant) {
            IGNORE_TENANT.remove();
        } else {
            IGNORE_TENANT.set(ignoreTenant);
        }
    }

    /**
     * 清除所有上下文
     */
    public void clear() {
        INCLUDE_RULES.remove();
        EXCLUDE_RULES.remove();
        IGNORE_TENANT.remove();
    }

    /**
     * 判断规则是否被排除
     *
     * @param rule 规则名称
     * @return 是否被排除
     */
    public boolean isExcluded(String rule) {
        Set<String> excludeRules = getExcludeRules();
        return excludeRules != null && excludeRules.contains(rule);
    }

    /**
     * 判断规则是否被包含
     *
     * @param rule 规则名称
     * @return 是否被包含
     */
    public boolean isIncluded(String rule) {
        Set<String> includeRules = getIncludeRules();
        // 如果没有指定包含规则，默认包含所有规则
        if (includeRules == null || includeRules.isEmpty()) {
            return !isExcluded(rule);
        }
        return includeRules.contains(rule);
    }

}
