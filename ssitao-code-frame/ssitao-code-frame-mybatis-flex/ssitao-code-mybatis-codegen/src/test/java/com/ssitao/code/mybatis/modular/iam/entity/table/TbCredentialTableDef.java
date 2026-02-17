package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 凭据 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCredentialTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 凭据
     */
    public static final TbCredentialTableDef TB_CREDENTIAL = new TbCredentialTableDef();

    /**
     * 密码
     */
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 用户名
     */
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    /**
     * 描述说明
     */
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 修改时间
     */
    public final QueryColumn SY_MODIFYTIME = new QueryColumn(this, "sy_modifytime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 账号类型
     */
    public final QueryColumn CREDENTIAL_TYPE = new QueryColumn(this, "credential_type");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 主键id
     */
    public final QueryColumn TB_CREDENTIAL_ID = new QueryColumn(this, "tb_credential_id");

    /**
     * 认证邮箱
     */
    public final QueryColumn CREDENTIAL_EMAIL = new QueryColumn(this, "credential_email");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 认证地址
     */
    public final QueryColumn CREDENTIAL_ADDRESS = new QueryColumn(this, "credential_address");

    /**
     * 唯一标识
     */
    public final QueryColumn UNIQUE_IDENTIFICATION = new QueryColumn(this, "unique_identification");

    /**
     * 镜像拉取密钥
     */
    public final QueryColumn CREDENTIAL_IMAGE_PULL_SECRETS = new QueryColumn(this, "credential_image_pull_secrets");

    /**
     * 密钥初始化状态
     */
    public final QueryColumn CREDENTIAL_INITIALIZATION_KEY_STATUS = new QueryColumn(this, "credential_initialization_key_status");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CREDENTIAL_ID, UNIQUE_IDENTIFICATION, USERNAME, PASSWORD, DESCRIPTION, CREDENTIAL_TYPE, CREDENTIAL_IMAGE_PULL_SECRETS, CREDENTIAL_EMAIL, CREDENTIAL_ADDRESS, CREDENTIAL_INITIALIZATION_KEY_STATUS, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public TbCredentialTableDef() {
        super("", "tb_credential");
    }

    private TbCredentialTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCredentialTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCredentialTableDef("", "tb_credential", alias));
    }

}
