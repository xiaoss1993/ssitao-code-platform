package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 产品申请部署流转日志 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbProductFlowlogTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 产品申请部署流转日志
     */
    public static final TbProductFlowlogTableDef TB_PRODUCT_FLOWLOG = new TbProductFlowlogTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 操作人
     */
    public final QueryColumn FLOWLOG_CZR = new QueryColumn(this, "flowlog_czr");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 操作时间
     */
    public final QueryColumn FLOWLOG_CZSJ = new QueryColumn(this, "flowlog_czsj");

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
     * 操作类型
     */
    public final QueryColumn FLOWLOG_CZLX_CODE = new QueryColumn(this, "flowlog_czlx_code");

    /**
     * 操作类型_name
     */
    public final QueryColumn FLOWLOG_CZLX_NAME = new QueryColumn(this, "flowlog_czlx_name");

    /**
     * 审批状态
     */
    public final QueryColumn FLOWLOG_SPZT_CODE = new QueryColumn(this, "flowlog_spzt_code");

    /**
     * 审批状态_name
     */
    public final QueryColumn FLOWLOG_SPZT_NAME = new QueryColumn(this, "flowlog_spzt_name");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 产品资源申请部署外键
     */
    public final QueryColumn FLOWLOG_PROJECT_ID = new QueryColumn(this, "flowlog_project_id");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 产品类型
     */
    public final QueryColumn FLOWLOG_PRODUCT_CODE = new QueryColumn(this, "flowlog_product_code");

    /**
     * 产品类型_name
     */
    public final QueryColumn FLOWLOG_PRODUCT_NAME = new QueryColumn(this, "flowlog_product_name");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 主键id
     */
    public final QueryColumn TB_PRODUCT_FLOWLOG_ID = new QueryColumn(this, "tb_product_flowlog_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_PRODUCT_FLOWLOG_ID, FLOWLOG_PRODUCT_CODE, FLOWLOG_PRODUCT_NAME, FLOWLOG_SPZT_CODE, FLOWLOG_SPZT_NAME, FLOWLOG_CZLX_CODE, FLOWLOG_CZLX_NAME, FLOWLOG_CZR, FLOWLOG_CZSJ, FLOWLOG_PROJECT_ID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbProductFlowlogTableDef() {
        super("", "tb_product_flowlog");
    }

    private TbProductFlowlogTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbProductFlowlogTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbProductFlowlogTableDef("", "tb_product_flowlog", alias));
    }

}
