package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 员工语言能力 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbHrmsStaffLanguageTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 员工语言能力
     */
    public static final TbHrmsStaffLanguageTableDef TB_HRMS_STAFF_LANGUAGE = new TbHrmsStaffLanguageTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 获取时间
     */
    public final QueryColumn GET_TIME = new QueryColumn(this, "get_time");

    /**
     * 语种等级id
     */
    public final QueryColumn LEVEL_ID = new QueryColumn(this, "level_id");

    /**
     * 创建人
     */
    public final QueryColumn CREATE_ID = new QueryColumn(this, "create_id");

    /**
     * 所属第三方业务数据id(员工id)
     */
    public final QueryColumn OBJECT_ID = new QueryColumn(this, "object_id");

    /**
     * 所属第三方业务数据的key(员工key)
     */
    public final QueryColumn OBJECT_KEY = new QueryColumn(this, "object_key");

    /**
     * 录入时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 最后更新人
     */
    public final QueryColumn LAST_UPDATE_ID = new QueryColumn(this, "last_update_id");

    /**
     * 最后更新时间
     */
    public final QueryColumn LAST_UPDATE_TIME = new QueryColumn(this, "last_update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, LEVEL_ID, GET_TIME, OBJECT_ID, OBJECT_KEY, CREATE_ID, CREATE_TIME, LAST_UPDATE_ID, LAST_UPDATE_TIME};

    public TbHrmsStaffLanguageTableDef() {
        super("", "tb_hrms_staff_language");
    }

    private TbHrmsStaffLanguageTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbHrmsStaffLanguageTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbHrmsStaffLanguageTableDef("", "tb_hrms_staff_language", alias));
    }

}
