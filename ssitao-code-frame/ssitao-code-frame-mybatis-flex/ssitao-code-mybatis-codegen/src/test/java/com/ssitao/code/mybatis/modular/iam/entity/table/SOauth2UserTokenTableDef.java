package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * OAuth2用户授权信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SOauth2UserTokenTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * OAuth2用户授权信息
     */
    public static final SOauth2UserTokenTableDef SOAUTH2_USER_TOKEN = new SOauth2UserTokenTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 授权范围
     */
    public final QueryColumn SCOPE = new QueryColumn(this, "scope");

    /**
     * 客户端id
     */
    public final QueryColumn CLIENT_ID = new QueryColumn(this, "client_id");

    /**
     * 服务端id
     */
    public final QueryColumn SERVER_ID = new QueryColumn(this, "server_id");

    /**
     * 有效期
     */
    public final QueryColumn EXPIRES_IN = new QueryColumn(this, "expires_in");

    /**
     * 授权方式
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
     * 客户端用户id
     */
    public final QueryColumn CLIENT_USER_ID = new QueryColumn(this, "client_user_id");

    /**
     * 更新码
     */
    public final QueryColumn REFRESH_TOKEN = new QueryColumn(this, "refresh_token");

    /**
     * 服务端用户id
     */
    public final QueryColumn SERVER_USER_ID = new QueryColumn(this, "server_user_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, CLIENT_USER_ID, SERVER_USER_ID, SERVER_ID, CLIENT_ID, ACCESS_TOKEN, REFRESH_TOKEN, EXPIRES_IN, SCOPE, CREATE_TIME, UPDATE_TIME, GRANT_TYPE};

    public SOauth2UserTokenTableDef() {
        super("", "s_oauth2_user_token");
    }

    private SOauth2UserTokenTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SOauth2UserTokenTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SOauth2UserTokenTableDef("", "s_oauth2_user_token", alias));
    }

}
