

package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.dialect.OperateType;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.Objects;

/**
 * 原生查询表。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class RawQueryTable extends QueryTable {

    protected String content;

    public RawQueryTable(String content) {
        this.content = content;
    }

    @Override
    public String toSql(IDialect dialect, OperateType operateType) {
        return this.content + WrapperUtil.buildAlias(alias, dialect);
    }

    @Override
    boolean isSameTable(QueryTable table) {
        if (table == null) {
            return false;
        }
        // 只比较别名，不比较内容
        if (StringUtil.hasText(alias)
            && StringUtil.hasText(table.alias)) {
            return Objects.equals(alias, table.alias);
        }
        return false;
    }

    @Override
    public String toString() {
        return "RawQueryTable{" +
            "content='" + content + '\'' +
            '}';
    }

    public String getContent() {
        return content;
    }

    @Override
    public RawQueryTable clone() {
        return (RawQueryTable) super.clone();
    }

}
