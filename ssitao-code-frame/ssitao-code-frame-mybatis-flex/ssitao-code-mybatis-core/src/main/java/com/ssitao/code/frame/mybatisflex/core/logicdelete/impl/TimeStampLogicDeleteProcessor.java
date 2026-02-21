

package com.ssitao.code.frame.mybatisflex.core.logicdelete.impl;

import com.ssitao.code.frame.mybatisflex.core.logicdelete.AbstractLogicDeleteProcessor;

/**
 * 时间戳类型的属性对应的逻辑删除处理器。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class TimeStampLogicDeleteProcessor extends AbstractLogicDeleteProcessor {

    /**
     * 逻辑删除字段值为 {@code 0} 表示数据未删除。
     */
    @Override
    public Object getLogicNormalValue() {
        return 0;
    }

    /**
     * 逻辑删除字段值为 {@code NOW()} 表示数据删除，并记录删除时时间戳。
     */
    @Override
    public Object getLogicDeletedValue() {
        return System.currentTimeMillis();
    }

}
