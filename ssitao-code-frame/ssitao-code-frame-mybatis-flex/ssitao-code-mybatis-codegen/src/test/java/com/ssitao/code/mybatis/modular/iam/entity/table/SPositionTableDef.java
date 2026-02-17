package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 职位 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SPositionTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 职位
     */
    public static final SPositionTableDef SPOSITION = new SPositionTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 职位名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 树结构编码
     */
    public final QueryColumn PATH = new QueryColumn(this, "path");

    /**
     * 级别
     */
    public final QueryColumn LEVEL = new QueryColumn(this, "level");

    /**
     * 持有的角色
     */
    public final QueryColumn ROLES = new QueryColumn(this, "roles");

    /**
     * 备注
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 父级id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 排序索引
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 部门id
     */
    public final QueryColumn DEPARTMENT_ID = new QueryColumn(this, "department_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, DEPARTMENT_ID, ROLES, REMARK, PARENT_ID, PATH, SORT_INDEX, LEVEL};

    public SPositionTableDef() {
        super("", "s_position");
    }

    private SPositionTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SPositionTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SPositionTableDef("", "s_position", alias));
    }

}
