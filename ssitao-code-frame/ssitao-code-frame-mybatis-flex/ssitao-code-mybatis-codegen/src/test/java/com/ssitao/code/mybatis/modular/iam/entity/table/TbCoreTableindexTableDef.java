package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 资源_索引管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreTableindexTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 资源_索引管理
     */
    public static final TbCoreTableindexTableDef TB_CORE_TABLEINDEX = new TbCoreTableindexTableDef();

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
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 产品名称
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 名称
     */
    public final QueryColumn TABLEINDEX_NAME = new QueryColumn(this, "tableindex_name");

    
    public final QueryColumn TABLEINDEX_TYPE = new QueryColumn(this, "tableindex_type");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 备注
     */
    public final QueryColumn TABLEINDEX_REMARK = new QueryColumn(this, "tableindex_remark");

    /**
     * 唯一
     */
    public final QueryColumn TABLEINDEX_UNIQUE = new QueryColumn(this, "tableindex_unique");

    /**
     * 分类
     */
    public final QueryColumn TABLEINDEX_CLASSIFY = new QueryColumn(this, "tableindex_classify");

    /**
     * 创建
     */
    public final QueryColumn TABLEINDEX_ISCREATE = new QueryColumn(this, "tableindex_iscreate");

    
    public final QueryColumn TB_CORE_TABLEINDEX_ID = new QueryColumn(this, "tb_core_tableindex_id");

    /**
     * 字段编码
     */
    public final QueryColumn TABLEINDEX_FIELDCODE = new QueryColumn(this, "tableindex_fieldcode");

    /**
     * 字段名称
     */
    public final QueryColumn TABLEINDEX_FIELDNAME = new QueryColumn(this, "tableindex_fieldname");

    /**
     * 表名
     */
    public final QueryColumn TABLEINDEX_TABLECODE = new QueryColumn(this, "tableindex_tablecode");

    /**
     * 外键
     */
    public final QueryColumn TABLEINDEX_RESOURCETABLE_ID = new QueryColumn(this, "tableindex_resourcetable_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_TABLEINDEX_ID, TABLEINDEX_REMARK, TABLEINDEX_FIELDCODE, TABLEINDEX_FIELDNAME, TABLEINDEX_TYPE, TABLEINDEX_NAME, TABLEINDEX_RESOURCETABLE_ID, TABLEINDEX_UNIQUE, TABLEINDEX_ISCREATE, TABLEINDEX_TABLECODE, TABLEINDEX_CLASSIFY, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_CREATEORGID, SY_CREATEUSERID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_ORDERINDEX, SY_STATUS};

    public TbCoreTableindexTableDef() {
        super("", "tb_core_tableindex");
    }

    private TbCoreTableindexTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreTableindexTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreTableindexTableDef("", "tb_core_tableindex", alias));
    }

}
