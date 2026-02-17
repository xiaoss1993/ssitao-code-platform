package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 用户设置 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SUserSettingTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 用户设置
     */
    public static final SUserSettingTableDef SUSER_SETTING = new SUserSettingTableDef();

    /**
     * 配置标识
     */
    public final QueryColumn KEY = new QueryColumn(this, "key");

    /**
     * uid
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 配置名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 用户ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 配置内容
     */
    public final QueryColumn SETTING = new QueryColumn(this, "setting");

    /**
     * 说明
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 自定义配置id
     */
    public final QueryColumn SETTING_ID = new QueryColumn(this, "setting_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 用户可操作权限
     */
    public final QueryColumn PERMISSION = new QueryColumn(this, "permission");

    /**
     * 创建时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, DESCRIBE, USER_ID, KEY, SETTING, SETTING_ID, CREATE_TIME, UPDATE_TIME, PERMISSION};

    public SUserSettingTableDef() {
        super("", "s_user_setting");
    }

    private SUserSettingTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SUserSettingTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SUserSettingTableDef("", "s_user_setting", alias));
    }

}
