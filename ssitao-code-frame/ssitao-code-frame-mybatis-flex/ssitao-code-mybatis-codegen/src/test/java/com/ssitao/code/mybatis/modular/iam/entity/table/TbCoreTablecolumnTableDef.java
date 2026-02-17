package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 资源_列管理 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreTablecolumnTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 资源_列管理
     */
    public static final TbCoreTablecolumnTableDef TB_CORE_TABLECOLUMN = new TbCoreTablecolumnTableDef();

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
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 登记者所在部门编码
     */
    public final QueryColumn SY_CREATEORG = new QueryColumn(this, "sy_createorg");

    /**
     * 修改人部门编码
     */
    public final QueryColumn SY_MODIFYORG = new QueryColumn(this, "sy_modifyorg");

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
     * 列编码
     */
    public final QueryColumn TABLECOLUMN_CODE = new QueryColumn(this, "tablecolumn_code");

    /**
     * 列名称
     */
    public final QueryColumn TABLECOLUMN_NAME = new QueryColumn(this, "tablecolumn_name");

    /**
     * 注解
     */
    public final QueryColumn TABLECOLUMN_NOTE = new QueryColumn(this, "tablecolumn_note");

    /**
     * 类型
     */
    public final QueryColumn TABLECOLUMN_TYPE = new QueryColumn(this, "tablecolumn_type");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 是否允许是空
     */
    public final QueryColumn TABLECOLUMN_ISNULL = new QueryColumn(this, "tablecolumn_isnull");

    /**
     * 长度
     */
    public final QueryColumn TABLECOLUMN_LENGTH = new QueryColumn(this, "tablecolumn_length");

    /**
     * 英文名称
     */
    public final QueryColumn TABLECOLUMN_NAME_EN = new QueryColumn(this, "tablecolumn_name_en");

    /**
     * 描述
     */
    public final QueryColumn TABLECOLUMN_REMARK = new QueryColumn(this, "tablecolumn_remark");

    /**
     * 字段来源
     */
    public final QueryColumn TABLECOLUMN_SOURCE = new QueryColumn(this, "tablecolumn_source");

    /**
     * 是否唯一
     */
    public final QueryColumn TABLECOLUMN_UNIQUE = new QueryColumn(this, "tablecolumn_unique");

    /**
     * 原编码
     */
    public final QueryColumn TABLECOLUMN_OLDCODE = new QueryColumn(this, "tablecolumn_oldcode");

    /**
     * 分类
     */
    public final QueryColumn TABLECOLUMN_CLASSIFY = new QueryColumn(this, "tablecolumn_classify");

    /**
     * 是否已创建
     */
    public final QueryColumn TABLECOLUMN_ISCREATE = new QueryColumn(this, "tablecolumn_iscreate");

    /**
     * 树形类型
     */
    public final QueryColumn TABLECOLUMN_TREETYPE = new QueryColumn(this, "tablecolumn_treetype");

    
    public final QueryColumn TB_CORE_TABLECOLUMN_ID = new QueryColumn(this, "tb_core_tablecolumn_id");

    /**
     * 数据字典配置
     */
    public final QueryColumn TABLECOLUMN_DICCONFIG = new QueryColumn(this, "tablecolumn_dicconfig");

    /**
     * 原是否唯一
     */
    public final QueryColumn TABLECOLUMN_OLDUNIQUE = new QueryColumn(this, "tablecolumn_oldunique");

    /**
     * 表编码
     */
    public final QueryColumn TABLECOLUMN_TABLECODE = new QueryColumn(this, "tablecolumn_tablecode");

    /**
     * 唯一约束编码
     */
    public final QueryColumn TABLECOLUMN_UNIQUECODE = new QueryColumn(this, "tablecolumn_uniquecode");

    /**
     * 视图配置
     */
    public final QueryColumn TABLECOLUMN_VIEWCONFIG = new QueryColumn(this, "tablecolumn_viewconfig");

    /**
     * 子功能配置
     */
    public final QueryColumn TABLECOLUMN_CHILDCONFIG = new QueryColumn(this, "tablecolumn_childconfig");

    /**
     * 查询选择配置
     */
    public final QueryColumn TABLECOLUMN_QUERYCONFIG = new QueryColumn(this, "tablecolumn_queryconfig");

    /**
     * 默认数值
     */
    public final QueryColumn TABLECOLUMN_DEFAULTVALUE = new QueryColumn(this, "tablecolumn_defaultvalue");

    /**
     * 数据字典查询字段
     */
    public final QueryColumn TABLECOLUMN_DICQUERYFIELD = new QueryColumn(this, "tablecolumn_dicqueryfield");

    /**
     * 表资源外键
     */
    public final QueryColumn TABLECOLUMN_RESOURCETABLE_ID = new QueryColumn(this, "tablecolumn_resourcetable_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_TABLECOLUMN_ID, TABLECOLUMN_CODE, TABLECOLUMN_NAME, TABLECOLUMN_TYPE, TABLECOLUMN_LENGTH, TABLECOLUMN_NOTE, TABLECOLUMN_ISNULL, TABLECOLUMN_ISCREATE, TABLECOLUMN_DEFAULTVALUE, TABLECOLUMN_TREETYPE, TABLECOLUMN_RESOURCETABLE_ID, TABLECOLUMN_TABLECODE, TABLECOLUMN_CLASSIFY, TABLECOLUMN_UNIQUE, TABLECOLUMN_OLDCODE, TABLECOLUMN_OLDUNIQUE, TABLECOLUMN_DICCONFIG, TABLECOLUMN_QUERYCONFIG, TABLECOLUMN_CHILDCONFIG, TABLECOLUMN_DICQUERYFIELD, TABLECOLUMN_VIEWCONFIG, TABLECOLUMN_UNIQUECODE, TABLECOLUMN_REMARK, TABLECOLUMN_NAME_EN, TABLECOLUMN_SOURCE, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_AUDFLAG, SY_CREATEORG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSER, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSER, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID};

    public TbCoreTablecolumnTableDef() {
        super("", "tb_core_tablecolumn");
    }

    private TbCoreTablecolumnTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreTablecolumnTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreTablecolumnTableDef("", "tb_core_tablecolumn", alias));
    }

}
