
package com.ssitao.code.starter.data.core.enums;


import com.ssitao.code.starter.data.core.function.ISqlFunction;

/**
 * 数据库类型枚举
 *
 */
public enum DatabaseType implements ISqlFunction {

    /**
     * MySQL
     */
    MYSQL("MySQL") {
        @Override
        public String findInSet() {
            return "find_in_set({0}, {1}) <> 0";
        }
    },

    /**
     * PostgreSQL
     */
    POSTGRE_SQL("PostgreSQL") {
        @Override
        public String findInSet() {
            return "(select position(',{0},' in ','||{1}||',')) <> 0";
        }
    },;

    private final String database;

    DatabaseType(String database) {
        this.database = database;
    }

    /**
     * 获取数据库类型
     *
     * @param database 数据库
     */
    public static DatabaseType get(String database) {
        for (DatabaseType databaseType : DatabaseType.values()) {
            if (databaseType.database.equalsIgnoreCase(database)) {
                return databaseType;
            }
        }
        return null;
    }

    public String getDatabase() {
        return database;
    }
}
