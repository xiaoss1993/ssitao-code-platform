package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 部门权限关联 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamDeptpermTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 部门权限关联
     */
    public static final TbIamDeptpermTableDef TB_IAM_DEPTPERM = new TbIamDeptpermTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 权限主键_id
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
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_DEPTPERM_ID = new QueryColumn(this, "tb_iam_deptperm_id");

    /**
     * 授权方式_code
     */
    public final QueryColumn DEPTPERM_TYPE_CODE = new QueryColumn(this, "deptperm_type_code");

    /**
     * 授权方式_name
     */
    public final QueryColumn DEPTPERM_TYPE_NAME = new QueryColumn(this, "deptperm_type_name");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 部门主键_id
     */
    public final QueryColumn TB_IAM_DEPARTMENT_ID = new QueryColumn(this, "tb_iam_department_id");

    /**
     * 是否不选中
     */
    public final QueryColumn DEPTPERM_NOT_CHECKED = new QueryColumn(this, "deptperm_not_checked");

    /**
     * 是否排他_code
     */
    public final QueryColumn DEPTPERM_EXCLUDE_CODE = new QueryColumn(this, "deptperm_exclude_code");

    /**
     * 是否排他_name
     */
    public final QueryColumn DEPTPERM_EXCLUDE_NAME = new QueryColumn(this, "deptperm_exclude_name");

    /**
     * 授权类型_code
     */
    public final QueryColumn DEPTPERM_GRANTTYPE_CODE = new QueryColumn(this, "deptperm_granttype_code");

    /**
     * 授权类型_name
     */
    public final QueryColumn DEPTPERM_GRANTTYPE_NAME = new QueryColumn(this, "deptperm_granttype_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_DEPARTMENT_ID, TB_IAM_PERM_ID, TB_IAM_DEPTPERM_ID, DEPTPERM_EXCLUDE_CODE, DEPTPERM_EXCLUDE_NAME, DEPTPERM_TYPE_CODE, DEPTPERM_TYPE_NAME, DEPTPERM_GRANTTYPE_CODE, DEPTPERM_GRANTTYPE_NAME, DEPTPERM_NOT_CHECKED, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbIamDeptpermTableDef() {
        super("", "tb_iam_deptperm");
    }

    private TbIamDeptpermTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamDeptpermTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamDeptpermTableDef("", "tb_iam_deptperm", alias));
    }

}
