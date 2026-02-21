package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * DB和后端数据类型 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class GenFieldTypeTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * DB和后端数据类型
     */
    public static final GenFieldTypeTableDef GEN_FIELD_TYPE = new GenFieldTypeTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 数据库类型（1:MySQL，2:Oracle，3:PostGreSql，4:SqlServer）
     */
    public final QueryColumn DB_TYPE = new QueryColumn(this, "db_type");

    /**
     * 逻辑删除字段（1删除0正常）
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 模板组id
     */
    public final QueryColumn GROUP_KEY = new QueryColumn(this, "group_key");

    /**
     * DB属性类型
     */
    public final QueryColumn COLUMN_KEY = new QueryColumn(this, "column_key");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 对应属性类型
     */
    public final QueryColumn COLUMN_VALUE = new QueryColumn(this, "column_value");

    /**
     * 属性包路径+类名
     */
    public final QueryColumn PACKAGE_NAME = new QueryColumn(this, "package_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, GROUP_KEY, COLUMN_KEY, COLUMN_VALUE, DB_TYPE, PACKAGE_NAME, DELETED, CREATE_TIME, UPDATE_TIME};

    public GenFieldTypeTableDef() {
        super("", "gen_field_type");
    }

    private GenFieldTypeTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public GenFieldTypeTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new GenFieldTypeTableDef("", "gen_field_type", alias));
    }

}
