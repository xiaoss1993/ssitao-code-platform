package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 用户菜单历史 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamMenuhistoryTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 用户菜单历史
     */
    public static final TbIamMenuhistoryTableDef TB_IAM_MENUHISTORY = new TbIamMenuhistoryTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

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
     * 菜单主键_id
     */
    public final QueryColumn TB_CORE_MENU_ID = new QueryColumn(this, "tb_core_menu_id");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 产品编码
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属产品名称
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 操作时间
     */
    public final QueryColumn MENUHISTORY_CZSJ = new QueryColumn(this, "menuhistory_czsj");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * appid
     */
    public final QueryColumn MENUHISTORY_APPID = new QueryColumn(this, "menuhistory_appid");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属用户_id
     */
    public final QueryColumn MENUHISTORY_USER_ID = new QueryColumn(this, "menuhistory_user_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_MENUHISTORY_ID = new QueryColumn(this, "tb_iam_menuhistory_id");

    /**
     * 所属用户名称
     */
    public final QueryColumn MENUHISTORY_USER_NAME = new QueryColumn(this, "menuhistory_user_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_MENU_ID, TB_IAM_MENUHISTORY_ID, MENUHISTORY_USER_ID, MENUHISTORY_USER_NAME, MENUHISTORY_CZSJ, MENUHISTORY_APPID, SY_CREATEORGID, SY_CREATEORG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSER, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamMenuhistoryTableDef() {
        super("", "tb_iam_menuhistory");
    }

    private TbIamMenuhistoryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamMenuhistoryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamMenuhistoryTableDef("", "tb_iam_menuhistory", alias));
    }

}
