
package com.ssitao.code.frame.mybatisflex.core.table;

import com.ssitao.code.frame.mybatisflex.core.dialect.OperateType;

public interface DynamicTableProcessor {

    /**
     * @deprecated 使用 {@link #process(String, OperateType)} 方法代替。
     */
    @Deprecated
    @SuppressWarnings("DeprecatedIsStillUsed")
    String process(String tableName);

    default String process(String tableName, OperateType operateType) {
        return process(tableName);
    }

}
