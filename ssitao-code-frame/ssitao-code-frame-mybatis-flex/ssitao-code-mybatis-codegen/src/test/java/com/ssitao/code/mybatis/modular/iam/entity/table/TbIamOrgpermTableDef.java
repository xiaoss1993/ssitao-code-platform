package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 机构权限关联 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamOrgpermTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 机构权限关联
     */
    public static final TbIamOrgpermTableDef TB_IAM_ORGPERM = new TbIamOrgpermTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 机构主键
     */
    public final QueryColumn TB_IAM_ORG_ID = new QueryColumn(this, "tb_iam_org_id");

    /**
     * 权限主键
     */
    public final QueryColumn TB_IAM_PERM_ID = new QueryColumn(this, "tb_iam_perm_id");

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
     * 主键id
     */
    public final QueryColumn TB_IAM_ORGPERM_ID = new QueryColumn(this, "tb_iam_orgperm_id");

    /**
     * 授权方式_code
     */
    public final QueryColumn ORGPERM_TYPE_CODE = new QueryColumn(this, "orgperm_type_code");

    /**
     * 授权方式_name
     */
    public final QueryColumn ORGPERM_TYPE_NAME = new QueryColumn(this, "orgperm_type_name");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 是否排他_code
     */
    public final QueryColumn ORGPERM_EXCLUDE_CODE = new QueryColumn(this, "orgperm_exclude_code");

    /**
     * 是否排他_name
     */
    public final QueryColumn ORGPERM_EXCLUDE_NAME = new QueryColumn(this, "orgperm_exclude_name");

    /**
     * 授权类型_code
     */
    public final QueryColumn ORGPERM_GRANTTYPE_CODE = new QueryColumn(this, "orgperm_granttype_code");

    /**
     * 授权类型_name
     */
    public final QueryColumn ORGPERM_GRANTTYPE_NAME = new QueryColumn(this, "orgperm_granttype_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_PERM_ID, TB_IAM_ORG_ID, ORGPERM_EXCLUDE_CODE, ORGPERM_EXCLUDE_NAME, ORGPERM_TYPE_CODE, ORGPERM_TYPE_NAME, ORGPERM_GRANTTYPE_CODE, ORGPERM_GRANTTYPE_NAME, SY_TENANT_ID, SY_TENANT_NAME, TB_IAM_ORGPERM_ID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbIamOrgpermTableDef() {
        super("", "tb_iam_orgperm");
    }

    private TbIamOrgpermTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamOrgpermTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamOrgpermTableDef("", "tb_iam_orgperm", alias));
    }

}
