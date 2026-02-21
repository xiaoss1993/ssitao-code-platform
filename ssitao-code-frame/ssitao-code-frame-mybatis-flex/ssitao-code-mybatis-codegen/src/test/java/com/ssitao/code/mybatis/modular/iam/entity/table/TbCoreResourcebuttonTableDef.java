package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 功能按钮 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreResourcebuttonTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 功能按钮
     */
    public static final TbCoreResourcebuttonTableDef TB_CORE_RESOURCEBUTTON = new TbCoreResourcebuttonTableDef();

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
     * 修改部门id
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
     * 样式
     */
    public final QueryColumn RESOURCEBUTTON_CLS = new QueryColumn(this, "resourcebutton_cls");

    /**
     * 提示信息
     */
    public final QueryColumn RESOURCEBUTTON_MSG = new QueryColumn(this, "resourcebutton_msg");

    /**
     * 表单上传虚字段
     */
    public final QueryColumn SY_FORMUPLOADFILES = new QueryColumn(this, "sy_formuploadfiles");

    /**
     * 编码
     */
    public final QueryColumn RESOURCEBUTTON_CODE = new QueryColumn(this, "resourcebutton_code");

    /**
     * 图标
     */
    public final QueryColumn RESOURCEBUTTON_ICON = new QueryColumn(this, "resourcebutton_icon");

    /**
     * 名称
     */
    public final QueryColumn RESOURCEBUTTON_NAME = new QueryColumn(this, "resourcebutton_name");

    /**
     * 类型
     */
    public final QueryColumn RESOURCEBUTTON_TYPE = new QueryColumn(this, "resourcebutton_type");

    /**
     * 依附按钮
     */
    public final QueryColumn RESOURCEBUTTON_YFAN = new QueryColumn(this, "resourcebutton_yfan");

    /**
     * 样式
     */
    public final QueryColumn RESOURCEBUTTON_XTYPE = new QueryColumn(this, "resourcebutton_xtype");

    /**
     * 是否隐藏
     */
    public final QueryColumn RESOURCEBUTTON_HIDDEN = new QueryColumn(this, "resourcebutton_hidden");

    /**
     * 英文名
     */
    public final QueryColumn RESOURCEBUTTON_NAME_EN = new QueryColumn(this, "resourcebutton_name_en");

    /**
     * 背景颜色
     */
    public final QueryColumn RESOURCEBUTTON_BGCOLOR = new QueryColumn(this, "resourcebutton_bgcolor");

    /**
     * saas产品
     */
    public final QueryColumn RESOURCEBUTTON_SAAS_PID = new QueryColumn(this, "resourcebutton_saas_pid");

    /**
     * 系统模式
     */
    public final QueryColumn RESOURCEBUTTON_SYSMODE = new QueryColumn(this, "resourcebutton_sysmode");

    /**
     * 是否可用
     */
    public final QueryColumn RESOURCEBUTTON_DISABLED = new QueryColumn(this, "resourcebutton_disabled");

    /**
     * 绑定表单验证
     */
    public final QueryColumn RESOURCEBUTTON_FORMBIND = new QueryColumn(this, "resourcebutton_formbind");

    /**
     * 按钮位置
     */
    public final QueryColumn RESOURCEBUTTON_POSITION = new QueryColumn(this, "resourcebutton_position");

    /**
     * 使用范围
     */
    public final QueryColumn RESOURCEBUTTON_USE_SCOPE = new QueryColumn(this, "resourcebutton_use_scope");

    
    public final QueryColumn TB_CORE_RESOURCEBUTTON_ID = new QueryColumn(this, "tb_core_resourcebutton_id");

    /**
     * 触发事件
     */
    public final QueryColumn RESOURCEBUTTON_FIREEVENT = new QueryColumn(this, "resourcebutton_fireevent");

    /**
     * 字体颜色
     */
    public final QueryColumn RESOURCEBUTTON_FONTCOLOR = new QueryColumn(this, "resourcebutton_fontcolor");

    /**
     * 图标颜色
     */
    public final QueryColumn RESOURCEBUTTON_ICONCOLOR = new QueryColumn(this, "resourcebutton_iconcolor");

    /**
     * 产品扩展功能id
     */
    public final QueryColumn RESOURCEBUTTON_NEWFUNCID = new QueryColumn(this, "resourcebutton_newfuncid");

    /**
     * 配置信息
     */
    public final QueryColumn RESOURCEBUTTON_CONFIGINFO = new QueryColumn(this, "resourcebutton_configinfo");

    /**
     * 功能外键
     */
    public final QueryColumn RESOURCEBUTTON_FUNCINFO_ID = new QueryColumn(this, "resourcebutton_funcinfo_id");

    /**
     * 监听事件
     */
    public final QueryColumn RESOURCEBUTTON_JSLISTENER = new QueryColumn(this, "resourcebutton_jslistener");

    /**
     * 不只读
     */
    public final QueryColumn RESOURCEBUTTON_NOREADONLY = new QueryColumn(this, "resourcebutton_noreadonly");

    /**
     * 边框颜色
     */
    public final QueryColumn RESOURCEBUTTON_BORDERCOLOR = new QueryColumn(this, "resourcebutton_bordercolor");

    /**
     * 显隐控制表达式
     */
    public final QueryColumn RESOURCEBUTTON_INTERPRETER = new QueryColumn(this, "resourcebutton_interpreter");

    /**
     * 流程结束可用
     */
    public final QueryColumn RESOURCEBUTTON_WFENDEDENABLE = new QueryColumn(this, "resourcebutton_wfendedenable");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_RESOURCEBUTTON_ID, RESOURCEBUTTON_CODE, RESOURCEBUTTON_NAME, RESOURCEBUTTON_NOREADONLY, RESOURCEBUTTON_HIDDEN, RESOURCEBUTTON_DISABLED, RESOURCEBUTTON_XTYPE, RESOURCEBUTTON_FIREEVENT, RESOURCEBUTTON_FORMBIND, RESOURCEBUTTON_WFENDEDENABLE, RESOURCEBUTTON_CONFIGINFO, RESOURCEBUTTON_SYSMODE, RESOURCEBUTTON_NEWFUNCID, RESOURCEBUTTON_JSLISTENER, RESOURCEBUTTON_INTERPRETER, RESOURCEBUTTON_FUNCINFO_ID, RESOURCEBUTTON_TYPE, RESOURCEBUTTON_MSG, RESOURCEBUTTON_NAME_EN, RESOURCEBUTTON_CLS, RESOURCEBUTTON_YFAN, RESOURCEBUTTON_POSITION, RESOURCEBUTTON_ICON, RESOURCEBUTTON_ICONCOLOR, RESOURCEBUTTON_SAAS_PID, RESOURCEBUTTON_USE_SCOPE, RESOURCEBUTTON_BGCOLOR, RESOURCEBUTTON_FONTCOLOR, RESOURCEBUTTON_BORDERCOLOR, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PYJZ, SY_PYQC, SY_FORMUPLOADFILES};

    public TbCoreResourcebuttonTableDef() {
        super("", "tb_core_resourcebutton");
    }

    private TbCoreResourcebuttonTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreResourcebuttonTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreResourcebuttonTableDef("", "tb_core_resourcebutton", alias));
    }

}
