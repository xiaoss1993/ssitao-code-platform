package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 租户数据权限 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbSaasFuncpermTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 租户数据权限
     */
    public static final TbSaasFuncpermTableDef TB_SAAS_FUNCPERM = new TbSaasFuncpermTableDef();

    /**
     * 租户id
     */
    public final QueryColumn SY_ZHID = new QueryColumn(this, "sy_zhid");

    /**
     * 租户名称
     */
    public final QueryColumn SY_ZHMC = new QueryColumn(this, "sy_zhmc");

    /**
     * 机构id
     */
    public final QueryColumn FUNCPERM_ORG_ID = new QueryColumn(this, "funcperm_org_id");

    /**
     * 权限过滤条件脚本
     */
    public final QueryColumn FUNCINFO_PERMJS = new QueryColumn(this, "funcinfo_permjs");

    /**
     * 部门id
     */
    public final QueryColumn FUNCPERM_DEPT_ID = new QueryColumn(this, "funcperm_dept_id");

    /**
     * 角色id
     */
    public final QueryColumn FUNCPERM_ROLE_ID = new QueryColumn(this, "funcperm_role_id");

    /**
     * 人员id
     */
    public final QueryColumn FUNCPERM_USER_ID = new QueryColumn(this, "funcperm_user_id");

    /**
     * 权限过滤条件
     */
    public final QueryColumn FUNCINFO_PERMSQL = new QueryColumn(this, "funcinfo_permsql");

    /**
     * 机构名称
     */
    public final QueryColumn FUNCPERM_ORG_NAME = new QueryColumn(this, "funcperm_org_name");

    /**
     * 部门名称
     */
    public final QueryColumn FUNCPERM_DEPT_NAME = new QueryColumn(this, "funcperm_dept_name");

    /**
     * 功能编码
     */
    public final QueryColumn FUNCPERM_FUNCCODE = new QueryColumn(this, "funcperm_funccode");

    /**
     * 功能名称
     */
    public final QueryColumn FUNCPERM_FUNCNAME = new QueryColumn(this, "funcperm_funcname");

    /**
     * 角色名称
     */
    public final QueryColumn FUNCPERM_ROLE_NAME = new QueryColumn(this, "funcperm_role_name");

    /**
     * 人员名称
     */
    public final QueryColumn FUNCPERM_USER_NAME = new QueryColumn(this, "funcperm_user_name");

    /**
     * 功能id
     */
    public final QueryColumn TB_CORE_FUNCINFO_ID = new QueryColumn(this, "tb_core_funcinfo_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_SAAS_FUNCPERM_ID = new QueryColumn(this, "tb_saas_funcperm_id");

    /**
     * 部门关系id
     */
    public final QueryColumn FUNCINFO_DEPTCJIDS = new QueryColumn(this, "funcinfo_deptcjids");

    /**
     * 控制部门字段
     */
    public final QueryColumn FUNCINFO_DEPTFIELD = new QueryColumn(this, "funcinfo_deptfield");

    /**
     * 控制人员字段
     */
    public final QueryColumn FUNCINFO_USERFIELD = new QueryColumn(this, "funcinfo_userfield");

    /**
     * 权限类型
     */
    public final QueryColumn FUNCINFO_PERMCONFIG = new QueryColumn(this, "funcinfo_permconfig");

    /**
     * 部门id
     */
    public final QueryColumn FUNCINFO_SEEDEPTIDS = new QueryColumn(this, "funcinfo_seedeptids");

    /**
     * 角色id
     */
    public final QueryColumn FUNCINFO_SEEROLEIDS = new QueryColumn(this, "funcinfo_seeroleids");

    /**
     * 人员id
     */
    public final QueryColumn FUNCINFO_SEEUSERIDS = new QueryColumn(this, "funcinfo_seeuserids");

    /**
     * 使用关系id
     */
    public final QueryColumn FUNCINFO_USECJGLIDS = new QueryColumn(this, "funcinfo_usecjglids");

    /**
     * 部门关系
     */
    public final QueryColumn FUNCINFO_DEPTCJNAMES = new QueryColumn(this, "funcinfo_deptcjnames");

    /**
     * 可见部门
     */
    public final QueryColumn FUNCINFO_SEEDEPTNAMES = new QueryColumn(this, "funcinfo_seedeptnames");

    /**
     * 可见角色
     */
    public final QueryColumn FUNCINFO_SEEROLENAMES = new QueryColumn(this, "funcinfo_seerolenames");

    /**
     * 岗位id
     */
    public final QueryColumn FUNCINFO_SEESENTRYIDS = new QueryColumn(this, "funcinfo_seesentryids");

    /**
     * 可见人员
     */
    public final QueryColumn FUNCINFO_SEEUSERNAMES = new QueryColumn(this, "funcinfo_seeusernames");

    /**
     * 使用关系
     */
    public final QueryColumn FUNCINFO_USECJGLNAMES = new QueryColumn(this, "funcinfo_usecjglnames");

    /**
     * 可见岗位
     */
    public final QueryColumn FUNCINFO_SEESENTRYNAMES = new QueryColumn(this, "funcinfo_seesentrynames");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_SAAS_FUNCPERM_ID, TB_CORE_FUNCINFO_ID, FUNCPERM_FUNCNAME, FUNCPERM_FUNCCODE, FUNCINFO_USECJGLNAMES, FUNCINFO_USECJGLIDS, FUNCINFO_SEEUSERNAMES, FUNCINFO_SEEUSERIDS, FUNCINFO_SEEDEPTNAMES, FUNCINFO_SEEDEPTIDS, FUNCINFO_SEEROLENAMES, FUNCINFO_SEEROLEIDS, FUNCINFO_SEESENTRYNAMES, FUNCINFO_SEESENTRYIDS, FUNCINFO_PERMCONFIG, FUNCINFO_PERMSQL, FUNCINFO_PERMJS, FUNCINFO_USERFIELD, FUNCINFO_DEPTFIELD, FUNCINFO_DEPTCJIDS, FUNCINFO_DEPTCJNAMES, FUNCPERM_ORG_NAME, FUNCPERM_ORG_ID, FUNCPERM_USER_ID, FUNCPERM_ROLE_NAME, FUNCPERM_ROLE_ID, FUNCPERM_USER_NAME, FUNCPERM_DEPT_ID, FUNCPERM_DEPT_NAME, SY_ZHID, SY_ZHMC};

    public TbSaasFuncpermTableDef() {
        super("", "tb_saas_funcperm");
    }

    private TbSaasFuncpermTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbSaasFuncpermTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbSaasFuncpermTableDef("", "tb_saas_funcperm", alias));
    }

}
