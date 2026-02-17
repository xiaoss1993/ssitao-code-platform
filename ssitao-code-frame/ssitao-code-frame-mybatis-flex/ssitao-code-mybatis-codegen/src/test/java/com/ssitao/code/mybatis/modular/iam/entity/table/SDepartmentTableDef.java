package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 部门 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SDepartmentTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 部门
     */
    public static final SDepartmentTableDef SDEPARTMENT = new SDepartmentTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 部门编码
     */
    public final QueryColumn CODE = new QueryColumn(this, "code");

    /**
     * 名称
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
     * 所在组织id
     */
    public final QueryColumn ORG_ID = new QueryColumn(this, "org_id");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 父级id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 排序序号
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, ORG_ID, CODE, PARENT_ID, PATH, SORT_INDEX, STATUS, LEVEL};

    public SDepartmentTableDef() {
        super("", "s_department");
    }

    private SDepartmentTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDepartmentTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDepartmentTableDef("", "s_department", alias));
    }

}
