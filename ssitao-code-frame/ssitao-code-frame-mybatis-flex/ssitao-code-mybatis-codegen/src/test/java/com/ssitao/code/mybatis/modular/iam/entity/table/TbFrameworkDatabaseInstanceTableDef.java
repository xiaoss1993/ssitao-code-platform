package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据库实例 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbFrameworkDatabaseInstanceTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库实例
     */
    public static final TbFrameworkDatabaseInstanceTableDef TB_FRAMEWORK_DATABASE_INSTANCE = new TbFrameworkDatabaseInstanceTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 实例名字
     */
    public final QueryColumn INSTANCE_NAME = new QueryColumn(this, "instance_name");

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
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 密码
     */
    public final QueryColumn INSTANCE_PASSWORD = new QueryColumn(this, "instance_password");

    /**
     * 用户名
     */
    public final QueryColumn INSTANCE_USERNAME = new QueryColumn(this, "instance_username");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 产品id
     */
    public final QueryColumn INSTANCE_PRODUCT_ID = new QueryColumn(this, "instance_product_id");

    /**
     * 初始化时间
     */
    public final QueryColumn INITIALIZATION_TIME = new QueryColumn(this, "initialization_time");

    /**
     * 数据库id
     */
    public final QueryColumn INSTANCE_DATABASE_ID = new QueryColumn(this, "instance_database_id");

    /**
     * 产品编码
     */
    public final QueryColumn INSTANCE_PRODUCT_CODE = new QueryColumn(this, "instance_product_code");

    /**
     * 产品名称
     */
    public final QueryColumn INSTANCE_PRODUCT_NAME = new QueryColumn(this, "instance_product_name");

    /**
     * 初始化状态
     */
    public final QueryColumn INITIALIZATION_STATUS = new QueryColumn(this, "initialization_status");

    /**
     * 初始化人
     */
    public final QueryColumn INITIALIZATION_USERNAME = new QueryColumn(this, "initialization_username");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, INSTANCE_NAME, INSTANCE_USERNAME, INSTANCE_PASSWORD, INSTANCE_DATABASE_ID, INSTANCE_PRODUCT_ID, INSTANCE_PRODUCT_NAME, INSTANCE_PRODUCT_CODE, INITIALIZATION_STATUS, INITIALIZATION_TIME, INITIALIZATION_USERNAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_STATUS, SY_ORDERINDEX};

    public TbFrameworkDatabaseInstanceTableDef() {
        super("", "tb_framework_database_instance");
    }

    private TbFrameworkDatabaseInstanceTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbFrameworkDatabaseInstanceTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbFrameworkDatabaseInstanceTableDef("", "tb_framework_database_instance", alias));
    }

}
