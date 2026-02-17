package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 菜单信息 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreMenuTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单信息
     */
    public static final TbCoreMenuTableDef TB_CORE_MENU = new TbCoreMenuTableDef();

    /**
     * 是否启用本条数据
     */
    public final QueryColumn SY_FLAG = new QueryColumn(this, "sy_flag");

    /**
     * 树形路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

    /**
     * 流程定义id
     */
    public final QueryColumn SY_PDID = new QueryColumn(this, "sy_pdid");

    /**
     * 流程实例id
     */
    public final QueryColumn SY_PIID = new QueryColumn(this, "sy_piid");

    /**
     * 拼音简写
     */
    public final QueryColumn SY_PYJZ = new QueryColumn(this, "sy_pyjz");

    /**
     * 拼音全称
     */
    public final QueryColumn SY_PYQC = new QueryColumn(this, "sy_pyqc");

    /**
     * je系统
     */
    public final QueryColumn SY_JESYS = new QueryColumn(this, "sy_jesys");

    /**
     * 层次
     */
    public final QueryColumn SY_LAYER = new QueryColumn(this, "sy_layer");

    /**
     * 节点编码
     */
    public final QueryColumn MENU_CODE = new QueryColumn(this, "menu_code");

    /**
     * 功能说明
     */
    public final QueryColumn MENU_HELP = new QueryColumn(this, "menu_help");

    /**
     * 图标
     */
    public final QueryColumn MENU_ICON = new QueryColumn(this, "menu_icon");

    /**
     * je核心
     */
    public final QueryColumn SY_JECORE = new QueryColumn(this, "sy_jecore");

    /**
     * 父节点id
     */
    public final QueryColumn SY_PARENT = new QueryColumn(this, "sy_parent");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 系统
     */
    public final QueryColumn SY_SYSTEM = new QueryColumn(this, "sy_system");

    /**
     * 数字提醒
     */
    public final QueryColumn MENU_BADGE = new QueryColumn(this, "menu_badge");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 功能展示方式
     */
    public final QueryColumn MENU_LAYOUT = new QueryColumn(this, "menu_layout");

    /**
     * 流程菜单别名
     */
    public final QueryColumn MENU_WFNAME = new QueryColumn(this, "menu_wfname");

    /**
     * 节点类型
     */
    public final QueryColumn SY_NODETYPE = new QueryColumn(this, "sy_nodetype");

    /**
     * 背景颜色
     */
    public final QueryColumn MENU_BGCOLOR = new QueryColumn(this, "menu_bgcolor");

    /**
     * 是否启用
     */
    public final QueryColumn MENU_ENABLED = new QueryColumn(this, "menu_enabled");

    /**
     * 激活刷新
     */
    public final QueryColumn MENU_REFRESH = new QueryColumn(this, "menu_refresh");

    /**
     * 服务名称
     */
    public final QueryColumn MENU_SERVICE = new QueryColumn(this, "menu_service");

    /**
     * 登记者所在部门编码
     */
    public final QueryColumn SY_CREATEORG = new QueryColumn(this, "sy_createorg");

    /**
     * 修改人部门编码
     */
    public final QueryColumn SY_MODIFYORG = new QueryColumn(this, "sy_modifyorg");

    /**
     * 产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 数字样式
     */
    public final QueryColumn MENU_BADGECLS = new QueryColumn(this, "menu_badgecls");

    /**
     * 功能类型
     */
    public final QueryColumn MENU_FUNCTYPE = new QueryColumn(this, "menu_functype");

    /**
     * 节点名称
     */
    public final QueryColumn MENU_MENUNAME = new QueryColumn(this, "menu_menuname");

    /**
     * 节点信息
     */
    public final QueryColumn MENU_NODEINFO = new QueryColumn(this, "menu_nodeinfo");

    /**
     * 树形路径
     */
    public final QueryColumn MENU_TREEPATH = new QueryColumn(this, "menu_treepath");

    /**
     * 流程模块
     */
    public final QueryColumn MENU_WFMODULE = new QueryColumn(this, "menu_wfmodule");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 登记人编码
     */
    public final QueryColumn SY_CREATEUSER = new QueryColumn(this, "sy_createuser");

    /**
     * 修改时间
     */
    public final QueryColumn SY_MODIFYTIME = new QueryColumn(this, "sy_modifytime");

    /**
     * 修改人编码
     */
    public final QueryColumn SY_MODIFYUSER = new QueryColumn(this, "sy_modifyuser");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 父节点路径
     */
    public final QueryColumn SY_PARENTPATH = new QueryColumn(this, "sy_parentpath");

    
    public final QueryColumn TB_CORE_MENU_ID = new QueryColumn(this, "tb_core_menu_id");

    /**
     * 来源内容
     */
    public final QueryColumn MENU_BADGEINFO = new QueryColumn(this, "menu_badgeinfo");

    /**
     * 更新条件
     */
    public final QueryColumn MENU_BADGELOAD = new QueryColumn(this, "menu_badgeload");

    /**
     * 数字来源
     */
    public final QueryColumn MENU_BADGETYPE = new QueryColumn(this, "menu_badgetype");

    /**
     * 大按钮菜单
     */
    public final QueryColumn MENU_BIGBUTTON = new QueryColumn(this, "menu_bigbutton");

    /**
     * 图标颜色
     */
    public final QueryColumn MENU_ICONCOLOR = new QueryColumn(this, "menu_iconcolor");

    /**
     * 是否桌面图标
     */
    public final QueryColumn MENU_ISDESKTOP = new QueryColumn(this, "menu_isdesktop");

    /**
     * 产品code
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 产品name
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 数字颜色
     */
    public final QueryColumn MENU_BADGECOLOR = new QueryColumn(this, "menu_badgecolor");

    /**
     * 一级模块
     */
    public final QueryColumn MENU_MODULECODE = new QueryColumn(this, "menu_modulecode");

    /**
     * 是否快速启动菜单
     */
    public final QueryColumn MENU_QUICKSTART = new QueryColumn(this, "menu_quickstart");

    /**
     * 英文标题
     */
    public final QueryColumn MENU_MENUSUBNAME = new QueryColumn(this, "menu_menusubname");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 数字背景
     */
    public final QueryColumn MENU_BADGEBGCOLOR = new QueryColumn(this, "menu_badgebgcolor");

    /**
     * 加和菜单
     */
    public final QueryColumn MENU_BADGEMENUIDS = new QueryColumn(this, "menu_badgemenuids");

    /**
     * 菜单导航
     */
    public final QueryColumn MENU_HIDELOCATION = new QueryColumn(this, "menu_hidelocation");

    /**
     * 节点信息类型
     */
    public final QueryColumn MENU_NODEINFOTYPE = new QueryColumn(this, "menu_nodeinfotype");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 树形排序字段
     */
    public final QueryColumn SY_TREEORDERINDEX = new QueryColumn(this, "sy_treeorderindex");

    /**
     * 关联顶部菜单id
     */
    public final QueryColumn MENU_LINK_TOPMENU_ID = new QueryColumn(this, "menu_link_topmenu_id");

    /**
     * 加载第一条数据
     */
    public final QueryColumn MENU_LOAD_FIRST_DATA = new QueryColumn(this, "menu_load_first_data");

    /**
     * 表单上传虚字段
     */
    public final QueryColumn SY_FORMUPLOADFILES = new QueryColumn(this, "sy_formuploadfiles");

    /**
     * 加和菜单text
     */
    public final QueryColumn MENU_BADGEMENUTEXTS = new QueryColumn(this, "menu_badgemenutexts");

    /**
     * 关联顶部菜单name
     */
    public final QueryColumn MENU_LINK_TOPMENU_NAME = new QueryColumn(this, "menu_link_topmenu_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_MENU_ID, MENU_MODULECODE, MENU_ICON, MENU_QUICKSTART, MENU_ISDESKTOP, MENU_TREEPATH, MENU_ENABLED, MENU_NODEINFO, MENU_NODEINFOTYPE, MENU_MENUNAME, MENU_CODE, MENU_FUNCTYPE, MENU_HELP, MENU_BIGBUTTON, MENU_MENUSUBNAME, MENU_BADGE, MENU_BADGETYPE, MENU_BADGECLS, MENU_BADGEINFO, MENU_BADGEMENUIDS, MENU_BADGEMENUTEXTS, MENU_BADGEBGCOLOR, MENU_BADGECOLOR, MENU_BADGELOAD, MENU_HIDELOCATION, MENU_WFMODULE, MENU_WFNAME, MENU_BGCOLOR, MENU_REFRESH, MENU_SERVICE, MENU_LAYOUT, MENU_LINK_TOPMENU_ID, MENU_LINK_TOPMENU_NAME, MENU_ICONCOLOR, MENU_LOAD_FIRST_DATA, SY_AUDFLAG, SY_CREATEORG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSER, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSER, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID, SY_PYJZ, SY_PYQC, SY_FORMUPLOADFILES, SY_PARENT, SY_NODETYPE, SY_LAYER, SY_PATH, SY_PARENTPATH, SY_TREEORDERINDEX, SY_JESYS, SY_JECORE, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_PRODUCT_ID, SY_SYSTEM};

    public TbCoreMenuTableDef() {
        super("", "tb_core_menu");
    }

    private TbCoreMenuTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreMenuTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreMenuTableDef("", "tb_core_menu", alias));
    }

}
