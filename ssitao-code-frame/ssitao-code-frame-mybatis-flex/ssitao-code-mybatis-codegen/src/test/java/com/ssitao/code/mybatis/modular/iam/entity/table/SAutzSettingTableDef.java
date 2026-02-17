package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 权限设置 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SAutzSettingTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 权限设置
     */
    public static final SAutzSettingTableDef SAUTZ_SETTING = new SAutzSettingTableDef();

    /**
     * uid
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 权限类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 设置给谁
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 备注
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 设置给谁
     */
    public final QueryColumn SETTING_FOR = new QueryColumn(this, "setting_for");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, TYPE, SETTING_FOR, DESCRIBE, STATUS};

    public SAutzSettingTableDef() {
        super("", "s_autz_setting");
    }

    private SAutzSettingTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SAutzSettingTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SAutzSettingTableDef("", "s_autz_setting", alias));
    }

}
