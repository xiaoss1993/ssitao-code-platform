package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * OAuth2 服务配置 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SOauth2ServerTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * OAuth2 服务配置
     */
    public static final SOauth2ServerTableDef SOAUTH2_SERVER = new SOauth2ServerTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 服务名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 认证地址
     */
    public final QueryColumn AUTH_URL = new QueryColumn(this, "auth_url");

    /**
     * 客户端id
     */
    public final QueryColumn CLIENT_ID = new QueryColumn(this, "client_id");

    /**
     * 备注
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 服务提供商
     */
    public final QueryColumn PROVIDER = new QueryColumn(this, "provider");

    /**
     * api根地址
     */
    public final QueryColumn API_BASE_URL = new QueryColumn(this, "api_base_url");

    /**
     * 其他配置
     */
    public final QueryColumn PROPERTIES = new QueryColumn(this, "properties");

    /**
     * 重定向地址
     */
    public final QueryColumn REDIRECT_URI = new QueryColumn(this, "redirect_uri");

    /**
     * 客户端密钥
     */
    public final QueryColumn CLIENT_SECRET = new QueryColumn(this, "client_secret");

    /**
     * token获取地址
     */
    public final QueryColumn ACCESS_TOKEN_URL = new QueryColumn(this, "access_token_url");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, DESCRIBE, API_BASE_URL, AUTH_URL, REDIRECT_URI, ACCESS_TOKEN_URL, CLIENT_ID, CLIENT_SECRET, PROVIDER, PROPERTIES, STATUS};

    public SOauth2ServerTableDef() {
        super("", "s_oauth2_server");
    }

    private SOauth2ServerTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SOauth2ServerTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SOauth2ServerTableDef("", "s_oauth2_server", alias));
    }

}
