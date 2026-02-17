package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * OAuth2授权码信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SOauth2AuthCodeTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * OAuth2授权码信息
     */
    public static final SOauth2AuthCodeTableDef SOAUTH2_AUTH_CODE = new SOauth2AuthCodeTableDef();

    /**
     * 授权码
     */
    public final QueryColumn CODE = new QueryColumn(this, "code");

    /**
     * 授权范围
     */
    public final QueryColumn SCOPE = new QueryColumn(this, "scope");

    /**
     * 授权对应的用户ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * client_id
     */
    public final QueryColumn CLIENT_ID = new QueryColumn(this, "client_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 重定向URI
     */
    public final QueryColumn REDIRECT_URI = new QueryColumn(this, "redirect_uri");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{CLIENT_ID, USER_ID, CODE, CREATE_TIME, SCOPE, REDIRECT_URI};

    public SOauth2AuthCodeTableDef() {
        super("", "s_oauth2_auth_code");
    }

    private SOauth2AuthCodeTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SOauth2AuthCodeTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SOauth2AuthCodeTableDef("", "s_oauth2_auth_code", alias));
    }

}
