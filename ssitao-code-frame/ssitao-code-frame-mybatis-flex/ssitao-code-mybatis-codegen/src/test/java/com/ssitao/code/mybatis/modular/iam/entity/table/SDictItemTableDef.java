package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据字典选项配置 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SDictItemTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典选项配置
     */
    public static final SDictItemTableDef SDICT_ITEM = new SDictItemTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 树编码
     */
    public final QueryColumn PATH = new QueryColumn(this, "path");

    /**
     * 字典文本
     */
    public final QueryColumn TEXT = new QueryColumn(this, "text");

    /**
     * 树结构层级
     */
    public final QueryColumn LEVEL = new QueryColumn(this, "level_");

    /**
     * 字典值
     */
    public final QueryColumn VALUE = new QueryColumn(this, "value");

    /**
     * 字典id
     */
    public final QueryColumn DICT_ID = new QueryColumn(this, "dict_id");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 识别码
     */
    public final QueryColumn ORDINAL = new QueryColumn(this, "ordinal");

    /**
     * 说明
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 父级选项
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 排序索引
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 字典值类型
     */
    public final QueryColumn VALUE_TYPE = new QueryColumn(this, "value_type");

    /**
     * 其他自定义属性
     */
    public final QueryColumn PROPERTIES = new QueryColumn(this, "properties");

    /**
     * 快速搜索码
     */
    public final QueryColumn SEARCH_CODE = new QueryColumn(this, "search_code");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, DICT_ID, NAME, VALUE, TEXT, VALUE_TYPE, STATUS, DESCRIBE, PARENT_ID, PATH, SEARCH_CODE, SORT_INDEX, LEVEL, ORDINAL, PROPERTIES};

    public SDictItemTableDef() {
        super("", "s_dict_item");
    }

    private SDictItemTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDictItemTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDictItemTableDef("", "s_dict_item", alias));
    }

}
