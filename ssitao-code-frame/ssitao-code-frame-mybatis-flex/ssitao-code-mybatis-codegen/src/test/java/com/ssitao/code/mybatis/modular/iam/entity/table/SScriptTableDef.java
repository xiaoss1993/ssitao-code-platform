package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 动态脚本 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SScriptTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 动态脚本
     */
    public static final SScriptTableDef SSCRIPT = new SScriptTableDef();

    /**
     * 脚本标签
     */
    public final QueryColumn TAG = new QueryColumn(this, "tag");

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 脚本名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 备注
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 脚本内容
     */
    public final QueryColumn SCRIPT = new QueryColumn(this, "script");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 脚本语言
     */
    public final QueryColumn LANGUAGE = new QueryColumn(this, "language");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, TYPE, SCRIPT, LANGUAGE, REMARK, STATUS, TAG};

    public SScriptTableDef() {
        super("", "s_script");
    }

    private SScriptTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SScriptTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SScriptTableDef("", "s_script", alias));
    }

}
