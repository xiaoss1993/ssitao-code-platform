package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 用户与角色关联 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SUserRoleTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 用户与角色关联
     */
    public static final SUserRoleTableDef SUSER_ROLE = new SUserRoleTableDef();

    /**
     * 角色ID
     */
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    /**
     * 用户ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ROLE_ID, USER_ID};

    public SUserRoleTableDef() {
        super("", "s_user_role");
    }

    private SUserRoleTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SUserRoleTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SUserRoleTableDef("", "s_user_role", alias));
    }

}
