package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 *  表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class FlywaySchemaHistoryTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final FlywaySchemaHistoryTableDef FLYWAY_SCHEMA_HISTORY = new FlywaySchemaHistoryTableDef();

    
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    
    public final QueryColumn SCRIPT = new QueryColumn(this, "script");

    
    public final QueryColumn SUCCESS = new QueryColumn(this, "success");

    
    public final QueryColumn VERSION = new QueryColumn(this, "version");

    
    public final QueryColumn CHECKSUM = new QueryColumn(this, "checksum");

    
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    
    public final QueryColumn INSTALLED_BY = new QueryColumn(this, "installed_by");

    
    public final QueryColumn INSTALLED_ON = new QueryColumn(this, "installed_on");

    
    public final QueryColumn EXECUTION_TIME = new QueryColumn(this, "execution_time");

    
    public final QueryColumn INSTALLED_RANK = new QueryColumn(this, "installed_rank");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{INSTALLED_RANK, VERSION, DESCRIPTION, TYPE, SCRIPT, CHECKSUM, INSTALLED_BY, INSTALLED_ON, EXECUTION_TIME, SUCCESS};

    public FlywaySchemaHistoryTableDef() {
        super("", "flyway_schema_history");
    }

    private FlywaySchemaHistoryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public FlywaySchemaHistoryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new FlywaySchemaHistoryTableDef("", "flyway_schema_history", alias));
    }

}
