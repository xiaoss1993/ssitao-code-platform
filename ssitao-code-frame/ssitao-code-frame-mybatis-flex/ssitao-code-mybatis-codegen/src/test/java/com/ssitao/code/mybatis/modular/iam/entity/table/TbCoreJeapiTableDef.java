package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 平台前台api 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreJeapiTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 平台前台api
     */
    public static final TbCoreJeapiTableDef TB_CORE_JEAPI = new TbCoreJeapiTableDef();

    /**
     * 树形路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

    /**
     * 层次
     */
    public final QueryColumn SY_LAYER = new QueryColumn(this, "sy_layer");

    /**
     * 所属
     */
    public final QueryColumn JEAPI_FOR = new QueryColumn(this, "jeapi_for");

    /**
     * 父节点id
     */
    public final QueryColumn SY_PARENT = new QueryColumn(this, "sy_parent");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 编码
     */
    public final QueryColumn JEAPI_CODE = new QueryColumn(this, "jeapi_code");

    /**
     * 名称
     */
    public final QueryColumn JEAPI_TEXT = new QueryColumn(this, "jeapi_text");

    /**
     * 类型
     */
    public final QueryColumn JEAPI_TYPE = new QueryColumn(this, "jeapi_type");

    /**
     * 是否启用
     */
    public final QueryColumn SY_DISABLED = new QueryColumn(this, "sy_disabled");

    /**
     * 节点类型
     */
    public final QueryColumn SY_NODETYPE = new QueryColumn(this, "sy_nodetype");

    /**
     * 说明
     */
    public final QueryColumn JEAPI_REMARK = new QueryColumn(this, "jeapi_remark");

    /**
     * 示例
     */
    public final QueryColumn JEAPI_EXAMPLE = new QueryColumn(this, "jeapi_example");

    /**
     * 图标
     */
    public final QueryColumn JEAPI_ICONCLS = new QueryColumn(this, "jeapi_iconcls");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 父节点路径
     */
    public final QueryColumn SY_PARENTPATH = new QueryColumn(this, "sy_parentpath");

    /**
     * 登记者所在部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    
    public final QueryColumn TB_CORE_JEAPI_ID = new QueryColumn(this, "tb_core_jeapi_id");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 树形排序字段
     */
    public final QueryColumn SY_TREEORDERINDEX = new QueryColumn(this, "sy_treeorderindex");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_JEAPI_ID, JEAPI_TYPE, JEAPI_REMARK, JEAPI_FOR, JEAPI_TEXT, JEAPI_CODE, JEAPI_EXAMPLE, JEAPI_ICONCLS, SY_PARENT, SY_NODETYPE, SY_PARENTPATH, SY_LAYER, SY_PATH, SY_DISABLED, SY_TREEORDERINDEX, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbCoreJeapiTableDef() {
        super("", "tb_core_jeapi");
    }

    private TbCoreJeapiTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreJeapiTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreJeapiTableDef("", "tb_core_jeapi", alias));
    }

}
