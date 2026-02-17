package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 单字段 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreResourcefieldTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 单字段
     */
    public static final TbCoreResourcefieldTableDef TB_CORE_RESOURCEFIELD = new TbCoreResourcefieldTableDef();

    /**
     * 是否启用本条数据
     */
    public final QueryColumn SY_FLAG = new QueryColumn(this, "sy_flag");

    /**
     * 拼音简写
     */
    public final QueryColumn SY_PYJZ = new QueryColumn(this, "sy_pyjz");

    /**
     * 拼音全称
     */
    public final QueryColumn SY_PYQC = new QueryColumn(this, "sy_pyqc");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

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
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门id
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人id
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 登记者所在部门
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
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 编码
     */
    public final QueryColumn RESOURCEFIELD_CODE = new QueryColumn(this, "resourcefield_code");

    /**
     * 列数(分组框用)
     */
    public final QueryColumn RESOURCEFIELD_COLS = new QueryColumn(this, "resourcefield_cols");

    /**
     * 比例
     */
    public final QueryColumn RESOURCEFIELD_FLEX = new QueryColumn(this, "resourcefield_flex");

    /**
     * 帮助提醒
     */
    public final QueryColumn RESOURCEFIELD_HELP = new QueryColumn(this, "resourcefield_help");

    /**
     * 是否主键
     */
    public final QueryColumn RESOURCEFIELD_ISPK = new QueryColumn(this, "resourcefield_ispk");

    /**
     * 名称
     */
    public final QueryColumn RESOURCEFIELD_NAME = new QueryColumn(this, "resourcefield_name");

    /**
     * 步长
     */
    public final QueryColumn RESOURCEFIELD_STEP = new QueryColumn(this, "resourcefield_step");

    /**
     * 表单上传虚字段
     */
    public final QueryColumn SY_FORMUPLOADFILES = new QueryColumn(this, "sy_formuploadfiles");

    /**
     * 数据字典带值配置
     */
    public final QueryColumn DIC_BAND_VALUE_CONFIG = new QueryColumn(this, "dic_band_value_config");

    /**
     * 正则表达式
     */
    public final QueryColumn RESOURCEFIELD_REGEX = new QueryColumn(this, "resourcefield_regex");

    /**
     * 默认值
     */
    public final QueryColumn RESOURCEFIELD_VALUE = new QueryColumn(this, "resourcefield_value");

    /**
     * 校验信息
     */
    public final QueryColumn RESOURCEFIELD_VTYPE = new QueryColumn(this, "resourcefield_vtype");

    /**
     * 宽度
     */
    public final QueryColumn RESOURCEFIELD_WIDTH = new QueryColumn(this, "resourcefield_width");

    /**
     * 类型
     */
    public final QueryColumn RESOURCEFIELD_XTYPE = new QueryColumn(this, "resourcefield_xtype");

    /**
     * 高度
     */
    public final QueryColumn RESOURCEFIELD_HEIGHT = new QueryColumn(this, "resourcefield_height");

    /**
     * 是否隐藏
     */
    public final QueryColumn RESOURCEFIELD_HIDDEN = new QueryColumn(this, "resourcefield_hidden");

    /**
     * 是否导入
     */
    public final QueryColumn RESOURCEFIELD_IFIMPL = new QueryColumn(this, "resourcefield_ifimpl");

    /**
     * 保持收起
     */
    public final QueryColumn RESOURCEFIELD_KEEPUP = new QueryColumn(this, "resourcefield_keepup");

    /**
     * 定位
     */
    public final QueryColumn RESOURCEFIELD_LOCATE = new QueryColumn(this, "resourcefield_locate");

    /**
     * 英文名
     */
    public final QueryColumn RESOURCEFIELD_NAME_EN = new QueryColumn(this, "resourcefield_name_en");

    /**
     * 表单方案外键
     */
    public final QueryColumn RESOURCEFIELD_PLAN_ID = new QueryColumn(this, "resourcefield_plan_id");

    /**
     * 根节点id
     */
    public final QueryColumn RESOURCEFIELD_ROOT_ID = new QueryColumn(this, "resourcefield_root_id");

    /**
     * 背景色
     */
    public final QueryColumn RESOURCEFIELD_BGCOLOR = new QueryColumn(this, "resourcefield_bgcolor");

    /**
     * 绑定信息
     */
    public final QueryColumn RESOURCEFIELD_BINDING = new QueryColumn(this, "resourcefield_binding");

    /**
     * 所占列数
     */
    public final QueryColumn RESOURCEFIELD_COLSPAN = new QueryColumn(this, "resourcefield_colspan");

    /**
     * 历史留痕
     */
    public final QueryColumn RESOURCEFIELD_HISTORY = new QueryColumn(this, "resourcefield_history");

    /**
     * 无样式
     */
    public final QueryColumn RESOURCEFIELD_NO_STYLE = new QueryColumn(this, "resourcefield_no_style");

    /**
     * 禁用
     */
    public final QueryColumn RESOURCEFIELD_REMOVED = new QueryColumn(this, "resourcefield_removed");

    /**
     * 必填
     */
    public final QueryColumn RESOURCEFIELD_REQUIRE = new QueryColumn(this, "resourcefield_require");

    /**
     * 所占行数
     */
    public final QueryColumn RESOURCEFIELD_ROWSPAN = new QueryColumn(this, "resourcefield_rowspan");

    /**
     * saas产品
     */
    public final QueryColumn RESOURCEFIELD_SAAS_PID = new QueryColumn(this, "resourcefield_saas_pid");

    /**
     * 系统模式
     */
    public final QueryColumn RESOURCEFIELD_SYSMODE = new QueryColumn(this, "resourcefield_sysmode");

    /**
     * 字段后缀
     */
    public final QueryColumn RESOURCEFIELD_UNITTPL = new QueryColumn(this, "resourcefield_unittpl");

    /**
     * 默认值函数
     */
    public final QueryColumn RESOURCEFIELD_VALUEFN = new QueryColumn(this, "resourcefield_valuefn");

    /**
     * 自定义验证方法
     */
    public final QueryColumn RESOURCEFIELD_VTYPEFN = new QueryColumn(this, "resourcefield_vtypefn");

    /**
     * 可添加
     */
    public final QueryColumn RESOURCEFIELD_ALLOW_ADD = new QueryColumn(this, "resourcefield_allow_add");

    /**
     * 可删除
     */
    public final QueryColumn RESOURCEFIELD_ALLOW_DEL = new QueryColumn(this, "resourcefield_allow_del");

    /**
     * 可批量添加
     */
    public final QueryColumn RESOURCEFIELD_BATCH_ADD = new QueryColumn(this, "resourcefield_batch_add");

    /**
     * 可批量删除
     */
    public final QueryColumn RESOURCEFIELD_BATCH_DEL = new QueryColumn(this, "resourcefield_batch_del");

    /**
     * 是否可用
     */
    public final QueryColumn RESOURCEFIELD_DISABLED = new QueryColumn(this, "resourcefield_disabled");

    /**
     * 可选可编辑
     */
    public final QueryColumn RESOURCEFIELD_EDITABLE = new QueryColumn(this, "resourcefield_editable");

    /**
     * 字段样式表
     */
    public final QueryColumn RESOURCEFIELD_FIELDCLS = new QueryColumn(this, "resourcefield_fieldcls");

    /**
     * 上传文件大小
     */
    public final QueryColumn RESOURCEFIELD_FILE_SIZE = new QueryColumn(this, "resourcefield_file_size");

    /**
     * 帮助提示高
     */
    public final QueryColumn RESOURCEFIELD_HELP_TIP_H = new QueryColumn(this, "resourcefield_help_tip_h");

    /**
     * 帮助提示宽
     */
    public final QueryColumn RESOURCEFIELD_HELP_TIP_W = new QueryColumn(this, "resourcefield_help_tip_w");

    /**
     * 最大值
     */
    public final QueryColumn RESOURCEFIELD_MAXVALUE = new QueryColumn(this, "resourcefield_maxvalue");

    /**
     * 最小值
     */
    public final QueryColumn RESOURCEFIELD_MINVALUE = new QueryColumn(this, "resourcefield_minvalue");

    /**
     * 禁止上传类型
     */
    public final QueryColumn RESOURCEFIELD_NO_UPLOAD = new QueryColumn(this, "resourcefield_no_upload");

    /**
     * 是否只读
     */
    public final QueryColumn RESOURCEFIELD_READONLY = new QueryColumn(this, "resourcefield_readonly");

    /**
     * 依附字段
     */
    public final QueryColumn RESOURCEFIELD_ROWFIELD = new QueryColumn(this, "resourcefield_rowfield");

    /**
     * 使用范围
     */
    public final QueryColumn RESOURCEFIELD_USE_SCOPE = new QueryColumn(this, "resourcefield_use_scope");

    /**
     * wheresql
     */
    public final QueryColumn RESOURCEFIELD_WHERESQL = new QueryColumn(this, "resourcefield_wheresql");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_RESOURCEFIELD_ID = new QueryColumn(this, "tb_core_resourcefield_id");

    /**
     * 基础色
     */
    public final QueryColumn RESOURCEFIELD_BASE_COLOR = new QueryColumn(this, "resourcefield_base_color");

    /**
     * 绑定表达式方法
     */
    public final QueryColumn RESOURCEFIELD_BINDINGFN = new QueryColumn(this, "resourcefield_bindingfn");

    /**
     * 允许上传类型
     */
    public final QueryColumn RESOURCEFIELD_CAN_UPLOAD = new QueryColumn(this, "resourcefield_can_upload");

    /**
     * 输入提示
     */
    public final QueryColumn RESOURCEFIELD_EMPTYTEXT = new QueryColumn(this, "resourcefield_emptytext");

    /**
     * 可选表达式
     */
    public final QueryColumn RESOURCEFIELD_ENABLEEXP = new QueryColumn(this, "resourcefield_enableexp");

    /**
     * 可选表达式提示语
     */
    public final QueryColumn RESOURCEFIELD_ENABLETIP = new QueryColumn(this, "resourcefield_enabletip");

    /**
     * 字段懒加载
     */
    public final QueryColumn RESOURCEFIELD_FIELDLAZY = new QueryColumn(this, "resourcefield_fieldlazy");

    /**
     * 分组框
     */
    public final QueryColumn RESOURCEFIELD_GROUPNAME = new QueryColumn(this, "resourcefield_groupname");

    /**
     * 隐藏标签
     */
    public final QueryColumn RESOURCEFIELD_HIDELABEL = new QueryColumn(this, "resourcefield_hidelabel");

    /**
     * 字段长度
     */
    public final QueryColumn RESOURCEFIELD_MAXLENGTH = new QueryColumn(this, "resourcefield_maxlength");

    /**
     * 产品扩展功能id
     */
    public final QueryColumn RESOURCEFIELD_NEWFUNCID = new QueryColumn(this, "resourcefield_newfuncid");

    /**
     * 数字提醒
     */
    public final QueryColumn RESOURCEFIELD_NUMBER_TIP = new QueryColumn(this, "resourcefield_number_tip");

    /**
     * 正则提示信息
     */
    public final QueryColumn RESOURCEFIELD_REGEXTEXT = new QueryColumn(this, "resourcefield_regextext");

    /**
     * 展示字段
     */
    public final QueryColumn RESOURCEFIELD_SHOW_FIELD = new QueryColumn(this, "resourcefield_show_field");

    /**
     * 上边框色
     */
    public final QueryColumn RESOURCEFIELD_ABOVE_COLOR = new QueryColumn(this, "resourcefield_above_color");

    /**
     * 是否为空
     */
    public final QueryColumn RESOURCEFIELD_ALLOWBLANK = new QueryColumn(this, "resourcefield_allowblank");

    /**
     * 可穿透
     */
    public final QueryColumn RESOURCEFIELD_CAN_THROUGH = new QueryColumn(this, "resourcefield_can_through");

    /**
     * 配置信息
     */
    public final QueryColumn RESOURCEFIELD_CONFIGINFO = new QueryColumn(this, "resourcefield_configinfo");

    /**
     * 字段颜色
     */
    public final QueryColumn RESOURCEFIELD_FIELDCOLOR = new QueryColumn(this, "resourcefield_fieldcolor");

    /**
     * 功能外键
     */
    public final QueryColumn RESOURCEFIELD_FUNCINFO_ID = new QueryColumn(this, "resourcefield_funcinfo_id");

    /**
     * 岗位过滤
     */
    public final QueryColumn RESOURCEFIELD_JOBS_FILTER = new QueryColumn(this, "resourcefield_jobs_filter");

    /**
     * 监听事件
     */
    public final QueryColumn RESOURCEFIELD_JSLISTENER = new QueryColumn(this, "resourcefield_jslistener");

    /**
     * 标签位置
     */
    public final QueryColumn RESOURCEFIELD_LABELALIGN = new QueryColumn(this, "resourcefield_labelalign");

    /**
     * 标签颜色
     */
    public final QueryColumn RESOURCEFIELD_LABELCOLOR = new QueryColumn(this, "resourcefield_labelcolor");

    /**
     * 弹出面板宽
     */
    public final QueryColumn RESOURCEFIELD_POPUP_WIDTH = new QueryColumn(this, "resourcefield_popup_width");

    /**
     * 查询字段
     */
    public final QueryColumn RESOURCEFIELD_QUERY_FIELD = new QueryColumn(this, "resourcefield_query_field");

    /**
     * 必填表达式
     */
    public final QueryColumn RESOURCEFIELD_REQUIRE_EXP = new QueryColumn(this, "resourcefield_require_exp");

    /**
     * 角色过滤
     */
    public final QueryColumn RESOURCEFIELD_ROLE_FILTER = new QueryColumn(this, "resourcefield_role_filter");

    /**
     * 标题色
     */
    public final QueryColumn RESOURCEFIELD_TITLE_COLOR = new QueryColumn(this, "resourcefield_title_color");

    /**
     * 组内标题宽
     */
    public final QueryColumn RESOURCEFIELD_TITLE_WIDTH = new QueryColumn(this, "resourcefield_title_width");

    /**
     * 边框色
     */
    public final QueryColumn RESOURCEFIELD_BORDER_COLOR = new QueryColumn(this, "resourcefield_border_color");

    /**
     * 按钮位置
     */
    public final QueryColumn RESOURCEFIELD_BUT_POSITION = new QueryColumn(this, "resourcefield_but_position");

    /**
     * 描述
     */
    public final QueryColumn RESOURCEFIELD_DESCRIPTION = new QueryColumn(this, "resourcefield_description");

    /**
     * 树形字典项可选表达式
     */
    public final QueryColumn RESOURCEFIELD_ENABLEEXPFN = new QueryColumn(this, "resourcefield_enableexpfn");

    /**
     * 显隐控制表达式
     */
    public final QueryColumn RESOURCEFIELD_INTERPRETER = new QueryColumn(this, "resourcefield_interpreter");

    /**
     * 级联数字提醒
     */
    public final QueryColumn RESOURCEFIELD_JL_NUMBER_TIP = new QueryColumn(this, "resourcefield_jl_number_tip");

    /**
     * 数字颜色
     */
    public final QueryColumn RESOURCEFIELD_NUMBER_COLOR = new QueryColumn(this, "resourcefield_number_color");

    /**
     * 辅助配置信息
     */
    public final QueryColumn RESOURCEFIELD_OTHERCONFIG = new QueryColumn(this, "resourcefield_otherconfig");

    /**
     * 弹出面板高
     */
    public final QueryColumn RESOURCEFIELD_POPUP_HEIGHT = new QueryColumn(this, "resourcefield_popup_height");

    /**
     * 只读表达式
     */
    public final QueryColumn RESOURCEFIELD_READONLYEXP = new QueryColumn(this, "resourcefield_readonlyexp");

    /**
     * 显示子功能
     */
    public final QueryColumn RESOURCEFIELD_SHOW_SUB_FUNC = new QueryColumn(this, "resourcefield_show_sub_func");

    /**
     * 标题背景色
     */
    public final QueryColumn RESOURCEFIELD_TITLEB_COLOR = new QueryColumn(this, "resourcefield_titleb_color");

    /**
     * 高级后缀
     */
    public final QueryColumn RESOURCEFIELD_UNITLISTTPL = new QueryColumn(this, "resourcefield_unitlisttpl");

    /**
     * 文件上传数量
     */
    public final QueryColumn RESOURCEFIELD_UPLOAD_COUNT = new QueryColumn(this, "resourcefield_upload_count");

    /**
     * 验证配置
     */
    public final QueryColumn RESOURCEFIELD_VTYPECONFIG = new QueryColumn(this, "resourcefield_vtypeconfig");

    /**
     * 批量添加类型
     */
    public final QueryColumn RESOURCEFIELD_BATCH_ADD_TYPE = new QueryColumn(this, "resourcefield_batch_add_type");

    /**
     * 是否多根树
     */
    public final QueryColumn RESOURCEFIELD_MULTIPLE_ROOT = new QueryColumn(this, "resourcefield_multiple_root");

    /**
     * 必填控制表达式方法
     */
    public final QueryColumn RESOURCEFIELD_REQUIRE_EXPFN = new QueryColumn(this, "resourcefield_require_expfn");

    /**
     * 显示主功能
     */
    public final QueryColumn RESOURCEFIELD_SHOW_MAIN_FUNC = new QueryColumn(this, "resourcefield_show_main_func");

    /**
     * 必填表达式
     */
    public final QueryColumn RESOURCEFIELD_ALLOWBLANKEXP = new QueryColumn(this, "resourcefield_allowblankexp");

    /**
     * 条件说明
     */
    public final QueryColumn RESOURCEFIELD_CONDITION_DESC = new QueryColumn(this, "resourcefield_condition_desc");

    /**
     * 文件操作权限
     */
    public final QueryColumn RESOURCEFIELD_FILE_AUTHORITY = new QueryColumn(this, "resourcefield_file_authority");

    /**
     * 显隐控制表达式方法
     */
    public final QueryColumn RESOURCEFIELD_INTERPRETERFN = new QueryColumn(this, "resourcefield_interpreterfn");

    /**
     * 只读控制表达式方法
     */
    public final QueryColumn RESOURCEFIELD_READONLYEXPFN = new QueryColumn(this, "resourcefield_readonlyexpfn");

    /**
     * 批量添加配置
     */
    public final QueryColumn RESOURCEFIELD_BATCH_ADD_CONFIG = new QueryColumn(this, "resourcefield_batch_add_config");

    /**
     * 快查取值字段
     */
    public final QueryColumn RESOURCEFIELD_FAST_QUERY_VALUE = new QueryColumn(this, "resourcefield_fast_query_value");

    /**
     * 树形展开层数
     */
    public final QueryColumn RESOURCEFIELD_TREE_OPEN_HEIGHT = new QueryColumn(this, "resourcefield_tree_open_height");

    /**
     * 必填控制表达式方法
     */
    public final QueryColumn RESOURCEFIELD_ALLOWBLANKEXPFN = new QueryColumn(this, "resourcefield_allowblankexpfn");

    /**
     * 工作组过滤
     */
    public final QueryColumn RESOURCEFIELD_WORK_GROUP_FILTER = new QueryColumn(this, "resourcefield_work_group_filter");

    /**
     * 回选过滤字段
     */
    public final QueryColumn RESOURCEFIELD_AGAIN_FILTER_FIELD = new QueryColumn(this, "resourcefield_again_filter_field");

    /**
     * 部门过滤
     */
    public final QueryColumn RESOURCEFIELD_DEPARTMENT_FILTER = new QueryColumn(this, "resourcefield_department_filter");

    /**
     * 快查条件
     */
    public final QueryColumn RESOURCEFIELD_FAST_QUERY_CONDITION = new QueryColumn(this, "resourcefield_fast_query_condition");

    /**
     * 字典自定义变量
     */
    public final QueryColumn RESOURCEFIELD_DD_CUSTOMER_VARIABLES = new QueryColumn(this, "resourcefield_dd_customer_variables");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_RESOURCEFIELD_ID, RESOURCEFIELD_CODE, RESOURCEFIELD_NAME, RESOURCEFIELD_WIDTH, RESOURCEFIELD_FLEX, RESOURCEFIELD_XTYPE, RESOURCEFIELD_ALLOWBLANK, RESOURCEFIELD_HIDDEN, RESOURCEFIELD_IFIMPL, RESOURCEFIELD_DISABLED, RESOURCEFIELD_READONLY, RESOURCEFIELD_ISPK, RESOURCEFIELD_CONFIGINFO, RESOURCEFIELD_VTYPE, RESOURCEFIELD_DESCRIPTION, RESOURCEFIELD_GROUPNAME, RESOURCEFIELD_COLSPAN, RESOURCEFIELD_FIELDCLS, RESOURCEFIELD_COLS, RESOURCEFIELD_ROWSPAN, RESOURCEFIELD_MINVALUE, RESOURCEFIELD_MAXVALUE, RESOURCEFIELD_STEP, RESOURCEFIELD_VALUE, RESOURCEFIELD_EDITABLE, RESOURCEFIELD_BINDING, RESOURCEFIELD_REGEX, RESOURCEFIELD_REGEXTEXT, RESOURCEFIELD_SYSMODE, RESOURCEFIELD_NEWFUNCID, RESOURCEFIELD_OTHERCONFIG, RESOURCEFIELD_JSLISTENER, RESOURCEFIELD_INTERPRETER, RESOURCEFIELD_EMPTYTEXT, RESOURCEFIELD_FUNCINFO_ID, RESOURCEFIELD_BGCOLOR, RESOURCEFIELD_WHERESQL, RESOURCEFIELD_HEIGHT, RESOURCEFIELD_HISTORY, RESOURCEFIELD_VTYPECONFIG, RESOURCEFIELD_ALLOWBLANKEXP, RESOURCEFIELD_READONLYEXP, RESOURCEFIELD_ENABLEEXP, RESOURCEFIELD_ENABLETIP, RESOURCEFIELD_LOCATE, RESOURCEFIELD_UNITTPL, RESOURCEFIELD_INTERPRETERFN, RESOURCEFIELD_ALLOWBLANKEXPFN, RESOURCEFIELD_ENABLEEXPFN, RESOURCEFIELD_READONLYEXPFN, RESOURCEFIELD_BINDINGFN, RESOURCEFIELD_VALUEFN, RESOURCEFIELD_LABELCOLOR, RESOURCEFIELD_FIELDCOLOR, RESOURCEFIELD_REMOVED, RESOURCEFIELD_NAME_EN, RESOURCEFIELD_UNITLISTTPL, RESOURCEFIELD_ROWFIELD, RESOURCEFIELD_FIELDLAZY, RESOURCEFIELD_VTYPEFN, RESOURCEFIELD_LABELALIGN, RESOURCEFIELD_HIDELABEL, RESOURCEFIELD_MAXLENGTH, RESOURCEFIELD_HELP, RESOURCEFIELD_KEEPUP, DIC_BAND_VALUE_CONFIG, RESOURCEFIELD_NUMBER_TIP, RESOURCEFIELD_JL_NUMBER_TIP, RESOURCEFIELD_TREE_OPEN_HEIGHT, RESOURCEFIELD_ROOT_ID, RESOURCEFIELD_MULTIPLE_ROOT, RESOURCEFIELD_HELP_TIP_W, RESOURCEFIELD_HELP_TIP_H, RESOURCEFIELD_CAN_UPLOAD, RESOURCEFIELD_NO_UPLOAD, RESOURCEFIELD_FILE_SIZE, RESOURCEFIELD_BUT_POSITION, RESOURCEFIELD_ALLOW_DEL, RESOURCEFIELD_BATCH_DEL, RESOURCEFIELD_ALLOW_ADD, RESOURCEFIELD_BATCH_ADD, RESOURCEFIELD_BATCH_ADD_TYPE, RESOURCEFIELD_BATCH_ADD_CONFIG, RESOURCEFIELD_UPLOAD_COUNT, RESOURCEFIELD_NUMBER_COLOR, RESOURCEFIELD_FILE_AUTHORITY, RESOURCEFIELD_BASE_COLOR, RESOURCEFIELD_TITLE_COLOR, RESOURCEFIELD_TITLEB_COLOR, RESOURCEFIELD_BORDER_COLOR, RESOURCEFIELD_ABOVE_COLOR, RESOURCEFIELD_NO_STYLE, RESOURCEFIELD_CAN_THROUGH, RESOURCEFIELD_SHOW_MAIN_FUNC, RESOURCEFIELD_SHOW_SUB_FUNC, RESOURCEFIELD_SHOW_FIELD, RESOURCEFIELD_QUERY_FIELD, RESOURCEFIELD_POPUP_WIDTH, RESOURCEFIELD_POPUP_HEIGHT, RESOURCEFIELD_DEPARTMENT_FILTER, RESOURCEFIELD_JOBS_FILTER, RESOURCEFIELD_ROLE_FILTER, RESOURCEFIELD_WORK_GROUP_FILTER, RESOURCEFIELD_AGAIN_FILTER_FIELD, RESOURCEFIELD_FAST_QUERY_CONDITION, RESOURCEFIELD_TITLE_WIDTH, RESOURCEFIELD_PLAN_ID, RESOURCEFIELD_REQUIRE, RESOURCEFIELD_REQUIRE_EXP, RESOURCEFIELD_CONDITION_DESC, RESOURCEFIELD_FAST_QUERY_VALUE, RESOURCEFIELD_REQUIRE_EXPFN, RESOURCEFIELD_SAAS_PID, RESOURCEFIELD_USE_SCOPE, RESOURCEFIELD_DD_CUSTOMER_VARIABLES, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PYJZ, SY_PYQC, SY_FORMUPLOADFILES};

    public TbCoreResourcefieldTableDef() {
        super("", "tb_core_resourcefield");
    }

    private TbCoreResourcefieldTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreResourcefieldTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreResourcefieldTableDef("", "tb_core_resourcefield", alias));
    }

}
