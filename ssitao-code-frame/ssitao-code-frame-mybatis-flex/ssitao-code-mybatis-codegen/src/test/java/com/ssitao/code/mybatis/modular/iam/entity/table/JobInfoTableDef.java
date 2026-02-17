package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 作业信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class JobInfoTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 作业信息
     */
    public static final JobInfoTableDef JOB_INFO = new JobInfoTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 作业名称
     */
    public final QueryColumn JOB_NAME = new QueryColumn(this, "job_name");

    /**
     * 代码类型
     */
    public final QueryColumn CODE_TYPE = new QueryColumn(this, "code_type");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 容器
     */
    public final QueryColumn CONTAINER = new QueryColumn(this, "container");

    /**
     * 产品id
     */
    public final QueryColumn PRODUCT_ID = new QueryColumn(this, "product_id");

    /**
     * 代码分支
     */
    public final QueryColumn CODE_BRANCH = new QueryColumn(this, "code_branch");

    /**
     * 代码地址
     */
    public final QueryColumn CODE_ADDRESS = new QueryColumn(this, "code_address");

    /**
     * jar上传路径
     */
    public final QueryColumn INFO_JARSCLJ = new QueryColumn(this, "info_jarsclj");

    /**
     * 产品编码
     */
    public final QueryColumn PRODUCT_CODE = new QueryColumn(this, "product_code");

    /**
     * 产品名称
     */
    public final QueryColumn PRODUCT_NAME = new QueryColumn(this, "product_name");

    /**
     * 凭证id
     */
    public final QueryColumn CREDENTIAL_ID = new QueryColumn(this, "credential_id");

    /**
     * 镜像版本
     */
    public final QueryColumn IMAGE_VERSION = new QueryColumn(this, "image_version");

    /**
     * 最后一次持续时间
     */
    public final QueryColumn LAST_DURATION = new QueryColumn(this, "last_duration");

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
     * 仓库地址
     */
    public final QueryColumn WAREHOUSE_HOST = new QueryColumn(this, "warehouse_host");

    /**
     * 仓库名字
     */
    public final QueryColumn WAREHOUSE_NAME = new QueryColumn(this, "warehouse_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 最后一次构建状态
     */
    public final QueryColumn LAST_BUILD_STATUS = new QueryColumn(this, "last_build_status");

    /**
     * 最后一次失败时间
     */
    public final QueryColumn LAST_FAILURE_TIME = new QueryColumn(this, "last_failure_time");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 打包命令
     */
    public final QueryColumn PACKAGING_COMMAND = new QueryColumn(this, "packaging_command");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 应用_id
     */
    public final QueryColumn INFO_APPLICATION_ID = new QueryColumn(this, "info_application_id");

    /**
     * 备注信息
     */
    public final QueryColumn REMARK_INFORMATION = new QueryColumn(this, "remark_information");

    /**
     * 最后一次成功时间
     */
    public final QueryColumn LAST_SUCCESSFUL_TIME = new QueryColumn(this, "last_successful_time");

    /**
     * 仓库命名空间
     */
    public final QueryColumn WAREHOUSE_NAMESPACE = new QueryColumn(this, "warehouse_namespace");

    /**
     * 仓库凭证id
     */
    public final QueryColumn WAREHOUSE_CREDENTIAL_ID = new QueryColumn(this, "warehouse_credential_id");

    /**
     * 仓库token
     */
    public final QueryColumn WAREHOUSE_WEBHOOK_TOKEN = new QueryColumn(this, "warehouse_webhook_token");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, JOB_NAME, CODE_TYPE, CODE_ADDRESS, CODE_BRANCH, PACKAGING_COMMAND, LAST_BUILD_STATUS, LAST_SUCCESSFUL_TIME, LAST_FAILURE_TIME, LAST_DURATION, PRODUCT_ID, PRODUCT_CODE, PRODUCT_NAME, CREDENTIAL_ID, REMARK_INFORMATION, WAREHOUSE_WEBHOOK_TOKEN, INFO_APPLICATION_ID, INFO_JARSCLJ, CONTAINER, WAREHOUSE_HOST, WAREHOUSE_NAMESPACE, WAREHOUSE_CREDENTIAL_ID, WAREHOUSE_NAME, IMAGE_VERSION, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public JobInfoTableDef() {
        super("", "job_info");
    }

    private JobInfoTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public JobInfoTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new JobInfoTableDef("", "job_info", alias));
    }

}
