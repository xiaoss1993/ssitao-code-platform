package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 模板组 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class GenTemplateGroupTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 模板组
     */
    public static final GenTemplateGroupTableDef GEN_TEMPLATE_GROUP = new GenTemplateGroupTableDef();

    /**
     * ID
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 缩略图
     */
    public final QueryColumn ICON = new QueryColumn(this, "icon");

    /**
     * 名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 逻辑删除
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 备注
     */
    public final QueryColumn REMARKS = new QueryColumn(this, "remarks");

    /**
     * 标识
     */
    public final QueryColumn GROUP_KEY = new QueryColumn(this, "group_key");

    /**
     * 是否需要使用使用数据表
     */
    public final QueryColumn USE_TABLE = new QueryColumn(this, "use_table");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 修改时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, GROUP_KEY, NAME, ICON, USE_TABLE, REMARKS, DELETED, CREATE_TIME, UPDATE_TIME};

    public GenTemplateGroupTableDef() {
        super("", "gen_template_group");
    }

    private GenTemplateGroupTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public GenTemplateGroupTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new GenTemplateGroupTableDef("", "gen_template_group", alias));
    }

}
