package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 升级包安装管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbMetaUpgradeinstallTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 升级包安装管理
     */
    public static final TbMetaUpgradeinstallTableDef TB_META_UPGRADEINSTALL = new TbMetaUpgradeinstallTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 产品主键
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 产品code
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 产品名称
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
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 包名
     */
    public final QueryColumn UPGRADEINSTALL_NAME = new QueryColumn(this, "upgradeinstall_name");

    /**
     * 升级说明
     */
    public final QueryColumn UPGRADEINSTALL_REMARK = new QueryColumn(this, "upgradeinstall_remark");

    /**
     * 文件key
     */
    public final QueryColumn UPGRADEINSTALL_FILE_KEY = new QueryColumn(this, "upgradeinstall_file_key");

    /**
     * 版本
     */
    public final QueryColumn UPGRADEINSTALL_VERSION = new QueryColumn(this, "upgradeinstall_version");

    /**
     * 主键id
     */
    public final QueryColumn TB_META_UPGRADEINSTALL_ID = new QueryColumn(this, "tb_meta_upgradeinstall_id");

    /**
     * 升级包主键
     */
    public final QueryColumn TB_META_UPGRADEPACKAGE_ID = new QueryColumn(this, "tb_meta_upgradepackage_id");

    /**
     * 安装状态_code
     */
    public final QueryColumn UPGRADEINSTALL_INSTALL_CODE = new QueryColumn(this, "upgradeinstall_install_code");

    /**
     * 安装状态_name
     */
    public final QueryColumn UPGRADEINSTALL_INSTALL_NAME = new QueryColumn(this, "upgradeinstall_install_name");

    /**
     * 升级时间
     */
    public final QueryColumn UPGRADEINSTALL_INSTALL_TIME = new QueryColumn(this, "upgradeinstall_install_time");

    /**
     * 压缩包
     */
    public final QueryColumn UPGRADEINSTALL_PACKAGE_FILE = new QueryColumn(this, "upgradeinstall_package_file");

    /**
     * 打包时间
     */
    public final QueryColumn UPGRADEINSTALL_PACKAGE_TIME = new QueryColumn(this, "upgradeinstall_package_time");

    /**
     * 是否平台_code
     */
    public final QueryColumn UPGRADEINSTALL_PLATFORM_CODE = new QueryColumn(this, "upgradeinstall_platform_code");

    /**
     * 是否平台_name
     */
    public final QueryColumn UPGRADEINSTALL_PLATFORM_NAME = new QueryColumn(this, "upgradeinstall_platform_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_META_UPGRADEINSTALL_ID, TB_META_UPGRADEPACKAGE_ID, UPGRADEINSTALL_NAME, UPGRADEINSTALL_VERSION, UPGRADEINSTALL_PACKAGE_FILE, UPGRADEINSTALL_REMARK, UPGRADEINSTALL_INSTALL_TIME, UPGRADEINSTALL_PLATFORM_CODE, UPGRADEINSTALL_PLATFORM_NAME, UPGRADEINSTALL_PACKAGE_TIME, UPGRADEINSTALL_INSTALL_CODE, UPGRADEINSTALL_INSTALL_NAME, UPGRADEINSTALL_FILE_KEY, SY_PRODUCT_CODE, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME, SY_PRODUCT_NAME, SY_PRODUCT_ID};

    public TbMetaUpgradeinstallTableDef() {
        super("", "tb_meta_upgradeinstall");
    }

    private TbMetaUpgradeinstallTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbMetaUpgradeinstallTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbMetaUpgradeinstallTableDef("", "tb_meta_upgradeinstall", alias));
    }

}
