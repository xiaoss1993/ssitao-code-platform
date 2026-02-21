package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据源配置 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SDatasourceConfTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源配置
     */
    public static final SDatasourceConfTableDef SDATASOURCE_CONF = new SDatasourceConfTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 数据源名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 是否启用
     */
    public final QueryColumn ENABLED = new QueryColumn(this, "enabled");

    /**
     * 备注
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * 创建日期
     */
    public final QueryColumn CREATE_DATE = new QueryColumn(this, "create_date");

    /**
     * 数据源配置
     */
    public final QueryColumn PROPERTIES = new QueryColumn(this, "properties");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, ENABLED, CREATE_DATE, PROPERTIES, DESCRIBE};

    public SDatasourceConfTableDef() {
        super("", "s_datasource_conf");
    }

    private SDatasourceConfTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDatasourceConfTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDatasourceConfTableDef("", "s_datasource_conf", alias));
    }

}
