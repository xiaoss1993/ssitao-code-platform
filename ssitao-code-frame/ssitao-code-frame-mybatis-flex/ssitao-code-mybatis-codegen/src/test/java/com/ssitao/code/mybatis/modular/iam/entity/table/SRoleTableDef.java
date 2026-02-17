package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 角色 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SRoleTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 角色
     */
    public static final SRoleTableDef SROLE = new SRoleTableDef();

    /**
     * uid
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 角色名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 说明
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, DESCRIBE, STATUS};

    public SRoleTableDef() {
        super("", "s_role");
    }

    private SRoleTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SRoleTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SRoleTableDef("", "s_role", alias));
    }

}
