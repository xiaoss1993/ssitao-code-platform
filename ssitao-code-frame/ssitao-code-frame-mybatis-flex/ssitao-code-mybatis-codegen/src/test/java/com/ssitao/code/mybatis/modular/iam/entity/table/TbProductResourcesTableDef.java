package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 产品资源管理 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbProductResourcesTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 产品资源管理
     */
    public static final TbProductResourcesTableDef TB_PRODUCT_RESOURCES = new TbProductResourcesTableDef();

    /**
     * 流程定义id
     */
    public final QueryColumn SY_PDID = new QueryColumn(this, "sy_pdid");

    /**
     * 流程实例id
     */
    public final QueryColumn SY_PIID = new QueryColumn(this, "sy_piid");

    /**
     * 集团公司id
     */
    public final QueryColumn SY_JTGSID = new QueryColumn(this, "sy_jtgsid");

    /**
     * 集团公司名称
     */
    public final QueryColumn SY_JTGSMC = new QueryColumn(this, "sy_jtgsmc");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 登记部门编码
     */
    public final QueryColumn SY_CREATEORG = new QueryColumn(this, "sy_createorg");

    /**
     * 所属产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 登记人编码
     */
    public final QueryColumn SY_CREATEUSER = new QueryColumn(this, "sy_createuser");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 数据库服务器id
     */
    public final QueryColumn RESOURCES_DB_ID = new QueryColumn(this, "resources_db_id");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 所属产品code
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属产品名称
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 数据库服务名称
     */
    public final QueryColumn RESOURCES_DB_NAME = new QueryColumn(this, "resources_db_name");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 命名前缀
     */
    public final QueryColumn RESOURCES_NAME_PREFIX = new QueryColumn(this, "resources_name_prefix");

    /**
     * 主键id
     */
    public final QueryColumn TB_PRODUCT_RESOURCES_ID = new QueryColumn(this, "tb_product_resources_id");

    /**
     * 数据库实例名code
     */
    public final QueryColumn RESOURCES_DB_INSTANCE_CODE = new QueryColumn(this, "resources_db_instance_code");

    /**
     * 数据库实例名
     */
    public final QueryColumn RESOURCES_DB_INSTANCE_NAME = new QueryColumn(this, "resources_db_instance_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_PRODUCT_RESOURCES_ID, RESOURCES_DB_INSTANCE_NAME, RESOURCES_DB_INSTANCE_CODE, RESOURCES_NAME_PREFIX, RESOURCES_DB_NAME, RESOURCES_DB_ID, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_AUDFLAG, SY_CREATEORGID, SY_CREATEORG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSER, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID, SY_JTGSMC, SY_JTGSID};

    public TbProductResourcesTableDef() {
        super("", "tb_product_resources");
    }

    private TbProductResourcesTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbProductResourcesTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbProductResourcesTableDef("", "tb_product_resources", alias));
    }

}
