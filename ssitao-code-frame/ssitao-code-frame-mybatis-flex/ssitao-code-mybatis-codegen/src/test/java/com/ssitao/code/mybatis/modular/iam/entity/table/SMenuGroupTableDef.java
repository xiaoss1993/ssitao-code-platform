package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 菜单分组 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SMenuGroupTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单分组
     */
    public static final SMenuGroupTableDef SMENU_GROUP = new SMenuGroupTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 分组名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 树路径
     */
    public final QueryColumn PATH = new QueryColumn(this, "path");

    /**
     * 树层级
     */
    public final QueryColumn LEVEL = new QueryColumn(this, "level");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 分组描述
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 父级id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 排序序号
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 是否默认
     */
    public final QueryColumn DEFAULT_GROUP = new QueryColumn(this, "default_group");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, PARENT_ID, NAME, DESCRIBE, DEFAULT_GROUP, PATH, LEVEL, SORT_INDEX, STATUS};

    public SMenuGroupTableDef() {
        super("", "s_menu_group");
    }

    private SMenuGroupTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SMenuGroupTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SMenuGroupTableDef("", "s_menu_group", alias));
    }

}
