package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 查询策略 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreQuerystrategyTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 查询策略
     */
    public static final TbCoreQuerystrategyTableDef TB_CORE_QUERYSTRATEGY = new TbCoreQuerystrategyTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

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
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 方法
     */
    public final QueryColumn QUERYSTRATEGY_FN = new QueryColumn(this, "querystrategy_fn");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 默认
     */
    public final QueryColumn QUERYSTRATEGY_DEF = new QueryColumn(this, "querystrategy_def");

    /**
     * sql
     */
    public final QueryColumn QUERYSTRATEGY_SQL = new QueryColumn(this, "querystrategy_sql");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 名称
     */
    public final QueryColumn QUERYSTRATEGY_NAME = new QueryColumn(this, "querystrategy_name");

    /**
     * 其他语种
     */
    public final QueryColumn QUERYSTRATEGY_QTYZ = new QueryColumn(this, "querystrategy_qtyz");

    /**
     * 英文名
     */
    public final QueryColumn QUERYSTRATEGY_NAME_EN = new QueryColumn(this, "querystrategy_name_en");

    /**
     * 用户id
     */
    public final QueryColumn QUERYSTRATEGY_USERID = new QueryColumn(this, "querystrategy_userid");

    /**
     * 覆盖功能sql
     */
    public final QueryColumn QUERYSTRATEGY_FGGNSQL = new QueryColumn(this, "querystrategy_fggnsql");

    /**
     * saas产品
     */
    public final QueryColumn QUERYSTRATEGY_SAAS_PID = new QueryColumn(this, "querystrategy_saas_pid");

    /**
     * 功能编码
     */
    public final QueryColumn QUERYSTRATEGY_FUNCCODE = new QueryColumn(this, "querystrategy_funccode");

    
    public final QueryColumn TB_CORE_QUERYSTRATEGY_ID = new QueryColumn(this, "tb_core_querystrategy_id");

    /**
     * 外键
     */
    public final QueryColumn QUERYSTRATEGY_FUNCINFO_ID = new QueryColumn(this, "querystrategy_funcinfo_id");

    /**
     * sql备注
     */
    public final QueryColumn QUERYSTRATEGY_SQL_REMARKS = new QueryColumn(this, "querystrategy_sql_remarks");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_QUERYSTRATEGY_ID, QUERYSTRATEGY_FUNCCODE, QUERYSTRATEGY_NAME, QUERYSTRATEGY_SQL, QUERYSTRATEGY_USERID, QUERYSTRATEGY_DEF, QUERYSTRATEGY_FUNCINFO_ID, QUERYSTRATEGY_FGGNSQL, QUERYSTRATEGY_FN, QUERYSTRATEGY_NAME_EN, QUERYSTRATEGY_SQL_REMARKS, QUERYSTRATEGY_QTYZ, QUERYSTRATEGY_SAAS_PID, SY_CREATEORGID, SY_CREATEUSERID, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_ORDERINDEX, SY_STATUS, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public TbCoreQuerystrategyTableDef() {
        super("", "tb_core_querystrategy");
    }

    private TbCoreQuerystrategyTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreQuerystrategyTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreQuerystrategyTableDef("", "tb_core_querystrategy", alias));
    }

}
