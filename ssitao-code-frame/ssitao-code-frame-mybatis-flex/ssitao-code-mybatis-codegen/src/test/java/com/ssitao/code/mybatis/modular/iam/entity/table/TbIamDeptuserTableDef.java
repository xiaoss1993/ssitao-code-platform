package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 部门人员关联 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamDeptuserTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 部门人员关联
     */
    public static final TbIamDeptuserTableDef TB_IAM_DEPTUSER = new TbIamDeptuserTableDef();

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
     * 用户主键
     */
    public final QueryColumn TB_IAM_USER_ID = new QueryColumn(this, "tb_iam_user_id");

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
     * 所属公司_code
     */
    public final QueryColumn SY_COMPANY_CODE = new QueryColumn(this, "sy_company_code");

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
     * 主管领导_id
     */
    public final QueryColumn DEPTUSER_MAJOR_ID = new QueryColumn(this, "deptuser_major_id");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_DEPTUSER_ID = new QueryColumn(this, "tb_iam_deptuser_id");

    /**
     * 是否主部门_code
     */
    public final QueryColumn DEPTUSER_MAIN_CODE = new QueryColumn(this, "deptuser_main_code");

    /**
     * 是否主部门_name
     */
    public final QueryColumn DEPTUSER_MAIN_NAME = new QueryColumn(this, "deptuser_main_name");

    /**
     * 是否主管_code
     */
    public final QueryColumn DEPTUSER_SFZG_CODE = new QueryColumn(this, "deptuser_sfzg_code");

    /**
     * 是否主管_name
     */
    public final QueryColumn DEPTUSER_SFZG_NAME = new QueryColumn(this, "deptuser_sfzg_name");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司_id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 主管领导_name
     */
    public final QueryColumn DEPTUSER_MAJOR_NAME = new QueryColumn(this, "deptuser_major_name");

    /**
     * 部门主键
     */
    public final QueryColumn TB_IAM_DEPARTMENT_ID = new QueryColumn(this, "tb_iam_department_id");

    /**
     * 所属集团公司_name
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 直接领导_id
     */
    public final QueryColumn DEPTUSER_DIRECTLEADER_ID = new QueryColumn(this, "deptuser_directleader_id");

    /**
     * 是否开通账号_code
     */
    public final QueryColumn DEPTUSER_OPENACCOUNT_CODE = new QueryColumn(this, "deptuser_openaccount_code");

    /**
     * 是否开通账号_name
     */
    public final QueryColumn DEPTUSER_OPENACCOUNT_NAME = new QueryColumn(this, "deptuser_openaccount_name");

    /**
     * 直接领导_name
     */
    public final QueryColumn DEPTUSER_DIRECTLEADER_NAME = new QueryColumn(this, "deptuser_directleader_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_DEPARTMENT_ID, TB_IAM_USER_ID, TB_IAM_DEPTUSER_ID, DEPTUSER_MAIN_CODE, DEPTUSER_MAIN_NAME, DEPTUSER_DIRECTLEADER_ID, DEPTUSER_DIRECTLEADER_NAME, DEPTUSER_MAJOR_ID, DEPTUSER_MAJOR_NAME, DEPTUSER_SFZG_CODE, DEPTUSER_SFZG_NAME, DEPTUSER_OPENACCOUNT_CODE, DEPTUSER_OPENACCOUNT_NAME, SY_COMPANY_CODE, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME};

    public TbIamDeptuserTableDef() {
        super("", "tb_iam_deptuser");
    }

    private TbIamDeptuserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamDeptuserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamDeptuserTableDef("", "tb_iam_deptuser", alias));
    }

}
