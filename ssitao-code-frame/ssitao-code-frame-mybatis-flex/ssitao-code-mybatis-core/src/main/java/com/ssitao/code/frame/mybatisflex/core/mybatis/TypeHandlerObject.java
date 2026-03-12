
package com.ssitao.code.frame.mybatisflex.core.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 类型处理器包装。
 *
 * @author ssitao
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class TypeHandlerObject implements Serializable {

    private final TypeHandler typeHandler;
    private final Object value;
    private final JdbcType jdbcType;

    public TypeHandlerObject(TypeHandler typeHandler, Object value, JdbcType jdbcType) {
        this.typeHandler = typeHandler;
        this.value = value;
        this.jdbcType = jdbcType;
    }

    public void setParameter(PreparedStatement ps, int i) throws SQLException {
        typeHandler.setParameter(ps, i, value, jdbcType);
    }

    /**
     * 获取未处理的原始值。
     *
     * @return 原始值
     */
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TypeHandlerObject{"
            + "value=" + value
            + ", typeHandler=" + typeHandler.getClass().getSimpleName()
            + '}';
    }

}
