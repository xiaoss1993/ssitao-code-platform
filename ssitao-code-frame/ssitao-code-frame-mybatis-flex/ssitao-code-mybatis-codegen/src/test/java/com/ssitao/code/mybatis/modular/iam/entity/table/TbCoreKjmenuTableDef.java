package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 快捷菜单 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreKjmenuTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 快捷菜单
     */
    public static final TbCoreKjmenuTableDef TB_CORE_KJMENU = new TbCoreKjmenuTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 菜单id
     */
    public final QueryColumn KJMENU_CDID = new QueryColumn(this, "kjmenu_cdid");

    /**
     * 菜单类型
     */
    public final QueryColumn KJMENU_TYPE = new QueryColumn(this, "kjmenu_type");

    /**
     * 用户ud
     */
    public final QueryColumn KJMENU_YHUD = new QueryColumn(this, "kjmenu_yhud");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * appid
     */
    public final QueryColumn KJMENU_APPID = new QueryColumn(this, "kjmenu_appid");

    /**
     * 登记部门编码
     */
    public final QueryColumn SY_CREATEORG = new QueryColumn(this, "sy_createorg");

    /**
     * 所属产品_id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 登记人编码
     */
    public final QueryColumn SY_CREATEUSER = new QueryColumn(this, "sy_createuser");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 产品编码
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属产品_name
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    
    public final QueryColumn TB_CORE_KJMENU_ID = new QueryColumn(this, "tb_core_kjmenu_id");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_KJMENU_ID, KJMENU_CDID, KJMENU_YHUD, KJMENU_TYPE, KJMENU_APPID, SY_CREATEORGID, SY_CREATEORG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSER, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreKjmenuTableDef() {
        super("", "tb_core_kjmenu");
    }

    private TbCoreKjmenuTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreKjmenuTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreKjmenuTableDef("", "tb_core_kjmenu", alias));
    }

}
