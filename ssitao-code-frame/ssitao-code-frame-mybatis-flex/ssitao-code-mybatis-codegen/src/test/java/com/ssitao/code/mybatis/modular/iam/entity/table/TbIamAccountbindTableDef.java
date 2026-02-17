package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 三方人员绑定 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamAccountbindTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 三方人员绑定
     */
    public static final TbIamAccountbindTableDef TB_IAM_ACCOUNTBIND = new TbIamAccountbindTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户_name
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 账户_id
     */
    public final QueryColumn TB_IAM_ACCOUNT_ID = new QueryColumn(this, "tb_iam_account_id");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 头像
     */
    public final QueryColumn ACCOUNTBIND_AVATAR = new QueryColumn(this, "accountbind_avatar");

    /**
     * 开放id
     */
    public final QueryColumn ACCOUNTBIND_OPENID = new QueryColumn(this, "accountbind_openid");

    /**
     * 地区
     */
    public final QueryColumn ACCOUNTBIND_REGION = new QueryColumn(this, "accountbind_region");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_ACCOUNTBIND_ID = new QueryColumn(this, "tb_iam_accountbind_id");

    /**
     * 昵称
     */
    public final QueryColumn ACCOUNTBIND_NICK_NAME = new QueryColumn(this, "accountbind_nick_name");

    /**
     * 绑定类型_code
     */
    public final QueryColumn ACCOUNTBIND_TYPE_CODE = new QueryColumn(this, "accountbind_type_code");

    /**
     * 绑定类型_name
     */
    public final QueryColumn ACCOUNTBIND_TYPE_NAME = new QueryColumn(this, "accountbind_type_name");

    /**
     * 账户_name
     */
    public final QueryColumn ACCOUNTBIND_ACCOUNT_NAME = new QueryColumn(this, "accountbind_account_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_ACCOUNTBIND_ID, TB_IAM_ACCOUNT_ID, ACCOUNTBIND_ACCOUNT_NAME, ACCOUNTBIND_OPENID, ACCOUNTBIND_AVATAR, ACCOUNTBIND_NICK_NAME, ACCOUNTBIND_TYPE_CODE, ACCOUNTBIND_TYPE_NAME, ACCOUNTBIND_REGION, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbIamAccountbindTableDef() {
        super("", "tb_iam_accountbind");
    }

    private TbIamAccountbindTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamAccountbindTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamAccountbindTableDef("", "tb_iam_accountbind", alias));
    }

}
