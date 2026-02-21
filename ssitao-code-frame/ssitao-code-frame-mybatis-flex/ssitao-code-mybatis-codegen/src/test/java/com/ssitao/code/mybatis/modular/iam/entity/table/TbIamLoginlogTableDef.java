package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 新登录日志 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamLoginlogTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 新登录日志
     */
    public static final TbIamLoginlogTableDef TB_IAM_LOGINLOG = new TbIamLoginlogTableDef();

    /**
     * 所属机构_id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 所属机构_name
     */
    public final QueryColumn SY_ORG_NAME = new QueryColumn(this, "sy_org_name");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 账号_id
     */
    public final QueryColumn SY_ACCOUNT_ID = new QueryColumn(this, "sy_account_id");

    /**
     * 所属公司_id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 备注信息
     */
    public final QueryColumn LOGINLOG_BZXX = new QueryColumn(this, "loginlog_bzxx");

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
     * 设备标识
     */
    public final QueryColumn LOGINLOG_DEVICE = new QueryColumn(this, "loginlog_device");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_LOGINLOG_ID = new QueryColumn(this, "tb_iam_loginlog_id");

    /**
     * 登录方式_code
     */
    public final QueryColumn LOGINLOG_TYPE_CODE = new QueryColumn(this, "loginlog_type_code");

    /**
     * 登录方式_name
     */
    public final QueryColumn LOGINLOG_TYPE_NAME = new QueryColumn(this, "loginlog_type_name");

    /**
     * 所属集团公司_id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 操作类型_code
     */
    public final QueryColumn LOGINLOG_OPTYPE_CODE = new QueryColumn(this, "loginlog_optype_code");

    /**
     * 操作类型_name
     */
    public final QueryColumn LOGINLOG_OPTYPE_NAME = new QueryColumn(this, "loginlog_optype_name");

    /**
     * 所属集团公司_name
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 账号_code
     */
    public final QueryColumn LOGINLOG_ACCOUNT_CODE = new QueryColumn(this, "loginlog_account_code");

    /**
     * 账号_name
     */
    public final QueryColumn LOGINLOG_ACCOUNT_NAME = new QueryColumn(this, "loginlog_account_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_LOGINLOG_ID, LOGINLOG_ACCOUNT_NAME, LOGINLOG_DEVICE, LOGINLOG_ACCOUNT_CODE, LOGINLOG_TYPE_CODE, LOGINLOG_TYPE_NAME, LOGINLOG_OPTYPE_CODE, LOGINLOG_OPTYPE_NAME, LOGINLOG_BZXX, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_STATUS, SY_ORDERINDEX, SY_ACCOUNT_ID, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_ORG_NAME, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamLoginlogTableDef() {
        super("", "tb_iam_loginlog");
    }

    private TbIamLoginlogTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamLoginlogTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamLoginlogTableDef("", "tb_iam_loginlog", alias));
    }

}
