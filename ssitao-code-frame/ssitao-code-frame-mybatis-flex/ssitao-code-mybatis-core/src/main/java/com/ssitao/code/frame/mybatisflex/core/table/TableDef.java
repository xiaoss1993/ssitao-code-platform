

package com.ssitao.code.frame.mybatisflex.core.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryTable;
import com.ssitao.code.frame.mybatisflex.core.util.MapUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 表定义，内包含字段。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class TableDef extends QueryTable {

    private static final Map<String, TableDef> CACHE = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    protected static <V extends TableDef> V getCache(String key, Function<String, V> mappingFunction) {
        return MapUtil.computeIfAbsent((Map<String, V>) CACHE, key, mappingFunction);
    }

    protected TableDef(String schema, String tableName) {
        super(schema, tableName);
    }

    protected TableDef(String schema, String tableName, String alias) {
        super(schema, tableName, alias);
    }

    /**
     * 兼容方法，与 {@link #getName()} 相同。
     *
     * @return 表名
     */
    public String getTableName() {
        return name;
    }

    public TableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TableDef(this.schema, this.name, alias));
    }

}
