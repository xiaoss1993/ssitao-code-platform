package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 人员职位关联 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SPersonPositionTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 人员职位关联
     */
    public static final SPersonPositionTableDef SPERSON_POSITION = new SPersonPositionTableDef();

    /**
     * 人员id
     */
    public final QueryColumn PERSON_ID = new QueryColumn(this, "person_id");

    /**
     * 职位id
     */
    public final QueryColumn POSITION_ID = new QueryColumn(this, "position_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{PERSON_ID, POSITION_ID};

    public SPersonPositionTableDef() {
        super("", "s_person_position");
    }

    private SPersonPositionTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SPersonPositionTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SPersonPositionTableDef("", "s_person_position", alias));
    }

}
