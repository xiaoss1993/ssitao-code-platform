package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 员工工作履历 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbHrmsStaffJobResumeTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 员工工作履历
     */
    public static final TbHrmsStaffJobResumeTableDef TB_HRMS_STAFF_JOB_RESUME = new TbHrmsStaffJobResumeTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 职务
     */
    public final QueryColumn JOB = new QueryColumn(this, "job");

    /**
     * 任职结束时间
     */
    public final QueryColumn END_TIME = new QueryColumn(this, "end_time");

    /**
     * 创建人
     */
    public final QueryColumn CREATE_ID = new QueryColumn(this, "create_id");

    /**
     * 所属第三方业务数据id(员工id)
     */
    public final QueryColumn OBJECT_ID = new QueryColumn(this, "object_id");

    /**
     * 任职单位
     */
    public final QueryColumn WORK_UNIT = new QueryColumn(this, "work_unit");

    /**
     * 所属第三方业务数据的key(员工key)
     */
    public final QueryColumn OBJECT_KEY = new QueryColumn(this, "object_key");

    /**
     * 任职开始时间
     */
    public final QueryColumn START_TIME = new QueryColumn(this, "start_time");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 部门
     */
    public final QueryColumn DEPARTMENT = new QueryColumn(this, "department");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, START_TIME, END_TIME, WORK_UNIT, DEPARTMENT, JOB, OBJECT_ID, OBJECT_KEY, CREATE_ID, CREATE_TIME, LAST_UPDATE_ID, LAST_UPDATE_TIME};

    public TbHrmsStaffJobResumeTableDef() {
        super("", "tb_hrms_staff_job_resume");
    }

    private TbHrmsStaffJobResumeTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbHrmsStaffJobResumeTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbHrmsStaffJobResumeTableDef("", "tb_hrms_staff_job_resume", alias));
    }

}
