package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 工作日历消息推送 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbSysCalendarPushTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 工作日历消息推送
     */
    public static final TbSysCalendarPushTableDef TB_SYS_CALENDAR_PUSH = new TbSysCalendarPushTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 推送时间
     */
    public final QueryColumn PUSH_TIME = new QueryColumn(this, "push_time");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 结束时间
     */
    public final QueryColumn PUSH_ENDTIME = new QueryColumn(this, "push_endtime");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 是否发送
     */
    public final QueryColumn PUSH_SFFS_CODE = new QueryColumn(this, "push_sffs_code");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 开始时间
     */
    public final QueryColumn PUSH_STARTTIME = new QueryColumn(this, "push_starttime");

    /**
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 工作日历外键
     */
    public final QueryColumn TB_SYS_CALENDAR_ID = new QueryColumn(this, "tb_sys_calendar_id");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 主键id
     */
    public final QueryColumn TB_SYS_CALENDAR_PUSH_ID = new QueryColumn(this, "tb_sys_calendar_push_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_SYS_CALENDAR_PUSH_ID, TB_SYS_CALENDAR_ID, PUSH_TIME, PUSH_SFFS_CODE, PUSH_STARTTIME, PUSH_ENDTIME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbSysCalendarPushTableDef() {
        super("", "tb_sys_calendar_push");
    }

    private TbSysCalendarPushTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbSysCalendarPushTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbSysCalendarPushTableDef("", "tb_sys_calendar_push", alias));
    }

}
