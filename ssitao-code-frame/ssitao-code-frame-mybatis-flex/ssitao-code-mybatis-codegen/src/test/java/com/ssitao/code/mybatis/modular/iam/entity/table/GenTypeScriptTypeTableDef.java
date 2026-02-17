package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 前端和后端数据类型管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class GenTypeScriptTypeTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 前端和后端数据类型管理
     */
    public static final GenTypeScriptTypeTableDef GEN_TYPE_SCRIPT_TYPE = new GenTypeScriptTypeTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * Java对应类型
     */
    public final QueryColumn CODE_KEY = new QueryColumn(this, "code_key");

    /**
     * 逻辑删除（0：正常，1：删除）
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 模板组标识
     */
    public final QueryColumn GROUP_KEY = new QueryColumn(this, "group_key");

    /**
     * 界面对应类型
     */
    public final QueryColumn CODE_VALUE = new QueryColumn(this, "code_value");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, GROUP_KEY, CODE_KEY, CODE_VALUE, DELETED, CREATE_TIME, UPDATE_TIME};

    public GenTypeScriptTypeTableDef() {
        super("", "gen_type_script_type");
    }

    private GenTypeScriptTypeTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public GenTypeScriptTypeTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new GenTypeScriptTypeTableDef("", "gen_type_script_type", alias));
    }

}
