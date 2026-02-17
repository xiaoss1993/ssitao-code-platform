package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 系统设置 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreSettingTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 系统设置
     */
    public static final TbCoreSettingTableDef TB_CORE_SETTING = new TbCoreSettingTableDef();

    /**
     * 名称
     */
    public final QueryColumn CODE = new QueryColumn(this, "code");

    /**
     * 总类型(开发，管理)
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 值
     */
    public final QueryColumn VALUE = new QueryColumn(this, "value");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 属性
     */
    public final QueryColumn ATTRIBUTE = new QueryColumn(this, "attribute");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

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
     * saas产品
     */
    public final QueryColumn SETTING_SAAS_PID = new QueryColumn(this, "setting_saas_pid");

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
    public final QueryColumn TB_CORE_SETTING_ID = new QueryColumn(this, "tb_core_setting_id");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_SETTING_ID, CODE, VALUE, TYPE, ATTRIBUTE, SY_TENANT_ID, SY_TENANT_NAME, SETTING_SAAS_PID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbCoreSettingTableDef() {
        super("", "tb_core_setting");
    }

    private TbCoreSettingTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreSettingTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreSettingTableDef("", "tb_core_setting", alias));
    }

}
