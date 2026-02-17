package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 产品应用服务器管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbProductServerTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 产品应用服务器管理
     */
    public static final TbProductServerTableDef TB_PRODUCT_SERVER = new TbProductServerTableDef();

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
     * 服务器名
     */
    public final QueryColumn SERVER_NAME = new QueryColumn(this, "server_name");

    /**
     * 登记部门编码
     */
    public final QueryColumn SY_CREATEORG = new QueryColumn(this, "sy_createorg");

    /**
     * 所属产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 内存
     */
    public final QueryColumn SERVER_MEMORY = new QueryColumn(this, "server_memory");

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
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 架构服务器主键
     */
    public final QueryColumn FRAMEWORK_SERVER_ID = new QueryColumn(this, "framework_server_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_PRODUCT_SERVER_ID = new QueryColumn(this, "tb_product_server_id");

    /**
     * 实例占用内存
     */
    public final QueryColumn SERVER_INSTANCE_MEMORY = new QueryColumn(this, "server_instance_memory");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_PRODUCT_SERVER_ID, SERVER_NAME, SERVER_IP, SERVER_MEMORY, SERVER_INSTANCE_MEMORY, REMARKS, FRAMEWORK_SERVER_ID, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_AUDFLAG, SY_CREATEORGID, SY_CREATEORG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSER, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID, SY_JTGSMC, SY_JTGSID};

    public TbProductServerTableDef() {
        super("", "tb_product_server");
    }

    private TbProductServerTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbProductServerTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbProductServerTableDef("", "tb_product_server", alias));
    }

}
