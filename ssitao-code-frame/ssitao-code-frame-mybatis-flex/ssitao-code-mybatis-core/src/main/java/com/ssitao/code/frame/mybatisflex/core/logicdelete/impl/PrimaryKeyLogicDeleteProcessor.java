

package com.ssitao.code.frame.mybatisflex.core.logicdelete.impl;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexAssert;
import com.ssitao.code.frame.mybatisflex.core.logicdelete.NullableColumnLogicDeleteProcessor;
import com.ssitao.code.frame.mybatisflex.core.table.IdInfo;
import com.ssitao.code.frame.mybatisflex.core.table.TableInfo;

import java.util.List;

import static com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts.EQUALS;

/**
 * 主键逻辑删除处理器。
 *
 * @author ssitao 
 * @see <a href="https://gitee.com/mybatis-flex/mybatis-flex/issues/I7O1VV">I7O1VV</a>
 * @since 1.0.0
 */
public class PrimaryKeyLogicDeleteProcessor extends NullableColumnLogicDeleteProcessor {

    @Override
    public String buildLogicDeletedSet(String logicColumn, TableInfo tableInfo, IDialect dialect) {
        List<IdInfo> primaryKeys = tableInfo.getPrimaryKeyList();
        FlexAssert.notEmpty(primaryKeys, "primaryKeys");
        String column = primaryKeys.get(0).getColumn();
        return dialect.wrap(logicColumn) + EQUALS + dialect.wrap(column);
    }

    /**
     * 逻辑删除后，则更新逻辑删除字段值为主键的值。
     */
    @Override
    public Object getLogicDeletedValue() {
        return null;
    }

}
