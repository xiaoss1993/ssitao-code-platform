
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.util.CollectionUtil;
import com.ssitao.code.frame.mybatisflex.core.util.SqlUtil;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 数据库 聚合函数，例如 CONVERT(NVARCHAR(30), GETDATE(), 126) 等等
 */
public class StringFunctionQueryColumn extends QueryColumn {

    protected String fnName;
    protected List<String> params;

    public StringFunctionQueryColumn(String fnName, String... params) {
        SqlUtil.keepColumnSafely(fnName);
        this.fnName = fnName;
        this.params = Arrays.asList(params);
    }


    public String getFnName() {
        return fnName;
    }

    public void setFnName(String fnName) {
        this.fnName = fnName;
    }


    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    @Override
    public String toSelectSql(List<QueryTable> queryTables, IDialect dialect) {
        String sql = StringUtil.join(SqlConsts.DELIMITER, params);
        if (StringUtil.noText(sql)) {
            return SqlConsts.EMPTY;
        }
        if (StringUtil.noText(alias)) {
            return fnName + WrapperUtil.withBracket(sql);
        }
        return fnName + WrapperUtil.withAlias(sql, alias, dialect);
    }

    @Override
    protected String toConditionSql(List<QueryTable> queryTables, IDialect dialect) {
        String sql = StringUtil.join(SqlConsts.DELIMITER, params);
        if (StringUtil.noText(sql)) {
            return SqlConsts.EMPTY;
        }
        return fnName + WrapperUtil.withBracket(sql);
    }


    @Override
    public String toString() {
        return "StringFunctionQueryColumn{" +
            "fnName='" + fnName + '\'' +
            ", params=" + params +
            '}';
    }

    @Override
    public StringFunctionQueryColumn clone() {
        StringFunctionQueryColumn clone = (StringFunctionQueryColumn) super.clone();
        // deep clone ...
        clone.params = CollectionUtil.newArrayList(this.params);
        return clone;
    }

}
