

package com.ssitao.code.frame.mybatisflex.core.logicdelete.impl;

import com.ssitao.code.frame.mybatisflex.core.logicdelete.AbstractLogicDeleteProcessor;

/**
 * {@link Boolean} 类型的属性对应的逻辑删除处理器。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class BooleanLogicDeleteProcessor extends AbstractLogicDeleteProcessor {

    /**
     * 逻辑删除字段值为 {@code false} 表示数据未删除。
     */
    @Override
    public Object getLogicNormalValue() {
        return false;
    }

    /**
     * 逻辑删除字段值为 {@code true} 表示数据删除。
     */
    @Override
    public Object getLogicDeletedValue() {
        return true;
    }

}
