package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 系统变量 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreConfigTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 系统变量
     */
    public static final TbCoreConfigTableDef TB_CORE_CONFIG = new TbCoreConfigTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 编码
     */
    public final QueryColumn CONFIG_CODE = new QueryColumn(this, "config_code");

    /**
     * 名称
     */
    public final QueryColumn CONFIG_NAME = new QueryColumn(this, "config_name");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 值
     */
    public final QueryColumn CONFIG_VALUE = new QueryColumn(this, "config_value");

    /**
     * 所属产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 说明
     */
    public final QueryColumn CONFIG_REMARK = new QueryColumn(this, "config_remark");

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
     * 启用
     */
    public final QueryColumn CONFIG_ENABLED = new QueryColumn(this, "config_enabled");

    /**
     * saas产品
     */
    public final QueryColumn CONFIG_SAAS_PID = new QueryColumn(this, "config_saas_pid");

    /**
     * 登记者所在部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 所属产品code
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属产品名称
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 类型
     */
    public final QueryColumn CONFIG_TYPE_CODE = new QueryColumn(this, "config_type_code");

    /**
     * 类型name
     */
    public final QueryColumn CONFIG_TYPE_NAME = new QueryColumn(this, "config_type_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    
    public final QueryColumn TB_CORE_CONFIG_ID = new QueryColumn(this, "tb_core_config_id");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_CONFIG_ID, CONFIG_CODE, CONFIG_NAME, CONFIG_TYPE_CODE, CONFIG_ENABLED, CONFIG_VALUE, CONFIG_TYPE_NAME, CONFIG_REMARK, CONFIG_SAAS_PID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreConfigTableDef() {
        super("", "tb_core_config");
    }

    private TbCoreConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreConfigTableDef("", "tb_core_config", alias));
    }

}
