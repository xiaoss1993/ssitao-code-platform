package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * OAuth2授权认证信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SOauth2AccessTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * OAuth2授权认证信息
     */
    public static final SOauth2AccessTableDef SOAUTH2_ACCESS = new SOauth2AccessTableDef();

    /**
     * 授权范围
     */
    public final QueryColumn SCOPE = new QueryColumn(this, "scope");

    /**
     * 授权对应的用户ID
     */
    public final QueryColumn OWNER_ID = new QueryColumn(this, "owner_id");

    /**
     * client_id
     */
    public final QueryColumn CLIENT_ID = new QueryColumn(this, "client_id");

    /**
     * 有效期
     */
    public final QueryColumn EXPIRES_IN = new QueryColumn(this, "expires_in");

    /**
     * 授权类型
     */
    public final QueryColumn GRANT_TYPE = new QueryColumn(this, "grant_type");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 授权码
     */
    public final QueryColumn ACCESS_TOKEN = new QueryColumn(this, "access_token");

    /**
     * 用于更新授权的token
     */
    public final QueryColumn REFRESH_TOKEN = new QueryColumn(this, "refresh_token");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{CLIENT_ID, OWNER_ID, ACCESS_TOKEN, EXPIRES_IN, REFRESH_TOKEN, CREATE_TIME, UPDATE_TIME, SCOPE, GRANT_TYPE};

    public SOauth2AccessTableDef() {
        super("", "s_oauth2_access");
    }

    private SOauth2AccessTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SOauth2AccessTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SOauth2AccessTableDef("", "s_oauth2_access", alias));
    }

}
