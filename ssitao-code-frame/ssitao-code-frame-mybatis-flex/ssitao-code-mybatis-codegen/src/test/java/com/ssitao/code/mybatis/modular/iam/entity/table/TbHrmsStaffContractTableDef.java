package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 员工合同信息 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbHrmsStaffContractTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 员工合同信息
     */
    public static final TbHrmsStaffContractTableDef TB_HRMS_STAFF_CONTRACT = new TbHrmsStaffContractTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 合同类型
     */
    public final QueryColumn MOLD_ID = new QueryColumn(this, "mold_id");

    /**
     * 合同类别
     */
    public final QueryColumn TYPE_ID = new QueryColumn(this, "type_id");

    /**
     * 结束时间
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
     * 开始时间
     */
    public final QueryColumn START_TIME = new QueryColumn(this, "start_time");

    /**
     * 录入时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 最后更新人
     */
    public final QueryColumn LAST_UPDATE_ID = new QueryColumn(this, "last_update_id");

    /**
     * 合同编号
     */
    public final QueryColumn CONTRACT_NUMBER = new QueryColumn(this, "contract_number");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, CONTRACT_NUMBER, TYPE_ID, MOLD_ID, START_TIME, END_TIME, OBJECT_ID, OBJECT_KEY, CREATE_ID, CREATE_TIME, LAST_UPDATE_ID, LAST_UPDATE_TIME};

    public TbHrmsStaffContractTableDef() {
        super("", "tb_hrms_staff_contract");
    }

    private TbHrmsStaffContractTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbHrmsStaffContractTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbHrmsStaffContractTableDef("", "tb_hrms_staff_contract", alias));
    }

}
