
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.FlexConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.dialect.OperateType;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.Objects;

/**
 * 查询表。
 *
 * @author michael
 * @author 王帅
 */
public class QueryTable implements CloneSupport<QueryTable> {


    protected String schema;
    protected String name;
    protected String alias;

    protected QueryTable() {
    }

    public QueryTable(String name) {
        String[] schemaAndTableName = StringUtil.getSchemaAndTableName(name);
        this.schema = schemaAndTableName[0];
        this.name = schemaAndTableName[1];
    }

    public QueryTable(String schema, String name) {
        this.schema = StringUtil.tryTrim(schema);
        this.name = StringUtil.tryTrim(name);
    }

    public QueryTable(String schema, String table, String alias) {
        this.schema = StringUtil.tryTrim(schema);
        this.name = StringUtil.tryTrim(table);
        this.alias = StringUtil.tryTrim(alias);
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameWithSchema() {
        return StringUtil.hasText(schema) ? schema + "." + name : name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public QueryTable as(String alias) {
        this.alias = alias;
        return this;
    }

    boolean isSameTable(QueryTable table) {
        if (table == null) {
            return false;
        }

        if (this == table) {
            return true;
        }

//        if (StringUtil.isNotBlank(alias)
//            && StringUtil.isNotBlank(table.alias)) {
//            return Objects.equals(alias, table.alias);
//        }
//
//        return Objects.equals(name, table.name);

        return Objects.equals(name, table.name)
            && Objects.equals(alias, table.alias);
    }

    Object[] getValueArray() {
        return FlexConsts.EMPTY_ARRAY;
    }

    public String toSql(IDialect dialect, OperateType operateType) {
        String sql;
        if (StringUtil.hasText(schema)) {
            String table = dialect.getRealTable(name, operateType);
            sql = dialect.wrap(dialect.getRealSchema(schema, table, operateType)) + "." + dialect.wrap(table) + WrapperUtil.buildAlias(alias, dialect);
        } else {
            sql = dialect.wrap(dialect.getRealTable(name, operateType)) + WrapperUtil.buildAlias(alias, dialect);
        }
        return sql;
    }

    @Override
    public String toString() {
        return "QueryTable{" + "schema='" + schema + '\'' + ", name='" + name + '\'' + ", alias='" + alias + '\'' + '}';
    }

    @Override
    public QueryTable clone() {
        try {
            return (QueryTable) super.clone();
        } catch (CloneNotSupportedException e) {
            throw FlexExceptions.wrap(e);
        }
    }

}
