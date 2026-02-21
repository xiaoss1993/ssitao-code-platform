package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 权限设置详情 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SAutzDetailTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 权限设置详情
     */
    public static final SAutzDetailTableDef SAUTZ_DETAIL = new SAutzDetailTableDef();

    /**
     * uid
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 可操作类型
     */
    public final QueryColumn ACTIONS = new QueryColumn(this, "actions");

    /**
     * 是否合并
     */
    public final QueryColumn IS_MERGE = new QueryColumn(this, "is_merge");

    /**
     * 优先级
     */
    public final QueryColumn PRIORITY = new QueryColumn(this, "priority");

    /**
     * 设置ID
     */
    public final QueryColumn SETTING_ID = new QueryColumn(this, "setting_id");

    /**
     * 数据权限控制
     */
    public final QueryColumn DATA_ACCESSES = new QueryColumn(this, "data_accesses");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, PERMISSION_ID, SETTING_ID, ACTIONS, DATA_ACCESSES, STATUS, PRIORITY, IS_MERGE};

    public SAutzDetailTableDef() {
        super("", "s_autz_detail");
    }

    private SAutzDetailTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SAutzDetailTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SAutzDetailTableDef("", "s_autz_detail", alias));
    }

}
