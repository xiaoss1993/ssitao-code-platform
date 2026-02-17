package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 升级包安装明细 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbMetaUpgradelogTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 升级包安装明细
     */
    public static final TbMetaUpgradelogTableDef TB_META_UPGRADELOG = new TbMetaUpgradelogTableDef();

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
     * 主键id
     */
    public final QueryColumn TB_META_UPGRADELOG_ID = new QueryColumn(this, "tb_meta_upgradelog_id");

    /**
     * 资源类型_code
     */
    public final QueryColumn UPGRADELOG_TYPE_CODE = new QueryColumn(this, "upgradelog_type_code");

    /**
     * 资源类型_name
     */
    public final QueryColumn UPGRADELOG_TYPE_NAME = new QueryColumn(this, "upgradelog_type_name");

    /**
     * 内容_code
     */
    public final QueryColumn UPGRADELOG_CONTENT_CODE = new QueryColumn(this, "upgradelog_content_code");

    /**
     * 内容_name
     */
    public final QueryColumn UPGRADELOG_CONTENT_NAME = new QueryColumn(this, "upgradelog_content_name");

    /**
     * 安装主键_id
     */
    public final QueryColumn TB_META_UPGRADEINSTALL_ID = new QueryColumn(this, "tb_meta_upgradeinstall_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_META_UPGRADELOG_ID, TB_META_UPGRADEINSTALL_ID, UPGRADELOG_TYPE_CODE, UPGRADELOG_TYPE_NAME, UPGRADELOG_CONTENT_NAME, UPGRADELOG_CONTENT_CODE, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbMetaUpgradelogTableDef() {
        super("", "tb_meta_upgradelog");
    }

    private TbMetaUpgradelogTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbMetaUpgradelogTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbMetaUpgradelogTableDef("", "tb_meta_upgradelog", alias));
    }

}
