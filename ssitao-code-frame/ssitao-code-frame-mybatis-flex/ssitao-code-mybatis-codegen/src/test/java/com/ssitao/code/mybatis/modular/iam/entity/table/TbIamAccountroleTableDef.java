package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 账号部门角色 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamAccountroleTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 账号部门角色
     */
    public static final TbIamAccountroleTableDef TB_IAM_ACCOUNTROLE = new TbIamAccountroleTableDef();

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
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属部门_id
     */
    public final QueryColumn ACCOUNTROLE_DEPT_ID = new QueryColumn(this, "accountrole_dept_id");

    /**
     * 角色_id
     */
    public final QueryColumn ACCOUNTROLE_ROLE_ID = new QueryColumn(this, "accountrole_role_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_ACCOUNTROLE_ID = new QueryColumn(this, "tb_iam_accountrole_id");

    /**
     * 所属部门_name
     */
    public final QueryColumn ACCOUNTROLE_DEPT_NAME = new QueryColumn(this, "accountrole_dept_name");

    /**
     * 是否主部门_code
     */
    public final QueryColumn ACCOUNTROLE_MAIN_CODE = new QueryColumn(this, "accountrole_main_code");

    /**
     * 角色_name
     */
    public final QueryColumn ACCOUNTROLE_ROLE_NAME = new QueryColumn(this, "accountrole_role_name");

    /**
     * 账号_id
     */
    public final QueryColumn ACCOUNTROLE_ACCOUNT_ID = new QueryColumn(this, "accountrole_account_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_ACCOUNTROLE_ID, ACCOUNTROLE_ROLE_ID, ACCOUNTROLE_ACCOUNT_ID, ACCOUNTROLE_ROLE_NAME, ACCOUNTROLE_DEPT_ID, ACCOUNTROLE_DEPT_NAME, ACCOUNTROLE_MAIN_CODE, SY_CREATETIME, SY_STATUS, SY_ORDERINDEX, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEUSERNAME};

    public TbIamAccountroleTableDef() {
        super("", "tb_iam_accountrole");
    }

    private TbIamAccountroleTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamAccountroleTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamAccountroleTableDef("", "tb_iam_accountrole", alias));
    }

}
