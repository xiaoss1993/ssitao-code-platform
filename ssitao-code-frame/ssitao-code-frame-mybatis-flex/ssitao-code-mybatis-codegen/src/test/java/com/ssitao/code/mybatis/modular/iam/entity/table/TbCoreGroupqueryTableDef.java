package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 高级查询配置 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreGroupqueryTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 高级查询配置
     */
    public static final TbCoreGroupqueryTableDef TB_CORE_GROUPQUERY = new TbCoreGroupqueryTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 编码
     */
    public final QueryColumn GROUPQUERY_BM = new QueryColumn(this, "groupquery_bm");

    /**
     * 宽度
     */
    public final QueryColumn GROUPQUERY_KD = new QueryColumn(this, "groupquery_kd");

    /**
     * 名称
     */
    public final QueryColumn GROUPQUERY_MC = new QueryColumn(this, "groupquery_mc");

    /**
     * 排序
     */
    public final QueryColumn GROUPQUERY_PX = new QueryColumn(this, "groupquery_px");

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
     * 标签宽度
     */
    public final QueryColumn GROUPQUERY_BQKD = new QueryColumn(this, "groupquery_bqkd");

    /**
     * 标签隐藏
     */
    public final QueryColumn GROUPQUERY_BQYC = new QueryColumn(this, "groupquery_bqyc");

    /**
     * 默认查询类型
     */
    public final QueryColumn GROUPQUERY_CXLX = new QueryColumn(this, "groupquery_cxlx");

    /**
     * 常用查询
     */
    public final QueryColumn GROUPQUERY_CYCX = new QueryColumn(this, "groupquery_cycx");

    /**
     * 功能id
     */
    public final QueryColumn GROUPQUERY_GNID = new QueryColumn(this, "groupquery_gnid");

    /**
     * 排序类型
     */
    public final QueryColumn GROUPQUERY_PXLX = new QueryColumn(this, "groupquery_pxlx");

    /**
     * 所占列数
     */
    public final QueryColumn GROUPQUERY_SZLS = new QueryColumn(this, "groupquery_szls");

    /**
     * 英文名称
     */
    public final QueryColumn GROUPQUERY_YWMC = new QueryColumn(this, "groupquery_ywmc");

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
     * saas产品
     */
    public final QueryColumn GROUPQUERY_SAAS_PID = new QueryColumn(this, "groupquery_saas_pid");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_GROUPQUERY_ID = new QueryColumn(this, "tb_core_groupquery_id");

    /**
     * 允许排序
     */
    public final QueryColumn GROUPQUERY_ALLOW_SORT = new QueryColumn(this, "groupquery_allow_sort");

    /**
     * 字段类型
     */
    public final QueryColumn GROUPQUERY_FIELD_TYPE = new QueryColumn(this, "groupquery_field_type");

    /**
     * 可选可编辑
     */
    public final QueryColumn GROUPQUERY_CHOICEANDEDIT = new QueryColumn(this, "groupquery_choiceandedit");

    /**
     * 展示查询类型
     */
    public final QueryColumn GROUPQUERY_SHOW_QUERY_TYPE = new QueryColumn(this, "groupquery_show_query_type");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_GROUPQUERY_ID, GROUPQUERY_BM, GROUPQUERY_MC, GROUPQUERY_CXLX, GROUPQUERY_SZLS, GROUPQUERY_KD, GROUPQUERY_BQKD, GROUPQUERY_BQYC, GROUPQUERY_PXLX, GROUPQUERY_PX, GROUPQUERY_CYCX, GROUPQUERY_GNID, GROUPQUERY_YWMC, GROUPQUERY_ALLOW_SORT, GROUPQUERY_CHOICEANDEDIT, GROUPQUERY_FIELD_TYPE, GROUPQUERY_SHOW_QUERY_TYPE, GROUPQUERY_SAAS_PID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreGroupqueryTableDef() {
        super("", "tb_core_groupquery");
    }

    private TbCoreGroupqueryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreGroupqueryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreGroupqueryTableDef("", "tb_core_groupquery", alias));
    }

}
