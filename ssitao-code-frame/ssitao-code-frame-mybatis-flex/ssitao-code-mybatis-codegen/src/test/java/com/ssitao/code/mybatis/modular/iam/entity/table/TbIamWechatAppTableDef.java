package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 企微应用管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamWechatAppTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 企微应用管理
     */
    public static final TbIamWechatAppTableDef TB_IAM_WECHAT_APP = new TbIamWechatAppTableDef();

    /**
     * 应用id
     */
    public final QueryColumn APP_ID = new QueryColumn(this, "app_id");

    /**
     * 应用名称
     */
    public final QueryColumn APP_NAME = new QueryColumn(this, "app_name");

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 菜单_id
     */
    public final QueryColumn APP_MENU_ID = new QueryColumn(this, "app_menu_id");

    /**
     * 应用描述
     */
    public final QueryColumn APP_REMARK = new QueryColumn(this, "app_remark");

    /**
     * 应用秘钥
     */
    public final QueryColumn APP_SECRET = new QueryColumn(this, "app_secret");

    /**
     * h5入口地址
     */
    public final QueryColumn APP_H5_INDEX = new QueryColumn(this, "app_h5_index");

    /**
     * h5登录地址
     */
    public final QueryColumn APP_H5_LOGIN = new QueryColumn(this, "app_h5_login");

    /**
     * pc入口地址
     */
    public final QueryColumn APP_PC_INDEX = new QueryColumn(this, "app_pc_index");

    /**
     * pc登录地址
     */
    public final QueryColumn APP_PC_LOGIN = new QueryColumn(this, "app_pc_login");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * h5入口参数
     */
    public final QueryColumn APP_H5_PARAMS = new QueryColumn(this, "app_h5_params");

    /**
     * 菜单_name
     */
    public final QueryColumn APP_MENU_NAME = new QueryColumn(this, "app_menu_name");

    /**
     * pc入口参数
     */
    public final QueryColumn APP_PC_PARAMS = new QueryColumn(this, "app_pc_params");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 移动端菜单_id
     */
    public final QueryColumn APP_APPMENU_ID = new QueryColumn(this, "app_appmenu_id");

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
     * 移动端菜单_name
     */
    public final QueryColumn APP_APPMENU_NAME = new QueryColumn(this, "app_appmenu_name");

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
    public final QueryColumn TB_IAM_WECHAT_APP_ID = new QueryColumn(this, "tb_iam_wechat_app_id");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_WECHAT_APP_ID, APP_NAME, APP_ID, APP_SECRET, APP_PC_INDEX, APP_H5_INDEX, APP_PC_LOGIN, APP_H5_LOGIN, APP_REMARK, APP_PC_PARAMS, APP_H5_PARAMS, APP_MENU_ID, APP_MENU_NAME, APP_APPMENU_ID, APP_APPMENU_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamWechatAppTableDef() {
        super("", "tb_iam_wechat_app");
    }

    private TbIamWechatAppTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamWechatAppTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamWechatAppTableDef("", "tb_iam_wechat_app", alias));
    }

}
