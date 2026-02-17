package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 用户 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SUserTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 用户
     */
    public static final SUserTableDef SUSER = new SUserTableDef();

    /**
     * uid
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 姓名
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 密码盐
     */
    public final QueryColumn SALT = new QueryColumn(this, "salt");

    /**
     * 用户状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 密码
     */
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    /**
     * 用户名
     */
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    /**
     * 创建者ID
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 上一次登录的ip地址
     */
    public final QueryColumn LAST_LOGIN_IP = new QueryColumn(this, "last_login_ip");

    /**
     * 上一次登录时间
     */
    public final QueryColumn LAST_LOGIN_TIME = new QueryColumn(this, "last_login_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, USERNAME, PASSWORD, SALT, STATUS, LAST_LOGIN_IP, LAST_LOGIN_TIME, CREATOR_ID, CREATE_TIME};

    public SUserTableDef() {
        super("", "s_user");
    }

    private SUserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SUserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SUserTableDef("", "s_user", alias));
    }

}
