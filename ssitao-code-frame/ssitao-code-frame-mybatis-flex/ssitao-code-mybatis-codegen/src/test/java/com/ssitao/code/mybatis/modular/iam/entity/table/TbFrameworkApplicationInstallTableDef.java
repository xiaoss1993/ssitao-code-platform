package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 应用安装信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbFrameworkApplicationInstallTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 应用安装信息
     */
    public static final TbFrameworkApplicationInstallTableDef TB_FRAMEWORK_APPLICATION_INSTALL = new TbFrameworkApplicationInstallTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 日志目录
     */
    public final QueryColumn INSTALL_LOG = new QueryColumn(this, "install_log");

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
     * 服务器id
     */
    public final QueryColumn INSTALL_SERVER_ID = new QueryColumn(this, "install_server_id");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 创建类型
     */
    public final QueryColumn INSTALL_BUILD_TYPE = new QueryColumn(this, "install_build_type");

    /**
     * 使用内存
     */
    public final QueryColumn INSTALL_USE_MEMORY = new QueryColumn(this, "install_use_memory");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 服务器名称
     */
    public final QueryColumn INSTALL_SERVER_NAME = new QueryColumn(this, "install_server_name");

    /**
     * 初始化时间 
     */
    public final QueryColumn INITIALIZATION_TIME = new QueryColumn(this, "initialization_time");

    /**
     * 安装实例名称
     */
    public final QueryColumn INSTALL_INSTANCE_NAME = new QueryColumn(this, "install_instance_name");

    /**
     * 初始化状态
     */
    public final QueryColumn INITIALIZATION_STATUS = new QueryColumn(this, "initialization_status");

    /**
     * 应用id
     */
    public final QueryColumn INSTALL_APPLICATION_ID = new QueryColumn(this, "install_application_id");

    /**
     * 初始化人
     */
    public final QueryColumn INITIALIZATION_USERNAME = new QueryColumn(this, "initialization_username");

    /**
     * 安装目录
     */
    public final QueryColumn INSTALL_DEPLOY_DIRECTORY = new QueryColumn(this, "install_deploy_directory");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, INSTALL_SERVER_NAME, INSTALL_INSTANCE_NAME, INSTALL_USE_MEMORY, INITIALIZATION_STATUS, INITIALIZATION_USERNAME, INITIALIZATION_TIME, INSTALL_DEPLOY_DIRECTORY, INSTALL_LOG, INSTALL_BUILD_TYPE, INSTALL_APPLICATION_ID, INSTALL_SERVER_ID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_STATUS, SY_ORDERINDEX};

    public TbFrameworkApplicationInstallTableDef() {
        super("", "tb_framework_application_install");
    }

    private TbFrameworkApplicationInstallTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbFrameworkApplicationInstallTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbFrameworkApplicationInstallTableDef("", "tb_framework_application_install", alias));
    }

}
