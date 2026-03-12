
package com.ssitao.code.frame.mybatisflex.core.handler;

import com.ssitao.code.frame.mybatisflex.annotation.EnumValue;
import com.ssitao.code.frame.mybatisflex.core.util.ClassUtil;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeEnumTypeHandler<E extends Enum<E>> implements TypeHandler<E> {

    private final TypeHandler<E> delegate;

    public CompositeEnumTypeHandler(Class<E> enumClass) {
        boolean isNotFound = false;
        List<Field> enumDbValueFields = ClassUtil.getAllFields(enumClass, f -> f.getAnnotation(EnumValue.class) != null);
        if (enumDbValueFields.isEmpty()) {
            Method enumDbValueMethod = ClassUtil.getFirstMethodByAnnotation(enumClass, EnumValue.class);
            if (enumDbValueMethod == null) {
                isNotFound = true;
            }
        }
        if (isNotFound) {
            delegate = new EnumTypeHandler<>(enumClass);
        } else {
            delegate = new FlexEnumTypeHandler<>(enumClass);
        }
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        delegate.setParameter(ps, i, parameter, jdbcType);
    }

    @Override
    public E getResult(ResultSet rs, String columnName) throws SQLException {
        return delegate.getResult(rs, columnName);
    }

    @Override
    public E getResult(ResultSet rs, int columnIndex) throws SQLException {
        return delegate.getResult(rs, columnIndex);
    }

    @Override
    public E getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return delegate.getResult(cs, columnIndex);
    }

}
