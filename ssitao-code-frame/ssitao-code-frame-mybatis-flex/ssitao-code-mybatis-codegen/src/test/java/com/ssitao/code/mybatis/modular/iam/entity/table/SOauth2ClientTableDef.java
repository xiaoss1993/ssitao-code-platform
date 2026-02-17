package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * OAuth2客户端 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SOauth2ClientTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * OAuth2客户端
     */
    public static final SOauth2ClientTableDef SOAUTH2_CLIENT = new SOauth2ClientTableDef();

    /**
     * client_id
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 客户端名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 客户端类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * client_secret
     */
    public final QueryColumn SECRET = new QueryColumn(this, "secret");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 绑定的用户ID
     */
    public final QueryColumn OWNER_ID = new QueryColumn(this, "owner_id");

    /**
     * 备注
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 创建者ID
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * redirect_uri
     */
    public final QueryColumn REDIRECT_URI = new QueryColumn(this, "redirect_uri");

    /**
     * 默认认证过期时间
     */
    public final QueryColumn DEFAULT_EXPIRES_IN = new QueryColumn(this, "default_expires_in");

    /**
     * 默认认证范围
     */
    public final QueryColumn DEFAULT_GRANT_SCOPE = new QueryColumn(this, "default_grant_scope");

    /**
     * 支持的授权列表
     */
    public final QueryColumn SUPPORT_GRANT_TYPES = new QueryColumn(this, "support_grant_types");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, SECRET, NAME, DESCRIBE, TYPE, OWNER_ID, CREATOR_ID, REDIRECT_URI, CREATE_TIME, SUPPORT_GRANT_TYPES, DEFAULT_EXPIRES_IN, DEFAULT_GRANT_SCOPE, STATUS};

    public SOauth2ClientTableDef() {
        super("", "s_oauth2_client");
    }

    private SOauth2ClientTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SOauth2ClientTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SOauth2ClientTableDef("", "s_oauth2_client", alias));
    }

}
