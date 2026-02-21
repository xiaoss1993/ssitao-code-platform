package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 关系信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SRelationInfoTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 关系信息
     */
    public static final SRelationInfoTableDef SRELATION_INFO = new SRelationInfoTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 关系定义id
     */
    public final QueryColumn RELATION_ID = new QueryColumn(this, "relation_id");

    /**
     * 关系至
     */
    public final QueryColumn RELATION_TO = new QueryColumn(this, "relation_to");

    /**
     * 关系从
     */
    public final QueryColumn RELATION_FROM = new QueryColumn(this, "relation_from");

    /**
     * 关系类型至,如:部门
     */
    public final QueryColumn RELATION_TYPE_TO = new QueryColumn(this, "relation_type_to");

    /**
     * 关系类型从,如:人员
     */
    public final QueryColumn RELATION_TYPE_FROM = new QueryColumn(this, "relation_type_from");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, RELATION_ID, RELATION_FROM, RELATION_TO, RELATION_TYPE_FROM, RELATION_TYPE_TO, STATUS};

    public SRelationInfoTableDef() {
        super("", "s_relation_info");
    }

    private SRelationInfoTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SRelationInfoTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SRelationInfoTableDef("", "s_relation_info", alias));
    }

}
