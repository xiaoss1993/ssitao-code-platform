package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 权限 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SPermissionTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 权限
     */
    public static final SPermissionTableDef SPERMISSION = new SPermissionTableDef();

    /**
     * uid
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 角色名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 可选操作(按钮)
     */
    public final QueryColumn ACTIONS = new QueryColumn(this, "actions");

    /**
     * 关联其他权限
     */
    public final QueryColumn PARENTS = new QueryColumn(this, "parents");

    /**
     * 说明
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 支持的数据权限类型
     */
    public final QueryColumn SPT_DA_TYPES = new QueryColumn(this, "spt_da_types");

    /**
     * 可选字段
     */
    public final QueryColumn OPTIONAL_FIELDS = new QueryColumn(this, "optional_fields");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, DESCRIBE, STATUS, ACTIONS, SPT_DA_TYPES, OPTIONAL_FIELDS, PARENTS, TYPE};

    public SPermissionTableDef() {
        super("", "s_permission");
    }

    private SPermissionTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SPermissionTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SPermissionTableDef("", "s_permission", alias));
    }

}
