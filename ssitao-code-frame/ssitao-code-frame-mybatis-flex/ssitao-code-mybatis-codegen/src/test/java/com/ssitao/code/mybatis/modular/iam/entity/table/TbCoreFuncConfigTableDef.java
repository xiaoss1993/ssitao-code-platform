package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 功能业务配置 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreFuncConfigTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 功能业务配置
     */
    public static final TbCoreFuncConfigTableDef TB_CORE_FUNC_CONFIG = new TbCoreFuncConfigTableDef();

    /**
     * 备注
     */
    public final QueryColumn CONFIG_BZ = new QueryColumn(this, "config_bz");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 配置信息
     */
    public final QueryColumn CONFIG_DATA = new QueryColumn(this, "config_data");

    /**
     * 业务分类
     */
    public final QueryColumn CONFIG_TYPE = new QueryColumn(this, "config_type");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 事件脚本
     */
    public final QueryColumn CONFIG_SCRIPT = new QueryColumn(this, "config_script");

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
     * saas产品
     */
    public final QueryColumn CONFIG_SAAS_PID = new QueryColumn(this, "config_saas_pid");

    /**
     * 功能code
     */
    public final QueryColumn CONFIG_FUNCCODE = new QueryColumn(this, "config_funccode");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 功能表名
     */
    public final QueryColumn CONFIG_TABLENAME = new QueryColumn(this, "config_tablename");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 功能主键
     */
    public final QueryColumn TB_CORE_FUNCINFO_ID = new QueryColumn(this, "tb_core_funcinfo_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_FUNC_CONFIG_ID = new QueryColumn(this, "tb_core_func_config_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_FUNCINFO_ID, TB_CORE_FUNC_CONFIG_ID, CONFIG_FUNCCODE, CONFIG_TYPE, CONFIG_DATA, CONFIG_SCRIPT, CONFIG_TABLENAME, CONFIG_BZ, CONFIG_SAAS_PID, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreFuncConfigTableDef() {
        super("", "tb_core_func_config");
    }

    private TbCoreFuncConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreFuncConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreFuncConfigTableDef("", "tb_core_func_config", alias));
    }

}
