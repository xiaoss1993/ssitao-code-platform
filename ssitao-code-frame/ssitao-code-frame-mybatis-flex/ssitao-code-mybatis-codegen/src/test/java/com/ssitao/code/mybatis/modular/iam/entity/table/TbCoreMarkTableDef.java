package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 功能数据标记 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreMarkTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 功能数据标记
     */
    public static final TbCoreMarkTableDef TB_CORE_MARK = new TbCoreMarkTableDef();

    /**
     * 1
     */
    public final QueryColumn MARK1 = new QueryColumn(this, "mark_1");

    /**
     * 2
     */
    public final QueryColumn MARK2 = new QueryColumn(this, "mark_2");

    /**
     * 3
     */
    public final QueryColumn MARK3 = new QueryColumn(this, "mark_3");

    /**
     * 4
     */
    public final QueryColumn MARK4 = new QueryColumn(this, "mark_4");

    /**
     * 5
     */
    public final QueryColumn MARK5 = new QueryColumn(this, "mark_5");

    /**
     * 6
     */
    public final QueryColumn MARK6 = new QueryColumn(this, "mark_6");

    /**
     * 7
     */
    public final QueryColumn MARK7 = new QueryColumn(this, "mark_7");

    /**
     * 功能id
     */
    public final QueryColumn MARK_FUNCID = new QueryColumn(this, "mark_funcid");

    /**
     * 用户id
     */
    public final QueryColumn MARK_USERID = new QueryColumn(this, "mark_userid");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 数据id
     */
    public final QueryColumn MARK_MODELID = new QueryColumn(this, "mark_modelid");

    /**
     * 功能编码
     */
    public final QueryColumn MARK_FUNCCODE = new QueryColumn(this, "mark_funccode");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    
    public final QueryColumn TB_CORE_MARK_ID = new QueryColumn(this, "tb_core_mark_id");

    /**
     * 表名
     */
    public final QueryColumn MARK_TABLECODE = new QueryColumn(this, "mark_tablecode");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_MARK_ID, MARK_FUNCID, MARK_FUNCCODE, MARK_TABLECODE, MARK_MODELID, MARK_USERID, MARK1, MARK2, MARK3, MARK4, MARK5, MARK6, MARK7, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreMarkTableDef() {
        super("", "tb_core_mark");
    }

    private TbCoreMarkTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreMarkTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreMarkTableDef("", "tb_core_mark", alias));
    }

}
