
package com.ssitao.code.frame.mybatisflex.core.logicdelete.impl;

import com.ssitao.code.frame.mybatisflex.core.FlexGlobalConfig;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.logicdelete.AbstractLogicDeleteProcessor;
import com.ssitao.code.frame.mybatisflex.core.table.TableInfo;

import static com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts.EQUALS;
import static com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts.SINGLE_QUOTE;

/**
 * 默认逻辑删除处理器。
 * @author michael
 */
public class DefaultLogicDeleteProcessor extends AbstractLogicDeleteProcessor {

    @Override
    public String buildLogicNormalCondition(String logicColumn, TableInfo tableInfo, IDialect dialect) {
        return dialect.wrap(logicColumn) + EQUALS + prepareValue(getLogicNormalValue());
    }

    @Override
    public String buildLogicDeletedSet(String logicColumn, TableInfo tableInfo, IDialect dialect) {
        return dialect.wrap(logicColumn) + EQUALS + prepareValue(getLogicDeletedValue());
    }

    @Override
    public Object getLogicNormalValue() {
        return FlexGlobalConfig.getDefaultConfig().getNormalValueOfLogicDelete();
    }

    @Override
    public Object getLogicDeletedValue() {
        return FlexGlobalConfig.getDefaultConfig().getDeletedValueOfLogicDelete();
    }

    private static Object prepareValue(Object value) {
        if (value instanceof Number || value instanceof Boolean) {
            return value;
        }
        return SINGLE_QUOTE + value + SINGLE_QUOTE;
    }

}


