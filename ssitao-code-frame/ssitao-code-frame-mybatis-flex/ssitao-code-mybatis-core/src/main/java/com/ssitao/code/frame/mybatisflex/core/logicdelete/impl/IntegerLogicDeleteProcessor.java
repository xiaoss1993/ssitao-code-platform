

package com.ssitao.code.frame.mybatisflex.core.logicdelete.impl;

import com.ssitao.code.frame.mybatisflex.core.logicdelete.AbstractLogicDeleteProcessor;

/**
 * {@link Integer} 类型的属性对应的逻辑删除处理器。
 *
 * @author 王帅
 * @since 2023-06-20
 */
public class IntegerLogicDeleteProcessor extends AbstractLogicDeleteProcessor {

    /**
     * 逻辑删除字段值为 {@code 0} 表示数据未删除。
     */
    @Override
    public Object getLogicNormalValue() {
        return 0;
    }

    /**
     * 逻辑删除字段值为 {@code 1} 表示数据删除。
     */
    @Override
    public Object getLogicDeletedValue() {
        return 1;
    }

}
