package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 行政区域 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SDistrictTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 行政区域
     */
    public static final SDistrictTableDef SDISTRICT = new SDistrictTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 行政区域代码,如:500000
     */
    public final QueryColumn CODE = new QueryColumn(this, "code");

    /**
     * 区域名称,如重庆市
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 树路径,如: asb3-lsat
     */
    public final QueryColumn PATH = new QueryColumn(this, "path");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 说明
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 区域全程,如重庆市江津区
     */
    public final QueryColumn FULL_NAME = new QueryColumn(this, "full_name");

    /**
     * 父级行政区域
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 区域级别编码,如:province
     */
    public final QueryColumn LEVEL_CODE = new QueryColumn(this, "level_code");

    /**
     * 区域级别名称,如:省
     */
    public final QueryColumn LEVEL_NAME = new QueryColumn(this, "level_name");

    /**
     * 排序索引
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, FULL_NAME, LEVEL_NAME, LEVEL_CODE, CODE, PARENT_ID, PATH, DESCRIBE, STATUS, SORT_INDEX};

    public SDistrictTableDef() {
        super("", "s_district");
    }

    private SDistrictTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDistrictTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDistrictTableDef("", "s_district", alias));
    }

}
