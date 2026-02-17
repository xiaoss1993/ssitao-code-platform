package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 单发布日志 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class SDynFormLogTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 单发布日志
     */
    public static final SDynFormLogTableDef SDYN_FORM_LOG = new SDynFormLogTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 表单ID
     */
    public final QueryColumn FORM_ID = new QueryColumn(this, "form_id");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 发布的版本
     */
    public final QueryColumn VERSION = new QueryColumn(this, "version");

    /**
     * 部署的元数据
     */
    public final QueryColumn META_DATA = new QueryColumn(this, "meta_data");

    /**
     * 发布时间
     */
    public final QueryColumn DEPLOY_TIME = new QueryColumn(this, "deploy_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, FORM_ID, VERSION, DEPLOY_TIME, META_DATA, STATUS};

    public SDynFormLogTableDef() {
        super("", "s_dyn_form_log");
    }

    private SDynFormLogTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDynFormLogTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDynFormLogTableDef("", "s_dyn_form_log", alias));
    }

}
