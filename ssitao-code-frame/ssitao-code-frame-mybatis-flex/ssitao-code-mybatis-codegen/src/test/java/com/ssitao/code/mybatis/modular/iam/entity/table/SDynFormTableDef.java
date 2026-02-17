package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 动态单 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SDynFormTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 动态单
     */
    public static final SDynFormTableDef SDYN_FORM = new SDynFormTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 表单名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 标签
     */
    public final QueryColumn TAGS = new QueryColumn(this, "tags");

    /**
     * 表单类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 别名
     */
    public final QueryColumn ALIAS = new QueryColumn(this, "alias");

    /**
     * 数据库表名
     */
    public final QueryColumn T_NAME = new QueryColumn(this, "t_name");

    /**
     * 数据库名
     */
    public final QueryColumn DB_NAME = new QueryColumn(this, "db_name");

    /**
     * 版本
     */
    public final QueryColumn VERSION = new QueryColumn(this, "version");

    /**
     * 备注
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 触发器
     */
    public final QueryColumn TRIGGERS = new QueryColumn(this, "triggers");

    /**
     * 创建人id
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 是否已发布
     */
    public final QueryColumn IS_DEPLOYED = new QueryColumn(this, "is_deployed");

    /**
     * 其他配置
     */
    public final QueryColumn PROPERTIES = new QueryColumn(this, "properties");

    /**
     * 修改时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 表链接
     */
    public final QueryColumn CORRELATIONS = new QueryColumn(this, "correlations");

    /**
     * 数据源id,为空使用默认数据源
     */
    public final QueryColumn DATA_SOURCE_ID = new QueryColumn(this, "data_source_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, T_NAME, DESCRIBE, TYPE, VERSION, IS_DEPLOYED, ALIAS, TRIGGERS, CORRELATIONS, DATA_SOURCE_ID, CREATOR_ID, CREATE_TIME, UPDATE_TIME, PROPERTIES, TAGS, DB_NAME};

    public SDynFormTableDef() {
        super("", "s_dyn_form");
    }

    private SDynFormTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDynFormTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDynFormTableDef("", "s_dyn_form", alias));
    }

}
