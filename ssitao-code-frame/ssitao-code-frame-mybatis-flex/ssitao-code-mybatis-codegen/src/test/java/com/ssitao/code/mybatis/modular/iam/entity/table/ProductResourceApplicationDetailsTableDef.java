package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 产品资源申请明细 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class ProductResourceApplicationDetailsTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 产品资源申请明细
     */
    public static final ProductResourceApplicationDetailsTableDef PRODUCT_RESOURCE_APPLICATION_DETAILS = new ProductResourceApplicationDetailsTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 作用域
     */
    public final QueryColumn SCOPE = new QueryColumn(this, "scope");

    /**
     * 内存
     */
    public final QueryColumn MEMORY = new QueryColumn(this, "memory");

    /**
     * 操作系统
     */
    public final QueryColumn SYSTEM = new QueryColumn(this, "system");

    /**
     * cpu核心数
     */
    public final QueryColumn CPU_CORES = new QueryColumn(this, "cpu_cores");

    /**
     * 硬盘
     */
    public final QueryColumn HARD_DISK = new QueryColumn(this, "hard_disk");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 作用域_name
     */
    public final QueryColumn SCOPE_NAME = new QueryColumn(this, "scope_name");

    /**
     * 服务器类型
     */
    public final QueryColumn SERVER_TYPE = new QueryColumn(this, "server_type");

    /**
     * 硬盘类型
     */
    public final QueryColumn HARD_DISK_TYPE = new QueryColumn(this, "hard_disk_type");

    /**
     * 服务器数量
     */
    public final QueryColumn SERVER_NUMBER = new QueryColumn(this, "server_number");

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
     * 服务器类型_name
     */
    public final QueryColumn SERVER_TYPE_NAME = new QueryColumn(this, "server_type_name");

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
     * 产品资源申请id
     */
    public final QueryColumn PRODUCT_RESOURCE_APPLICATION_ID = new QueryColumn(this, "product_resource_application_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, SCOPE, SERVER_TYPE, SERVER_NUMBER, CPU_CORES, MEMORY, HARD_DISK, HARD_DISK_TYPE, SYSTEM, PRODUCT_RESOURCE_APPLICATION_ID, SCOPE_NAME, SERVER_TYPE_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public ProductResourceApplicationDetailsTableDef() {
        super("", "product_resource_application_details");
    }

    private ProductResourceApplicationDetailsTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public ProductResourceApplicationDetailsTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new ProductResourceApplicationDetailsTableDef("", "product_resource_application_details", alias));
    }

}
