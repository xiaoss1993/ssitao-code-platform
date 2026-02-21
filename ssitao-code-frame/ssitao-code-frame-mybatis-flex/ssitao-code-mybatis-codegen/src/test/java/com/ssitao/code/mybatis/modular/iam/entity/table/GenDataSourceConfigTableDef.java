package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据源 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class GenDataSourceConfigTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源
     */
    public static final GenDataSourceConfigTableDef GEN_DATA_SOURCE_CONFIG = new GenDataSourceConfigTableDef();

    /**
     * ID
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 数据库连接
     */
    public final QueryColumn URL = new QueryColumn(this, "url");

    /**
     * 数据源key
     */
    public final QueryColumn DS_KEY = new QueryColumn(this, "ds_key");

    /**
     * 标题
     */
    public final QueryColumn TITLE = new QueryColumn(this, "title");

    /**
     * 逻辑删除标识
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 数据库密码
     */
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    /**
     * 数据库用户名
     */
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 删除时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, TITLE, DS_KEY, USERNAME, PASSWORD, URL, DELETED, CREATE_TIME, UPDATE_TIME};

    public GenDataSourceConfigTableDef() {
        super("", "gen_data_source_config");
    }

    private GenDataSourceConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public GenDataSourceConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new GenDataSourceConfigTableDef("", "gen_data_source_config", alias));
    }

}
