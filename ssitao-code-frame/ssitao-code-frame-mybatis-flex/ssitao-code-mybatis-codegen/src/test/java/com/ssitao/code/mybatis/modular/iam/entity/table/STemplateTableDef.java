package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 模板 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class STemplateTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 模板
     */
    public static final STemplateTableDef STEMPLATE = new STemplateTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 模板名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 模板类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 模板配置
     */
    public final QueryColumn CONFIG = new QueryColumn(this, "config");

    /**
     * 版本号
     */
    public final QueryColumn VERSION = new QueryColumn(this, "version");

    /**
     * 模板内容
     */
    public final QueryColumn TEMPLATE = new QueryColumn(this, "template");

    /**
     * 模板分类
     */
    public final QueryColumn CLASSIFIED = new QueryColumn(this, "classified");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, TYPE, TEMPLATE, CONFIG, VERSION, CLASSIFIED};

    public STemplateTableDef() {
        super("", "s_template");
    }

    private STemplateTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public STemplateTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new STemplateTableDef("", "s_template", alias));
    }

}
