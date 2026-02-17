package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 账号部门关联 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamAccountdeptTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 账号部门关联
     */
    public static final TbIamAccountdeptTableDef TB_IAM_ACCOUNTDEPT = new TbIamAccountdeptTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 所属公司_id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

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
     * 所属公司_name
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司_id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 部门_id
     */
    public final QueryColumn ACCOUNTDEPT_DEPT_ID = new QueryColumn(this, "accountdept_dept_id");

    /**
     * 所属集团公司_name
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_ACCOUNTDEPT_ID = new QueryColumn(this, "tb_iam_accountdept_id");

    /**
     * 部门_name
     */
    public final QueryColumn ACCOUNTDEPT_DEPT_NAME = new QueryColumn(this, "accountdept_dept_name");

    /**
     * 账号_id
     */
    public final QueryColumn ACCOUNTDEPT_ACCOUNT_ID = new QueryColumn(this, "accountdept_account_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_ACCOUNTDEPT_ID, ACCOUNTDEPT_ACCOUNT_ID, ACCOUNTDEPT_DEPT_ID, ACCOUNTDEPT_DEPT_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamAccountdeptTableDef() {
        super("", "tb_iam_accountdept");
    }

    private TbIamAccountdeptTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamAccountdeptTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamAccountdeptTableDef("", "tb_iam_accountdept", alias));
    }

}
