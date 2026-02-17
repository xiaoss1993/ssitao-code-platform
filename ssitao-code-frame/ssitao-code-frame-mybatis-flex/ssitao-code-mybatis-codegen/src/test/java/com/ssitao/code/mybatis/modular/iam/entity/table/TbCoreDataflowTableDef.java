package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据流转策略 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreDataflowTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据流转策略
     */
    public static final TbCoreDataflowTableDef TB_CORE_DATAFLOW = new TbCoreDataflowTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 备注
     */
    public final QueryColumn DATAFLOW_BZ = new QueryColumn(this, "dataflow_bz");

    /**
     * 操作
     */
    public final QueryColumn DATAFLOW_CZ = new QueryColumn(this, "dataflow_cz");

    /**
     * 动作
     */
    public final QueryColumn DATAFLOW_DZ = new QueryColumn(this, "dataflow_dz");

    /**
     * 回调
     */
    public final QueryColumn DATAFLOW_HD = new QueryColumn(this, "dataflow_hd");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * sql
     */
    public final QueryColumn DATAFLOW_SQL = new QueryColumn(this, "dataflow_sql");

    /**
     * 操作方式
     */
    public final QueryColumn DATAFLOW_CZFS = new QueryColumn(this, "dataflow_czfs");

    /**
     * 启用条件
     */
    public final QueryColumn DATAFLOW_QYTJ = new QueryColumn(this, "dataflow_qytj");

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
     * saas产品
     */
    public final QueryColumn DATAFLOW_SAAS_PID = new QueryColumn(this, "dataflow_saas_pid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    
    public final QueryColumn TB_CORE_DATAFLOW_ID = new QueryColumn(this, "tb_core_dataflow_id");

    /**
     * 功能id
     */
    public final QueryColumn DATAFLOW_FUNCINFO_ID = new QueryColumn(this, "dataflow_funcinfo_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_DATAFLOW_ID, DATAFLOW_QYTJ, DATAFLOW_DZ, DATAFLOW_CZ, DATAFLOW_SQL, DATAFLOW_HD, DATAFLOW_CZFS, DATAFLOW_FUNCINFO_ID, DATAFLOW_SAAS_PID, DATAFLOW_BZ, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreDataflowTableDef() {
        super("", "tb_core_dataflow");
    }

    private TbCoreDataflowTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreDataflowTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreDataflowTableDef("", "tb_core_dataflow", alias));
    }

}
