package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 主子功能关联字段 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreAssociationfieldTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 主子功能关联字段
     */
    public static final TbCoreAssociationfieldTableDef TB_CORE_ASSOCIATIONFIELD = new TbCoreAssociationfieldTableDef();

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
     * 表单上传虚字段
     */
    public final QueryColumn SY_FORMUPLOADFILES = new QueryColumn(this, "sy_formuploadfiles");

    /**
     * 主功能主键
     */
    public final QueryColumn ASSOCIATIONFIELD_FID = new QueryColumn(this, "associationfield_fid");

    /**
     * 子功能主键
     */
    public final QueryColumn ASSOCIATIONFIELD_PID = new QueryColumn(this, "associationfield_pid");

    /**
     * 自定义sql
     */
    public final QueryColumn ASSOCIATIONFIELD_SQL = new QueryColumn(this, "associationfield_sql");

    /**
     * 是否硬编码
     */
    public final QueryColumn ASSOCIATIONFIELD_HARD = new QueryColumn(this, "associationfield_hard");

    /**
     * 说明
     */
    public final QueryColumn ASSOCIATIONFIELD_REMARK = new QueryColumn(this, "associationfield_remark");

    /**
     * saas产品
     */
    public final QueryColumn ASSOCIATIONFIELD_SAAS_PID = new QueryColumn(this, "associationfield_saas_pid");

    /**
     * 系统模式
     */
    public final QueryColumn ASSOCIATIONFIELD_SYSMODE = new QueryColumn(this, "associationfield_sysmode");

    /**
     * 删除子单
     */
    public final QueryColumn ASSOCIATIONFIELD_DELCHILD = new QueryColumn(this, "associationfield_delchild");

    /**
     * 传值
     */
    public final QueryColumn ASSOCIATIONFIELD_TRANSMIT = new QueryColumn(this, "associationfield_transmit");

    /**
     * where条件
     */
    public final QueryColumn ASSOCIATIONFIELD_WHERECON = new QueryColumn(this, "associationfield_wherecon");

    
    public final QueryColumn TB_CORE_ASSOCIATIONFIELD_ID = new QueryColumn(this, "tb_core_associationfield_id");

    /**
     * 关联关系
     */
    public final QueryColumn ASSOCIATIONFIELD_ASSOCIATION = new QueryColumn(this, "associationfield_association");

    /**
     * 外键
     */
    public final QueryColumn ASSOCIATIONFIELD_FUNCRELAT_ID = new QueryColumn(this, "associationfield_funcrelat_id");

    /**
     * 子字段编码
     */
    public final QueryColumn ASSOCIATIONFIELD_CHIFIELDCODE = new QueryColumn(this, "associationfield_chifieldcode");

    /**
     * 子字段名称
     */
    public final QueryColumn ASSOCIATIONFIELD_CHIFIELDNAME = new QueryColumn(this, "associationfield_chifieldname");

    /**
     * 主字段编码
     */
    public final QueryColumn ASSOCIATIONFIELD_PRIFIELDCODE = new QueryColumn(this, "associationfield_prifieldcode");

    /**
     * 主字段名称
     */
    public final QueryColumn ASSOCIATIONFIELD_PRIFIELDNAME = new QueryColumn(this, "associationfield_prifieldname");

    /**
     * 级联更新
     */
    public final QueryColumn ASSOCIATIONFIELD_CASCADEUPDATE = new QueryColumn(this, "associationfield_cascadeupdate");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_ASSOCIATIONFIELD_ID, ASSOCIATIONFIELD_PRIFIELDNAME, ASSOCIATIONFIELD_PRIFIELDCODE, ASSOCIATIONFIELD_CHIFIELDNAME, ASSOCIATIONFIELD_CHIFIELDCODE, ASSOCIATIONFIELD_WHERECON, ASSOCIATIONFIELD_ASSOCIATION, ASSOCIATIONFIELD_TRANSMIT, ASSOCIATIONFIELD_REMARK, ASSOCIATIONFIELD_FID, ASSOCIATIONFIELD_PID, ASSOCIATIONFIELD_HARD, ASSOCIATIONFIELD_CASCADEUPDATE, ASSOCIATIONFIELD_DELCHILD, ASSOCIATIONFIELD_SYSMODE, ASSOCIATIONFIELD_FUNCRELAT_ID, ASSOCIATIONFIELD_SQL, ASSOCIATIONFIELD_SAAS_PID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PYJZ, SY_PYQC, SY_FORMUPLOADFILES, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreAssociationfieldTableDef() {
        super("", "tb_core_associationfield");
    }

    private TbCoreAssociationfieldTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreAssociationfieldTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreAssociationfieldTableDef("", "tb_core_associationfield", alias));
    }

}
