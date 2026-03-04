package com.ssitao.code.modular.iam.authorization.infrastructure.filter;

import com.ssitao.code.frame.mybatisflex.core.table.TableDef;
import com.ssitao.code.modular.iam.authorization.domain.context.DataPermissionContext;
import com.ssitao.code.modular.iam.authorization.domain.model.IamDataRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.*;

/**
 * 数据权限SQL过滤器
 * <p>
 * 使用 MyBatis 拦截器机制，在 SQL 执行前注入数据权限过滤条件。
 * <p>
 * 支持的数据权限范围：
 * <ul>
 *     <li>ALL - 全部数据，不过滤</li>
 *     <li>DEPT - 本部门数据</li>
 *     <li>DEPT_AND_CHILD - 本部门及子部门数据</li>
 *     <li>SELF - 仅本人数据</li>
 *     <li>CUSTOM - 自定义数据权限，根据配置的规则过滤</li>
 * </ul>
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class DataPermissionFilter implements Interceptor {

    /**
     * 数据权限范围常量
     */
    public static final String DATA_SCOPE_ALL = "ALL";
    public static final String DATA_SCOPE_DEPT = "DEPT";
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "DEPT_AND_CHILD";
    public static final String DATA_SCOPE_SELF = "SELF";
    public static final String DATA_SCOPE_CUSTOM = "CUSTOM";

    /**
     * 默认的部门ID字段名
     */
    private static final String DEFAULT_DEPT_FIELD = "dept_id";

    /**
     * 默认的创建人字段名
     */
    private static final String DEFAULT_CREATE_BY_FIELD = "create_by";

    /**
     * 需要忽略的表（不进行数据权限过滤）
     */
    private static final Set<String> IGNORE_TABLES = new HashSet<>();

    static {
        // 忽略数据权限相关表
        IGNORE_TABLES.add("iam_data_rule");
        IGNORE_TABLES.add("iam_role_data_rule");
        IGNORE_TABLES.add("iam_role");
        IGNORE_TABLES.add("iam_permission");
        IGNORE_TABLES.add("iam_perm_group");
        IGNORE_TABLES.add("sys_tenant");
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取 StatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        // 获取 SQL 类型
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 只对查询语句进行数据权限过滤
        if (sqlCommandType != SqlCommandType.SELECT) {
            return invocation.proceed();
        }

        // 获取数据权限上下文
        String dataScope = DataPermissionContext.getDataScope();
        if (dataScope == null || DATA_SCOPE_ALL.equals(dataScope)) {
            // 全部数据权限或未设置，直接放行
            return invocation.proceed();
        }

        // 获取BoundSql
        BoundSql boundSql = statementHandler.getBoundSql();
        String originalSql = boundSql.getSql();
        String tableName = extractTableName(originalSql);

        // 判断是否需要忽略该表
        if (shouldIgnoreTable(tableName)) {
            return invocation.proceed();
        }

        // 构建数据权限过滤条件
        String dataPermissionCondition = buildDataPermissionCondition(tableName, dataScope);
        if (dataPermissionCondition == null) {
            return invocation.proceed();
        }

        // 注入过滤条件到 SQL
        String modifiedSql = injectDataPermissionCondition(originalSql, dataPermissionCondition);

        // 修改 SQL
        Field sqlField = BoundSql.class.getDeclaredField("sql");
        sqlField.setAccessible(true);
        sqlField.set(boundSql, modifiedSql);

        log.debug("数据权限过滤: table={}, dataScope={}, originalSql={}", tableName, dataScope, originalSql);
        log.debug("数据权限过滤: modifiedSql={}", modifiedSql);

        return invocation.proceed();
    }

    /**
     * 判断是否需要忽略该表
     *
     * @param tableName 表名
     * @return 是否忽略
     */
    private boolean shouldIgnoreTable(String tableName) {
        if (tableName == null) {
            return true;
        }
        // 转换为小写进行比较
        String lowerTableName = tableName.toLowerCase();
        return IGNORE_TABLES.stream().anyMatch(ignoreTable ->
                lowerTableName.contains(ignoreTable.toLowerCase()));
    }

    /**
     * 从 SQL 中提取表名（简单实现）
     *
     * @param sql SQL语句
     * @return 表名
     */
    private String extractTableName(String sql) {
        if (sql == null) {
            return null;
        }
        // 转换为小写，便于匹配
        String lowerSql = sql.toLowerCase();

        // 尝试匹配 FROM 子句
        int fromIndex = lowerSql.indexOf("from ");
        if (fromIndex >= 0) {
            String afterFrom = sql.substring(fromIndex + 5).trim();
            // 获取表名（可能包含别名）
            String[] parts = afterFrom.split("\\s+");
            if (parts.length > 0) {
                String tableName = parts[0];
                // 去除可能的数据库名前缀
                if (tableName.contains(".")) {
                    tableName = tableName.substring(tableName.lastIndexOf(".") + 1);
                }
                // 去除反引号
                tableName = tableName.replace("`", "");
                return tableName;
            }
        }
        return null;
    }

    /**
     * 构建数据权限过滤条件
     *
     * @param tableName  表名
     * @param dataScope  数据权限范围
     * @return 过滤条件SQL片段
     */
    private String buildDataPermissionCondition(String tableName, String dataScope) {
        String tableAlias = getTableAlias(tableName);
        String deptField = tableAlias + "." + DEFAULT_DEPT_FIELD;
        String createByField = tableAlias + "." + DEFAULT_CREATE_BY_FIELD;

        switch (dataScope) {
            case DATA_SCOPE_ALL:
                // 全部数据，不过滤
                return null;

            case DATA_SCOPE_DEPT:
                // 本部门数据
                String deptId = DataPermissionContext.getDeptId();
                if (deptId == null || deptId.isEmpty()) {
                    // 没有部门信息，只看自己的数据
                    return buildSelfCondition(createByField);
                }
                return buildDeptCondition(deptField, deptId);

            case DATA_SCOPE_DEPT_AND_CHILD:
                // 本部门及子部门数据
                String deptIdForDeptAndChild = DataPermissionContext.getDeptId();
                if (deptIdForDeptAndChild == null || deptIdForDeptAndChild.isEmpty()) {
                    return buildSelfCondition(createByField);
                }
                // 获取允许访问的部门ID列表（包括子部门）
                List<String> allowedDeptIds = DataPermissionContext.getAllowedDeptIds();
                if (allowedDeptIds != null && !allowedDeptIds.isEmpty()) {
                    return buildDeptInCondition(deptField, allowedDeptIds);
                }
                return buildDeptCondition(deptField, deptIdForDeptAndChild);

            case DATA_SCOPE_SELF:
                // 仅本人数据
                return buildSelfCondition(createByField);

            case DATA_SCOPE_CUSTOM:
                // 自定义数据权限
                return buildCustomCondition(tableName, tableAlias);

            default:
                log.warn("未知的数据权限范围: {}", dataScope);
                return null;
        }
    }

    /**
     * 获取表别名
     *
     * @param tableName 表名
     * @return 表别名
     */
    private String getTableAlias(String tableName) {
        // 默认使用表名作为别名
        return tableName != null ? tableName : "t";
    }

    /**
     * 构建部门相等条件
     *
     * @param deptField 部门字段
     * @param deptId    部门ID
     * @return 条件SQL
     */
    private String buildDeptCondition(String deptField, String deptId) {
        return deptField + " = '" + deptId + "'";
    }

    /**
     * 构建部门IN条件（用于部门及子部门）
     *
     * @param deptField     部门字段
     * @param allowedDeptIds 允许访问的部门ID列表
     * @return 条件SQL
     */
    private String buildDeptInCondition(String deptField, List<String> allowedDeptIds) {
        if (allowedDeptIds == null || allowedDeptIds.isEmpty()) {
            return "1=0"; // 无权限
        }
        StringBuilder sb = new StringBuilder();
        sb.append(deptField).append(" IN (");
        for (int i = 0; i < allowedDeptIds.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("'").append(allowedDeptIds.get(i)).append("'");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * 构建本人数据条件
     *
     * @param createByField 创建人字段
     * @return 条件SQL
     */
    private String buildSelfCondition(String createByField) {
        // 从上下文获取当前用户ID，这里假设在DataPermissionContext中已设置
        // 实际实现需要从登录信息中获取
        String userId = getCurrentUserId();
        if (userId == null || userId.isEmpty()) {
            return "1=0"; // 无法获取用户ID，拒绝访问
        }
        return createByField + " = '" + userId + "'";
    }

    /**
     * 获取当前用户ID
     *
     * @return 当前用户ID
     */
    private String getCurrentUserId() {
        // 从 Sa-Token 或其他方式获取当前用户ID
        // 这里暂时返回 null，实际使用需要从登录上下文获取
        try {
            com.ssitao.code.frame.satoken.core.SecurityUtil.getLoginUser();
            // 实际实现需要从 LoginUser 获取用户ID
            return null;
        } catch (Exception e) {
            log.warn("获取当前用户ID失败: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 构建自定义数据权限条件
     *
     * @param tableName  表名
     * @param tableAlias 表别名
     * @return 条件SQL
     */
    private String buildCustomCondition(String tableName, String tableAlias) {
        // CUSTOM 类型需要根据用户配置的数据规则进行过滤
        // 这里可以实现更复杂的规则匹配逻辑
        // 目前先实现部门级别的自定义权限

        List<String> allowedDeptIds = DataPermissionContext.getAllowedDeptIds();
        if (allowedDeptIds != null && !allowedDeptIds.isEmpty()) {
            String deptField = tableAlias + "." + DEFAULT_DEPT_FIELD;
            return buildDeptInCondition(deptField, allowedDeptIds);
        }

        // 如果没有配置自定义部门权限，则回退到只看自己的数据
        String createByField = tableAlias + "." + DEFAULT_CREATE_BY_FIELD;
        return buildSelfCondition(createByField);
    }

    /**
     * 注入数据权限过滤条件到 SQL
     *
     * @param originalSql      原始SQL
     * @param condition        过滤条件
     * @return 修改后的SQL
     */
    private String injectDataPermissionCondition(String originalSql, String condition) {
        // 转换为小写，便于查找关键字
        String lowerSql = originalSql.toLowerCase();

        // 查找 WHERE 关键字的位置
        int whereIndex = lowerSql.indexOf("where ");

        if (whereIndex >= 0) {
            // 找到 WHERE 子句，在后面添加条件
            // 需要保持原始大小写
            return originalSql.substring(0, whereIndex + 6) +
                    condition +
                    " AND " +
                    originalSql.substring(whereIndex + 6);
        } else {
            // 没有 WHERE 子句，添加 WHERE 和条件
            // 查找 FROM 关键字，在其后添加
            int fromIndex = lowerSql.indexOf("from ");
            if (fromIndex >= 0) {
                return originalSql.substring(0, fromIndex + 5) +
                        condition +
                        " AND " +
                        originalSql.substring(fromIndex + 5);
            }
        }

        // 无法处理，直接返回原始SQL
        log.warn("无法注入数据权限条件到SQL: {}", originalSql);
        return originalSql;
    }

    @Override
    public Object plugin(Object target) {
        // 只对 StatementHandler 进行拦截
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以在这里读取配置属性
        log.info("数据权限过滤器初始化完成");
    }
}
