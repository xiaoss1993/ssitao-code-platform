package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 菜单分组关联 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SMenuGroupBindTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单分组关联
     */
    public static final SMenuGroupBindTableDef SMENU_GROUP_BIND = new SMenuGroupBindTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 树结构编码
     */
    public final QueryColumn PATH = new QueryColumn(this, "path");

    /**
     * 树层级
     */
    public final QueryColumn LEVEL = new QueryColumn(this, "level");

    /**
     * 菜单id
     */
    public final QueryColumn MENU_ID = new QueryColumn(this, "menu_id");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 分组id
     */
    public final QueryColumn GROUP_ID = new QueryColumn(this, "group_id");

    /**
     * 父级id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 排序序号
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, MENU_ID, GROUP_ID, PATH, PARENT_ID, LEVEL, SORT_INDEX, STATUS};

    public SMenuGroupBindTableDef() {
        super("", "s_menu_group_bind");
    }

    private SMenuGroupBindTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SMenuGroupBindTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SMenuGroupBindTableDef("", "s_menu_group_bind", alias));
    }

}
