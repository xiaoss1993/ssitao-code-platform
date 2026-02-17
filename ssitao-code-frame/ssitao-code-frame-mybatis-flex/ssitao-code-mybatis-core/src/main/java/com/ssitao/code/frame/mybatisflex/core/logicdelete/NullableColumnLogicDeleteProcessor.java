

package com.ssitao.code.frame.mybatisflex.core.logicdelete;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.query.*;
import com.ssitao.code.frame.mybatisflex.core.table.TableInfo;

/**
 * 数据列默认值为 {@code null} 值的逻辑删除处理器。
 *
 * @author 王帅
 * @since 2023-07-30
 */
public abstract class NullableColumnLogicDeleteProcessor extends AbstractLogicDeleteProcessor {

    @Override
    public String buildLogicNormalCondition(String logicColumn, TableInfo tableInfo, IDialect dialect) {
        return dialect.wrap(logicColumn) + " IS NULL";
    }

    @Override
    public void buildQueryCondition(QueryWrapper queryWrapper, TableInfo tableInfo, String joinTableAlias) {
        QueryTable queryTable = new QueryTable(tableInfo.getSchema(), tableInfo.getTableName()).as(joinTableAlias);
        QueryColumn queryColumn = new QueryColumn(queryTable, tableInfo.getLogicDeleteColumn());
        queryWrapper.and(queryColumn.isNull());
    }

    /**
     * 逻辑删除字段值为 {@code null} 表示数据未删除。
     */
    @Override
    public Object getLogicNormalValue() {
        return null;
    }

}
