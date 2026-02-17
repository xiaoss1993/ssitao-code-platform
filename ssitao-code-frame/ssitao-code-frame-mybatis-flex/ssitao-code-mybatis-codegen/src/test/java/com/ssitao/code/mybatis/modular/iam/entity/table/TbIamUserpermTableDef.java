package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 用户权限关联 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamUserpermTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 用户权限关联
     */
    public static final TbIamUserpermTableDef TB_IAM_USERPERM = new TbIamUserpermTableDef();

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
     * 权限_id
     */
    public final QueryColumn TB_IAM_PERM_ID = new QueryColumn(this, "tb_iam_perm_id");

    /**
     * 用户_id
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
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_USERPERM_ID = new QueryColumn(this, "tb_iam_userperm_id");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 授权方式_code
     */
    public final QueryColumn USERPERM_TYPE_CODE = new QueryColumn(this, "userperm_type_code");

    /**
     * 授权方式_name
     */
    public final QueryColumn USERPERM_TYPE_NAME = new QueryColumn(this, "userperm_type_name");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 授权类型_code
     */
    public final QueryColumn USERPERM_GRANTTYPE_CODE = new QueryColumn(this, "userperm_granttype_code");

    /**
     * 授权类型_name
     */
    public final QueryColumn USERPERM_GRANTTYPE_NAME = new QueryColumn(this, "userperm_granttype_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_USERPERM_ID, TB_IAM_USER_ID, TB_IAM_PERM_ID, USERPERM_TYPE_CODE, USERPERM_TYPE_NAME, USERPERM_GRANTTYPE_CODE, USERPERM_GRANTTYPE_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamUserpermTableDef() {
        super("", "tb_iam_userperm");
    }

    private TbIamUserpermTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamUserpermTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamUserpermTableDef("", "tb_iam_userperm", alias));
    }

}
