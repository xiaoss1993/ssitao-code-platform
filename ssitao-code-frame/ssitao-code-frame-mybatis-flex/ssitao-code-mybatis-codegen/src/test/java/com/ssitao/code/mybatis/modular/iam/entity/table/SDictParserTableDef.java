package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据字典解析配置 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SDictParserTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典解析配置
     */
    public static final SDictParserTableDef SDICT_PARSER = new SDictParserTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 转换失败时的操作
     */
    public final QueryColumn ON_ERROR = new QueryColumn(this, "on_error");

    /**
     * 说明
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 文本到值转换方式
     */
    public final QueryColumn T_VPARSER = new QueryColumn(this, "t_v_parser");

    /**
     * 值到文本转换方式
     */
    public final QueryColumn V_TPARSER = new QueryColumn(this, "v_t_parser");

    /**
     * 创建人id
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 分类id
     */
    public final QueryColumn CLASSIFIED_ID = new QueryColumn(this, "classified_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, V_TPARSER, T_VPARSER, ON_ERROR, CREATE_TIME, CREATOR_ID, UPDATE_TIME, NAME, DESCRIBE, CLASSIFIED_ID};

    public SDictParserTableDef() {
        super("", "s_dict_parser");
    }

    private SDictParserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDictParserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDictParserTableDef("", "s_dict_parser", alias));
    }

}
