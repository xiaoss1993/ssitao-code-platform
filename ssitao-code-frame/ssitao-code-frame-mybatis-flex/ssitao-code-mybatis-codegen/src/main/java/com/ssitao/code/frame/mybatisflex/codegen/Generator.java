
package com.ssitao.code.frame.mybatisflex.codegen;

import com.ssitao.code.frame.mybatisflex.codegen.config.GlobalConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.StrategyConfig;
import com.ssitao.code.frame.mybatisflex.codegen.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;
import com.ssitao.code.frame.mybatisflex.codegen.generator.GeneratorFactory;
import com.ssitao.code.frame.mybatisflex.codegen.generator.IGenerator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 代码生成器。
 *
 * @author ssitao
 */
public class Generator {

    protected DataSource dataSource;
    protected GlobalConfig globalConfig;
    protected IDialect dialect = IDialect.DEFAULT;

    public Generator(DataSource dataSource, GlobalConfig globalConfig) {
        this.dataSource = dataSource;
        this.globalConfig = globalConfig;
    }

    public Generator(DataSource dataSource, GlobalConfig globalConfig, IDialect dialect) {
        this.dataSource = dataSource;
        this.globalConfig = globalConfig;
        this.dialect = dialect;
    }

    public void generate() {
        generate(getTables());
    }

    public void generate(List<Table> tables) {
        if (tables == null || tables.isEmpty()) {
            System.err.printf("table %s not found.%n", globalConfig.getGenerateTables());
            return;
        } else {
            System.out.printf("find tables: %s%n", tables.stream().map(Table::getName).collect(Collectors.toSet()));
        }

        for (Table table : tables) {
            Collection<IGenerator> generators = GeneratorFactory.getGenerators();
            for (IGenerator generator : generators) {
                generator.generate(table, globalConfig);
            }
        }
        System.out.println("Code is generated successfully.");
    }


    public List<Table> getTables() {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData dbMeta = conn.getMetaData();
            return buildTables(dbMeta, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void buildPrimaryKey(DatabaseMetaData dbMeta, Connection conn, Table table) throws SQLException {
        try (ResultSet rs = dbMeta.getPrimaryKeys(conn.getCatalog(), null, table.getName())) {
            while (rs.next()) {
                String primaryKey = rs.getString("COLUMN_NAME");
                table.addPrimaryKey(primaryKey);
            }
        }
    }

    protected List<Table> buildTables(DatabaseMetaData dbMeta, Connection conn) throws SQLException {
        StrategyConfig strategyConfig = globalConfig.getStrategyConfig();
        String schemaName = strategyConfig.getGenerateSchema();
        List<Table> tables = new ArrayList<>();
        try (ResultSet rs = getTablesResultSet(dbMeta, conn, schemaName)) {
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (!strategyConfig.isSupportGenerate(tableName)) {
                    continue;
                }

                Table table = new Table();
                table.setGlobalConfig(globalConfig);
                table.setTableConfig(strategyConfig.getTableConfig(tableName));
                table.setEntityConfig(globalConfig.getEntityConfig());

                table.setSchema(schemaName);
                table.setName(tableName);

                String remarks = rs.getString("REMARKS");
                table.setComment(remarks);


                buildPrimaryKey(dbMeta, conn, table);

                dialect.buildTableColumns(schemaName, table, globalConfig, dbMeta, conn);

                tables.add(table);
            }
        }
        return tables;
    }


    protected ResultSet getTablesResultSet(DatabaseMetaData dbMeta, Connection conn, String schema) throws SQLException {
        if (globalConfig.getStrategyConfig().isGenerateForView()) {
            return dialect.getTablesResultSet(dbMeta, conn, schema, new String[]{"TABLE", "VIEW"});
        } else {
            return dialect.getTablesResultSet(dbMeta, conn, schema, new String[]{"TABLE"});
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Generator setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public Generator setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }

    public IDialect getDialect() {
        return dialect;
    }

    public Generator setDialect(IDialect dialect) {
        this.dialect = dialect;
        return this;
    }

}
