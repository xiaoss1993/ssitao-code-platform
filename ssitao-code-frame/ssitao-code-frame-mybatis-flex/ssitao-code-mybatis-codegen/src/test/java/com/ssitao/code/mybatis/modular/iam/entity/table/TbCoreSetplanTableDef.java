package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 系统设置方案 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreSetplanTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 系统设置方案
     */
    public static final TbCoreSetplanTableDef TB_CORE_SETPLAN = new TbCoreSetplanTableDef();

    /**
     * 租户id（旧）
     */
    public final QueryColumn SY_ZHID = new QueryColumn(this, "sy_zhid");

    /**
     * 租户名称（旧）
     */
    public final QueryColumn SY_ZHMC = new QueryColumn(this, "sy_zhmc");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 浏览器显示图标（icon）
     */
    public final QueryColumn TB_SYS_ICON = new QueryColumn(this, "tb_sys_icon");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 系统访问地址（url）
     */
    public final QueryColumn TB_SYS_SYSURL = new QueryColumn(this, "tb_sys_sysurl");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户code
     */
    public final QueryColumn SY_TENANT_CODE = new QueryColumn(this, "sy_tenant_code");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 左侧信息图（img）
     */
    public final QueryColumn TB_SYS_LEFTIMG = new QueryColumn(this, "tb_sys_leftimg");

    /**
     * 系统名称（sysname）
     */
    public final QueryColumn TB_SYS_SYSNAME = new QueryColumn(this, "tb_sys_sysname");

    /**
     * 顶部菜单位置
     */
    public final QueryColumn TB_SYS_TOPMENU = new QueryColumn(this, "tb_sys_topmenu");

    /**
     * 系统顶部标题（logo）
     */
    public final QueryColumn TB_SYS_WEBLOGO = new QueryColumn(this, "tb_sys_weblogo");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 浏览页页面标题（title）
     */
    public final QueryColumn TB_SYS_WEBTITLE = new QueryColumn(this, "tb_sys_webtitle");

    /**
     * saas产品
     */
    public final QueryColumn SETPLAN_SAAS_PID = new QueryColumn(this, "setplan_saas_pid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 系统灰色模式
     */
    public final QueryColumn TB_SYS_GRAY_MODEL = new QueryColumn(this, "tb_sys_gray_model");

    /**
     * 系统标题图（logo）
     */
    public final QueryColumn TB_SYS_TITLELOGO = new QueryColumn(this, "tb_sys_titlelogo");

    /**
     * 是否默认方案
     */
    public final QueryColumn SETPLAN_IS_DEFULT = new QueryColumn(this, "setplan_is_defult");

    /**
     * 方案名称
     */
    public final QueryColumn SETPLAN_PLANNAME = new QueryColumn(this, "setplan_planname");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_SETPLAN_ID = new QueryColumn(this, "tb_core_setplan_id");

    /**
     * 系统默认色调（hue）
     */
    public final QueryColumn TB_SYS_DEFAULT_HUE = new QueryColumn(this, "tb_sys_default_hue");

    /**
     * 登录页高亮元素颜色
     */
    public final QueryColumn TB_SYS_LOGINCOLOR = new QueryColumn(this, "tb_sys_logincolor");

    /**
     * 路径方案
     */
    public final QueryColumn TB_SYS_PATHSCHEME = new QueryColumn(this, "tb_sys_pathscheme");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 登录页方式
     */
    public final QueryColumn TB_SYS_LOGINMETHOD = new QueryColumn(this, "tb_sys_loginmethod");

    /**
     * 备案号
     */
    public final QueryColumn TB_SYS_FILING_NUMBER = new QueryColumn(this, "tb_sys_filing_number");

    /**
     * 是否可用
     */
    public final QueryColumn SETPLAN_YESORNOUSED = new QueryColumn(this, "setplan_yesornoused");

    /**
     * 网页背景图（background）
     */
    public final QueryColumn TB_SYS_BACKGROUNDLOGO = new QueryColumn(this, "tb_sys_backgroundlogo");

    /**
     * 路径方案名称
     */
    public final QueryColumn TB_SYS_PATHSCHEMENAME = new QueryColumn(this, "tb_sys_pathschemename");

    /**
     * 是否个性化
     */
    public final QueryColumn SETPLAN_PRIVATE_ENABLE = new QueryColumn(this, "setplan_private_enable");

    /**
     * 系统默认菜单色调
     */
    public final QueryColumn TB_SYS_DEFAULT_MENUCOLOR = new QueryColumn(this, "tb_sys_default_menucolor");

    /**
     * 可使用产品
     */
    public final QueryColumn TB_SYS_AVAILABLEPRODUCTS = new QueryColumn(this, "tb_sys_availableproducts");

    /**
     * 关联顶部菜单id
     */
    public final QueryColumn SETPLAN_ASSOCIATE_TOP_MENU_ID = new QueryColumn(this, "setplan_associate_top_menu_id");

    /**
     * 是否关联所有顶部菜单
     */
    public final QueryColumn SETPLAN_ASSOCIATE_ALL_TOP_MENU = new QueryColumn(this, "setplan_associate_all_top_menu");

    /**
     * 可使用产品name
     */
    public final QueryColumn TB_SYS_AVAILABLEPRODUCTS_NAME = new QueryColumn(this, "tb_sys_availableproducts_name");

    /**
     * 关联顶部菜单名称
     */
    public final QueryColumn SETPLAN_ASSOCIATE_TOP_MENU_NAME = new QueryColumn(this, "setplan_associate_top_menu_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_SETPLAN_ID, SETPLAN_PLANNAME, SETPLAN_SAAS_PID, SETPLAN_PRIVATE_ENABLE, SETPLAN_ASSOCIATE_TOP_MENU_ID, SETPLAN_ASSOCIATE_TOP_MENU_NAME, SETPLAN_ASSOCIATE_ALL_TOP_MENU, SETPLAN_IS_DEFULT, SETPLAN_YESORNOUSED, TB_SYS_PATHSCHEME, TB_SYS_AVAILABLEPRODUCTS, TB_SYS_LOGINMETHOD, TB_SYS_LOGINCOLOR, TB_SYS_TITLELOGO, TB_SYS_BACKGROUNDLOGO, TB_SYS_LEFTIMG, TB_SYS_SYSNAME, TB_SYS_SYSURL, TB_SYS_ICON, TB_SYS_WEBTITLE, TB_SYS_WEBLOGO, TB_SYS_DEFAULT_HUE, TB_SYS_AVAILABLEPRODUCTS_NAME, TB_SYS_PATHSCHEMENAME, TB_SYS_TOPMENU, TB_SYS_GRAY_MODEL, TB_SYS_DEFAULT_MENUCOLOR, TB_SYS_FILING_NUMBER, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_ZHMC, SY_ZHID, SY_TENANT_ID, SY_TENANT_NAME, SY_TENANT_CODE};

    public TbCoreSetplanTableDef() {
        super("", "tb_core_setplan");
    }

    private TbCoreSetplanTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreSetplanTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreSetplanTableDef("", "tb_core_setplan", alias));
    }

}
