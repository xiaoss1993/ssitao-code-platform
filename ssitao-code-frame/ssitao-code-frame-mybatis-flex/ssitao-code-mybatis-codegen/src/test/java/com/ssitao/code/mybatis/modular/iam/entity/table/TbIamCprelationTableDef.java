package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 三方组织人员关系 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamCprelationTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 三方组织人员关系
     */
    public static final TbIamCprelationTableDef TB_IAM_CPRELATION = new TbIamCprelationTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 所属公司id
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
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 类型
     */
    public final QueryColumn CPRELATION_TYPE = new QueryColumn(this, "cprelation_type");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 部门_id
     */
    public final QueryColumn CPRELATION_DEPT_ID = new QueryColumn(this, "cprelation_dept_id");

    /**
     * 人员_id
     */
    public final QueryColumn CPRELATION_USER_ID = new QueryColumn(this, "cprelation_user_id");

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
    public final QueryColumn TB_IAM_CPRELATION_ID = new QueryColumn(this, "tb_iam_cprelation_id");

    /**
     * 部门名称
     */
    public final QueryColumn CPRELATION_DEPT_NAME = new QueryColumn(this, "cprelation_dept_name");

    /**
     * 部门全路径
     */
    public final QueryColumn CPRELATION_DEPT_PATH = new QueryColumn(this, "cprelation_dept_path");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_CPRELATION_ID, CPRELATION_DEPT_ID, CPRELATION_USER_ID, CPRELATION_DEPT_NAME, CPRELATION_TYPE, CPRELATION_DEPT_PATH, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID};

    public TbIamCprelationTableDef() {
        super("", "tb_iam_cprelation");
    }

    private TbIamCprelationTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamCprelationTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamCprelationTableDef("", "tb_iam_cprelation", alias));
    }

}
