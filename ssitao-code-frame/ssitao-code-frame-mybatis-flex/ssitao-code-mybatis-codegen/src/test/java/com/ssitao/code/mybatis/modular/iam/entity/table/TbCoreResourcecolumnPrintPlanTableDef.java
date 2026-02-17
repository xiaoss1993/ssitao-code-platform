package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 列打印方案 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreResourcecolumnPrintPlanTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 列打印方案
     */
    public static final TbCoreResourcecolumnPrintPlanTableDef TB_CORE_RESOURCECOLUMN_PRINT_PLAN = new TbCoreResourcecolumnPrintPlanTableDef();

    /**
     * 方案编码
     */
    public final QueryColumn PLAN_CODE = new QueryColumn(this, "plan_code");

    /**
     * 方案名称
     */
    public final QueryColumn PLAN_NAME = new QueryColumn(this, "plan_name");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 备注
     */
    public final QueryColumn PLAN_REMARK = new QueryColumn(this, "plan_remark");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 是否默认方案
     */
    public final QueryColumn PLAN_DEFAULT = new QueryColumn(this, "plan_default");

    /**
     * saas产品
     */
    public final QueryColumn PLAN_SAAS_PID = new QueryColumn(this, "plan_saas_pid");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户编码
     */
    public final QueryColumn SY_TENANT_CODE = new QueryColumn(this, "sy_tenant_code");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 功能外键
     */
    public final QueryColumn PLAN_FUNCINFO_ID = new QueryColumn(this, "plan_funcinfo_id");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 打印配置
     */
    public final QueryColumn PLAN_PRINT_CONFIG = new QueryColumn(this, "plan_print_config");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_RESOURCECOLUMN_PRINT_PLAN_ID = new QueryColumn(this, "tb_core_resourcecolumn_print_plan_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_RESOURCECOLUMN_PRINT_PLAN_ID, PLAN_PRINT_CONFIG, PLAN_FUNCINFO_ID, PLAN_SAAS_PID, PLAN_NAME, PLAN_CODE, PLAN_DEFAULT, PLAN_REMARK, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_TENANT_NAME, SY_TENANT_CODE, SY_TENANT_ID};

    public TbCoreResourcecolumnPrintPlanTableDef() {
        super("", "tb_core_resourcecolumn_print_plan");
    }

    private TbCoreResourcecolumnPrintPlanTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreResourcecolumnPrintPlanTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreResourcecolumnPrintPlanTableDef("", "tb_core_resourcecolumn_print_plan", alias));
    }

}
