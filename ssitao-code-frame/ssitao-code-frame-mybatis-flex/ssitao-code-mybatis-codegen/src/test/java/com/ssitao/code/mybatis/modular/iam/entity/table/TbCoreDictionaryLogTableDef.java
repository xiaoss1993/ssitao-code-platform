package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据字典删除日志 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreDictionaryLogTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典删除日志
     */
    public static final TbCoreDictionaryLogTableDef TB_CORE_DICTIONARY_LOG = new TbCoreDictionaryLogTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 字典编码
     */
    public final QueryColumn LOG_DIC_CODE = new QueryColumn(this, "log_dic_code");

    /**
     * 字典信息
     */
    public final QueryColumn LOG_DIC_INFO = new QueryColumn(this, "log_dic_info");

    /**
     * 字典名称
     */
    public final QueryColumn LOG_DIC_NAME = new QueryColumn(this, "log_dic_name");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 字典项信息
     */
    public final QueryColumn LOG_ITEM_INFO = new QueryColumn(this, "log_item_info");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_DICTIONARY_LOG_ID = new QueryColumn(this, "tb_core_dictionary_log_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_DICTIONARY_LOG_ID, LOG_DIC_CODE, LOG_DIC_NAME, LOG_DIC_INFO, LOG_ITEM_INFO, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreDictionaryLogTableDef() {
        super("", "tb_core_dictionary_log");
    }

    private TbCoreDictionaryLogTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreDictionaryLogTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreDictionaryLogTableDef("", "tb_core_dictionary_log", alias));
    }

}
