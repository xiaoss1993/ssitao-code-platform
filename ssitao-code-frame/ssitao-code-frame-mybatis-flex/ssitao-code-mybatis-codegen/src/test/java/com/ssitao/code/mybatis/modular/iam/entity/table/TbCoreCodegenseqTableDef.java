package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 业务编号 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreCodegenseqTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 业务编号
     */
    public static final TbCoreCodegenseqTableDef TB_CORE_CODEGENSEQ = new TbCoreCodegenseqTableDef();

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
     * 租户id
     */
    public final QueryColumn SY_ZHID = new QueryColumn(this, "sy_zhid");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户id
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
     * 步长
     */
    public final QueryColumn CODEGENSEQ_STEP = new QueryColumn(this, "codegenseq_step");

    /**
     * 类型
     */
    public final QueryColumn CODEGENSEQ_TYPE = new QueryColumn(this, "codegenseq_type");

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
     * 功能id
     */
    public final QueryColumn CODEGENSEQ_FUNCID = new QueryColumn(this, "codegenseq_funcid");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * saas产品
     */
    public final QueryColumn CODEGENSEQ_SAAS_PID = new QueryColumn(this, "codegenseq_saas_pid");

    /**
     * 表单上传虚字段
     */
    public final QueryColumn SY_FORMUPLOADFILES = new QueryColumn(this, "sy_formuploadfiles");

    /**
     * 序列基数
     */
    public final QueryColumn CODEGENSEQ_CODEBASE = new QueryColumn(this, "codegenseq_codebase");

    /**
     * 功能编码
     */
    public final QueryColumn CODEGENSEQ_FUNCCODE = new QueryColumn(this, "codegenseq_funccode");

    /**
     * 功能名称
     */
    public final QueryColumn CODEGENSEQ_FUNCNAME = new QueryColumn(this, "codegenseq_funcname");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_CODEGENSEQ_ID = new QueryColumn(this, "tb_core_codegenseq_id");

    /**
     * 字段当前值
     */
    public final QueryColumn CODEGENSEQ_CODEVALUE = new QueryColumn(this, "codegenseq_codevalue");

    /**
     * 清空周期
     */
    public final QueryColumn CODEGENSEQ_EMPTYTYPE = new QueryColumn(this, "codegenseq_emptytype");

    /**
     * 字段名
     */
    public final QueryColumn CODEGENSEQ_FIELDNAME = new QueryColumn(this, "codegenseq_fieldname");

    /**
     * 上次清空时间
     */
    public final QueryColumn CODEGENSEQ_LASTEMPTYTIME = new QueryColumn(this, "codegenseq_lastemptytime");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_CODEGENSEQ_ID, CODEGENSEQ_FUNCID, CODEGENSEQ_FIELDNAME, CODEGENSEQ_CODEBASE, CODEGENSEQ_CODEVALUE, CODEGENSEQ_EMPTYTYPE, CODEGENSEQ_LASTEMPTYTIME, CODEGENSEQ_FUNCNAME, CODEGENSEQ_STEP, CODEGENSEQ_TYPE, CODEGENSEQ_FUNCCODE, CODEGENSEQ_SAAS_PID, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_ZHID, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PYJZ, SY_PYQC, SY_FORMUPLOADFILES};

    public TbCoreCodegenseqTableDef() {
        super("", "tb_core_codegenseq");
    }

    private TbCoreCodegenseqTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreCodegenseqTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreCodegenseqTableDef("", "tb_core_codegenseq", alias));
    }

}
