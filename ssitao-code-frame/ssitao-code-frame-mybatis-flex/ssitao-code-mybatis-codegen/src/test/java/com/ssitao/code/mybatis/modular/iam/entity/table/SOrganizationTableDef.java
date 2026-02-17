package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 组织,公司 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SOrganizationTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 组织,公司
     */
    public static final SOrganizationTableDef SORGANIZATION = new SOrganizationTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 机构编码
     */
    public final QueryColumn CODE = new QueryColumn(this, "code");

    /**
     * 名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 树定位码
     */
    public final QueryColumn PATH = new QueryColumn(this, "path");

    /**
     * 级别
     */
    public final QueryColumn LEVEL = new QueryColumn(this, "level");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 全称
     */
    public final QueryColumn FULL_NAME = new QueryColumn(this, "full_name");

    /**
     * 上级机构id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 树结构编码
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 所在行政区域ID
     */
    public final QueryColumn DISTRICT_ID = new QueryColumn(this, "district_id");

    /**
     * 可选角色
     */
    public final QueryColumn OPTIONAL_ROLES = new QueryColumn(this, "optional_roles");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, FULL_NAME, CODE, DISTRICT_ID, OPTIONAL_ROLES, PARENT_ID, PATH, SORT_INDEX, STATUS, LEVEL};

    public SOrganizationTableDef() {
        super("", "s_organization");
    }

    private SOrganizationTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SOrganizationTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SOrganizationTableDef("", "s_organization", alias));
    }

}
