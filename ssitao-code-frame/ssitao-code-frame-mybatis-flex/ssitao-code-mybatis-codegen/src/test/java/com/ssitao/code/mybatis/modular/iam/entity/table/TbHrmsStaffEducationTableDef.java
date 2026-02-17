package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 员工教育背景 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbHrmsStaffEducationTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 员工教育背景
     */
    public static final TbHrmsStaffEducationTableDef TB_HRMS_STAFF_EDUCATION = new TbHrmsStaffEducationTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 专业
     */
    public final QueryColumn MAJOR = new QueryColumn(this, "major");

    /**
     * 毕业时间
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
     * 所属第三方业务数据的key(员工key)
     */
    public final QueryColumn OBJECT_KEY = new QueryColumn(this, "object_key");

    /**
     * 入学时间
     */
    public final QueryColumn START_TIME = new QueryColumn(this, "start_time");

    /**
     * 录入时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 学历id
     */
    public final QueryColumn EDUCATION_ID = new QueryColumn(this, "education_id");

    /**
     * 最后更新人
     */
    public final QueryColumn LAST_UPDATE_ID = new QueryColumn(this, "last_update_id");

    /**
     * 学校性质
     */
    public final QueryColumn SCHOOL_NATURE = new QueryColumn(this, "school_nature");

    /**
     * 最后更新时间
     */
    public final QueryColumn LAST_UPDATE_TIME = new QueryColumn(this, "last_update_time");

    /**
     * 毕业学校
     */
    public final QueryColumn GRADUCTION_SCHOOL = new QueryColumn(this, "graduction_school");

    /**
     * 学习形式
     */
    public final QueryColumn LEARNING_MODALITY_ID = new QueryColumn(this, "learning_modality_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, EDUCATION_ID, START_TIME, END_TIME, GRADUCTION_SCHOOL, MAJOR, LEARNING_MODALITY_ID, SCHOOL_NATURE, OBJECT_ID, OBJECT_KEY, CREATE_ID, CREATE_TIME, LAST_UPDATE_ID, LAST_UPDATE_TIME};

    public TbHrmsStaffEducationTableDef() {
        super("", "tb_hrms_staff_education");
    }

    private TbHrmsStaffEducationTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbHrmsStaffEducationTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbHrmsStaffEducationTableDef("", "tb_hrms_staff_education", alias));
    }

}
