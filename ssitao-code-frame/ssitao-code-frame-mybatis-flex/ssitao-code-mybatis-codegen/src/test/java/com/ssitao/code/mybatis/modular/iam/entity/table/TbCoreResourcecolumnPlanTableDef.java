package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 列方案 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreResourcecolumnPlanTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 列方案
     */
    public static final TbCoreResourcecolumnPlanTableDef TB_CORE_RESOURCECOLUMN_PLAN = new TbCoreResourcecolumnPlanTableDef();

    /**
     * 是否系统方案
     */
    public final QueryColumn PLAN_SY = new QueryColumn(this, "plan_sy");

    /**
     * 方案编码
     */
    public final QueryColumn PLAN_CODE = new QueryColumn(this, "plan_code");

    /**
     * 名称
     */
    public final QueryColumn PLAN_NAME = new QueryColumn(this, "plan_name");

    /**
     * 类型
     */
    public final QueryColumn PLAN_TYPE = new QueryColumn(this, "plan_type");

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
     * 提示类型
     */
    public final QueryColumn PLAN_TIP_TYPE = new QueryColumn(this, "plan_tip_type");

    /**
     * 父id
     */
    public final QueryColumn PLAN_PARENT_ID = new QueryColumn(this, "plan_parent_id");

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
    public final QueryColumn TB_CORE_RESOURCECOLUMN_PLAN_ID = new QueryColumn(this, "tb_core_resourcecolumn_plan_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_RESOURCECOLUMN_PLAN_ID, PLAN_NAME, PLAN_FUNCINFO_ID, PLAN_CODE, PLAN_TYPE, PLAN_DEFAULT, PLAN_REMARK, PLAN_SY, PLAN_PARENT_ID, PLAN_TIP_TYPE, SY_TENANT_ID, SY_TENANT_CODE, SY_TENANT_NAME, PLAN_SAAS_PID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbCoreResourcecolumnPlanTableDef() {
        super("", "tb_core_resourcecolumn_plan");
    }

    private TbCoreResourcecolumnPlanTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreResourcecolumnPlanTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreResourcecolumnPlanTableDef("", "tb_core_resourcecolumn_plan", alias));
    }

}
