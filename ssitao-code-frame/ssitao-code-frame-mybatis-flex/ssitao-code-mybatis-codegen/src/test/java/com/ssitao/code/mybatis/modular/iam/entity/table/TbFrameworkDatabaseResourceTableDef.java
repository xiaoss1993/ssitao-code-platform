package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据库资源 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbFrameworkDatabaseResourceTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库资源
     */
    public static final TbFrameworkDatabaseResourceTableDef TB_FRAMEWORK_DATABASE_RESOURCE = new TbFrameworkDatabaseResourceTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 服务器id
     */
    public final QueryColumn SERVER_ID = new QueryColumn(this, "server_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 创建类型
     */
    public final QueryColumn BUILD_TYPE = new QueryColumn(this, "build_type");

    /**
     * 备注
     */
    public final QueryColumn RESOURCE_BZ = new QueryColumn(this, "resource_bz");

    /**
     * 服务器名称
     */
    public final QueryColumn SERVER_NAME = new QueryColumn(this, "server_name");

    /**
     * 资源日志目录
     */
    public final QueryColumn RESOURCE_LOG = new QueryColumn(this, "resource_log");

    /**
     * 数据库名称
     */
    public final QueryColumn DATABASE_NAME = new QueryColumn(this, "database_name");

    /**
     * 数据库端口
     */
    public final QueryColumn DATABASE_PORT = new QueryColumn(this, "database_port");

    /**
     * 数据库类型
     */
    public final QueryColumn DATABASE_TYPE = new QueryColumn(this, "database_type");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 修改时间
     */
    public final QueryColumn SY_MODIFYTIME = new QueryColumn(this, "sy_modifytime");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 备注信息
     */
    public final QueryColumn RESOURCE_REMARK = new QueryColumn(this, "resource_remark");

    /**
     * 是否可用
     */
    public final QueryColumn RESOURCE_USABLE = new QueryColumn(this, "resource_usable");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 上架状态
     */
    public final QueryColumn RESOURCE_SHELVES = new QueryColumn(this, "resource_shelves");

    /**
     * 登记部门
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
     * 资源目录
     */
    public final QueryColumn RESOURCE_DIRECTORY = new QueryColumn(this, "resource_directory");

    /**
     * 初始化时间
     */
    public final QueryColumn INITIALIZATION_TIME = new QueryColumn(this, "initialization_time");

    /**
     * 上架时间
     */
    public final QueryColumn RESOURCE_ONLINE_TIME = new QueryColumn(this, "resource_online_time");

    /**
     * 初始化状态
     */
    public final QueryColumn INITIALIZATION_STATUS = new QueryColumn(this, "initialization_status");

    /**
     * 上架人_id
     */
    public final QueryColumn RESOURCE_ONLINEUSER_ID = new QueryColumn(this, "resource_onlineuser_id");

    /**
     * 数据库管理员密码
     */
    public final QueryColumn DATABASE_MANAGE_PASSWORD = new QueryColumn(this, "database_manage_password");

    /**
     * 数据库管理员用户名
     */
    public final QueryColumn DATABASE_MANAGE_USERNAME = new QueryColumn(this, "database_manage_username");

    /**
     * 初始化人
     */
    public final QueryColumn INITIALIZATION_USERNAME = new QueryColumn(this, "initialization_username");

    /**
     * 上架人_name
     */
    public final QueryColumn RESOURCE_ONLINEUSER_NAME = new QueryColumn(this, "resource_onlineuser_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, RESOURCE_DIRECTORY, RESOURCE_LOG, SERVER_ID, DATABASE_MANAGE_USERNAME, DATABASE_MANAGE_PASSWORD, SERVER_NAME, DATABASE_TYPE, BUILD_TYPE, INITIALIZATION_STATUS, INITIALIZATION_USERNAME, INITIALIZATION_TIME, DATABASE_NAME, DATABASE_PORT, RESOURCE_USABLE, RESOURCE_BZ, RESOURCE_SHELVES, RESOURCE_ONLINEUSER_ID, RESOURCE_ONLINEUSER_NAME, RESOURCE_REMARK, RESOURCE_ONLINE_TIME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_STATUS};

    public TbFrameworkDatabaseResourceTableDef() {
        super("", "tb_framework_database_resource");
    }

    private TbFrameworkDatabaseResourceTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbFrameworkDatabaseResourceTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbFrameworkDatabaseResourceTableDef("", "tb_framework_database_resource", alias));
    }

}
