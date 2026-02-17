package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 关系定义 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SRelationDefTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 关系定义
     */
    public static final SRelationDefTableDef SRELATION_DEF = new SRelationDefTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 关系名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 关系类型
     */
    public final QueryColumn TYPE_ID = new QueryColumn(this, "type_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, TYPE_ID, STATUS};

    public SRelationDefTableDef() {
        super("", "s_relation_def");
    }

    private SRelationDefTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SRelationDefTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SRelationDefTableDef("", "s_relation_def", alias));
    }

}
