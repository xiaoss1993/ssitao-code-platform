package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 常用人员 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamLatestuserTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 常用人员
     */
    public static final TbIamLatestuserTableDef TB_IAM_LATESTUSER = new TbIamLatestuserTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 部门主键_id
     */
    public final QueryColumn TB_IAM_DEPT_ID = new QueryColumn(this, "tb_iam_dept_id");

    /**
     * 人员主键_id
     */
    public final QueryColumn TB_IAM_USER_ID = new QueryColumn(this, "tb_iam_user_id");

    /**
     * 电话
     */
    public final QueryColumn ACCOUNT_PHONE = new QueryColumn(this, "account_phone");

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
     * 所属公司名称
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
     * 选择次数
     */
    public final QueryColumn LATESTUSER_COUNT = new QueryColumn(this, "latestuser_count");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 常用人员部门_id
     */
    public final QueryColumn LATESTUSER_DEPT_ID = new QueryColumn(this, "latestuser_dept_id");

    /**
     * 常用人员_id
     */
    public final QueryColumn LATESTUSER_USER_ID = new QueryColumn(this, "latestuser_user_id");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_LATESTUSER_ID = new QueryColumn(this, "tb_iam_latestuser_id");

    /**
     * 关联用户_id
     */
    public final QueryColumn USER_ASSOCIATION_ID = new QueryColumn(this, "user_association_id");

    /**
     * 常用人员部门_code
     */
    public final QueryColumn LATESTUSER_DEPT_CODE = new QueryColumn(this, "latestuser_dept_code");

    /**
     * 常用人员部门_name
     */
    public final QueryColumn LATESTUSER_DEPT_NAME = new QueryColumn(this, "latestuser_dept_name");

    /**
     * 常用人员_code
     */
    public final QueryColumn LATESTUSER_USER_CODE = new QueryColumn(this, "latestuser_user_code");

    /**
     * 常用人员_name
     */
    public final QueryColumn LATESTUSER_USER_NAME = new QueryColumn(this, "latestuser_user_name");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 账号部门_id
     */
    public final QueryColumn TB_IAM_ACCOUNTDEPT_ID = new QueryColumn(this, "tb_iam_accountdept_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_ACCOUNTDEPT_ID, TB_IAM_LATESTUSER_ID, TB_IAM_USER_ID, TB_IAM_DEPT_ID, LATESTUSER_USER_ID, LATESTUSER_DEPT_ID, LATESTUSER_USER_NAME, LATESTUSER_DEPT_NAME, LATESTUSER_COUNT, LATESTUSER_USER_CODE, LATESTUSER_DEPT_CODE, USER_ASSOCIATION_ID, ACCOUNT_PHONE, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamLatestuserTableDef() {
        super("", "tb_iam_latestuser");
    }

    private TbIamLatestuserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamLatestuserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamLatestuserTableDef("", "tb_iam_latestuser", alias));
    }

}
