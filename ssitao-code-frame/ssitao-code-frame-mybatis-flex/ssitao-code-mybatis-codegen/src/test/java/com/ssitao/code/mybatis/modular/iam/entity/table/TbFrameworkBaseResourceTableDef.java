package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 基础资源 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbFrameworkBaseResourceTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 基础资源
     */
    public static final TbFrameworkBaseResourceTableDef TB_FRAMEWORK_BASE_RESOURCE = new TbFrameworkBaseResourceTableDef();

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
     * 资源日志目录
     */
    public final QueryColumn RESOURCE_LOG = new QueryColumn(this, "resource_log");

    /**
     * 资源名称
     */
    public final QueryColumn RESOURCE_NAME = new QueryColumn(this, "resource_name");

    /**
     * 资源端口
     */
    public final QueryColumn RESOURCE_PORT = new QueryColumn(this, "resource_port");

    /**
     * 资源角色
     */
    public final QueryColumn RESOURCE_ROLE = new QueryColumn(this, "resource_role");

    /**
     * 资源类型
     */
    public final QueryColumn RESOURCE_TYPE = new QueryColumn(this, "resource_type");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

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
     * 资源目录
     */
    public final QueryColumn RESOURCE_DIRECTORY = new QueryColumn(this, "resource_directory");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, RESOURCE_ROLE, RESOURCE_NAME, RESOURCE_DIRECTORY, RESOURCE_LOG, RESOURCE_PORT, RESOURCE_TYPE, SERVER_ID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbFrameworkBaseResourceTableDef() {
        super("", "tb_framework_base_resource");
    }

    private TbFrameworkBaseResourceTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbFrameworkBaseResourceTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbFrameworkBaseResourceTableDef("", "tb_framework_base_resource", alias));
    }

}
