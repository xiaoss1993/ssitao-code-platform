package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 顶部菜单 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamHeadmenuTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 顶部菜单
     */
    public static final TbIamHeadmenuTableDef TB_IAM_HEADMENU = new TbIamHeadmenuTableDef();

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
     * 所属产品_id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 菜单编码
     */
    public final QueryColumn HEADMENU_CODE = new QueryColumn(this, "headmenu_code");

    /**
     * 图标
     */
    public final QueryColumn HEADMENU_ICON = new QueryColumn(this, "headmenu_icon");

    /**
     * 菜单名称
     */
    public final QueryColumn HEADMENU_NAME = new QueryColumn(this, "headmenu_name");

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
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 所属产品_name
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 备注信息
     */
    public final QueryColumn HEADMENU_REMARK = new QueryColumn(this, "headmenu_remark");

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
     * 主键id
     */
    public final QueryColumn TB_IAM_HEADMENU_ID = new QueryColumn(this, "tb_iam_headmenu_id");

    /**
     * 关联门户_id
     */
    public final QueryColumn HEADMENU_PARTOL_ID = new QueryColumn(this, "headmenu_partol_id");

    /**
     * 可见机构_id
     */
    public final QueryColumn HEADMENU_SEEORG_ID = new QueryColumn(this, "headmenu_seeorg_id");

    /**
     * 类型_code
     */
    public final QueryColumn HEADMENU_TYPE_CODE = new QueryColumn(this, "headmenu_type_code");

    /**
     * 类型_name
     */
    public final QueryColumn HEADMENU_TYPE_NAME = new QueryColumn(this, "headmenu_type_name");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 可见部门_id
     */
    public final QueryColumn HEADMENU_SEEDEPT_ID = new QueryColumn(this, "headmenu_seedept_id");

    /**
     * 可见角色_id
     */
    public final QueryColumn HEADMENU_SEEROLE_ID = new QueryColumn(this, "headmenu_seerole_id");

    /**
     * 可见人_id
     */
    public final QueryColumn HEADMENU_SEEUSER_ID = new QueryColumn(this, "headmenu_seeuser_id");

    /**
     * 配置信息
     */
    public final QueryColumn HEADMENU_CONFIGINFO = new QueryColumn(this, "headmenu_configinfo");

    /**
     * 初始菜单_id
     */
    public final QueryColumn HEADMENU_INITMENU_ID = new QueryColumn(this, "headmenu_initmenu_id");

    /**
     * 关联门户_name
     */
    public final QueryColumn HEADMENU_PARTOL_NAME = new QueryColumn(this, "headmenu_partol_name");

    /**
     * 是否公用_code
     */
    public final QueryColumn HEADMENU_PUBLIC_CODE = new QueryColumn(this, "headmenu_public_code");

    /**
     * 是否公用_name
     */
    public final QueryColumn HEADMENU_PUBLIC_NAME = new QueryColumn(this, "headmenu_public_name");

    /**
     * 可见机构_name
     */
    public final QueryColumn HEADMENU_SEEORG_NAME = new QueryColumn(this, "headmenu_seeorg_name");

    /**
     * 系统logo
     */
    public final QueryColumn HEADMENU_SYSTEM_LOGO = new QueryColumn(this, "headmenu_system_logo");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 功能展示方式_code
     */
    public final QueryColumn HEADMENU_DISPALY_CODE = new QueryColumn(this, "headmenu_dispaly_code");

    /**
     * 功能展示方式_name
     */
    public final QueryColumn HEADMENU_DISPALY_NAME = new QueryColumn(this, "headmenu_dispaly_name");

    /**
     * 可见部门_name
     */
    public final QueryColumn HEADMENU_SEEDEPT_NAME = new QueryColumn(this, "headmenu_seedept_name");

    /**
     * 可见角色_name
     */
    public final QueryColumn HEADMENU_SEEROLE_NAME = new QueryColumn(this, "headmenu_seerole_name");

    /**
     * 可见人_name
     */
    public final QueryColumn HEADMENU_SEEUSER_NAME = new QueryColumn(this, "headmenu_seeuser_name");

    /**
     * 初始菜单_name
     */
    public final QueryColumn HEADMENU_INITMENU_NAME = new QueryColumn(this, "headmenu_initmenu_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_HEADMENU_ID, HEADMENU_NAME, HEADMENU_CODE, HEADMENU_TYPE_CODE, HEADMENU_TYPE_NAME, HEADMENU_ICON, HEADMENU_PARTOL_ID, HEADMENU_PARTOL_NAME, HEADMENU_SYSTEM_LOGO, HEADMENU_REMARK, HEADMENU_PUBLIC_CODE, HEADMENU_PUBLIC_NAME, HEADMENU_SEEROLE_ID, HEADMENU_SEEROLE_NAME, HEADMENU_SEEDEPT_ID, HEADMENU_SEEDEPT_NAME, HEADMENU_SEEORG_ID, HEADMENU_SEEORG_NAME, HEADMENU_SEEUSER_ID, HEADMENU_SEEUSER_NAME, HEADMENU_CONFIGINFO, HEADMENU_DISPALY_CODE, HEADMENU_DISPALY_NAME, HEADMENU_INITMENU_ID, HEADMENU_INITMENU_NAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME, SY_PRODUCT_ID, SY_PRODUCT_NAME};

    public TbIamHeadmenuTableDef() {
        super("", "tb_iam_headmenu");
    }

    private TbIamHeadmenuTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamHeadmenuTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamHeadmenuTableDef("", "tb_iam_headmenu", alias));
    }

}
