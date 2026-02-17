package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 页面配置 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbMetaPageConfigTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 页面配置
     */
    public static final TbMetaPageConfigTableDef TB_META_PAGE_CONFIG = new TbMetaPageConfigTableDef();

    /**
     * 配置名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 节点ID
     */
    public final QueryColumn NODE_ID = new QueryColumn(this, "node_id");

    /**
     * 路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

    /**
     * 列数据（JSON格式）
     */
    public final QueryColumn COL_DATA = new QueryColumn(this, "col_data");

    /**
     * 子类型（ADD/EDIT/VIEW/QUERY）
     */
    public final QueryColumn SUB_TYPE = new QueryColumn(this, "sub_type");

    /**
     * 层级
     */
    public final QueryColumn SY_LAYER = new QueryColumn(this, "sy_layer");

    /**
     * 是否实体（true/false）
     */
    public final QueryColumn IS_ENTITY = new QueryColumn(this, "is_entity");

    /**
     * 映射字段
     */
    public final QueryColumn MAP_FIELD = new QueryColumn(this, "map_field");

    /**
     * 页面类型（FORM/LIST）
     */
    public final QueryColumn PAGE_TYPE = new QueryColumn(this, "page_type");

    /**
     * 父级ID
     */
    public final QueryColumn SY_PARENT = new QueryColumn(this, "sy_parent");

    /**
     * 状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 页面标题
     */
    public final QueryColumn PAGE_TITLE = new QueryColumn(this, "page_title");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 对象类型
     */
    public final QueryColumn OBJECT_TYPE = new QueryColumn(this, "object_type");

    /**
     * 节点类型
     */
    public final QueryColumn SY_NODETYPE = new QueryColumn(this, "sy_nodetype");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 工作流ID
     */
    public final QueryColumn WORKFLOW_ID = new QueryColumn(this, "workflow_id");

    /**
     * 实体类型ID
     */
    public final QueryColumn ENTITY_TYPE_ID = new QueryColumn(this, "entity_type_id");

    /**
     * 拥有者字段IDs
     */
    public final QueryColumn OWER_FIELD_IDS = new QueryColumn(this, "ower_field_ids");

    /**
     * 排序索引
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 父级路径
     */
    public final QueryColumn SY_PARENTPATH = new QueryColumn(this, "sy_parentpath");

    /**
     * 转换ID
     */
    public final QueryColumn TRANSITION_ID = new QueryColumn(this, "transition_id");

    /**
     * 操作者字段IDs
     */
    public final QueryColumn ACTOR_FIELD_IDS = new QueryColumn(this, "actor_field_ids");

    /**
     * 是否全部只读（Y/N）
     */
    public final QueryColumn IS_ALL_ONLY_READ = new QueryColumn(this, "is_all_only_read");

    /**
     * 阅读者字段IDs
     */
    public final QueryColumn READER_FIELD_IDS = new QueryColumn(this, "reader_field_ids");

    /**
     * 页面字段参数
     */
    public final QueryColumn PAGE_FIELD_PARAMS = new QueryColumn(this, "page_field_params");

    /**
     * 功能主键
     */
    public final QueryColumn TB_CORE_FUNCINFO_ID = new QueryColumn(this, "tb_core_funcinfo_id");

    /**
     * 配置ID
     */
    public final QueryColumn TB_CORE_PAGE_CONFIG_ID = new QueryColumn(this, "tb_core_page_config_id");

    /**
     * 是否保存转换或节点页面（Y/N）
     */
    public final QueryColumn IS_SAVE_TRAN_OR_NODE_PAGE = new QueryColumn(this, "is_save_tran_or_node_page");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_PAGE_CONFIG_ID, SY_PARENT, SY_LAYER, SY_NODETYPE, SY_ORDERINDEX, SY_PARENTPATH, SY_PATH, SY_STATUS, OBJECT_TYPE, PAGE_TYPE, SUB_TYPE, PAGE_TITLE, WORKFLOW_ID, TB_CORE_FUNCINFO_ID, PAGE_FIELD_PARAMS, ENTITY_TYPE_ID, NODE_ID, TRANSITION_ID, OWER_FIELD_IDS, ACTOR_FIELD_IDS, READER_FIELD_IDS, IS_ALL_ONLY_READ, IS_ENTITY, MAP_FIELD, IS_SAVE_TRAN_OR_NODE_PAGE, NAME, COL_DATA, CREATE_TIME, UPDATE_TIME};

    public TbMetaPageConfigTableDef() {
        super("", "tb_meta_page_config");
    }

    private TbMetaPageConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbMetaPageConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbMetaPageConfigTableDef("", "tb_meta_page_config", alias));
    }

}
