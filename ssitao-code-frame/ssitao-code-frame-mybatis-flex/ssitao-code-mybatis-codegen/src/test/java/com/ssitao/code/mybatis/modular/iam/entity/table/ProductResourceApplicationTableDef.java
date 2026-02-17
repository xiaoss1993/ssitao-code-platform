package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 产品资源申请 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class ProductResourceApplicationTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 产品资源申请
     */
    public static final ProductResourceApplicationTableDef PRODUCT_RESOURCE_APPLICATION = new ProductResourceApplicationTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 用途
     */
    public final QueryColumn PURPOSE = new QueryColumn(this, "purpose");

    /**
     * 修改人
     */
    public final QueryColumn MODIFIER = new QueryColumn(this, "modifier");

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
     * 申请人
     */
    public final QueryColumn APPLICANT = new QueryColumn(this, "applicant");

    /**
     * 使用时间
     */
    public final QueryColumn USAGE_TIME = new QueryColumn(this, "usage_time");

    /**
     * 产品名称
     */
    public final QueryColumn PRODUCT_NAME = new QueryColumn(this, "product_name");

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
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 紧急程度
     */
    public final QueryColumn DEGREE_URGENCY = new QueryColumn(this, "degree_urgency");

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
     * 堆内存
     */
    public final QueryColumn APPLICATION_DNC = new QueryColumn(this, "application_dnc");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 备注信息
     */
    public final QueryColumn APPLICATION_BZXX = new QueryColumn(this, "application_bzxx");

    /**
     * 代码分支
     */
    public final QueryColumn APPLICATION_DMFZ = new QueryColumn(this, "application_dmfz");

    /**
     * 反馈信息
     */
    public final QueryColumn APPLICATION_FKXX = new QueryColumn(this, "application_fkxx");

    /**
     * 申请时间
     */
    public final QueryColumn APPLICATION_TIME = new QueryColumn(this, "application_time");

    /**
     * 产品类型
     */
    public final QueryColumn APPLICATION_TYPE = new QueryColumn(this, "application_type");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 产品端口号
     */
    public final QueryColumn APPLICATION_CPDKH = new QueryColumn(this, "application_cpdkh");

    /**
     * 修改时间
     */
    public final QueryColumn MODIFICATION_TIME = new QueryColumn(this, "modification_time");

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
     * 是否邮件通知
     */
    public final QueryColumn EMAIL_NOTIFICATION = new QueryColumn(this, "email_notification");

    /**
     * 申请人电话
     */
    public final QueryColumn APPLICANT_TELEPHONE = new QueryColumn(this, "applicant_telephone");

    /**
     * jar构建路径
     */
    public final QueryColumn APPLICATION_JARGJLJ = new QueryColumn(this, "application_jargjlj");

    /**
     * 配置中心应用名
     */
    public final QueryColumn APPLICATION_PZZXYYM = new QueryColumn(this, "application_pzzxyym");

    /**
     * 预估用户数
     */
    public final QueryColumn ESTIMATED_NUMBER_USERS = new QueryColumn(this, "estimated_number_users");

    /**
     * 项目仓库地址
     */
    public final QueryColumn PROJECT_WAREHOUSE_ADDRESS = new QueryColumn(this, "project_warehouse_address");

    /**
     * 预估同时在线数
     */
    public final QueryColumn ESTIMATED_NUMBER_SIMULTANEOUS_ONLINE = new QueryColumn(this, "estimated_number_simultaneous_online");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PRODUCT_NAME, ESTIMATED_NUMBER_USERS, ESTIMATED_NUMBER_SIMULTANEOUS_ONLINE, DATABASE_TYPE, PURPOSE, USAGE_TIME, DEGREE_URGENCY, PROJECT_WAREHOUSE_ADDRESS, APPLICANT, APPLICATION_TIME, APPLICANT_TELEPHONE, APPLICANT_EMAIL, EMAIL_NOTIFICATION, PROCESSING_STATUS, APPLICATION_BZXX, APPLICATION_TYPE, APPLICATION_JARGJLJ, APPLICATION_DNC, APPLICATION_DMFZ, APPLICATION_CPDKH, APPLICATION_PZZXYYM, APPLICATION_FKXX, MODIFIER, MODIFICATION_TIME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_JTGSMC, SY_JTGSID, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public ProductResourceApplicationTableDef() {
        super("", "product_resource_application");
    }

    private ProductResourceApplicationTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public ProductResourceApplicationTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new ProductResourceApplicationTableDef("", "product_resource_application", alias));
    }

}
