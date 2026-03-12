
package com.ssitao.code.frame.mybatisflex.core.table;

import com.ssitao.code.frame.mybatisflex.core.dialect.OperateType;

public interface DynamicSchemaProcessor {

    String process(String schema, String table, OperateType operateType);

}
