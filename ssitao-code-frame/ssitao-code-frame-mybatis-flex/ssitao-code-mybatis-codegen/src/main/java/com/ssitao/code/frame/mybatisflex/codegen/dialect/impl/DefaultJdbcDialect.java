
package com.ssitao.code.frame.mybatisflex.codegen.dialect.impl;

import com.ssitao.code.frame.mybatisflex.codegen.dialect.AbstractJdbcDialect;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

/**
 * @author ssitao
 */
public class DefaultJdbcDialect extends AbstractJdbcDialect {
    @Override
    protected String forBuildColumnsSql(String schema, String tableName) {
        return "SELECT * FROM " + (StringUtil.hasText(schema) ? schema + "." : "") + tableName + " WHERE 1 = 2";
    }
}
