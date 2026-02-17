package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 产品升级包管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbMetaUpgradepackageTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 产品升级包管理
     */
    public static final TbMetaUpgradepackageTableDef TB_META_UPGRADEPACKAGE = new TbMetaUpgradepackageTableDef();

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
    public final QueryColumn UPGRADEPACKAGE_NAME = new QueryColumn(this, "upgradepackage_name");

    /**
     * 升级说明
     */
    public final QueryColumn UPGRADEPACKAGE_REMARK = new QueryColumn(this, "upgradepackage_remark");

    /**
     * 版本
     */
    public final QueryColumn UPGRADEPACKAGE_VERSION = new QueryColumn(this, "upgradepackage_version");

    /**
     * 主键id
     */
    public final QueryColumn TB_META_UPGRADEPACKAGE_ID = new QueryColumn(this, "tb_meta_upgradepackage_id");

    /**
     * 已打包_code
     */
    public final QueryColumn UPGRADEPACKAGE_PACKAGE_CODE = new QueryColumn(this, "upgradepackage_package_code");

    /**
     * 压缩包
     */
    public final QueryColumn UPGRADEPACKAGE_PACKAGE_FILE = new QueryColumn(this, "upgradepackage_package_file");

    /**
     * 已打包_name
     */
    public final QueryColumn UPGRADEPACKAGE_PACKAGE_NAME = new QueryColumn(this, "upgradepackage_package_name");

    /**
     * 打包时间
     */
    public final QueryColumn UPGRADEPACKAGE_PACKAGE_TIME = new QueryColumn(this, "upgradepackage_package_time");

    /**
     * 升级包类型_code
     */
    public final QueryColumn UPGRADEPACKAGE_PLATFORM_CODE = new QueryColumn(this, "upgradepackage_platform_code");

    /**
     * 升级包类型_name
     */
    public final QueryColumn UPGRADEPACKAGE_PLATFORM_NAME = new QueryColumn(this, "upgradepackage_platform_name");

    /**
     * 打包人_id
     */
    public final QueryColumn UPGRADEPACKAGE_PACKAGEUSER_ID = new QueryColumn(this, "upgradepackage_packageuser_id");

    /**
     * 打包人_name
     */
    public final QueryColumn UPGRADEPACKAGE_PACKAGEUSER_NAME = new QueryColumn(this, "upgradepackage_packageuser_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_META_UPGRADEPACKAGE_ID, UPGRADEPACKAGE_NAME, UPGRADEPACKAGE_VERSION, UPGRADEPACKAGE_PACKAGE_CODE, UPGRADEPACKAGE_PACKAGE_NAME, UPGRADEPACKAGE_PACKAGE_FILE, UPGRADEPACKAGE_PACKAGE_TIME, UPGRADEPACKAGE_PLATFORM_CODE, UPGRADEPACKAGE_PLATFORM_NAME, UPGRADEPACKAGE_REMARK, UPGRADEPACKAGE_PACKAGEUSER_ID, UPGRADEPACKAGE_PACKAGEUSER_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME, SY_PRODUCT_NAME, SY_PRODUCT_ID};

    public TbMetaUpgradepackageTableDef() {
        super("", "tb_meta_upgradepackage");
    }

    private TbMetaUpgradepackageTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbMetaUpgradepackageTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbMetaUpgradepackageTableDef("", "tb_meta_upgradepackage", alias));
    }

}
