package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 仪盘配置 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SDashboardConfTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 仪盘配置
     */
    public static final SDashboardConfTableDef SDASHBOARD_CONF = new SDashboardConfTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 配置名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 配置类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 脚本
     */
    public final QueryColumn SCRIPT = new QueryColumn(this, "script");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 模板
     */
    public final QueryColumn TEMPLATE = new QueryColumn(this, "template");

    /**
     * 创建人
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 是否默认
     */
    public final QueryColumn IS_DEFAULT = new QueryColumn(this, "is_default");

    /**
     * 排序
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 权限设置
     */
    public final QueryColumn PERMISSION = new QueryColumn(this, "permission");

    /**
     * 脚本语言
     */
    public final QueryColumn SCRIPT_LANG = new QueryColumn(this, "script_lang");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, TYPE, TEMPLATE, SCRIPT, SCRIPT_LANG, PERMISSION, CREATOR_ID, CREATE_TIME, SORT_INDEX, STATUS, IS_DEFAULT};

    public SDashboardConfTableDef() {
        super("", "s_dashboard_conf");
    }

    private SDashboardConfTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDashboardConfTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDashboardConfTableDef("", "s_dashboard_conf", alias));
    }

}
