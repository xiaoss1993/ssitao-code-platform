package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 列字段 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreResourcecolumnTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 列字段
     */
    public static final TbCoreResourcecolumnTableDef TB_CORE_RESOURCECOLUMN = new TbCoreResourcecolumnTableDef();

    /**
     * 是否启用本条数据
     */
    public final QueryColumn SY_FLAG = new QueryColumn(this, "sy_flag");

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
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

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
     * 表单上传虚字段
     */
    public final QueryColumn SY_FORMUPLOADFILES = new QueryColumn(this, "sy_formuploadfiles");

    /**
     * 编码
     */
    public final QueryColumn RESOURCECOLUMN_CODE = new QueryColumn(this, "resourcecolumn_code");

    /**
     * 比例
     */
    public final QueryColumn RESOURCECOLUMN_FLEX = new QueryColumn(this, "resourcecolumn_flex");

    /**
     * 个性化
     */
    public final QueryColumn RESOURCECOLUMN_IDIT = new QueryColumn(this, "resourcecolumn_idit");

    /**
     * 是否在字典中取
     */
    public final QueryColumn RESOURCECOLUMN_ISDD = new QueryColumn(this, "resourcecolumn_isdd");

    /**
     * 是否主键
     */
    public final QueryColumn RESOURCECOLUMN_ISPK = new QueryColumn(this, "resourcecolumn_ispk");

    /**
     * 名称
     */
    public final QueryColumn RESOURCECOLUMN_NAME = new QueryColumn(this, "resourcecolumn_name");

    /**
     * 步长
     */
    public final QueryColumn RESOURCECOLUMN_STEP = new QueryColumn(this, "resourcecolumn_step");

    /**
     * 列居中
     */
    public final QueryColumn RESOURCECOLUMN_ALIGN = new QueryColumn(this, "resourcecolumn_align");

    /**
     * 分组
     */
    public final QueryColumn RESOURCECOLUMN_GROUP = new QueryColumn(this, "resourcecolumn_group");

    /**
     * 是否索引
     */
    public final QueryColumn RESOURCECOLUMN_INDEX = new QueryColumn(this, "resourcecolumn_index");

    /**
     * 合并单元格
     */
    public final QueryColumn RESOURCECOLUMN_MERGE = new QueryColumn(this, "resourcecolumn_merge");

    /**
     * 是否排序
     */
    public final QueryColumn RESOURCECOLUMN_ORDER = new QueryColumn(this, "resourcecolumn_order");

    /**
     * 默认值
     */
    public final QueryColumn RESOURCECOLUMN_VALUE = new QueryColumn(this, "resourcecolumn_value");

    /**
     * 校验信息
     */
    public final QueryColumn RESOURCECOLUMN_VTYPE = new QueryColumn(this, "resourcecolumn_vtype");

    /**
     * 宽度
     */
    public final QueryColumn RESOURCECOLUMN_WIDTH = new QueryColumn(this, "resourcecolumn_width");

    /**
     * 类型
     */
    public final QueryColumn RESOURCECOLUMN_XTYPE = new QueryColumn(this, "resourcecolumn_xtype");

    /**
     * 高亮策略中的color
     */
    public final QueryColumn RESOURCECOLUMN_COLOR1 = new QueryColumn(this, "resourcecolumn_color1");

    /**
     * 字典策略中的color
     */
    public final QueryColumn RESOURCECOLUMN_COLOR2 = new QueryColumn(this, "resourcecolumn_color2");

    /**
     * 字典编码
     */
    public final QueryColumn RESOURCECOLUMN_DDCODE = new QueryColumn(this, "resourcecolumn_ddcode");

    /**
     * 编码
     */
    public final QueryColumn RESOURCECOLUMN_FKCODE = new QueryColumn(this, "resourcecolumn_fkcode");

    /**
     * 是否隐藏
     */
    public final QueryColumn RESOURCECOLUMN_HIDDEN = new QueryColumn(this, "resourcecolumn_hidden");

    /**
     * 是否导入
     */
    public final QueryColumn RESOURCECOLUMN_IFIMPL = new QueryColumn(this, "resourcecolumn_ifimpl");

    /**
     * 锁定列
     */
    public final QueryColumn RESOURCECOLUMN_LOCKED = new QueryColumn(this, "resourcecolumn_locked");

    /**
     * 英文名
     */
    public final QueryColumn RESOURCECOLUMN_NAME_EN = new QueryColumn(this, "resourcecolumn_name_en");

    /**
     * 列表方案外键
     */
    public final QueryColumn RESOURCECOLUMN_PLAN_ID = new QueryColumn(this, "resourcecolumn_plan_id");

    /**
     * 关键字查询组建名称
     */
    public final QueryColumn RESOURCECOLUMN_KEYWORD = new QueryColumn(this, "resourcecolumn_keyword");

    /**
     * saas产品
     */
    public final QueryColumn RESOURCECOLUMN_SAAS_PID = new QueryColumn(this, "resourcecolumn_saas_pid");

    /**
     * 系统模式
     */
    public final QueryColumn RESOURCECOLUMN_SYSMODE = new QueryColumn(this, "resourcecolumn_sysmode");

    /**
     * 策略
     */
    public final QueryColumn RESOURCECOLUMN_TACTICS = new QueryColumn(this, "resourcecolumn_tactics");

    /**
     * 英文宽度
     */
    public final QueryColumn RESOURCECOLUMN_WIDTH_EN = new QueryColumn(this, "resourcecolumn_width_en");

    /**
     * 自动填充
     */
    public final QueryColumn RESOURCECOLUMN_AUTO_FILL = new QueryColumn(this, "resourcecolumn_auto_fill");

    /**
     * 分步加载
     */
    public final QueryColumn RESOURCECOLUMN_LAZYLOAD = new QueryColumn(this, "resourcecolumn_lazyload");

    /**
     * 最大值
     */
    public final QueryColumn RESOURCECOLUMN_MAXVALUE = new QueryColumn(this, "resourcecolumn_maxvalue");

    /**
     * 最小值
     */
    public final QueryColumn RESOURCECOLUMN_MINVALUE = new QueryColumn(this, "resourcecolumn_minvalue");

    
    public final QueryColumn TB_CORE_RESOURCECOLUMN_ID = new QueryColumn(this, "tb_core_resourcecolumn_id");

    /**
     * 是否编辑
     */
    public final QueryColumn RESOURCECOLUMN_ALLOWEDIT = new QueryColumn(this, "resourcecolumn_allowedit");

    /**
     * 列提示
     */
    public final QueryColumn RESOURCECOLUMN_COLUMNTIP = new QueryColumn(this, "resourcecolumn_columntip");

    /**
     * 字典策略中的color
     */
    public final QueryColumn RESOURCECOLUMN_FONTCOLOR = new QueryColumn(this, "resourcecolumn_fontcolor");

    /**
     * 是否超链接
     */
    public final QueryColumn RESOURCECOLUMN_HYPERLINK = new QueryColumn(this, "resourcecolumn_hyperlink");

    /**
     * 锁定后列
     */
    public final QueryColumn RESOURCECOLUMN_LOCK_AFTER = new QueryColumn(this, "resourcecolumn_lock_after");

    /**
     * 多行显示
     */
    public final QueryColumn RESOURCECOLUMN_MULTIROWS = new QueryColumn(this, "resourcecolumn_multirows");

    /**
     * 产品扩展功能id
     */
    public final QueryColumn RESOURCECOLUMN_NEWFUNCID = new QueryColumn(this, "resourcecolumn_newfuncid");

    /**
     * 查询信息
     */
    public final QueryColumn RESOURCECOLUMN_QUERYINFO = new QueryColumn(this, "resourcecolumn_queryinfo");

    /**
     * 查询类型
     */
    public final QueryColumn RESOURCECOLUMN_QUERYTYPE = new QueryColumn(this, "resourcecolumn_querytype");

    /**
     * 标题对齐方式
     */
    public final QueryColumn RESOURCECOLUMN_ALIGN_TITLE = new QueryColumn(this, "resourcecolumn_align_title");

    /**
     * 是否为空
     */
    public final QueryColumn RESOURCECOLUMN_ALLOWBLANK = new QueryColumn(this, "resourcecolumn_allowblank");

    /**
     * 选中样式
     */
    public final QueryColumn RESOURCECOLUMN_CHECKEDCLS = new QueryColumn(this, "resourcecolumn_checkedcls");

    /**
     * 配置信息
     */
    public final QueryColumn RESOURCECOLUMN_CONFIGINFO = new QueryColumn(this, "resourcecolumn_configinfo");

    /**
     * 是否拥转换器conversion
     */
    public final QueryColumn RESOURCECOLUMN_CONVERSION = new QueryColumn(this, "resourcecolumn_conversion");

    /**
     * 时间格式
     */
    public final QueryColumn RESOURCECOLUMN_DATEFORMAT = new QueryColumn(this, "resourcecolumn_dateformat");

    /**
     * 启用图标
     */
    public final QueryColumn RESOURCECOLUMN_ENABLEICON = new QueryColumn(this, "resourcecolumn_enableicon");

    /**
     * 表单列
     */
    public final QueryColumn RESOURCECOLUMN_FORMCOLUMN = new QueryColumn(this, "resourcecolumn_formcolumn");

    /**
     * 功能外键
     */
    public final QueryColumn RESOURCECOLUMN_FUNCINFO_ID = new QueryColumn(this, "resourcecolumn_funcinfo_id");

    /**
     * 监听事件
     */
    public final QueryColumn RESOURCECOLUMN_JSLISTENER = new QueryColumn(this, "resourcecolumn_jslistener");

    /**
     * 链接方法
     */
    public final QueryColumn RESOURCECOLUMN_LINKMETHOD = new QueryColumn(this, "resourcecolumn_linkmethod");

    /**
     * 多表头
     */
    public final QueryColumn RESOURCECOLUMN_MORECOLUMN = new QueryColumn(this, "resourcecolumn_morecolumn");

    /**
     * 查询排序
     */
    public final QueryColumn RESOURCECOLUMN_QUERYINDEX = new QueryColumn(this, "resourcecolumn_queryindex");

    /**
     * 是否快速查询
     */
    public final QueryColumn RESOURCECOLUMN_QUICKQUERY = new QueryColumn(this, "resourcecolumn_quickquery");

    /**
     * 颜色
     */
    public final QueryColumn RESOURCECOLUMN_TITLECOLOR = new QueryColumn(this, "resourcecolumn_titlecolor");

    /**
     * 字段增量类型
     */
    public final QueryColumn RESOURCECOLUMN_BULKING_TYPE = new QueryColumn(this, "resourcecolumn_bulking_type");

    /**
     * 描述
     */
    public final QueryColumn RESOURCECOLUMN_DESCRIPTION = new QueryColumn(this, "resourcecolumn_description");

    /**
     * 开启更多按钮
     */
    public final QueryColumn RESOURCECOLUMN_OPENMOREBUT = new QueryColumn(this, "resourcecolumn_openmorebut");

    /**
     * 辅助配置项
     */
    public final QueryColumn RESOURCECOLUMN_OTHERCONFIG = new QueryColumn(this, "resourcecolumn_otherconfig");

    /**
     * 启用批量更新
     */
    public final QueryColumn RESOURCECOLUMN_ENABLEUPDATE = new QueryColumn(this, "resourcecolumn_enableupdate");

    /**
     * 隐藏编辑提示
     */
    public final QueryColumn RESOURCECOLUMN_HIDETITLECLS = new QueryColumn(this, "resourcecolumn_hidetitlecls");

    /**
     * 是否拥转换器highlighting
     */
    public final QueryColumn RESOURCECOLUMN_HIGHLIGHTING = new QueryColumn(this, "resourcecolumn_highlighting");

    /**
     * 右边框色
     */
    public final QueryColumn RESOURCECOLUMN_RBORDERCOLOR = new QueryColumn(this, "resourcecolumn_rbordercolor");

    /**
     * 补选中样式
     */
    public final QueryColumn RESOURCECOLUMN_UNCHECKEDCLS = new QueryColumn(this, "resourcecolumn_uncheckedcls");

    /**
     * 复选框样式
     */
    public final QueryColumn RESOURCECOLUMN_CHECKBOX_STYLE = new QueryColumn(this, "resourcecolumn_checkbox_style");

    /**
     * 锁定前列
     */
    public final QueryColumn RESOURCECOLUMN_LOCK_FOREFRONT = new QueryColumn(this, "resourcecolumn_lock_forefront");

    /**
     * 更多按钮配置
     */
    public final QueryColumn RESOURCECOLUMN_MOREBUTCONFIG = new QueryColumn(this, "resourcecolumn_morebutconfig");

    /**
     * 编辑时全选
     */
    public final QueryColumn RESOURCECOLUMN_SELECTONFOCUS = new QueryColumn(this, "resourcecolumn_selectonfocus");

    /**
     * 总统计描述
     */
    public final QueryColumn RESOURCECOLUMN_STATISTALLMSG = new QueryColumn(this, "resourcecolumn_statistallmsg");

    /**
     * 统计描述
     */
    public final QueryColumn RESOURCECOLUMN_STATISTICSMSG = new QueryColumn(this, "resourcecolumn_statisticsmsg");

    /**
     * 统计数字格式
     */
    public final QueryColumn RESOURCECOLUMN_SUMMARYFORMAT = new QueryColumn(this, "resourcecolumn_summaryformat");

    /**
     * 快查带值
     */
    public final QueryColumn RESOURCECOLUMN_CHECKBANDVALUE = new QueryColumn(this, "resourcecolumn_checkbandvalue");

    /**
     * 多表头名称
     */
    public final QueryColumn RESOURCECOLUMN_MORECOLUMNNAME = new QueryColumn(this, "resourcecolumn_morecolumnname");

    /**
     * 快速查询时查询类型
     */
    public final QueryColumn RESOURCECOLUMN_QUICKQUERYTYPE = new QueryColumn(this, "resourcecolumn_quickquerytype");

    /**
     * 统计类型
     */
    public final QueryColumn RESOURCECOLUMN_STATISTICSTYPE = new QueryColumn(this, "resourcecolumn_statisticstype");

    /**
     * 列查询类型
     */
    public final QueryColumn RESOURCECOLUMN_COLUMNQUERYTYPE = new QueryColumn(this, "resourcecolumn_columnquerytype");

    /**
     * 启用超级链接
     */
    public final QueryColumn RESOURCECOLUMN_ENABLHYPERLINKS = new QueryColumn(this, "resourcecolumn_enablhyperlinks");

    /**
     * 列表排序字段
     */
    public final QueryColumn RESOURCECOLUMN_FIELDORDERINDEX = new QueryColumn(this, "resourcecolumn_fieldorderindex");

    /**
     * 列表快速查询
     */
    public final QueryColumn RESOURCECOLUMN_QUICKQUERYVALUE = new QueryColumn(this, "resourcecolumn_quickqueryvalue");

    /**
     * 统计配置信息
     */
    public final QueryColumn RESOURCECOLUMN_STATISTICSCONFIG = new QueryColumn(this, "resourcecolumn_statisticsconfig");

    /**
     * 统计策略
     */
    public final QueryColumn RESOURCECOLUMN_STATISTICALSTRATEGY = new QueryColumn(this, "resourcecolumn_statisticalstrategy");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_RESOURCECOLUMN_ID, RESOURCECOLUMN_DDCODE, RESOURCECOLUMN_ISDD, RESOURCECOLUMN_COLOR1, RESOURCECOLUMN_COLOR2, RESOURCECOLUMN_FONTCOLOR, RESOURCECOLUMN_MERGE, RESOURCECOLUMN_KEYWORD, RESOURCECOLUMN_CONVERSION, RESOURCECOLUMN_HIGHLIGHTING, RESOURCECOLUMN_HYPERLINK, RESOURCECOLUMN_LINKMETHOD, RESOURCECOLUMN_ALLOWEDIT, RESOURCECOLUMN_SELECTONFOCUS, RESOURCECOLUMN_MINVALUE, RESOURCECOLUMN_MAXVALUE, RESOURCECOLUMN_STEP, RESOURCECOLUMN_CODE, RESOURCECOLUMN_NAME, RESOURCECOLUMN_WIDTH, RESOURCECOLUMN_FLEX, RESOURCECOLUMN_XTYPE, RESOURCECOLUMN_QUERYTYPE, RESOURCECOLUMN_QUICKQUERYTYPE, RESOURCECOLUMN_QUICKQUERY, RESOURCECOLUMN_ALLOWBLANK, RESOURCECOLUMN_HIDDEN, RESOURCECOLUMN_IFIMPL, RESOURCECOLUMN_ISPK, RESOURCECOLUMN_QUERYINFO, RESOURCECOLUMN_CONFIGINFO, RESOURCECOLUMN_TACTICS, RESOURCECOLUMN_VTYPE, RESOURCECOLUMN_DESCRIPTION, RESOURCECOLUMN_VALUE, RESOURCECOLUMN_LOCKED, RESOURCECOLUMN_MORECOLUMN, RESOURCECOLUMN_MORECOLUMNNAME, RESOURCECOLUMN_STATISTICSTYPE, RESOURCECOLUMN_STATISTICSMSG, RESOURCECOLUMN_FIELDORDERINDEX, RESOURCECOLUMN_QUERYINDEX, RESOURCECOLUMN_MULTIROWS, RESOURCECOLUMN_DATEFORMAT, RESOURCECOLUMN_SYSMODE, RESOURCECOLUMN_NEWFUNCID, RESOURCECOLUMN_FKCODE, RESOURCECOLUMN_ENABLEICON, RESOURCECOLUMN_ALIGN, RESOURCECOLUMN_COLUMNTIP, RESOURCECOLUMN_FUNCINFO_ID, RESOURCECOLUMN_JSLISTENER, RESOURCECOLUMN_INDEX, RESOURCECOLUMN_ORDER, RESOURCECOLUMN_ENABLEUPDATE, RESOURCECOLUMN_CHECKEDCLS, RESOURCECOLUMN_UNCHECKEDCLS, RESOURCECOLUMN_RBORDERCOLOR, RESOURCECOLUMN_HIDETITLECLS, RESOURCECOLUMN_STATISTALLMSG, RESOURCECOLUMN_GROUP, RESOURCECOLUMN_SUMMARYFORMAT, RESOURCECOLUMN_COLUMNQUERYTYPE, RESOURCECOLUMN_LAZYLOAD, RESOURCECOLUMN_TITLECOLOR, RESOURCECOLUMN_NAME_EN, RESOURCECOLUMN_WIDTH_EN, RESOURCECOLUMN_OTHERCONFIG, RESOURCECOLUMN_IDIT, RESOURCECOLUMN_FORMCOLUMN, RESOURCECOLUMN_AUTO_FILL, RESOURCECOLUMN_LOCK_FOREFRONT, RESOURCECOLUMN_LOCK_AFTER, RESOURCECOLUMN_CHECKBANDVALUE, RESOURCECOLUMN_OPENMOREBUT, RESOURCECOLUMN_MOREBUTCONFIG, RESOURCECOLUMN_ENABLHYPERLINKS, RESOURCECOLUMN_ALIGN_TITLE, RESOURCECOLUMN_STATISTICSCONFIG, RESOURCECOLUMN_QUICKQUERYVALUE, RESOURCECOLUMN_PLAN_ID, RESOURCECOLUMN_BULKING_TYPE, RESOURCECOLUMN_CHECKBOX_STYLE, RESOURCECOLUMN_SAAS_PID, RESOURCECOLUMN_STATISTICALSTRATEGY, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_COMPANY_ID, SY_TENANT_ID, SY_TENANT_NAME, SY_AUDFLAG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID, SY_PYJZ, SY_PYQC, SY_FORMUPLOADFILES};

    public TbCoreResourcecolumnTableDef() {
        super("", "tb_core_resourcecolumn");
    }

    private TbCoreResourcecolumnTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreResourcecolumnTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreResourcecolumnTableDef("", "tb_core_resourcecolumn", alias));
    }

}
