package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 功能依赖 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreFuncrelyonTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 功能依赖
     */
    public static final TbCoreFuncrelyonTableDef TB_CORE_FUNCRELYON = new TbCoreFuncrelyonTableDef();

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
     * 目标功能id
     */
    public final QueryColumn FUNCRELYON_FUNCID = new QueryColumn(this, "funcrelyon_funcid");

    /**
     * 依赖关系
     */
    public final QueryColumn FUNCRELYON_REGARD = new QueryColumn(this, "funcrelyon_regard");

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
    public final QueryColumn FUNCRELYON_SAAS_PID = new QueryColumn(this, "funcrelyon_saas_pid");

    /**
     * 表单上传虚字段
     */
    public final QueryColumn SY_FORMUPLOADFILES = new QueryColumn(this, "sy_formuploadfiles");

    /**
     * 目标功能编码
     */
    public final QueryColumn FUNCRELYON_FUNCCODE = new QueryColumn(this, "funcrelyon_funccode");

    /**
     * 目标功能名称
     */
    public final QueryColumn FUNCRELYON_FUNCNAME = new QueryColumn(this, "funcrelyon_funcname");

    /**
     * 依赖类型
     */
    public final QueryColumn FUNCRELYON_RELYTYPE = new QueryColumn(this, "funcrelyon_relytype");

    
    public final QueryColumn TB_CORE_FUNCRELYON_ID = new QueryColumn(this, "tb_core_funcrelyon_id");

    /**
     * 功能外键
     */
    public final QueryColumn FUNCRELYON_FUNCINFO_ID = new QueryColumn(this, "funcrelyon_funcinfo_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_FUNCRELYON_ID, FUNCRELYON_FUNCNAME, FUNCRELYON_FUNCCODE, FUNCRELYON_REGARD, FUNCRELYON_RELYTYPE, FUNCRELYON_FUNCINFO_ID, FUNCRELYON_FUNCID, FUNCRELYON_SAAS_PID, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PYJZ, SY_PYQC, SY_FORMUPLOADFILES};

    public TbCoreFuncrelyonTableDef() {
        super("", "tb_core_funcrelyon");
    }

    private TbCoreFuncrelyonTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreFuncrelyonTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreFuncrelyonTableDef("", "tb_core_funcrelyon", alias));
    }

}
