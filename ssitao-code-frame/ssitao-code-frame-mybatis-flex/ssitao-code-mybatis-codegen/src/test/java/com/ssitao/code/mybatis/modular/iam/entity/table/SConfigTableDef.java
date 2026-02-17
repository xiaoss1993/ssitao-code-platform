package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 系统配置文件 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SConfigTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 系统配置文件
     */
    public static final SConfigTableDef SCONFIG = new SConfigTableDef();

    /**
     * uid
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 备注
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 配置内容
     */
    public final QueryColumn CONTENT = new QueryColumn(this, "content");

    /**
     * 创建者ID
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 创建日期
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 修改日期
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 分类id
     */
    public final QueryColumn CLASSIFIED_ID = new QueryColumn(this, "classified_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, CONTENT, REMARK, CREATOR_ID, CREATE_TIME, UPDATE_TIME, CLASSIFIED_ID};

    public SConfigTableDef() {
        super("", "s_config");
    }

    private SConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SConfigTableDef("", "s_config", alias));
    }

}
