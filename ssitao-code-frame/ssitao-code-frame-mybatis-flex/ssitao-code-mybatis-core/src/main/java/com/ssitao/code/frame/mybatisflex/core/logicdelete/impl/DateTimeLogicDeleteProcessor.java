

package com.ssitao.code.frame.mybatisflex.core.logicdelete.impl;

import com.ssitao.code.frame.mybatisflex.core.logicdelete.NullableColumnLogicDeleteProcessor;

/**
 * {@link java.time.LocalDateTime} 类型的属性对应的逻辑删除处理器。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class DateTimeLogicDeleteProcessor extends NullableColumnLogicDeleteProcessor {

    /**
     * 逻辑删除字段值为 {@code NOW()} 表示数据删除，并记录删除时间。
     */
    @Override
    public Object getLogicDeletedValue() {
        return "NOW()";
    }

}
