package com.ssitao.code.frame.security.datapermission.rule;

import com.mybatis.flex.core.table.TableDef;

/**
 * 数据权限规则接口
 * <p>
 * 定义数据权限规则的扩展点
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface DataPermissionRule {

    /**
     * 获取规则名称
     *
     * @return 规则名称
     */
    String getName();

    /**
     * 判断是否忽略指定表
     *
     * @param table 表定义
     * @return 是否忽略
     */
    default boolean ignoreTable(TableDef table) {
        return false;
    }

    /**
     * 获取 SQL 片段
     * <p>
     * 例如: dept_id IN (1, 2, 3) 或 user_id = 1
     *
     * @param table 表定义
     * @param tableAlias 表别名
     * @return SQL 片段
     */
    String getSqlSegment(TableDef table, String tableAlias);

    /**
     * 获取规则表达式
     * <p>
     * 用于框架内部解析，默认返回 null
     *
     * @param table 表定义
     * @param tableAlias 表别名
     * @return 规则表达式
     */
    default String getExpression(TableDef table, String tableAlias) {
        return null;
    }

}
