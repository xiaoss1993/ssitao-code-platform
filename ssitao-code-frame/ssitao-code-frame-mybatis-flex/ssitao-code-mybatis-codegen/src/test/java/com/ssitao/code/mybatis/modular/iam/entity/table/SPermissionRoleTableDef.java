package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 权限与角色关联 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SPermissionRoleTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 权限与角色关联
     */
    public static final SPermissionRoleTableDef SPERMISSION_ROLE = new SPermissionRoleTableDef();

    /**
     * 角色ID
     */
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    /**
     * 可选操作
     */
    public final QueryColumn ACTIONS = new QueryColumn(this, "actions");

    /**
     * 数据级控制配置
     */
    public final QueryColumn DATA_ACCESS = new QueryColumn(this, "data_access");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ROLE_ID, PERMISSION_ID, ACTIONS, DATA_ACCESS};

    public SPermissionRoleTableDef() {
        super("", "s_permission_role");
    }

    private SPermissionRoleTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SPermissionRoleTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SPermissionRoleTableDef("", "s_permission_role", alias));
    }

}
