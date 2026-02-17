package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 权限设置菜单 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SAutzMenuTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 权限设置菜单
     */
    public static final SAutzMenuTableDef SAUTZ_MENU = new SAutzMenuTableDef();

    /**
     * uid
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 树编码
     */
    public final QueryColumn PATH = new QueryColumn(this, "path");

    /**
     * 树深度
     */
    public final QueryColumn LEVEL = new QueryColumn(this, "level");

    /**
     * 其他配置
     */
    public final QueryColumn CONFIG = new QueryColumn(this, "config");

    /**
     * 菜单ID
     */
    public final QueryColumn MENU_ID = new QueryColumn(this, "menu_id");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 父级ID
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 设置ID
     */
    public final QueryColumn SETTING_ID = new QueryColumn(this, "setting_id");

    /**
     * 树编码
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, PARENT_ID, MENU_ID, SETTING_ID, PATH, SORT_INDEX, STATUS, LEVEL, CONFIG};

    public SAutzMenuTableDef() {
        super("", "s_autz_menu");
    }

    private SAutzMenuTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SAutzMenuTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SAutzMenuTableDef("", "s_autz_menu", alias));
    }

}
