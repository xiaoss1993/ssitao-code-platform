package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 系统信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SSystemTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 系统信息
     */
    public static final SSystemTableDef SSYSTEM = new SSystemTableDef();

    /**
     * 系统名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 系统说明
     */
    public final QueryColumn COMMENT = new QueryColumn(this, "comment");

    /**
     * 系统网址
     */
    public final QueryColumn WEBSITE = new QueryColumn(this, "website");

    /**
     * 是否快照版
     */
    public final QueryColumn SNAPSHOT = new QueryColumn(this, "snapshot");

    /**
     * 依赖详情
     */
    public final QueryColumn DEPENDENCIES = new QueryColumn(this, "dependencies");

    /**
     * 主版本号
     */
    public final QueryColumn MAJOR_VERSION = new QueryColumn(this, "major_version");

    /**
     * 次版本号
     */
    public final QueryColumn MINOR_VERSION = new QueryColumn(this, "minor_version");

    /**
     * 修订版
     */
    public final QueryColumn REVISION_VERSION = new QueryColumn(this, "revision_version");

    /**
     * 框架版本
     */
    public final QueryColumn FRAMEWORK_VERSION = new QueryColumn(this, "framework_version");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{NAME, MAJOR_VERSION, MINOR_VERSION, REVISION_VERSION, SNAPSHOT, COMMENT, WEBSITE, FRAMEWORK_VERSION, DEPENDENCIES};

    public SSystemTableDef() {
        super("", "s_system");
    }

    private SSystemTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SSystemTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SSystemTableDef("", "s_system", alias));
    }

}
