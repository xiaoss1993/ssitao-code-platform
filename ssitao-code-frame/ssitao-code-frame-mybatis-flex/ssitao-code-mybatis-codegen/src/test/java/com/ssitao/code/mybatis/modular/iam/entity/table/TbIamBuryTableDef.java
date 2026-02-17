package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 操作埋点记录 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamBuryTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 操作埋点记录
     */
    public static final TbIamBuryTableDef TB_IAM_BURY = new TbIamBuryTableDef();

    /**
     * 所属机构_id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

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
     * 主键id
     */
    public final QueryColumn TB_IAM_BURY_ID = new QueryColumn(this, "tb_iam_bury_id");

    /**
     * 操作类型_code
     */
    public final QueryColumn BURY_TYPE_CODE = new QueryColumn(this, "bury_type_code");

    /**
     * 操作类型_name
     */
    public final QueryColumn BURY_TYPE_NAME = new QueryColumn(this, "bury_type_name");

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
     * 所属公司_nane
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 操作对象_code
     */
    public final QueryColumn BURY_OBJECT_CODE = new QueryColumn(this, "bury_object_code");

    /**
     * 操作对象_name
     */
    public final QueryColumn BURY_OBJECT_NAME = new QueryColumn(this, "bury_object_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 操作账号_id
     */
    public final QueryColumn TB_IAM_ACCOUNT_ID = new QueryColumn(this, "tb_iam_account_id");

    /**
     * 操作账号_name
     */
    public final QueryColumn BURY_ACCOUNT_NAME = new QueryColumn(this, "bury_account_name");

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
     * 所属集团公司_name
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_ACCOUNT_ID, TB_IAM_BURY_ID, BURY_OBJECT_CODE, BURY_OBJECT_NAME, BURY_TYPE_CODE, BURY_TYPE_NAME, BURY_ACCOUNT_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_ORG_ID, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamBuryTableDef() {
        super("", "tb_iam_bury");
    }

    private TbIamBuryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamBuryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamBuryTableDef("", "tb_iam_bury", alias));
    }

}
