package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 模板文件目录项 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class GenTemplateEntryTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 模板文件目录项
     */
    public static final GenTemplateEntryTableDef GEN_TEMPLATE_ENTRY = new GenTemplateEntryTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 文件类型 1：文件夹 2：模板文件 3: 二进制文件
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 逻辑删除标识
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 备注
     */
    public final QueryColumn REMARKS = new QueryColumn(this, "remarks");

    /**
     * 文件夹路径/模板文件名称（支持占位符）
     */
    public final QueryColumn FILENAME = new QueryColumn(this, "filename");

    /**
     * 模板组标识
     */
    public final QueryColumn GROUP_KEY = new QueryColumn(this, "group_key");

    /**
     * 父级Id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 模板引擎类型 1：velocity
     */
    public final QueryColumn ENGINE_TYPE = new QueryColumn(this, "engine_type");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 模板内容
     */
    public final QueryColumn FILE_CONTENT = new QueryColumn(this, "file_content");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, GROUP_KEY, FILENAME, TYPE, PARENT_ID, FILE_CONTENT, ENGINE_TYPE, REMARKS, DELETED, CREATE_TIME, UPDATE_TIME};

    public GenTemplateEntryTableDef() {
        super("", "gen_template_entry");
    }

    private GenTemplateEntryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public GenTemplateEntryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new GenTemplateEntryTableDef("", "gen_template_entry", alias));
    }

}
