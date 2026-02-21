package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 应用资源 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbFrameworkApplicationResourceTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 应用资源
     */
    public static final TbFrameworkApplicationResourceTableDef TB_FRAMEWORK_APPLICATION_RESOURCE = new TbFrameworkApplicationResourceTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

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
     * 创建类型
     */
    public final QueryColumn BUILD_TYPE = new QueryColumn(this, "build_type");

    /**
     * 产品id
     */
    public final QueryColumn PRODUCT_ID = new QueryColumn(this, "product_id");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 应用日志目录
     */
    public final QueryColumn PRODUCT_LOG = new QueryColumn(this, "product_log");

    /**
     * 备注
     */
    public final QueryColumn RESOURCE_BZ = new QueryColumn(this, "resource_bz");

    /**
     * 产品编码
     */
    public final QueryColumn PRODUCT_CODE = new QueryColumn(this, "product_code");

    /**
     * 产品名称
     */
    public final QueryColumn PRODUCT_NAME = new QueryColumn(this, "product_name");

    /**
     * 应用端口号
     */
    public final QueryColumn PRODUCT_PORT = new QueryColumn(this, "product_port");

    /**
     * 持久化pvc
     */
    public final QueryColumn RESOURCE_PVC = new QueryColumn(this, "resource_pvc");

    /**
     * 登记部门编码
     */
    public final QueryColumn SY_CREATEORG = new QueryColumn(this, "sy_createorg");

    /**
     * jar构建路径
     */
    public final QueryColumn JAR_BUILD_PATH = new QueryColumn(this, "jar_build_path");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 登记人编码
     */
    public final QueryColumn SY_CREATEUSER = new QueryColumn(this, "sy_createuser");

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
     * 上架状态
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
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 仓库分支
     */
    public final QueryColumn WAREHOUSE_BRANCH = new QueryColumn(this, "warehouse_branch");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 使用堆内存
     */
    public final QueryColumn RESOURCE_USE_MEMORY = new QueryColumn(this, "resource_use_memory");

    /**
     * 仓库密码
     */
    public final QueryColumn WAREHOUSE_PASSWORD = new QueryColumn(this, "warehouse_password");

    /**
     * 仓库用户名
     */
    public final QueryColumn WAREHOUSE_USERNAME = new QueryColumn(this, "warehouse_username");

    /**
     * license持久化
     */
    public final QueryColumn RESOURCE_LICENSE_PVC = new QueryColumn(this, "resource_license_pvc");

    /**
     * 代码仓库地址
     */
    public final QueryColumn CODE_WAREHOUSE_ADDRESS = new QueryColumn(this, "code_warehouse_address");

    /**
     * license镜像
     */
    public final QueryColumn RESOURCE_LICENSE_IMAGE = new QueryColumn(this, "resource_license_image");

    /**
     * 服务运行状态
     */
    public final QueryColumn SERVICE_OPERATOR_STATUS = new QueryColumn(this, "service_operator_status");

    /**
     * 产品部署目录
     */
    public final QueryColumn PRODUCT_DEPLOY_DIRECTORY = new QueryColumn(this, "product_deploy_directory");

    /**
     * 数据库实例id
     */
    public final QueryColumn RESOURCE_DATABASE_INSTANCE_ID = new QueryColumn(this, "resource_database_instance_id");

    /**
     * 数据库
     */
    public final QueryColumn RESOURCE_DATABASE_INSTANCE_NAME = new QueryColumn(this, "resource_database_instance_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, RESOURCE_DATABASE_INSTANCE_ID, PRODUCT_DEPLOY_DIRECTORY, PRODUCT_PORT, PRODUCT_LOG, BUILD_TYPE, CODE_WAREHOUSE_ADDRESS, WAREHOUSE_USERNAME, WAREHOUSE_PASSWORD, WAREHOUSE_BRANCH, PRODUCT_ID, PRODUCT_CODE, PRODUCT_NAME, JAR_BUILD_PATH, RESOURCE_USABLE, RESOURCE_BZ, RESOURCE_USE_MEMORY, RESOURCE_DATABASE_INSTANCE_NAME, RESOURCE_LICENSE_PVC, RESOURCE_PVC, SERVICE_OPERATOR_STATUS, RESOURCE_LICENSE_IMAGE, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_AUDFLAG, SY_CREATEORGID, SY_CREATEORG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSER, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID, SY_JTGSMC, SY_JTGSID};

    public TbFrameworkApplicationResourceTableDef() {
        super("", "tb_framework_application_resource");
    }

    private TbFrameworkApplicationResourceTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbFrameworkApplicationResourceTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbFrameworkApplicationResourceTableDef("", "tb_framework_application_resource", alias));
    }

}
