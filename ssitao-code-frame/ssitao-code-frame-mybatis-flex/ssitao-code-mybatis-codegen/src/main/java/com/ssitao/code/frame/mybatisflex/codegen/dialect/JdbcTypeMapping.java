
package com.ssitao.code.frame.mybatisflex.codegen.dialect;

import com.ssitao.code.frame.mybatisflex.codegen.entity.Column;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 字段类型映射。
 *
 * @author ssitao
 */
public class JdbcTypeMapping {

    private JdbcTypeMapping() {
    }

    private static final Map<String, String> mapping = new HashMap<>();
    private static JdbcTypeMapper typeMapper;

    static {
        registerMapping("[B", "byte[]");
        registerMapping("oracle.jdbc.OracleBlob", "byte[]");
    }

    public static void registerMapping(Class<?> from, Class<?> to) {
        registerMapping(from.getName(), to.getName());
    }

    public static void registerMapping(String from, String to) {
        mapping.put(from, to);
    }

    public static Map<String, String> getMapping() {
        return mapping;
    }

    public static JdbcTypeMapper getTypeMapper() {
        return typeMapper;
    }

    public static void setTypeMapper(JdbcTypeMapper typeMapper) {
        JdbcTypeMapping.typeMapper = typeMapper;
    }

    /**
     * 当只使用 date 类型来映射数据库的所有 "时间" 类型时，调用此方法
     */
    public static void registerDateTypes() {
        registerMapping("java.sql.Time", "java.util.Date");
        registerMapping("java.sql.Timestamp", "java.util.Date");
        registerMapping("java.time.LocalDateTime", "java.util.Date");
        registerMapping("java.time.LocalDate", "java.util.Date");
    }

    public static String getType(String jdbcType, Table table, Column column) {
        if (typeMapper != null) {
            String type = typeMapper.getType(jdbcType, table, column);
            if (StringUtil.hasText(type)) {
                return type;
            }
        }
        String registered = mapping.get(jdbcType);
        return StringUtil.hasText(registered) ? registered : jdbcType;
    }

    public interface JdbcTypeMapper {
        String getType(String jdbcType, Table table, Column column);
    }

}
