
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.List;

/**
 * 原生条件。
 *
 * @author michael
 * @author 王帅
 */
public class RawQueryCondition extends QueryCondition {

    protected String content;

    public RawQueryCondition(String content) {
        this.content = content;
    }

    public RawQueryCondition(String content, Object... paras) {
        this.content = content;
        this.setValue(paras);
    }

    @Override
    boolean containsTable(String... tables) {
        for (String table : tables) {
            String[] tableNameWithAlias = StringUtil.getTableNameWithAlias(table);
            if (content.contains(tableNameWithAlias[0])
                || (tableNameWithAlias[1] != null && content.contains(tableNameWithAlias[1]))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toSql(List<QueryTable> queryTables, IDialect dialect) {
        StringBuilder sql = new StringBuilder();

        //检测是否生效
        if (checkEffective()) {
            QueryCondition prevEffectiveCondition = getPrevEffectiveCondition();
            if (prevEffectiveCondition != null && this.connector != null) {
                sql.append(this.connector);
            }
            sql.append(SqlConsts.BLANK).append(content).append(SqlConsts.BLANK);
        }

        if (this.next != null) {
            return sql + next.toSql(queryTables, dialect);
        }

        return sql.toString();
    }

    @Override
    public String toString() {
        return "RawQueryCondition{" +
            "content='" + content + '\'' +
            '}';
    }

    public String getContent() {
        return content;
    }

    @Override
    public RawQueryCondition clone() {
        return (RawQueryCondition) super.clone();
    }

}
