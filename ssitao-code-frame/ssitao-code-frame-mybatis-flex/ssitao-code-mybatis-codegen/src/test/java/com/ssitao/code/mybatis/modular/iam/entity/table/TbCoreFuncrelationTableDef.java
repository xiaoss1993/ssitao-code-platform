package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 子功能 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreFuncrelationTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 子功能
     */
    public static final TbCoreFuncrelationTableDef TB_CORE_FUNCRELATION = new TbCoreFuncrelationTableDef();

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
     * 功能编码
     */
    public final QueryColumn FUNCRELATION_CODE = new QueryColumn(this, "funcrelation_code");

    /**
     * 级联复制
     */
    public final QueryColumn FUNCRELATION_COPY = new QueryColumn(this, "funcrelation_copy");

    /**
     * 图标
     */
    public final QueryColumn FUNCRELATION_ICON = new QueryColumn(this, "funcrelation_icon");

    /**
     * 功能名称
     */
    public final QueryColumn FUNCRELATION_NAME = new QueryColumn(this, "funcrelation_name");

    /**
     * 主从关系
     */
    public final QueryColumn FUNCRELATION_ZCGX = new QueryColumn(this, "funcrelation_zcgx");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 分组
     */
    public final QueryColumn FUNCRELATION_GROUP = new QueryColumn(this, "funcrelation_group");

    /**
     * 表单上传虚字段
     */
    public final QueryColumn SY_FORMUPLOADFILES = new QueryColumn(this, "sy_formuploadfiles");

    /**
     * 功能主键
     */
    public final QueryColumn FUNCRELATION_FUNCID = new QueryColumn(this, "funcrelation_funcid");

    /**
     * 高度
     */
    public final QueryColumn FUNCRELATION_HEIGHT = new QueryColumn(this, "funcrelation_height");

    /**
     * 定位
     */
    public final QueryColumn FUNCRELATION_LOCATE = new QueryColumn(this, "funcrelation_locate");

    /**
     * 功能英文名
     */
    public final QueryColumn FUNCRELATION_NAME_EN = new QueryColumn(this, "funcrelation_name_en");

    /**
     * 是否启用
     */
    public final QueryColumn FUNCRELATION_ENABLED = new QueryColumn(this, "funcrelation_enabled");

    /**
     * saas产品
     */
    public final QueryColumn FUNCRELATION_SAAS_PID = new QueryColumn(this, "funcrelation_saas_pid");

    /**
     * 系统模式
     */
    public final QueryColumn FUNCRELATION_SYSMODE = new QueryColumn(this, "funcrelation_sysmode");

    /**
     * 个性化显示方式
     */
    public final QueryColumn FUNCRELATION_SHOWTYPE = new QueryColumn(this, "funcrelation_showtype");

    
    public final QueryColumn TB_CORE_FUNCRELATION_ID = new QueryColumn(this, "tb_core_funcrelation_id");

    /**
     * 过滤条件
     */
    public final QueryColumn FUNCRELATION_FILTERSQL = new QueryColumn(this, "funcrelation_filtersql");

    /**
     * 产品扩展功能id
     */
    public final QueryColumn FUNCRELATION_NEWFUNCID = new QueryColumn(this, "funcrelation_newfuncid");

    /**
     * 显示标题
     */
    public final QueryColumn FUNCRELATION_SHOWTITLE = new QueryColumn(this, "funcrelation_showtitle");

    /**
     * 表名
     */
    public final QueryColumn FUNCRELATION_TABLENAME = new QueryColumn(this, "funcrelation_tablename");

    /**
     * 实体全限定名
     */
    public final QueryColumn FUNCRELATION_ENTITYNAME = new QueryColumn(this, "funcrelation_entityname");

    /**
     * 功能外键
     */
    public final QueryColumn FUNCRELATION_FUNCINFO_ID = new QueryColumn(this, "funcrelation_funcinfo_id");

    /**
     * 是否树形功能
     */
    public final QueryColumn FUNCRELATION_ISTREEFUNC = new QueryColumn(this, "funcrelation_istreefunc");

    /**
     * 依赖类型
     */
    public final QueryColumn FUNCRELATION_RELYONTYPE = new QueryColumn(this, "funcrelation_relyontype");

    /**
     * 控制显隐
     */
    public final QueryColumn FUNCRELATION_INTERPRETER = new QueryColumn(this, "funcrelation_interpreter");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_FUNCRELATION_ID, FUNCRELATION_NAME, FUNCRELATION_CODE, FUNCRELATION_ENTITYNAME, FUNCRELATION_FILTERSQL, FUNCRELATION_RELYONTYPE, FUNCRELATION_ISTREEFUNC, FUNCRELATION_TABLENAME, FUNCRELATION_SYSMODE, FUNCRELATION_NEWFUNCID, FUNCRELATION_INTERPRETER, FUNCRELATION_FUNCINFO_ID, FUNCRELATION_ENABLED, FUNCRELATION_SHOWTYPE, FUNCRELATION_FUNCID, FUNCRELATION_COPY, FUNCRELATION_LOCATE, FUNCRELATION_NAME_EN, FUNCRELATION_HEIGHT, FUNCRELATION_SHOWTITLE, FUNCRELATION_GROUP, FUNCRELATION_ICON, FUNCRELATION_SAAS_PID, FUNCRELATION_ZCGX, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PYJZ, SY_PYQC, SY_FORMUPLOADFILES};

    public TbCoreFuncrelationTableDef() {
        super("", "tb_core_funcrelation");
    }

    private TbCoreFuncrelationTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreFuncrelationTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreFuncrelationTableDef("", "tb_core_funcrelation", alias));
    }

}
