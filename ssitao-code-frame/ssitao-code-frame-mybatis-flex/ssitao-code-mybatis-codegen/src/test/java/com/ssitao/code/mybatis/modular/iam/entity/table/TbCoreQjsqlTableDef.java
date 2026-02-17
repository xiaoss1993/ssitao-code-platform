package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * sql模板库 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreQjsqlTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * sql模板库
     */
    public static final TbCoreQjsqlTableDef TB_CORE_QJSQL = new TbCoreQjsqlTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 模板编码
     */
    public final QueryColumn QJSQL_MBBM = new QueryColumn(this, "qjsql_mbbm");

    /**
     * 模板内容
     */
    public final QueryColumn QJSQL_MBNR = new QueryColumn(this, "qjsql_mbnr");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 模板编写规范
     */
    public final QueryColumn QJSQL_MBBXGF = new QueryColumn(this, "qjsql_mbbxgf");

    /**
     * 模板功能描述
     */
    public final QueryColumn QJSQL_MBGNMS = new QueryColumn(this, "qjsql_mbgnms");

    /**
     * 启用
     */
    public final QueryColumn QJSQL_QY_CODE = new QueryColumn(this, "qjsql_qy_code");

    /**
     * 所属模块_id
     */
    public final QueryColumn QJSQL_SSMK_ID = new QueryColumn(this, "qjsql_ssmk_id");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 所属组
     */
    public final QueryColumn QJSQL_SSZ_NAME = new QueryColumn(this, "qjsql_ssz_name");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 修改时间
     */
    public final QueryColumn SY_MODIFYTIME = new QueryColumn(this, "sy_modifytime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 所属模块
     */
    public final QueryColumn QJSQL_SSMK_CODE = new QueryColumn(this, "qjsql_ssmk_code");

    /**
     * 所属模块_name
     */
    public final QueryColumn QJSQL_SSMK_NAME = new QueryColumn(this, "qjsql_ssmk_name");

    /**
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_QJSQL_ID = new QueryColumn(this, "tb_core_qjsql_id");

    /**
     *  所属服务_id
     */
    public final QueryColumn QJSQL_PROJECT_ID = new QueryColumn(this, "qjsql_project_id");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 所属服务_code
     */
    public final QueryColumn QJSQL_PROJECT_CODE = new QueryColumn(this, "qjsql_project_code");

    /**
     *  所属服务
     */
    public final QueryColumn QJSQL_PROJECT_NAME = new QueryColumn(this, "qjsql_project_name");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 执行策略
     */
    public final QueryColumn QJSQL_SQLJBZXCL_CODE = new QueryColumn(this, "qjsql_sqljbzxcl_code");

    /**
     * 执行策略_name
     */
    public final QueryColumn QJSQL_SQLJBZXCL_NAME = new QueryColumn(this, "qjsql_sqljbzxcl_name");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_QJSQL_ID, QJSQL_MBGNMS, QJSQL_MBBM, QJSQL_MBNR, QJSQL_QY_CODE, QJSQL_PROJECT_NAME, QJSQL_PROJECT_ID, QJSQL_MBBXGF, QJSQL_SQLJBZXCL_NAME, QJSQL_SQLJBZXCL_CODE, QJSQL_PROJECT_CODE, QJSQL_SSZ_NAME, QJSQL_SSMK_NAME, QJSQL_SSMK_CODE, QJSQL_SSMK_ID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public TbCoreQjsqlTableDef() {
        super("", "tb_core_qjsql");
    }

    private TbCoreQjsqlTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreQjsqlTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreQjsqlTableDef("", "tb_core_qjsql", alias));
    }

}
