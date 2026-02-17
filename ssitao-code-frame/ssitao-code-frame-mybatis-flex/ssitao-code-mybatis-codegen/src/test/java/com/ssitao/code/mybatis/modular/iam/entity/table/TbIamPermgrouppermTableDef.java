package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 权限组权限关联 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamPermgrouppermTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 权限组权限关联
     */
    public static final TbIamPermgrouppermTableDef TB_IAM_PERMGROUPPERM = new TbIamPermgrouppermTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 权限id
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
     * 租户名称
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
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 权限组id
     */
    public final QueryColumn TB_IAM_PERMGROUP_ID = new QueryColumn(this, "tb_iam_permgroup_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_PERMGROUPPERM_ID = new QueryColumn(this, "tb_iam_permgroupperm_id");

    /**
     * 授权类型_code
     */
    public final QueryColumn PERMGROUPPERM_GRANTTYPE_CODE = new QueryColumn(this, "permgroupperm_granttype_code");

    /**
     * 授权类型_name
     */
    public final QueryColumn PERMGROUPPERM_GRANTTYPE_NAME = new QueryColumn(this, "permgroupperm_granttype_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_PERMGROUPPERM_ID, TB_IAM_PERM_ID, TB_IAM_PERMGROUP_ID, PERMGROUPPERM_GRANTTYPE_CODE, PERMGROUPPERM_GRANTTYPE_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamPermgrouppermTableDef() {
        super("", "tb_iam_permgroupperm");
    }

    private TbIamPermgrouppermTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamPermgrouppermTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamPermgrouppermTableDef("", "tb_iam_permgroupperm", alias));
    }

}
