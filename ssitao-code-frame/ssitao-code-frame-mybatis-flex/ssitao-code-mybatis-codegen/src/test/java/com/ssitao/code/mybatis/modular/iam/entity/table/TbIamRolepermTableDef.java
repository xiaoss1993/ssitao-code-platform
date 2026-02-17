package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 角色权限关联 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamRolepermTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 角色权限关联
     */
    public static final TbIamRolepermTableDef TB_IAM_ROLEPERM = new TbIamRolepermTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 权限主键
     */
    public final QueryColumn TB_IAM_PERM_ID = new QueryColumn(this, "tb_iam_perm_id");

    /**
     * 角色主键
     */
    public final QueryColumn TB_IAM_ROLE_ID = new QueryColumn(this, "tb_iam_role_id");

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
    public final QueryColumn TB_IAM_ROLEPERM_ID = new QueryColumn(this, "tb_iam_roleperm_id");

    /**
     * 授权方式_code
     */
    public final QueryColumn ROLEPERM_TYPE_CODE = new QueryColumn(this, "roleperm_type_code");

    /**
     * 授权方式_name
     */
    public final QueryColumn ROLEPERM_TYPE_NAME = new QueryColumn(this, "roleperm_type_name");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 是否不选中
     */
    public final QueryColumn ROLEPERM_NOT_CHECKED = new QueryColumn(this, "roleperm_not_checked");

    /**
     * 是否排他_code
     */
    public final QueryColumn ROLEPERM_EXCLUDE_CODE = new QueryColumn(this, "roleperm_exclude_code");

    /**
     * 是否排他_name
     */
    public final QueryColumn ROLEPERM_EXCLUDE_NAME = new QueryColumn(this, "roleperm_exclude_name");

    /**
     * 授权类型_code
     */
    public final QueryColumn ROLEPERM_GRANTTYPE_CODE = new QueryColumn(this, "roleperm_granttype_code");

    /**
     * 授权类型_name
     */
    public final QueryColumn ROLEPERM_GRANTTYPE_NAME = new QueryColumn(this, "roleperm_granttype_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_ROLEPERM_ID, TB_IAM_ROLE_ID, TB_IAM_PERM_ID, ROLEPERM_EXCLUDE_CODE, ROLEPERM_EXCLUDE_NAME, ROLEPERM_TYPE_CODE, ROLEPERM_TYPE_NAME, ROLEPERM_GRANTTYPE_CODE, ROLEPERM_GRANTTYPE_NAME, ROLEPERM_NOT_CHECKED, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbIamRolepermTableDef() {
        super("", "tb_iam_roleperm");
    }

    private TbIamRolepermTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamRolepermTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamRolepermTableDef("", "tb_iam_roleperm", alias));
    }

}
