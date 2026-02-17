package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 员工家庭情况 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbHrmsStaffFamilyTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 员工家庭情况
     */
    public static final TbHrmsStaffFamilyTableDef TB_HRMS_STAFF_FAMILY = new TbHrmsStaffFamilyTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 职务
     */
    public final QueryColumn JOB = new QueryColumn(this, "job");

    /**
     * 性别
     */
    public final QueryColumn SEX = new QueryColumn(this, "sex");

    /**
     * 姓名
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 证件类型id
     */
    public final QueryColumn CARD_TYPE = new QueryColumn(this, "card_type");

    /**
     * 创建人
     */
    public final QueryColumn CREATE_ID = new QueryColumn(this, "create_id");

    /**
     * 所属第三方业务数据id(员工id)
     */
    public final QueryColumn OBJECT_ID = new QueryColumn(this, "object_id");

    /**
     * 工作单位
     */
    public final QueryColumn WORK_UNIT = new QueryColumn(this, "work_unit");

    /**
     * 所属第三方业务数据的key(员工key)
     */
    public final QueryColumn OBJECT_KEY = new QueryColumn(this, "object_key");

    /**
     * 政治面貌id
     */
    public final QueryColumn POLITIC_ID = new QueryColumn(this, "politic_id");

    /**
     * 证件号码
     */
    public final QueryColumn CARD_NUMBER = new QueryColumn(this, "card_number");

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
     * 与本人关系id
     */
    public final QueryColumn RELATIONSHIP_ID = new QueryColumn(this, "relationship_id");

    /**
     * 是否紧急联系人
     */
    public final QueryColumn EMERGENCY_CONTACT = new QueryColumn(this, "emergency_contact");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, RELATIONSHIP_ID, NAME, CARD_TYPE, CARD_NUMBER, SEX, WORK_UNIT, JOB, POLITIC_ID, EMERGENCY_CONTACT, OBJECT_ID, OBJECT_KEY, CREATE_ID, CREATE_TIME, LAST_UPDATE_ID, LAST_UPDATE_TIME};

    public TbHrmsStaffFamilyTableDef() {
        super("", "tb_hrms_staff_family");
    }

    private TbHrmsStaffFamilyTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbHrmsStaffFamilyTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbHrmsStaffFamilyTableDef("", "tb_hrms_staff_family", alias));
    }

}
