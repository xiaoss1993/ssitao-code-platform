package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 架构管理（暂时没用） 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbFrameworkManageTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 架构管理（暂时没用）
     */
    public static final TbFrameworkManageTableDef TB_FRAMEWORK_MANAGE = new TbFrameworkManageTableDef();

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
     * 流程定义id
     */
    public final QueryColumn SY_PDID = new QueryColumn(this, "sy_pdid");

    /**
     * 流程实例id
     */
    public final QueryColumn SY_PIID = new QueryColumn(this, "sy_piid");

    /**
     * 备注
     */
    public final QueryColumn REMARKS = new QueryColumn(this, "remarks");

    /**
     * 服务器ip
     */
    public final QueryColumn SERVER_IP = new QueryColumn(this, "server_ip");

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
     * 上架时间
     */
    public final QueryColumn ONLINE_TIME = new QueryColumn(this, "online_time");

    /**
     * 上架人
     */
    public final QueryColumn ONLINE_USER = new QueryColumn(this, "online_user");

    /**
     * 服务器名称
     */
    public final QueryColumn SERVER_NAME = new QueryColumn(this, "server_name");

    /**
     * 服务器端口
     */
    public final QueryColumn SERVER_PORT = new QueryColumn(this, "server_port");

    /**
     * 登记部门编码
     */
    public final QueryColumn SY_CREATEORG = new QueryColumn(this, "sy_createorg");

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
     * 主键id
     */
    public final QueryColumn TB_FRAMEWORK_MANAGE_ID = new QueryColumn(this, "tb_framework_manage_id");

    /**
     * 已建实例数
     */
    public final QueryColumn CREATE_INSTANCES_NUMBER = new QueryColumn(this, "create_instances_number");

    /**
     * 剩余实例数
     */
    public final QueryColumn SURPLUS_INSTANCES_NUMBER = new QueryColumn(this, "surplus_instances_number");

    /**
     * 推荐实例数
     */
    public final QueryColumn RECOMMENDED_INSTANCES_NUMBER = new QueryColumn(this, "recommended_instances_number");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_FRAMEWORK_MANAGE_ID, SERVER_NAME, SERVER_IP, SERVER_PORT, MEMORY, RECOMMENDED_INSTANCES_NUMBER, CREATE_INSTANCES_NUMBER, SURPLUS_INSTANCES_NUMBER, SCOPE, REMARKS, STATE, ONLINE_USER, ONLINE_TIME, SY_AUDFLAG, SY_CREATEORGID, SY_CREATEORG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSER, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID, SY_JTGSMC, SY_JTGSID};

    public TbFrameworkManageTableDef() {
        super("", "tb_framework_manage");
    }

    private TbFrameworkManageTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbFrameworkManageTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbFrameworkManageTableDef("", "tb_framework_manage", alias));
    }

}
