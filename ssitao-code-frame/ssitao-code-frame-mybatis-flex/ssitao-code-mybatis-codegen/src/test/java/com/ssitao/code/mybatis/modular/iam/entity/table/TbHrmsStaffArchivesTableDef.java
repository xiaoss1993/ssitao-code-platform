package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 员工档案信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbHrmsStaffArchivesTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 员工档案信息
     */
    public static final TbHrmsStaffArchivesTableDef TB_HRMS_STAFF_ARCHIVES = new TbHrmsStaffArchivesTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 档案状态
     */
    public final QueryColumn STATE = new QueryColumn(this, "state");

    /**
     * 备注
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 创建人
     */
    public final QueryColumn CREATE_ID = new QueryColumn(this, "create_id");

    /**
     * 所属第三方业务数据id(员工id)
     */
    public final QueryColumn OBJECT_ID = new QueryColumn(this, "object_id");

    /**
     * 管理单位
     */
    public final QueryColumn COMPANY_ID = new QueryColumn(this, "company_id");

    /**
     * 所属第三方业务数据的key(员工key)
     */
    public final QueryColumn OBJECT_KEY = new QueryColumn(this, "object_key");

    /**
     * 录入时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 档案学历
     */
    public final QueryColumn EDUCATION_ID = new QueryColumn(this, "education_id");

    /**
     * 入档时间
     */
    public final QueryColumn ARCHIVES_TIME = new QueryColumn(this, "archives_time");

    /**
     * 档案保管地
     */
    public final QueryColumn CUSTODY_PLACE = new QueryColumn(this, "custody_place");

    /**
     * 最后更新人
     */
    public final QueryColumn LAST_UPDATE_ID = new QueryColumn(this, "last_update_id");

    /**
     * 档案室
     */
    public final QueryColumn ARCHIVES_CENTER = new QueryColumn(this, "archives_center");

    /**
     * 档案编号
     */
    public final QueryColumn ARCHIVES_NUMBER = new QueryColumn(this, "archives_number");

    /**
     * 最后更新时间
     */
    public final QueryColumn LAST_UPDATE_TIME = new QueryColumn(this, "last_update_time");

    /**
     * 是否在档
     */
    public final QueryColumn WHETHER_ARCHIVES = new QueryColumn(this, "whether_archives");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ARCHIVES_NUMBER, COMPANY_ID, CUSTODY_PLACE, ARCHIVES_CENTER, ARCHIVES_TIME, EDUCATION_ID, WHETHER_ARCHIVES, STATE, REMARK, OBJECT_ID, OBJECT_KEY, CREATE_ID, CREATE_TIME, LAST_UPDATE_ID, LAST_UPDATE_TIME};

    public TbHrmsStaffArchivesTableDef() {
        super("", "tb_hrms_staff_archives");
    }

    private TbHrmsStaffArchivesTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbHrmsStaffArchivesTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbHrmsStaffArchivesTableDef("", "tb_hrms_staff_archives", alias));
    }

}
