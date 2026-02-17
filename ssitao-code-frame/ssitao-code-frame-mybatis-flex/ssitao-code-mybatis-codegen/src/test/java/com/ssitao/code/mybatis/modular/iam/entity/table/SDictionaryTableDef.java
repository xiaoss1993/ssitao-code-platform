package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据字典 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SDictionaryTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典
     */
    public static final SDictionaryTableDef SDICTIONARY = new SDictionaryTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 字典名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 说明
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 创建人id
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, CLASSIFIED_ID, DESCRIBE, CREATE_TIME, CREATOR_ID, STATUS};

    public SDictionaryTableDef() {
        super("", "s_dictionary");
    }

    private SDictionaryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDictionaryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDictionaryTableDef("", "s_dictionary", alias));
    }

}
