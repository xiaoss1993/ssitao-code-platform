package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 资源 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreResourcetableTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 资源
     */
    public static final TbCoreResourcetableTableDef TB_CORE_RESOURCETABLE = new TbCoreResourcetableTableDef();

    /**
     * 是否启用本条数据
     */
    public final QueryColumn SY_FLAG = new QueryColumn(this, "sy_flag");

    /**
     * 树形路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

    /**
     * 流程定义id
     */
    public final QueryColumn SY_PDID = new QueryColumn(this, "sy_pdid");

    /**
     * 流程实例id
     */
    public final QueryColumn SY_PIID = new QueryColumn(this, "sy_piid");

    /**
     * je系统
     */
    public final QueryColumn SY_JESYS = new QueryColumn(this, "sy_jesys");

    /**
     * 层次
     */
    public final QueryColumn SY_LAYER = new QueryColumn(this, "sy_layer");

    /**
     * je核心
     */
    public final QueryColumn SY_JECORE = new QueryColumn(this, "sy_jecore");

    /**
     * 父节点id
     */
    public final QueryColumn SY_PARENT = new QueryColumn(this, "sy_parent");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 是否禁用
     */
    public final QueryColumn SY_DISABLED = new QueryColumn(this, "sy_disabled");

    /**
     * 节点类型
     */
    public final QueryColumn SY_NODETYPE = new QueryColumn(this, "sy_nodetype");

    /**
     * 登记者所在部门编码
     */
    public final QueryColumn SY_CREATEORG = new QueryColumn(this, "sy_createorg");

    /**
     * 修改人部门编码
     */
    public final QueryColumn SY_MODIFYORG = new QueryColumn(this, "sy_modifyorg");

    /**
     * 所属产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

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
     * 父节点路径
     */
    public final QueryColumn SY_PARENTPATH = new QueryColumn(this, "sy_parentpath");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 所属产品code
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属产品名称
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    
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
     * 视图sql
     */
    public final QueryColumn RESOURCETABLE_SQL = new QueryColumn(this, "resourcetable_sql");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 树形排序字段
     */
    public final QueryColumn SY_TREEORDERINDEX = new QueryColumn(this, "sy_treeorderindex");

    /**
     * 图标
     */
    public final QueryColumn RESOURCETABLE_ICON = new QueryColumn(this, "resourcetable_icon");

    /**
     * 表类型
     */
    public final QueryColumn RESOURCETABLE_TYPE = new QueryColumn(this, "resourcetable_type");

    /**
     * 存储数据条数
     */
    public final QueryColumn RESOURCETABLE_CCSJTS = new QueryColumn(this, "resourcetable_ccsjts");

    /**
     * 初始化流程信息
     */
    public final QueryColumn RESOURCETABLE_IMPLWF = new QueryColumn(this, "resourcetable_implwf");

    /**
     * 是否是导入的表
     */
    public final QueryColumn RESOURCETABLE_IMPORT = new QueryColumn(this, "resourcetable_import");

    /**
     * 主键编码
     */
    public final QueryColumn RESOURCETABLE_PKCODE = new QueryColumn(this, "resourcetable_pkcode");

    /**
     * 备注
     */
    public final QueryColumn RESOURCETABLE_REMARK = new QueryColumn(this, "resourcetable_remark");

    /**
     * 使用初始化功能
     */
    public final QueryColumn RESOURCETABLE_USEFUNC = new QueryColumn(this, "resourcetable_usefunc");

    /**
     * 是否已创建
     */
    public final QueryColumn RESOURCETABLE_ISCREATE = new QueryColumn(this, "resourcetable_iscreate");

    /**
     * 树形多根
     */
    public final QueryColumn RESOURCETABLE_MOREROOT = new QueryColumn(this, "resourcetable_moreroot");

    /**
     * 节点信息
     */
    public final QueryColumn RESOURCETABLE_NODEINFO = new QueryColumn(this, "resourcetable_nodeinfo");

    
    public final QueryColumn TB_CORE_RESOURCETABLE_ID = new QueryColumn(this, "tb_core_resourcetable_id");

    /**
     * 表编码
     */
    public final QueryColumn RESOURCETABLE_TABLECODE = new QueryColumn(this, "resourcetable_tablecode");

    /**
     * 表名称
     */
    public final QueryColumn RESOURCETABLE_TABLENAME = new QueryColumn(this, "resourcetable_tablename");

    /**
     * 注解
     */
    public final QueryColumn RESOURCETABLE_TABLENOTE = new QueryColumn(this, "resourcetable_tablenote");

    /**
     * 表坐标
     */
    public final QueryColumn RESOURCETABLE_COORDINATE = new QueryColumn(this, "resourcetable_coordinate");

    /**
     * 计算存储数据条数时间
     */
    public final QueryColumn RESOURCETABLE_JSCCSJTSSJ = new QueryColumn(this, "resourcetable_jsccsjtssj");

    /**
     * 模块编码
     */
    public final QueryColumn RESOURCETABLE_MODULECODE = new QueryColumn(this, "resourcetable_modulecode");

    /**
     * 模块名称
     */
    public final QueryColumn RESOURCETABLE_MODULENAME = new QueryColumn(this, "resourcetable_modulename");

    /**
     * 视图表信息
     */
    public final QueryColumn RESOURCETABLE_TABLESINFO = new QueryColumn(this, "resourcetable_tablesinfo");

    /**
     * 节点信息类型
     */
    public final QueryColumn RESOURCETABLE_NODEINFOTYPE = new QueryColumn(this, "resourcetable_nodeinfotype");

    /**
     * 原表名
     */
    public final QueryColumn RESOURCETABLE_OLDTABLECODE = new QueryColumn(this, "resourcetable_oldtablecode");

    /**
     * 视图关系说明
     */
    public final QueryColumn RESOURCETABLE_RELATION_DESC = new QueryColumn(this, "resourcetable_relation_desc");

    /**
     * 字段命名规则
     */
    public final QueryColumn RESOURCETABLE_COLUMN_NAME_ROLE = new QueryColumn(this, "resourcetable_column_name_role");

    /**
     * 拥有子表
     */
    public final QueryColumn RESOURCETABLE_CHILDTABLECODES = new QueryColumn(this, "resourcetable_childtablecodes");

    /**
     * 序列名称
     */
    public final QueryColumn RESOURCETABLE_INCREMENTER_NAME = new QueryColumn(this, "resourcetable_incrementer_name");

    /**
     * 是否存在外键
     */
    public final QueryColumn RESOURCETABLE_ISUSEFOREIGNKEY = new QueryColumn(this, "resourcetable_isuseforeignkey");

    /**
     * 主键生成查询sql
     */
    public final QueryColumn RESOURCETABLE_KEY_GENERATOR_SQL = new QueryColumn(this, "resourcetable_key_generator_sql");

    /**
     * 主键生成策略_name
     */
    public final QueryColumn RESOURCETABLE_KEY_GENERATOR_NAME = new QueryColumn(this, "resourcetable_key_generator_name");

    /**
     * 主键生成策略
     */
    public final QueryColumn RESOURCETABLE_KEY_GENERATOR_TYPE = new QueryColumn(this, "resourcetable_key_generator_type");

    /**
     * 所属父表
     */
    public final QueryColumn RESOURCETABLE_PARENTTABLECODES = new QueryColumn(this, "resourcetable_parenttablecodes");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_RESOURCETABLE_ID, RESOURCETABLE_NODEINFO, RESOURCETABLE_NODEINFOTYPE, RESOURCETABLE_TABLENAME, RESOURCETABLE_TABLECODE, RESOURCETABLE_TYPE, RESOURCETABLE_TABLENOTE, RESOURCETABLE_ISCREATE, RESOURCETABLE_ISUSEFOREIGNKEY, RESOURCETABLE_USEFUNC, RESOURCETABLE_CHILDTABLECODES, RESOURCETABLE_REMARK, RESOURCETABLE_MODULENAME, RESOURCETABLE_MODULECODE, RESOURCETABLE_PARENTTABLECODES, RESOURCETABLE_OLDTABLECODE, RESOURCETABLE_PKCODE, RESOURCETABLE_IMPLWF, RESOURCETABLE_SQL, RESOURCETABLE_MOREROOT, RESOURCETABLE_IMPORT, RESOURCETABLE_TABLESINFO, RESOURCETABLE_COORDINATE, RESOURCETABLE_ICON, RESOURCETABLE_KEY_GENERATOR_NAME, RESOURCETABLE_KEY_GENERATOR_TYPE, RESOURCETABLE_KEY_GENERATOR_SQL, RESOURCETABLE_INCREMENTER_NAME, RESOURCETABLE_CCSJTS, RESOURCETABLE_JSCCSJTSSJ, RESOURCETABLE_RELATION_DESC, RESOURCETABLE_COLUMN_NAME_ROLE, SY_TREEORDERINDEX, SY_PARENTPATH, SY_DISABLED, SY_JECORE, SY_JESYS, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_AUDFLAG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSER, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_CREATEORG, SY_CREATEUSER, SY_PARENT, SY_NODETYPE, SY_LAYER, SY_PATH};

    public TbCoreResourcetableTableDef() {
        super("", "tb_core_resourcetable");
    }

    private TbCoreResourcetableTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreResourcetableTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreResourcetableTableDef("", "tb_core_resourcetable", alias));
    }

}
