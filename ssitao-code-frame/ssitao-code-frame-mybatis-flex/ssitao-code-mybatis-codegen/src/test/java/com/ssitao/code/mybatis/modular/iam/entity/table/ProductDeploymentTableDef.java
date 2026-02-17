package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 产品部署管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class ProductDeploymentTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 产品部署管理
     */
    public static final ProductDeploymentTableDef PRODUCT_DEPLOYMENT = new ProductDeploymentTableDef();

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
     * 是否异常
     */
    public final QueryColumn ABNORMAL = new QueryColumn(this, "abnormal");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 申请人
     */
    public final QueryColumn APPLICANT = new QueryColumn(this, "applicant");

    /**
     * 产品id
     */
    public final QueryColumn PRODUCT_ID = new QueryColumn(this, "product_id");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 测试负责人
     */
    public final QueryColumn TEST_LEADER = new QueryColumn(this, "test_leader");

    /**
     * 产品编码
     */
    public final QueryColumn PRODUCT_CODE = new QueryColumn(this, "product_code");

    /**
     * 产品名称
     */
    public final QueryColumn PRODUCT_NAME = new QueryColumn(this, "product_name");

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
     * 紧急程度
     */
    public final QueryColumn DEGREE_URGENCY = new QueryColumn(this, "degree_urgency");

    /**
     * 堆内存
     */
    public final QueryColumn DEPLOYMENT_DNC = new QueryColumn(this, "deployment_dnc");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 申请人邮箱
     */
    public final QueryColumn APPLICANT_EMAIL = new QueryColumn(this, "applicant_email");

    /**
     * 申请人电话
     */
    public final QueryColumn APPLICANT_PHONE = new QueryColumn(this, "applicant_phone");

    /**
     * 代码分支
     */
    public final QueryColumn DEPLOYMENT_DMFZ = new QueryColumn(this, "deployment_dmfz");

    /**
     * 反馈信息
     */
    public final QueryColumn DEPLOYMENT_FKXX = new QueryColumn(this, "deployment_fkxx");

    /**
     * 产品类型
     */
    public final QueryColumn DEPLOYMENT_TYPE = new QueryColumn(this, "deployment_type");

    /**
     * 影响范围
     */
    public final QueryColumn SCOPE_INFLUENCE = new QueryColumn(this, "scope_influence");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 升级概要
     */
    public final QueryColumn UPGRADE_SUMMARY = new QueryColumn(this, "upgrade_summary");

    /**
     * 申请时间
     */
    public final QueryColumn APPLICATION_TIME = new QueryColumn(this, "application_time");

    /**
     * 产品端口号
     */
    public final QueryColumn DEPLOYMENT_CPDKH = new QueryColumn(this, "deployment_cpdkh");

    /**
     * 要求结束时间
     */
    public final QueryColumn REQUIRED_END_TIME = new QueryColumn(this, "required_end_time");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 处理状态
     */
    public final QueryColumn PROCESSING_STATUS = new QueryColumn(this, "processing_status");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * jar构建路径
     */
    public final QueryColumn DEPLOYMENT_JARGJLJ = new QueryColumn(this, "deployment_jargjlj");

    /**
     * 配置中心应用名
     */
    public final QueryColumn DEPLOYMENT_PZZXYYM = new QueryColumn(this, "deployment_pzzxyym");

    /**
     * 备注信息
     */
    public final QueryColumn REMARK_INFORMATION = new QueryColumn(this, "remark_information");

    /**
     * 要求开始时间
     */
    public final QueryColumn REQUIRED_START_TIME = new QueryColumn(this, "required_start_time");

    /**
     * 功能负责人
     */
    public final QueryColumn FUNCTIONAL_DIRECTOR = new QueryColumn(this, "functional_director");

    /**
     * 升级信息
     */
    public final QueryColumn UPGRADE_INFORMATION = new QueryColumn(this, "upgrade_information");

    /**
     * 异常信息
     */
    public final QueryColumn ABNORMAL_INFORMATION = new QueryColumn(this, "abnormal_information");

    /**
     * 测试负责人邮箱
     */
    public final QueryColumn EMAIL_ADDRESS_TEST_PRINCIPAL = new QueryColumn(this, "email_address_test_principal");

    /**
     * 功能负责人邮箱
     */
    public final QueryColumn EMAIL_ADDRESS_FUNCTION_LEADER = new QueryColumn(this, "email_address_function_leader");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PRODUCT_NAME, PRODUCT_CODE, PRODUCT_ID, DEGREE_URGENCY, REQUIRED_START_TIME, REQUIRED_END_TIME, APPLICANT, APPLICATION_TIME, APPLICANT_EMAIL, TEST_LEADER, EMAIL_ADDRESS_TEST_PRINCIPAL, FUNCTIONAL_DIRECTOR, EMAIL_ADDRESS_FUNCTION_LEADER, PROCESSING_STATUS, ABNORMAL, UPGRADE_SUMMARY, UPGRADE_INFORMATION, SCOPE_INFLUENCE, ABNORMAL_INFORMATION, REMARK_INFORMATION, APPLICANT_PHONE, DEPLOYMENT_TYPE, DEPLOYMENT_FKXX, DEPLOYMENT_PZZXYYM, DEPLOYMENT_CPDKH, DEPLOYMENT_DNC, DEPLOYMENT_DMFZ, DEPLOYMENT_JARGJLJ, SY_AUDFLAG, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public ProductDeploymentTableDef() {
        super("", "product_deployment");
    }

    private ProductDeploymentTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public ProductDeploymentTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new ProductDeploymentTableDef("", "product_deployment", alias));
    }

}
