package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 系统菜单 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SMenuTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 系统菜单
     */
    public static final SMenuTableDef SMENU = new SMenuTableDef();

    /**
     * uid
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * URL
     */
    public final QueryColumn URL = new QueryColumn(this, "url");

    /**
     * 图标
     */
    public final QueryColumn ICON = new QueryColumn(this, "icon");

    /**
     * 名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 树编码
     */
    public final QueryColumn PATH = new QueryColumn(this, "path");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 备注
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 父级ID
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 树编码
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 权限ID
     */
    public final QueryColumn PERMISSION_ID = new QueryColumn(this, "permission_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, PARENT_ID, PERMISSION_ID, PATH, SORT_INDEX, DESCRIBE, URL, ICON, STATUS};

    public SMenuTableDef() {
        super("", "s_menu");
    }

    private SMenuTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SMenuTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SMenuTableDef("", "s_menu", alias));
    }

}
