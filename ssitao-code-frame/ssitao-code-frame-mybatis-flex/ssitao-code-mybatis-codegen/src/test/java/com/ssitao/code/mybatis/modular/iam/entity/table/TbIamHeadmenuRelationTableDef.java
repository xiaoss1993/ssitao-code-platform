package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 顶部菜单关联 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamHeadmenuRelationTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 顶部菜单关联
     */
    public static final TbIamHeadmenuRelationTableDef TB_IAM_HEADMENU_RELATION = new TbIamHeadmenuRelationTableDef();

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
     * 备份列
     */
    public final QueryColumn RELATION_BFL = new QueryColumn(this, "relation_bfl");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 所属产品_id
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
     * 所属产品_name
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
     * 顶部菜单_id
     */
    public final QueryColumn TB_IAM_HEADMENU_ID = new QueryColumn(this, "tb_iam_headmenu_id");

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
     * 菜单模块_id
     */
    public final QueryColumn RELATION_MENUMODULE_ID = new QueryColumn(this, "relation_menumodule_id");

    /**
     * 菜单模块_名称
     */
    public final QueryColumn RELATION_MENUMODULE_NAME = new QueryColumn(this, "relation_menumodule_name");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_HEADMENU_RELATION_ID = new QueryColumn(this, "tb_iam_headmenu_relation_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_HEADMENU_RELATION_ID, TB_IAM_HEADMENU_ID, RELATION_BFL, RELATION_MENUMODULE_NAME, RELATION_MENUMODULE_ID, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamHeadmenuRelationTableDef() {
        super("", "tb_iam_headmenu_relation");
    }

    private TbIamHeadmenuRelationTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamHeadmenuRelationTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamHeadmenuRelationTableDef("", "tb_iam_headmenu_relation", alias));
    }

}
