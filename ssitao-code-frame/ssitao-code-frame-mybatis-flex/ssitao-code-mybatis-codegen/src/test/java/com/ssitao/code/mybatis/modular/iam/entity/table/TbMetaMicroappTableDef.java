package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 微应用管理 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbMetaMicroappTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 微应用管理
     */
    public static final TbMetaMicroappTableDef TB_META_MICROAPP = new TbMetaMicroappTableDef();

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
     * 应用编码
     */
    public final QueryColumn MICROAPP_CODE = new QueryColumn(this, "microapp_code");

    /**
     * 应用名称
     */
    public final QueryColumn MICROAPP_NAME = new QueryColumn(this, "microapp_name");

    /**
     * 平台应用
     */
    public final QueryColumn MICROAPP_PTYY = new QueryColumn(this, "microapp_ptyy");

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
     * 应用入口
     */
    public final QueryColumn MICROAPP_ENTRY = new QueryColumn(this, "microapp_entry");

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
     * 所属产品_code
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属产品_name
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 备注
     */
    public final QueryColumn MICROAPP_REMARK = new QueryColumn(this, "microapp_remark");

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
     * 主键id
     */
    public final QueryColumn TB_META_MICROAPP_ID = new QueryColumn(this, "tb_meta_microapp_id");

    /**
     * 激活方式
     */
    public final QueryColumn MICROAPP_ACTIVETYPE = new QueryColumn(this, "microapp_activetype");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 应用激活路由
     */
    public final QueryColumn MICROAPP_ACTIVEROUTE = new QueryColumn(this, "microapp_activeroute");

    /**
     * 激活菜单类型
     */
    public final QueryColumn MICROAPP_MENUTYPE_CODE = new QueryColumn(this, "microapp_menutype_code");

    /**
     * 激活菜单类型_name
     */
    public final QueryColumn MICROAPP_MENUTYPE_NAME = new QueryColumn(this, "microapp_menutype_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_META_MICROAPP_ID, MICROAPP_CODE, MICROAPP_NAME, MICROAPP_ENTRY, MICROAPP_ACTIVEROUTE, MICROAPP_ACTIVETYPE, MICROAPP_REMARK, MICROAPP_PTYY, MICROAPP_MENUTYPE_NAME, MICROAPP_MENUTYPE_CODE, SY_PRODUCT_ID, SY_PRODUCT_CODE, SY_PRODUCT_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public TbMetaMicroappTableDef() {
        super("", "tb_meta_microapp");
    }

    private TbMetaMicroappTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbMetaMicroappTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbMetaMicroappTableDef("", "tb_meta_microapp", alias));
    }

}
