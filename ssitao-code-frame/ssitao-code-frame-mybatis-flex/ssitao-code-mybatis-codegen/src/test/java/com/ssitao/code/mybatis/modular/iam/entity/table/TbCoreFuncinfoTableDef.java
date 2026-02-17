package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 功能 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreFuncinfoTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 功能
     */
    public static final TbCoreFuncinfoTableDef TB_CORE_FUNCINFO = new TbCoreFuncinfoTableDef();

    /**
     * 是否启用本条数据
     */
    public final QueryColumn SY_FLAG = new QueryColumn(this, "sy_flag");

    /**
     * 树形路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

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
     * 节点类型
     */
    public final QueryColumn SY_NODETYPE = new QueryColumn(this, "sy_nodetype");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * sql
     */
    public final QueryColumn FUNCINFO_SQL = new QueryColumn(this, "funcinfo_sql");

    /**
     * 所属产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 帮助
     */
    public final QueryColumn FUNCINFO_HELP = new QueryColumn(this, "funcinfo_help");

    /**
     * 功能图标
     */
    public final QueryColumn FUNCINFO_ICON = new QueryColumn(this, "funcinfo_icon");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

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

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 是否需对比
     */
    public final QueryColumn FUNCINFO_SFXDB = new QueryColumn(this, "funcinfo_sfxdb");

    /**
     * 是否挂接工作流
     */
    public final QueryColumn FUNCINFO_USEWF = new QueryColumn(this, "funcinfo_usewf");

    /**
     * 登记人所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门id
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 所属产品code
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属产品名称
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 数据源
     */
    public final QueryColumn FUNCINFO_DBNAME = new QueryColumn(this, "funcinfo_dbname");

    /**
     * 主键编码
     */
    public final QueryColumn FUNCINFO_IDNAME = new QueryColumn(this, "funcinfo_idname");

    /**
     * 一对一卡片
     */
    public final QueryColumn FUNCINFO_OCFORM = new QueryColumn(this, "funcinfo_ocform");

    /**
     * 数据权限条件脚本
     */
    public final QueryColumn FUNCINFO_PERMJS = new QueryColumn(this, "funcinfo_permjs");

    /**
     * 主键名称
     */
    public final QueryColumn FUNCINFO_PKNAME = new QueryColumn(this, "funcinfo_pkname");

    /**
     * 是否启用安全模式
     */
    public final QueryColumn FUNCINFO_SAFETY = new QueryColumn(this, "funcinfo_safety");

    /**
     * 最后静态时间
     */
    public final QueryColumn FUNCINFO_ZHJTSJ = new QueryColumn(this, "funcinfo_zhjtsj");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人id
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 功能组态
     */
    public final QueryColumn FUNCINFO_COMBINE = new QueryColumn(this, "funcinfo_combine");

    /**
     * 拖动排序
     */
    public final QueryColumn FUNCINFO_DDORDER = new QueryColumn(this, "funcinfo_ddorder");

    /**
     * 功能字典
     */
    public final QueryColumn FUNCINFO_FUNCDIC = new QueryColumn(this, "funcinfo_funcdic");

    /**
     * 列表操作说明
     */
    public final QueryColumn FUNCINFO_GRIDTIP = new QueryColumn(this, "funcinfo_gridtip");

    /**
     * 图标样式
     */
    public final QueryColumn FUNCINFO_ICONCLS = new QueryColumn(this, "funcinfo_iconcls");

    /**
     * 权限sql
     */
    public final QueryColumn FUNCINFO_PERMSQL = new QueryColumn(this, "funcinfo_permsql");

    /**
     * saas产品
     */
    public final QueryColumn FUNCINFO_SAAS_PID = new QueryColumn(this, "funcinfo_saas_pid");

    /**
     * 系统模式
     */
    public final QueryColumn FUNCINFO_SYSMODE = new QueryColumn(this, "funcinfo_sysmode");

    /**
     * 快速查询操作说明
     */
    public final QueryColumn FUNCINFO_TREETIP = new QueryColumn(this, "funcinfo_treetip");

    /**
     * 启用修改标记
     */
    public final QueryColumn FUNCINFO_USEEDIT = new QueryColumn(this, "funcinfo_useedit");

    /**
     * 启用数据标记
     */
    public final QueryColumn FUNCINFO_USEMARK = new QueryColumn(this, "funcinfo_usemark");

    /**
     * saas模式
     */
    public final QueryColumn FUNCINFO_USESAAS = new QueryColumn(this, "funcinfo_usesaas");

    /**
     * 版本
     */
    public final QueryColumn FUNCINFO_VERSION = new QueryColumn(this, "funcinfo_version");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 扩展js文件
     */
    public final QueryColumn FUNCINFO_EXPANDJS = new QueryColumn(this, "funcinfo_expandjs");

    /**
     * 表单列数
     */
    public final QueryColumn FUNCINFO_FORMCOLS = new QueryColumn(this, "funcinfo_formcols");

    /**
     * 表单懒加载
     */
    public final QueryColumn FUNCINFO_FORMLAZY = new QueryColumn(this, "funcinfo_formlazy");

    /**
     * 功能编码
     */
    public final QueryColumn FUNCINFO_FUNCCODE = new QueryColumn(this, "funcinfo_funccode");

    /**
     * 功能名称
     */
    public final QueryColumn FUNCINFO_FUNCNAME = new QueryColumn(this, "funcinfo_funcname");

    /**
     * 功能类型
     */
    public final QueryColumn FUNCINFO_FUNCTYPE = new QueryColumn(this, "funcinfo_functype");

    /**
     * 行编辑
     */
    public final QueryColumn FUNCINFO_LINE_EDIT = new QueryColumn(this, "funcinfo_line_edit");

    /**
     * 列表表单
     */
    public final QueryColumn FUNCINFO_LISTFORM = new QueryColumn(this, "funcinfo_listform");

    /**
     * 节点信息
     */
    public final QueryColumn FUNCINFO_NODEINFO = new QueryColumn(this, "funcinfo_nodeinfo");

    /**
     * 新窗口编辑卡片
     */
    public final QueryColumn FUNCINFO_OPENFORM = new QueryColumn(this, "funcinfo_openform");

    /**
     * 排序条件
     */
    public final QueryColumn FUNCINFO_ORDERSQL = new QueryColumn(this, "funcinfo_ordersql");

    /**
     * 行数
     */
    public final QueryColumn FUNCINFO_PAGESIZE = new QueryColumn(this, "funcinfo_pagesize");

    /**
     * 启用附件
     */
    public final QueryColumn FUNCINFO_USEFILES = new QueryColumn(this, "funcinfo_usefiles");

    /**
     * 是否使用向导
     */
    public final QueryColumn FUNCINFO_USEGUIDE = new QueryColumn(this, "funcinfo_useguide");

    /**
     * 启用流程审批记录
     */
    public final QueryColumn FUNCINFO_USEWFLOG = new QueryColumn(this, "funcinfo_usewflog");

    /**
     * 列表过滤条件
     */
    public final QueryColumn FUNCINFO_WHERESQL = new QueryColumn(this, "funcinfo_wheresql");

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

    
    public final QueryColumn TB_CORE_FUNCINFO_ID = new QueryColumn(this, "tb_core_funcinfo_id");

    /**
     * 是否大按钮
     */
    public final QueryColumn FUNCINFO_BIGBUTTON = new QueryColumn(this, "funcinfo_bigbutton");

    /**
     * 操作人
     */
    public final QueryColumn FUNCINFO_CHECKUSER = new QueryColumn(this, "funcinfo_checkuser");

    /**
     * 控制部门字段
     */
    public final QueryColumn FUNCINFO_DEPTFIELD = new QueryColumn(this, "funcinfo_deptfield");

    /**
     * 启用审核功能
     */
    public final QueryColumn FUNCINFO_ENABLEDSH = new QueryColumn(this, "funcinfo_enabledsh");

    /**
     * 字段懒加载
     */
    public final QueryColumn FUNCINFO_FIELDLAZY = new QueryColumn(this, "funcinfo_fieldlazy");

    /**
     * 表单状态
     */
    public final QueryColumn FUNCINFO_FORM_STATE = new QueryColumn(this, "funcinfo_form_state");

    /**
     * 表单样式
     */
    public final QueryColumn FUNCINFO_FORM_STYLE = new QueryColumn(this, "funcinfo_form_style");

    /**
     * 表单标题
     */
    public final QueryColumn FUNCINFO_FORMTITLE = new QueryColumn(this, "funcinfo_formtitle");

    /**
     * 表单宽度
     */
    public final QueryColumn FUNCINFO_FORMWIDTH = new QueryColumn(this, "funcinfo_formwidth");

    /**
     * 列表隐藏元素
     */
    public final QueryColumn FUNCINFO_GRIDHIDES = new QueryColumn(this, "funcinfo_gridhides");

    /**
     * 列表操作说明
     */
    public final QueryColumn FUNCINFO_GRIDTIP_EN = new QueryColumn(this, "funcinfo_gridtip_en");

    /**
     * 产品扩展功能id
     */
    public final QueryColumn FUNCINFO_NEWFUNCID = new QueryColumn(this, "funcinfo_newfuncid");

    /**
     * 一对一表单
     */
    public final QueryColumn FUNCINFO_ONETOFORM = new QueryColumn(this, "funcinfo_onetoform");

    /**
     * 存储过程
     */
    public final QueryColumn FUNCINFO_PROCEDURE = new QueryColumn(this, "funcinfo_procedure");

    /**
     * 简洁工具条
     */
    public final QueryColumn FUNCINFO_SIMPLEBAR = new QueryColumn(this, "funcinfo_simplebar");

    /**
     * 启用表格表单
     */
    public final QueryColumn FUNCINFO_TABLEFORM = new QueryColumn(this, "funcinfo_tableform");

    /**
     * 表名
     */
    public final QueryColumn FUNCINFO_TABLENAME = new QueryColumn(this, "funcinfo_tablename");

    /**
     * 自定义列表
     */
    public final QueryColumn FUNCINFO_TABLEVIEW = new QueryColumn(this, "funcinfo_tableview");

    /**
     * 快速查询操作说明英文
     */
    public final QueryColumn FUNCINFO_TREETIP_EN = new QueryColumn(this, "funcinfo_treetip_en");

    /**
     * 快速查询标题
     */
    public final QueryColumn FUNCINFO_TREETITLE = new QueryColumn(this, "funcinfo_treetitle");

    /**
     * 快速查询宽度
     */
    public final QueryColumn FUNCINFO_TREEWIDTH = new QueryColumn(this, "funcinfo_treewidth");

    /**
     * 控制人员字段
     */
    public final QueryColumn FUNCINFO_USERFIELD = new QueryColumn(this, "funcinfo_userfield");

    /**
     * 弹出表单宽高
     */
    public final QueryColumn FUNCINFO_WINFORMWH = new QueryColumn(this, "funcinfo_winformwh");

    /**
     * 表单上传虚字段
     */
    public final QueryColumn SY_FORMUPLOADFILES = new QueryColumn(this, "sy_formuploadfiles");

    /**
     * 启用图表
     */
    public final QueryColumn FUNCINFO_CHARTSOPEN = new QueryColumn(this, "funcinfo_chartsopen");

    /**
     * 是否启用列分步加载
     */
    public final QueryColumn FUNCINFO_COLUMNLAZY = new QueryColumn(this, "funcinfo_columnlazy");

    /**
     * 数据源
     */
    public final QueryColumn FUNCINFO_DATASOURCE = new QueryColumn(this, "funcinfo_datasource");

    /**
     * 是否表单分页
     */
    public final QueryColumn FUNCINFO_FORMPAGING = new QueryColumn(this, "funcinfo_formpaging");

    /**
     * 对应action
     */
    public final QueryColumn FUNCINFO_FUNCACTION = new QueryColumn(this, "funcinfo_funcaction");

    /**
     * 功能英文名
     */
    public final QueryColumn FUNCINFO_FUNCNAME_EN = new QueryColumn(this, "funcinfo_funcname_en");

    /**
     * 功能描述
     */
    public final QueryColumn FUNCINFO_FUNCREMARK = new QueryColumn(this, "funcinfo_funcremark");

    /**
     * 列表行模版
     */
    public final QueryColumn FUNCINFO_GRIDROWTIP = new QueryColumn(this, "funcinfo_gridrowtip");

    /**
     * 分组字段
     */
    public final QueryColumn FUNCINFO_GROUPFIELD = new QueryColumn(this, "funcinfo_groupfield");

    /**
     * 帮助数据源
     */
    public final QueryColumn FUNCINFO_HELPSOURCE = new QueryColumn(this, "funcinfo_helpsource");

    /**
     * 数据录入方式
     */
    public final QueryColumn FUNCINFO_INSERTTYPE = new QueryColumn(this, "funcinfo_inserttype");

    /**
     * 是否启动书签
     */
    public final QueryColumn FUNCINFO_ISBOOKMARK = new QueryColumn(this, "funcinfo_isbookmark");

    /**
     * 功能信息是否完善
     */
    public final QueryColumn FUNCINFO_ISCOMPLETE = new QueryColumn(this, "funcinfo_iscomplete");

    /**
     * 是否表格树
     */
    public final QueryColumn FUNCINFO_ISGRIDTREE = new QueryColumn(this, "funcinfo_isgridtree");

    /**
     * 布局配置方式
     */
    public final QueryColumn FUNCINFO_LAYOUT_TYPE = new QueryColumn(this, "funcinfo_layout_type");

    /**
     * lodop打印
     */
    public final QueryColumn FUNCINFO_LODOPPRINT = new QueryColumn(this, "funcinfo_lodopprint");

    /**
     * 全息查询是否多选
     */
    public final QueryColumn FUNCINFO_MULTIQUERY = new QueryColumn(this, "funcinfo_multiquery");

    /**
     * 权限配置内容
     */
    public final QueryColumn FUNCINFO_PERMCONFIG = new QueryColumn(this, "funcinfo_permconfig");

    /**
     * 查询参数
     */
    public final QueryColumn FUNCINFO_QUERYPARAM = new QueryColumn(this, "funcinfo_queryparam");

    /**
     * 全息查询宽度
     */
    public final QueryColumn FUNCINFO_QUERYWIDTH = new QueryColumn(this, "funcinfo_querywidth");

    /**
     * 只读流程
     */
    public final QueryColumn FUNCINFO_READONLYWF = new QueryColumn(this, "funcinfo_readonlywf");

    /**
     * 报表附件
     */
    public final QueryColumn FUNCINFO_REPORTFILE = new QueryColumn(this, "funcinfo_reportfile");

    /**
     * 可见部门id
     */
    public final QueryColumn FUNCINFO_SEEDEPTIDS = new QueryColumn(this, "funcinfo_seedeptids");

    /**
     * 可见角色id
     */
    public final QueryColumn FUNCINFO_SEEROLEIDS = new QueryColumn(this, "funcinfo_seeroleids");

    /**
     * 可见人员id
     */
    public final QueryColumn FUNCINFO_SEEUSERIDS = new QueryColumn(this, "funcinfo_seeuserids");

    /**
     * 子系统
     */
    public final QueryColumn FUNCINFO_SYSTEMNAME = new QueryColumn(this, "funcinfo_systemname");

    /**
     * 表格样式
     */
    public final QueryColumn FUNCINFO_TABLESTYLE = new QueryColumn(this, "funcinfo_tablestyle");

    /**
     * 使用关系id
     */
    public final QueryColumn FUNCINFO_USECJGLIDS = new QueryColumn(this, "funcinfo_usecjglids");

    /**
     * 启用数据留痕
     */
    public final QueryColumn FUNCINFO_USEDATALOG = new QueryColumn(this, "funcinfo_usedatalog");

    /**
     * 版本_dev
     */
    public final QueryColumn FUNCINFO_VERSION_DEV = new QueryColumn(this, "funcinfo_version_dev");

    /**
     * 操作状态
     */
    public final QueryColumn FUNCINFO_CHECKSTATUS = new QueryColumn(this, "funcinfo_checkstatus");

    /**
     * 操作人主键
     */
    public final QueryColumn FUNCINFO_CHECKUSERID = new QueryColumn(this, "funcinfo_checkuserid");

    /**
     * 子功能多条过滤
     */
    public final QueryColumn FUNCINFO_CHILDFILTER = new QueryColumn(this, "funcinfo_childfilter");

    /**
     * 子功能高度
     */
    public final QueryColumn FUNCINFO_CHILDHEIGHT = new QueryColumn(this, "funcinfo_childheight");

    /**
     * 默认行高
     */
    public final QueryColumn FUNCINFO_COLUMN_WIDTH = new QueryColumn(this, "funcinfo_column_width");

    /**
     * 弹出表单位置
     */
    public final QueryColumn FUNCINFO_FORM_POSTION = new QueryColumn(this, "funcinfo_form_postion");

    /**
     * 手动切换表单展示方案
     */
    public final QueryColumn FUNCINFO_FORM_SHOW_WAY = new QueryColumn(this, "funcinfo_form_show_way");

    /**
     * 表单背景色
     */
    public final QueryColumn FUNCINFO_FORMBGCOLOR = new QueryColumn(this, "funcinfo_formbgcolor");

    /**
     * 列表子功能并排显示
     */
    public final QueryColumn FUNCINFO_GRIDCHILDSS = new QueryColumn(this, "funcinfo_gridchildss");

    /**
     * 列表请求超时时间
     */
    public final QueryColumn FUNCINFO_GRIDTIMEOUT = new QueryColumn(this, "funcinfo_gridtimeout");

    /**
     * lodop打印配置
     */
    public final QueryColumn FUNCINFO_LODOPCONFIG = new QueryColumn(this, "funcinfo_lodopconfig");

    /**
     * 列表多选
     */
    public final QueryColumn FUNCINFO_MULTISELECT = new QueryColumn(this, "funcinfo_multiselect");

    /**
     * 开启更多按钮
     */
    public final QueryColumn FUNCINFO_OPEN_MORE_BUT = new QueryColumn(this, "funcinfo_open_more_but");

    /**
     * 排序字段描述
     */
    public final QueryColumn FUNCINFO_ORDERSQL_DES = new QueryColumn(this, "funcinfo_ordersql_des");

    /**
     * 辅助配置项
     */
    public final QueryColumn FUNCINFO_OTHERCONFIG = new QueryColumn(this, "funcinfo_otherconfig");

    /**
     * 子系统外键
     */
    public final QueryColumn FUNCINFO_SUBSYSTEM_ID = new QueryColumn(this, "funcinfo_subsystem_id");

    /**
     * 快速查询分步加载
     */
    public final QueryColumn FUNCINFO_TREEREFRESH = new QueryColumn(this, "funcinfo_treerefresh");

    /**
     * 启用树形列表视图
     */
    public final QueryColumn FUNCINFO_USETREEGRID = new QueryColumn(this, "funcinfo_usetreegrid");

    /**
     * 过滤条件描述
     */
    public final QueryColumn FUNCINFO_WHERESQL_DES = new QueryColumn(this, "funcinfo_wheresql_des");

    /**
     * 操作人_dev
     */
    public final QueryColumn FUNCINFO_CHECKUSER_DEV = new QueryColumn(this, "funcinfo_checkuser_dev");

    /**
     * 子功能分步加载数据
     */
    public final QueryColumn FUNCINFO_CHILDREFRESH = new QueryColumn(this, "funcinfo_childrefresh");

    /**
     * 个性化列定义
     */
    public final QueryColumn FUNCINFO_CUSTOM_COLUMN = new QueryColumn(this, "funcinfo_custom_column");

    /**
     * 查询策略选择模式
     */
    public final QueryColumn FUNCINFO_CXCLSELMODEL = new QueryColumn(this, "funcinfo_cxclselmodel");

    /**
     * 扩展panel
     */
    public final QueryColumn FUNCINFO_EXPANDPANELS = new QueryColumn(this, "funcinfo_expandpanels");

    /**
     * 弹出字段间距
     */
    public final QueryColumn FUNCINFO_FIELD_SPACING = new QueryColumn(this, "funcinfo_field_spacing");

    /**
     * 弹出表单位置
     */
    public final QueryColumn FUNCINFO_FORM_POSITION = new QueryColumn(this, "funcinfo_form_position");

    /**
     * 手动切换表单打印方案
     */
    public final QueryColumn FUNCINFO_FORM_PRINT_WAY = new QueryColumn(this, "funcinfo_form_print_way");

    /**
     * 默认宽度
     */
    public final QueryColumn FUNCINFO_FORMDEFWIDTH = new QueryColumn(this, "funcinfo_formdefwidth");

    /**
     * 表单最小宽度
     */
    public final QueryColumn FUNCINFO_FORMMINWIDTH = new QueryColumn(this, "funcinfo_formminwidth");

    /**
     * 快速定位位置
     */
    public final QueryColumn FUNCINFO_FORMNAVIGATE = new QueryColumn(this, "funcinfo_formnavigate");

    /**
     * 弹出表单显示子功能
     */
    public final QueryColumn FUNCINFO_FORMWINCHILD = new QueryColumn(this, "funcinfo_formwinchild");

    /**
     * 列表无限滚动
     */
    public final QueryColumn FUNCINFO_GRIDBUFFERED = new QueryColumn(this, "funcinfo_gridbuffered");

    /**
     * 隐藏表单工具条
     */
    public final QueryColumn FUNCINFO_HIDEFORMTBAR = new QueryColumn(this, "funcinfo_hideformtbar");

    /**
     * 隐藏列表工具条
     */
    public final QueryColumn FUNCINFO_HIDEGRIDTBAR = new QueryColumn(this, "funcinfo_hidegridtbar");

    /**
     * 初始化列表加载数据
     */
    public final QueryColumn FUNCINFO_INIT_LOAD_DATA = new QueryColumn(this, "funcinfo_init_load_data");

    /**
     * 标签背景颜色
     */
    public final QueryColumn FUNCINFO_LABELBGCOLOR = new QueryColumn(this, "funcinfo_labelbgcolor");

    /**
     * 节点信息类型
     */
    public final QueryColumn FUNCINFO_NODEINFOTYPE = new QueryColumn(this, "funcinfo_nodeinfotype");

    /**
     * 可见部门
     */
    public final QueryColumn FUNCINFO_SEEDEPTNAMES = new QueryColumn(this, "funcinfo_seedeptnames");

    /**
     * 可见角色
     */
    public final QueryColumn FUNCINFO_SEEROLENAMES = new QueryColumn(this, "funcinfo_seerolenames");

    /**
     * 可见岗位id
     */
    public final QueryColumn FUNCINFO_SEESENTRYIDS = new QueryColumn(this, "funcinfo_seesentryids");

    /**
     * 可见人员
     */
    public final QueryColumn FUNCINFO_SEEUSERNAMES = new QueryColumn(this, "funcinfo_seeusernames");

    /**
     * 简化查询
     */
    public final QueryColumn FUNCINFO_SIMPLESEARCH = new QueryColumn(this, "funcinfo_simplesearch");

    /**
     * 排序sql描述
     */
    public final QueryColumn FUNCINFO_SORT_DESCRIBE = new QueryColumn(this, "funcinfo_sort_describe");

    /**
     * 表格表单模版
     */
    public final QueryColumn FUNCINFO_TABLEFORMTPL = new QueryColumn(this, "funcinfo_tableformtpl");

    /**
     * 自定义列表tpl
     */
    public final QueryColumn FUNCINFO_TABLEVIEW_TPL = new QueryColumn(this, "funcinfo_tableview_tpl");

    /**
     * 使用关系
     */
    public final QueryColumn FUNCINFO_USECJGLNAMES = new QueryColumn(this, "funcinfo_usecjglnames");

    /**
     * 操作人code
     */
    public final QueryColumn FUNCINFO_CHECKUSERCODE = new QueryColumn(this, "funcinfo_checkusercode");

    /**
     * 子卡片横向显示
     */
    public final QueryColumn FUNCINFO_CHILDSHOWTYPE = new QueryColumn(this, "funcinfo_childshowtype");

    /**
     * 关闭拖拽排序
     */
    public final QueryColumn FUNCINFO_CLOSE_DRAG_SORT = new QueryColumn(this, "funcinfo_close_drag_sort");

    /**
     * 操作表名
     */
    public final QueryColumn FUNCINFO_CRUDTABLENAME = new QueryColumn(this, "funcinfo_crudtablename");

    /**
     * 高级查询禁用change事件
     */
    public final QueryColumn FUNCINFO_DISABLE_CHANGE = new QueryColumn(this, "funcinfo_disable_change");

    /**
     * 表单标题类型
     */
    public final QueryColumn FUNCINFO_FORMTITLETYPE = new QueryColumn(this, "funcinfo_formtitletype");

    /**
     * 功能字典配置信息
     */
    public final QueryColumn FUNCINFO_FUNCDICCONFIG = new QueryColumn(this, "funcinfo_funcdicconfig");

    /**
     * 隐藏表格线
     */
    public final QueryColumn FUNCINFO_GRIDHIDELINES = new QueryColumn(this, "funcinfo_gridhidelines");

    /**
     * 列表打印配置
     */
    public final QueryColumn FUNCINFO_GRIDPRINTINFO = new QueryColumn(this, "funcinfo_gridprintinfo");

    /**
     * 分组框快速定位
     */
    public final QueryColumn FUNCINFO_GROUP_LOCATION = new QueryColumn(this, "funcinfo_group_location");

    /**
     * 分组数据模版
     */
    public final QueryColumn FUNCINFO_GROUPFIELDTPL = new QueryColumn(this, "funcinfo_groupfieldtpl");

    /**
     * 高级查询展开
     */
    public final QueryColumn FUNCINFO_GROUPFORMOPEN = new QueryColumn(this, "funcinfo_groupformopen");

    /**
     * 向导位置
     */
    public final QueryColumn FUNCINFO_GUIDELOCATION = new QueryColumn(this, "funcinfo_guidelocation");

    /**
     * 标签位置整体设置
     */
    public final QueryColumn FUNCINFO_LABELPOSITION = new QueryColumn(this, "funcinfo_labelposition");

    /**
     * 留痕依赖功能
     */
    public final QueryColumn FUNCINFO_LEAVE_MARK_FUNC = new QueryColumn(this, "funcinfo_leave_mark_func");

    /**
     * 分页信息位置
     */
    public final QueryColumn FUNCINFO_PAGEINFOALIGN = new QueryColumn(this, "funcinfo_pageinfoalign");

    /**
     * sql查询参数
     */
    public final QueryColumn FUNCINFO_QUERYPARAM_SQL = new QueryColumn(this, "funcinfo_queryparam_sql");

    /**
     * 自定义列表列数
     */
    public final QueryColumn FUNCINFO_TABLEVIEW_COLS = new QueryColumn(this, "funcinfo_tableview_cols");

    /**
     * 附件保存路径
     */
    public final QueryColumn FUNCINFO_ATTACHMENTPATH = new QueryColumn(this, "funcinfo_attachmentpath");

    /**
     * 书签配置信息
     */
    public final QueryColumn FUNCINFO_BOOKMARKCONFIG = new QueryColumn(this, "funcinfo_bookmarkconfig");

    /**
     * 操作状态_dev
     */
    public final QueryColumn FUNCINFO_CHECKSTATUS_DEV = new QueryColumn(this, "funcinfo_checkstatus_dev");

    /**
     * 操作人主键_dev
     */
    public final QueryColumn FUNCINFO_CHECKUSERID_DEV = new QueryColumn(this, "funcinfo_checkuserid_dev");

    /**
     * 过滤sql条件描述
     */
    public final QueryColumn FUNCINFO_FILTER_DESCRIBE = new QueryColumn(this, "funcinfo_filter_describe");

    /**
     * 字段标题色
     */
    public final QueryColumn FUNCINFO_FORM_LABELCOLOR = new QueryColumn(this, "funcinfo_form_labelcolor");

    /**
     * 表单监听事件
     */
    public final QueryColumn FUNCINFO_FORMJSLISTENER = new QueryColumn(this, "funcinfo_formjslistener");

    /**
     * 表单项label宽度
     */
    public final QueryColumn FUNCINFO_FORMLABELWIDTH = new QueryColumn(this, "funcinfo_formlabelwidth");

    /**
     * 表单标题
     */
    public final QueryColumn FUNCINFO_FORMTITLE4FUNC = new QueryColumn(this, "funcinfo_formtitle4func");

    /**
     * 列表监听事件
     */
    public final QueryColumn FUNCINFO_GRIDJSLISTENER = new QueryColumn(this, "funcinfo_gridjslistener");

    /**
     * 列表行模板总是展开
     */
    public final QueryColumn FUNCINFO_GRIDROWTIPSHOW = new QueryColumn(this, "funcinfo_gridrowtipshow");

    /**
     * 帮助数据源名称
     */
    public final QueryColumn FUNCINFO_HELPSOURCE_TEXT = new QueryColumn(this, "funcinfo_helpsource_text");

    /**
     * 标签对齐整体设置
     */
    public final QueryColumn FUNCINFO_LABELALIGNMENT = new QueryColumn(this, "funcinfo_labelalignment");

    /**
     * 微邮标题
     */
    public final QueryColumn FUNCINFO_MICRO_MAIL_TITLE = new QueryColumn(this, "funcinfo_micro_mail_title");

    /**
     * 开启更多按钮
     */
    public final QueryColumn FUNCINFO_OPEN_MORE_BUTTON = new QueryColumn(this, "funcinfo_open_more_button");

    /**
     * 打印表单标题
     */
    public final QueryColumn FUNCINFO_PRINT_FORM_TITLE = new QueryColumn(this, "funcinfo_print_form_title");

    /**
     * 可见岗位
     */
    public final QueryColumn FUNCINFO_SEESENTRYNAMES = new QueryColumn(this, "funcinfo_seesentrynames");

    /**
     * 表单直接打印
     */
    public final QueryColumn FUNCINFO_TABLEFORMPRINT = new QueryColumn(this, "funcinfo_tableformprint");

    /**
     * 自定义表单宽度
     */
    public final QueryColumn FUNCINFO_TABLEFORMWIDTH = new QueryColumn(this, "funcinfo_tableformwidth");

    /**
     * 视图配置信息
     */
    public final QueryColumn FUNCINFO_VIEWCONFIGINFO = new QueryColumn(this, "funcinfo_viewconfiginfo");

    /**
     * 高级查询禁用change事件
     */
    public final QueryColumn FUNCTION_DISABLED_CHANGE = new QueryColumn(this, "function_disabled_change");

    /**
     * 批注标题
     */
    public final QueryColumn FUNCINFO_ANNOTATION_TITLE = new QueryColumn(this, "funcinfo_annotation_title");

    /**
     * 绑定工作流
     */
    public final QueryColumn FUNCINFO_BINDING_WORKFLOW = new QueryColumn(this, "funcinfo_binding_workflow");

    /**
     * 禁用功能查询sql
     */
    public final QueryColumn FUNCINFO_DISABLEQUERYSQL = new QueryColumn(this, "funcinfo_disablequerysql");

    /**
     * 启用表单打印按钮
     */
    public final QueryColumn FUNCINFO_ENABLEFORMPRINT = new QueryColumn(this, "funcinfo_enableformprint");

    /**
     * 自定义表单高度
     */
    public final QueryColumn FUNCINFO_TABLEFORMHEIGHT = new QueryColumn(this, "funcinfo_tableformheight");

    /**
     * 自定义表单宽度2
     */
    public final QueryColumn FUNCINFO_TABLEFORMWIDTH2 = new QueryColumn(this, "funcinfo_tableformwidth2");

    /**
     * 是否加入查询策略
     */
    public final QueryColumn FUNCINFO_ADD_QUERY_STRATEGY = new QueryColumn(this, "funcinfo_add_query_strategy");

    /**
     * 扩展实体全限定名
     */
    public final QueryColumn FUNCINFO_EXPANDENTITYNAME = new QueryColumn(this, "funcinfo_expandentityname");

    /**
     * 字段边框颜色
     */
    public final QueryColumn FUNCINFO_FIELDBORDERCOLOR = new QueryColumn(this, "funcinfo_fieldbordercolor");

    /**
     * 表单字段间距
     */
    public final QueryColumn FUNCINFO_FORMFIELD_SPACING = new QueryColumn(this, "funcinfo_formfield_spacing");

    /**
     * 表单项label英文宽度
     */
    public final QueryColumn FUNCINFO_FORMLABELWIDTH_EN = new QueryColumn(this, "funcinfo_formlabelwidth_en");

    /**
     * 表单分页配置
     */
    public final QueryColumn FUNCINFO_FORMPAGINGCONFIG = new QueryColumn(this, "funcinfo_formpagingconfig");

    /**
     * 更多按钮配置
     */
    public final QueryColumn FUNCINFO_MORE_BUTTON_CONFIG = new QueryColumn(this, "funcinfo_more_button_config");

    /**
     * 是否启用检索按钮
     */
    public final QueryColumn FUNCINFO_OPEN_SEARCH_BUTTON = new QueryColumn(this, "funcinfo_open_search_button");

    /**
     * 字段标题位置
     */
    public final QueryColumn FUNCINFO_APPFORM_LABEL_ALIGN = new QueryColumn(this, "funcinfo_appform_label_align");

    /**
     * 字段标题宽度
     */
    public final QueryColumn FUNCINFO_APPFORM_LABEL_WIDTH = new QueryColumn(this, "funcinfo_appform_label_width");

    /**
     * 绑定工作流id
     */
    public final QueryColumn FUNCINFO_BINDING_WORKFLOWID = new QueryColumn(this, "funcinfo_binding_workflowid");

    /**
     * 栅格布局列数
     */
    public final QueryColumn FUNCINFO_LAYOUT_COLUMN_COUNT = new QueryColumn(this, "funcinfo_layout_column_count");

    /**
     * 快速查询动态刷新
     */
    public final QueryColumn FUNCINFO_TREEDYNAMICREFRESH = new QueryColumn(this, "funcinfo_treedynamicrefresh");

    /**
     * 隐藏表单子功能导航
     */
    public final QueryColumn FUNCINFO_APPFORM_DISABLE_CRUMB = new QueryColumn(this, "funcinfo_appform_disable_crumb");

    /**
     * 多级子功能导航
     */
    public final QueryColumn FUNCINFO_CHILDFUNC_NAVIGATION = new QueryColumn(this, "funcinfo_childfunc_navigation");

    /**
     * 隔行变色
     */
    public final QueryColumn FUNCINFO_INTERLACED_DISCOLOUR = new QueryColumn(this, "funcinfo_interlaced_discolour");

    /**
     * 统计策略
     */
    public final QueryColumn FUNCINFO_STATISTICALSTRATEGY = new QueryColumn(this, "funcinfo_statisticalstrategy");

    /**
     * 标题宽
     */
    public final QueryColumn FUNCINFO_GROUPQUERY_LABEL_WIDTH = new QueryColumn(this, "funcinfo_groupquery_label_width");

    /**
     * 启用搜索按钮
     */
    public final QueryColumn FUNCINFO_GROUPQUERY_ENABEL_QUERY = new QueryColumn(this, "funcinfo_groupquery_enabel_query");

    /**
     * 启用导出按钮
     */
    public final QueryColumn FUNCINFO_GROUPQUERY_START_EXPORT = new QueryColumn(this, "funcinfo_groupquery_start_export");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_FUNCINFO_ID, FUNCINFO_NODEINFO, FUNCINFO_NODEINFOTYPE, FUNCINFO_FUNCNAME, FUNCINFO_FUNCCODE, FUNCINFO_NEWFUNCID, FUNCINFO_ICON, FUNCINFO_SUBSYSTEM_ID, FUNCINFO_SYSTEMNAME, FUNCINFO_IDNAME, FUNCINFO_EXPANDENTITYNAME, FUNCINFO_EXPANDJS, FUNCINFO_FUNCACTION, FUNCINFO_PKNAME, FUNCINFO_TABLENAME, FUNCINFO_FORMTITLE, FUNCINFO_WHERESQL, FUNCINFO_PAGESIZE, FUNCINFO_ORDERSQL, FUNCINFO_ISCOMPLETE, FUNCINFO_OCFORM, FUNCINFO_OPENFORM, FUNCINFO_CHILDSHOWTYPE, FUNCINFO_FUNCDIC, FUNCINFO_ISGRIDTREE, FUNCINFO_FUNCDICCONFIG, FUNCINFO_FORMLABELWIDTH, FUNCINFO_FORMCOLS, FUNCINFO_FORMDEFWIDTH, FUNCINFO_EXPANDPANELS, FUNCINFO_CHARTSOPEN, FUNCINFO_USEWF, FUNCINFO_USEFILES, FUNCINFO_USEDATALOG, FUNCINFO_DATASOURCE, FUNCINFO_REPORTFILE, FUNCINFO_QUERYWIDTH, FUNCINFO_MULTIQUERY, FUNCINFO_SYSMODE, FUNCINFO_ATTACHMENTPATH, FUNCINFO_PERMCONFIG, FUNCINFO_FORMPAGING, FUNCINFO_FORMPAGINGCONFIG, FUNCINFO_GROUPFIELD, FUNCINFO_BIGBUTTON, FUNCINFO_GROUPFIELDTPL, FUNCINFO_CHECKUSER, FUNCINFO_CHECKUSERID, FUNCINFO_CHECKUSERCODE, FUNCINFO_CHECKSTATUS, FUNCINFO_VERSION, FUNCINFO_ISBOOKMARK, FUNCINFO_BOOKMARKCONFIG, FUNCINFO_FORMJSLISTENER, FUNCINFO_GRIDJSLISTENER, FUNCINFO_ONETOFORM, FUNCINFO_USEGUIDE, FUNCINFO_GUIDELOCATION, FUNCINFO_ICONCLS, FUNCINFO_FUNCTYPE, FUNCINFO_INSERTTYPE, FUNCINFO_LISTFORM, FUNCINFO_GRIDROWTIP, FUNCINFO_GRIDTIP, FUNCINFO_TREETIP, FUNCINFO_FORMBGCOLOR, FUNCINFO_FORMTITLETYPE, FUNCINFO_FUNCREMARK, FUNCINFO_MULTISELECT, FUNCINFO_WHERESQL_DES, FUNCINFO_ORDERSQL_DES, FUNCINFO_CXCLSELMODEL, FUNCINFO_SEEROLENAMES, FUNCINFO_SEEROLEIDS, FUNCINFO_SEESENTRYIDS, FUNCINFO_SEEUSERNAMES, FUNCINFO_SEEUSERIDS, FUNCINFO_SEESENTRYNAMES, FUNCINFO_FIELDBORDERCOLOR, FUNCINFO_LABELBGCOLOR, FUNCINFO_TABLESTYLE, FUNCINFO_FORMMINWIDTH, FUNCINFO_CHILDREFRESH, FUNCINFO_TABLEFORM, FUNCINFO_TABLEFORMTPL, FUNCINFO_TABLEFORMHEIGHT, FUNCINFO_TABLEFORMWIDTH, FUNCINFO_CHILDFILTER, FUNCINFO_WINFORMWH, FUNCINFO_DDORDER, FUNCINFO_GRIDPRINTINFO, FUNCINFO_TABLEFORMWIDTH2, FUNCINFO_TREEREFRESH, FUNCINFO_GRIDCHILDSS, FUNCINFO_ENABLEFORMPRINT, FUNCINFO_HIDEGRIDTBAR, FUNCINFO_COMBINE, FUNCINFO_HELP, FUNCINFO_FORMLAZY, FUNCINFO_COLUMNLAZY, FUNCINFO_GROUPFORMOPEN, FUNCINFO_HIDEFORMTBAR, FUNCINFO_LODOPCONFIG, FUNCINFO_LODOPPRINT, FUNCINFO_TABLEFORMPRINT, FUNCINFO_SEEDEPTNAMES, FUNCINFO_SEEDEPTIDS, FUNCINFO_FUNCNAME_EN, FUNCINFO_FORMLABELWIDTH_EN, FUNCINFO_GRIDTIP_EN, FUNCINFO_TREETIP_EN, FUNCINFO_FIELDLAZY, FUNCINFO_GRIDBUFFERED, FUNCINFO_GRIDTIMEOUT, FUNCINFO_GRIDROWTIPSHOW, FUNCINFO_GRIDHIDES, FUNCINFO_CRUDTABLENAME, FUNCINFO_VERSION_DEV, FUNCINFO_CHECKUSER_DEV, FUNCINFO_CHECKUSERID_DEV, FUNCINFO_CHECKSTATUS_DEV, FUNCINFO_SFXDB, FUNCINFO_USEWFLOG, FUNCINFO_CHILDHEIGHT, FUNCINFO_USECJGLNAMES, FUNCINFO_USETREEGRID, FUNCINFO_USECJGLIDS, FUNCINFO_SIMPLESEARCH, FUNCINFO_READONLYWF, FUNCINFO_USEMARK, FUNCINFO_PERMSQL, FUNCINFO_TABLEVIEW, FUNCINFO_TABLEVIEW_COLS, FUNCINFO_TABLEVIEW_TPL, FUNCINFO_GRIDHIDELINES, FUNCINFO_ENABLEDSH, FUNCINFO_DBNAME, FUNCINFO_SIMPLEBAR, FUNCINFO_PROCEDURE, FUNCINFO_PAGEINFOALIGN, FUNCINFO_QUERYPARAM, FUNCINFO_SQL, FUNCINFO_FORMWIDTH, FUNCINFO_FORMNAVIGATE, FUNCINFO_HELPSOURCE, FUNCINFO_HELPSOURCE_TEXT, FUNCINFO_DISABLEQUERYSQL, FUNCINFO_FORMTITLE4FUNC, FUNCINFO_VIEWCONFIGINFO, FUNCINFO_USESAAS, FUNCINFO_OTHERCONFIG, FUNCINFO_USEEDIT, FUNCINFO_PERMJS, FUNCINFO_USERFIELD, FUNCINFO_DEPTFIELD, FUNCINFO_SAFETY, FUNCINFO_ZHJTSJ, FUNCINFO_OPEN_MORE_BUTTON, FUNCINFO_MORE_BUTTON_CONFIG, FUNCINFO_BINDING_WORKFLOW, FUNCINFO_DISABLE_CHANGE, FUNCINFO_LEAVE_MARK_FUNC, FUNCINFO_FILTER_DESCRIBE, FUNCINFO_SORT_DESCRIBE, FUNCINFO_PRINT_FORM_TITLE, FUNCINFO_FORM_POSITION, FUNCINFO_GROUP_LOCATION, FUNCINFO_TREEDYNAMICREFRESH, FUNCINFO_TREETITLE, FUNCINFO_TREEWIDTH, FUNCINFO_ANNOTATION_TITLE, FUNCINFO_MICRO_MAIL_TITLE, FUNCINFO_INTERLACED_DISCOLOUR, FUNCINFO_LINE_EDIT, FUNCINFO_CUSTOM_COLUMN, FUNCINFO_FORMWINCHILD, FUNCINFO_COLUMN_WIDTH, FUNCINFO_FORM_POSTION, FUNCINFO_FIELD_SPACING, FUNCINFO_LABELALIGNMENT, FUNCINFO_LABELPOSITION, FUNCINFO_FORM_SHOW_WAY, FUNCINFO_FORM_PRINT_WAY, FUNCINFO_OPEN_MORE_BUT, FUNCINFO_QUERYPARAM_SQL, FUNCINFO_FORM_STATE, FUNCINFO_INIT_LOAD_DATA, FUNCINFO_FORMFIELD_SPACING, FUNCINFO_LAYOUT_TYPE, FUNCINFO_LAYOUT_COLUMN_COUNT, FUNCINFO_ADD_QUERY_STRATEGY, FUNCINFO_CHILDFUNC_NAVIGATION, FUNCINFO_BINDING_WORKFLOWID, FUNCTION_DISABLED_CHANGE, FUNCINFO_FORM_LABELCOLOR, FUNCINFO_GROUPQUERY_LABEL_WIDTH, FUNCINFO_GROUPQUERY_ENABEL_QUERY, FUNCINFO_OPEN_SEARCH_BUTTON, FUNCINFO_FORM_STYLE, FUNCINFO_SAAS_PID, FUNCINFO_STATISTICALSTRATEGY, FUNCINFO_CLOSE_DRAG_SORT, FUNCINFO_GROUPQUERY_START_EXPORT, FUNCINFO_APPFORM_LABEL_ALIGN, FUNCINFO_APPFORM_LABEL_WIDTH, FUNCINFO_APPFORM_DISABLE_CRUMB, SY_PARENT, SY_NODETYPE, SY_LAYER, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_MODIFYUSER, SY_PRODUCT_ID, SY_PRODUCT_CODE, SY_PRODUCT_NAME, SY_JECORE, SY_JESYS, SY_TREEORDERINDEX, SY_PARENTPATH, SY_PATH, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PYJZ, SY_PYQC, SY_FORMUPLOADFILES};

    public TbCoreFuncinfoTableDef() {
        super("", "tb_core_funcinfo");
    }

    private TbCoreFuncinfoTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreFuncinfoTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreFuncinfoTableDef("", "tb_core_funcinfo", alias));
    }

}
