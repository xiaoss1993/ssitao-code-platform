
package com.ssitao.code.frame.mybatisflex.core.logicdelete;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.query.*;
import com.ssitao.code.frame.mybatisflex.core.table.TableInfo;

import static com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts.EQUALS;

/**
 * 逻辑删除处理器抽象类。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public abstract class AbstractLogicDeleteProcessor implements LogicDeleteProcessor {

    @Override
    public String buildLogicNormalCondition(String logicColumn, TableInfo tableInfo, IDialect dialect) {
        return dialect.wrap(logicColumn) + EQUALS + getLogicNormalValue();
    }

    @Override
    public String buildLogicDeletedSet(String logicColumn, TableInfo tableInfo, IDialect dialect) {
        return dialect.wrap(logicColumn) + EQUALS + getLogicDeletedValue();
    }

    @Override
    public void buildQueryCondition(QueryWrapper queryWrapper, TableInfo tableInfo, String joinTableAlias) {
        QueryTable queryTable = new QueryTable(tableInfo.getSchema(), tableInfo.getTableName()).as(joinTableAlias);
        QueryColumn queryColumn = new QueryColumn(queryTable, tableInfo.getLogicDeleteColumn());
        queryWrapper.and(queryColumn.eq(getLogicNormalValue()));
    }

}


