package org.tweb.mybatis.rdb.dialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库方言工厂
 * <p>
 * 用于创建和管理不同数据库的方言实例
 *
 * @author ssitao
 * @since 1.0.0
 */
public class DialectFactory {

    private static final Logger log = LoggerFactory.getLogger(DialectFactory.class);

    /**
     * 方言缓存
     */
    private static final Map<String, Dialect> DIALECT_CACHE = new ConcurrentHashMap<>();

    /**
     * 默认方言
     */
    private static volatile Dialect DEFAULT_DIALECT;

    static {
        // 预加载常用方言
        registerDialect(new MySQLDialect());
        registerDialect(new OracleDialect());
        registerDialect(new PostgreSQLDialect());
    }

    /**
     * 获取默认方言
     *
     * @return 默认方言实例
     */
    public static Dialect getDefaultDialect() {
        if (DEFAULT_DIALECT == null) {
            DEFAULT_DIALECT = getDialect("mysql");
        }
        return DEFAULT_DIALECT;
    }

    /**
     * 设置默认方言
     *
     * @param dialect 方言实例
     */
    public static void setDefaultDialect(Dialect dialect) {
        DEFAULT_DIALECT = dialect;
        log.info("设置默认方言为: {}", dialect.getName());
    }

    /**
     * 根据名称获取方言
     *
     * @param name 方言名称（不区分大小写）
     * @return 方言实例
     */
    public static Dialect getDialect(String name) {
        if (name == null || name.isEmpty()) {
            return getDefaultDialect();
        }

        String key = name.toLowerCase();
        Dialect dialect = DIALECT_CACHE.get(key);

        if (dialect == null) {
            // 尝试动态创建
            dialect = createDialect(key);
            if (dialect != null) {
                registerDialect(dialect);
            }
        }

        return dialect;
    }

    /**
     * 根据数据库产品名称获取方言
     *
     * @param productName 数据库产品名称（从 JDBC DatabaseMetaData 获取）
     * @return 方言实例
     */
    public static Dialect getDialectByProductName(String productName) {
        if (productName == null || productName.isEmpty()) {
            return getDefaultDialect();
        }

        String lowerName = productName.toLowerCase();

        if (lowerName.contains("mysql")) {
            return getDialect("mysql");
        } else if (lowerName.contains("oracle")) {
            return getDialect("oracle");
        } else if (lowerName.contains("postgresql") || lowerName.contains("postgres")) {
            return getDialect("postgresql");
        } else if (lowerName.contains("sql server") || lowerName.contains("microsoft")) {
            return getDialect("sqlserver");
        } else if (lowerName.contains("h2")) {
            return getDialect("h2");
        } else if (lowerName.contains("hsql") || lowerName.contains("hypersonic")) {
            return getDialect("hsql");
        } else if (lowerName.contains("db2")) {
            return getDialect("db2");
        } else if (lowerName.contains("sqlite")) {
            return getDialect("sqlite");
        } else if (lowerName.contains("informix")) {
            return getDialect("informix");
        } else if (lowerName.contains("sybase")) {
            return getDialect("sybase");
        }

        log.warn("未找到匹配的方言，使用默认方言: {} (数据库: {})", getDefaultDialect().getName(), productName);
        return getDefaultDialect();
    }

    /**
     * 注册方言
     *
     * @param dialect 方言实例
     */
    public static void registerDialect(Dialect dialect) {
        if (dialect != null) {
            String key = dialect.getName().toLowerCase();
            DIALECT_CACHE.put(key, dialect);
            log.debug("注册方言: {} -> {}", key, dialect.getClass().getSimpleName());
        }
    }

    /**
     * 动态创建方言
     *
     * @param name 方言名称
     * @return 方言实例，如果无法创建则返回 null
     */
    private static Dialect createDialect(String name) {
        try {
            String className = "org.tweb.mybatis.rdb.dialect." +
                    Character.toUpperCase(name.charAt(0)) +
                    name.substring(1).toLowerCase() +
                    "Dialect";
            Class<?> clazz = Class.forName(className);
            if (Dialect.class.isAssignableFrom(clazz)) {
                return (Dialect) clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            log.warn("无法创建方言: {}", name, e);
        }
        return null;
    }

    /**
     * 清除方言缓存
     */
    public static void clearCache() {
        DIALECT_CACHE.clear();
        DEFAULT_DIALECT = null;
        log.debug("清除方言缓存");
    }

    /**
     * 获取已注册的方言数量
     *
     * @return 方言数量
     */
    public static int getRegisteredCount() {
        return DIALECT_CACHE.size();
    }
}