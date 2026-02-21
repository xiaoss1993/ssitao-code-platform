package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 资源_键管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreTablekeyTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 资源_键管理
     */
    public static final TbCoreTablekeyTableDef TB_CORE_TABLEKEY = new TbCoreTablekeyTableDef();

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
     * 产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

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
     * 键编码
     */
    public final QueryColumn TABLEKEY_CODE = new QueryColumn(this, "tablekey_code");

    /**
     * 类型
     */
    public final QueryColumn TABLEKEY_TYPE = new QueryColumn(this, "tablekey_type");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门id
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 产品名称
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

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
     * 是否检测
     */
    public final QueryColumn TABLEKEY_CHECKED = new QueryColumn(this, "tablekey_checked");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 分类
     */
    public final QueryColumn TABLEKEY_CLASSIFY = new QueryColumn(this, "tablekey_classify");

    /**
     * 是否已创建
     */
    public final QueryColumn TABLEKEY_ISCREATE = new QueryColumn(this, "tablekey_iscreate");

    /**
     * 关联类型
     */
    public final QueryColumn TABLEKEY_LINETYLE = new QueryColumn(this, "tablekey_linetyle");

    
    public final QueryColumn TB_CORE_TABLEKEY_ID = new QueryColumn(this, "tb_core_tablekey_id");

    /**
     * 关联表
     */
    public final QueryColumn TABLEKEY_LINKTABLE = new QueryColumn(this, "tablekey_linktable");

    /**
     * 表编码
     */
    public final QueryColumn TABLEKEY_TABLECODE = new QueryColumn(this, "tablekey_tablecode");

    /**
     * 字段编码
     */
    public final QueryColumn TABLEKEY_COLUMNCODE = new QueryColumn(this, "tablekey_columncode");

    /**
     * 字段名称
     */
    public final QueryColumn TABLEKEY_COLUMNNAME = new QueryColumn(this, "tablekey_columnname");

    /**
     * 是否强制性约束
     */
    public final QueryColumn TABLEKEY_ISRESTRAINT = new QueryColumn(this, "tablekey_isrestraint");

    /**
     * 关联字段
     */
    public final QueryColumn TABLEKEY_LINECOLUMNCODE = new QueryColumn(this, "tablekey_linecolumncode");

    /**
     * 资源表外键
     */
    public final QueryColumn TABLEKEY_RESOURCETABLE_ID = new QueryColumn(this, "tablekey_resourcetable_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_TABLEKEY_ID, TABLEKEY_CODE, TABLEKEY_TYPE, TABLEKEY_COLUMNCODE, TABLEKEY_LINKTABLE, TABLEKEY_LINECOLUMNCODE, TABLEKEY_LINETYLE, TABLEKEY_CHECKED, TABLEKEY_ISRESTRAINT, TABLEKEY_ISCREATE, TABLEKEY_RESOURCETABLE_ID, TABLEKEY_TABLECODE, TABLEKEY_CLASSIFY, TABLEKEY_COLUMNNAME, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID};

    public TbCoreTablekeyTableDef() {
        super("", "tb_core_tablekey");
    }

    private TbCoreTablekeyTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreTablekeyTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreTablekeyTableDef("", "tb_core_tablekey", alias));
    }

}
