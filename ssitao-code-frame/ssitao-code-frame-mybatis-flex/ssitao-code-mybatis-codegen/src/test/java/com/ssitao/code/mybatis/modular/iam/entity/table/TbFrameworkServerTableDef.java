package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 服务器维护 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbFrameworkServerTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 服务器维护
     */
    public static final TbFrameworkServerTableDef TB_FRAMEWORK_SERVER = new TbFrameworkServerTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 作用域
     */
    public final QueryColumn SCOPE = new QueryColumn(this, "scope");

    /**
     * 状态
     */
    public final QueryColumn STATE = new QueryColumn(this, "state");

    /**
     * 标准内存
     */
    public final QueryColumn MEMORY = new QueryColumn(this, "memory");

    /**
     * 备注
     */
    public final QueryColumn REMARKS = new QueryColumn(this, "remarks");

    /**
     * 服务器ip
     */
    public final QueryColumn SERVER_IP = new QueryColumn(this, "server_ip");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 创建类型
     */
    public final QueryColumn BUILD_TYPE = new QueryColumn(this, "build_type");

    /**
     * 服务器密钥
     */
    public final QueryColumn SERVER_KEY = new QueryColumn(this, "server_key");

    /**
     * 上架时间
     */
    public final QueryColumn ONLINE_TIME = new QueryColumn(this, "online_time");

    /**
     * 服务器名称
     */
    public final QueryColumn SERVER_NAME = new QueryColumn(this, "server_name");

    /**
     * 服务器端口
     */
    public final QueryColumn SERVER_PORT = new QueryColumn(this, "server_port");

    /**
     * 服务器类型
     */
    public final QueryColumn SERVER_TYPE = new QueryColumn(this, "server_type");

    /**
     * 安装服务标签
     */
    public final QueryColumn SERVER_LABEL = new QueryColumn(this, "server_label");

    /**
     * 上架人id
     */
    public final QueryColumn ONLINE_USER_ID = new QueryColumn(this, "online_user_id");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 修改时间
     */
    public final QueryColumn SY_MODIFYTIME = new QueryColumn(this, "sy_modifytime");

    /**
     * 上架状态
     */
    public final QueryColumn SERVER_SHELVES = new QueryColumn(this, "server_shelves");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 上架人姓名
     */
    public final QueryColumn ONLINE_USER_NAME = new QueryColumn(this, "online_user_name");

    /**
     * 服务器密码
     */
    public final QueryColumn SERVER_PASSWORD = new QueryColumn(this, "server_password");

    /**
     * 服务器用户名
     */
    public final QueryColumn SERVER_USERNAME = new QueryColumn(this, "server_username");

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
     * 连接状态
     */
    public final QueryColumn SERVER_CONN_STATUS = new QueryColumn(this, "server_conn_status");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 剩余内存
     */
    public final QueryColumn SERVER_SURPLUS_MEMORY = new QueryColumn(this, "server_surplus_memory");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, SERVER_NAME, SERVER_IP, SERVER_PORT, SERVER_SURPLUS_MEMORY, SERVER_PASSWORD, SERVER_LABEL, SERVER_SHELVES, SERVER_KEY, BUILD_TYPE, SERVER_USERNAME, SERVER_CONN_STATUS, MEMORY, SCOPE, REMARKS, STATE, ONLINE_TIME, SERVER_TYPE, ONLINE_USER_NAME, ONLINE_USER_ID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_STATUS};

    public TbFrameworkServerTableDef() {
        super("", "tb_framework_server");
    }

    private TbFrameworkServerTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbFrameworkServerTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbFrameworkServerTableDef("", "tb_framework_server", alias));
    }

}
