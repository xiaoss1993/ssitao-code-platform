package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 资源_留痕 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreTabletraceTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 资源_留痕
     */
    public static final TbCoreTabletraceTableDef TB_CORE_TABLETRACE = new TbCoreTabletraceTableDef();

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
     * 修改人部门编码
     */
    public final QueryColumn SY_MODIFYORG = new QueryColumn(this, "sy_modifyorg");

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
     * 操作时间
     */
    public final QueryColumn TABLETRACE_CZSJ = new QueryColumn(this, "tabletrace_czsj");

    /**
     * 操作类型
     */
    public final QueryColumn TABLETRACE_OPER = new QueryColumn(this, "tabletrace_oper");

    /**
     * 是否应用
     */
    public final QueryColumn TABLETRACE_SFYY = new QueryColumn(this, "tabletrace_sfyy");

    /**
     * 修改内容
     */
    public final QueryColumn TABLETRACE_XGNR = new QueryColumn(this, "tabletrace_xgnr");

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
     * 操作人编码
     */
    public final QueryColumn TABLETRACE_CZRCODE = new QueryColumn(this, "tabletrace_czrcode");

    /**
     * 操作人
     */
    public final QueryColumn TABLETRACE_CZRNAME = new QueryColumn(this, "tabletrace_czrname");

    /**
     * 修改内容json
     */
    public final QueryColumn TABLETRACE_XGNRJSON = new QueryColumn(this, "tabletrace_xgnrjson");

    
    public final QueryColumn TB_CORE_TABLETRACE_ID = new QueryColumn(this, "tb_core_tabletrace_id");

    /**
     * 修改列编码
     */
    public final QueryColumn TABLETRACE_FIELDCODE = new QueryColumn(this, "tabletrace_fieldcode");

    /**
     * 修改列名称
     */
    public final QueryColumn TABLETRACE_FIELDNAME = new QueryColumn(this, "tabletrace_fieldname");

    /**
     * 修改列类型
     */
    public final QueryColumn TABLETRACE_FIELDTYPE = new QueryColumn(this, "tabletrace_fieldtype");

    /**
     * 操作表编码
     */
    public final QueryColumn TABLETRACE_TABLECODE = new QueryColumn(this, "tabletrace_tablecode");

    /**
     * 操作表
     */
    public final QueryColumn TABLETRACE_TABLENAME = new QueryColumn(this, "tabletrace_tablename");

    /**
     * 资源表主键
     */
    public final QueryColumn TABLETRACE_RESOURCETABLE_ID = new QueryColumn(this, "tabletrace_resourcetable_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_TABLETRACE_ID, TABLETRACE_CZSJ, TABLETRACE_XGNR, TABLETRACE_FIELDTYPE, TABLETRACE_FIELDNAME, TABLETRACE_RESOURCETABLE_ID, TABLETRACE_CZRCODE, TABLETRACE_SFYY, TABLETRACE_CZRNAME, TABLETRACE_TABLECODE, TABLETRACE_OPER, TABLETRACE_TABLENAME, TABLETRACE_XGNRJSON, TABLETRACE_FIELDCODE, SY_MODIFYTIME, SY_STATUS, SY_CREATEUSERNAME, SY_MODIFYORG, SY_MODIFYUSER, SY_ORDERINDEX, SY_FLAG, SY_MODIFYUSERNAME, SY_FORMUPLOADFILES, SY_MODIFYORGNAME, SY_CREATETIME, SY_PYJZ, SY_CREATEORGNAME, SY_PYQC, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID};

    public TbCoreTabletraceTableDef() {
        super("", "tb_core_tabletrace");
    }

    private TbCoreTabletraceTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreTabletraceTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreTabletraceTableDef("", "tb_core_tabletrace", alias));
    }

}
