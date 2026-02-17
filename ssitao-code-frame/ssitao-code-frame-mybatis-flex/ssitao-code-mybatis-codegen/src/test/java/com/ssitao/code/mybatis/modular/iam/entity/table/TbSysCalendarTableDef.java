package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 工作日历 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbSysCalendarTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 工作日历
     */
    public static final TbSysCalendarTableDef TB_SYS_CALENDAR = new TbSysCalendarTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 链接
     */
    public final QueryColumn CALENDAR_URL = new QueryColumn(this, "calendar_url");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 颜色
     */
    public final QueryColumn CALENDAR_COLOR = new QueryColumn(this, "calendar_color");

    /**
     * 内容
     */
    public final QueryColumn CALENDAR_NOTES = new QueryColumn(this, "calendar_notes");

    /**
     * 标题
     */
    public final QueryColumn CALENDAR_TITLE = new QueryColumn(this, "calendar_title");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 关联功能配置
     */
    public final QueryColumn CALENDAR_CONFIG = new QueryColumn(this, "calendar_config");

    /**
     * 来源
     */
    public final QueryColumn CALENDAR_SOURCE = new QueryColumn(this, "calendar_source");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 地点
     */
    public final QueryColumn CALENDAR_ADDRESS = new QueryColumn(this, "calendar_address");

    /**
     * 结束时间
     */
    public final QueryColumn CALENDAR_ENDTIME = new QueryColumn(this, "calendar_endtime");

    /**
     * 所属组id
     */
    public final QueryColumn CALENDAR_GROUPID = new QueryColumn(this, "calendar_groupid");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    
    public final QueryColumn TB_SYS_CALENDAR_ID = new QueryColumn(this, "tb_sys_calendar_id");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属组
     */
    public final QueryColumn CALENDAR_GROUPNAME = new QueryColumn(this, "calendar_groupname");

    /**
     * 开始时间
     */
    public final QueryColumn CALENDAR_STARTTIME = new QueryColumn(this, "calendar_starttime");

    /**
     * 所属组颜色
     */
    public final QueryColumn CALENDAR_GROUPCOLOR = new QueryColumn(this, "calendar_groupcolor");

    /**
     * 提醒时间
     */
    public final QueryColumn CALENDAR_REMIND_TIME = new QueryColumn(this, "calendar_remind_time");

    /**
     * 关联业务主键
     */
    public final QueryColumn CALENDAR_BUSI_PKVALUE = new QueryColumn(this, "calendar_busi_pkvalue");

    /**
     * 全天
     */
    public final QueryColumn CALENDAR_YESORNO_CODE = new QueryColumn(this, "calendar_yesorno_code");

    /**
     * 全天_name
     */
    public final QueryColumn CALENDAR_YESORNO_NAME = new QueryColumn(this, "calendar_yesorno_name");

    /**
     * 提醒_code
     */
    public final QueryColumn CALENDAR_REMINDER_CODE = new QueryColumn(this, "calendar_reminder_code");

    /**
     * 提醒_name
     */
    public final QueryColumn CALENDAR_REMINDER_NAME = new QueryColumn(this, "calendar_reminder_name");

    /**
     * 消息提醒
     */
    public final QueryColumn CALENDAR_MESSAGETYPE_CODE = new QueryColumn(this, "calendar_messagetype_code");

    /**
     * 消息提醒_name
     */
    public final QueryColumn CALENDAR_MESSAGETYPE_NAME = new QueryColumn(this, "calendar_messagetype_name");

    /**
     * 日历任务类型
     */
    public final QueryColumn CALENDAR_CALENDARTYPE_CODE = new QueryColumn(this, "calendar_calendartype_code");

    /**
     * 日历任务类型_name
     */
    public final QueryColumn CALENDAR_CALENDARTYPE_NAME = new QueryColumn(this, "calendar_calendartype_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_SYS_CALENDAR_ID, CALENDAR_CALENDARTYPE_CODE, CALENDAR_ADDRESS, CALENDAR_NOTES, CALENDAR_STARTTIME, CALENDAR_ENDTIME, CALENDAR_URL, CALENDAR_MESSAGETYPE_NAME, CALENDAR_YESORNO_NAME, CALENDAR_YESORNO_CODE, CALENDAR_CALENDARTYPE_NAME, CALENDAR_TITLE, CALENDAR_MESSAGETYPE_CODE, CALENDAR_BUSI_PKVALUE, CALENDAR_COLOR, CALENDAR_SOURCE, CALENDAR_CONFIG, CALENDAR_REMIND_TIME, CALENDAR_REMINDER_NAME, CALENDAR_REMINDER_CODE, CALENDAR_GROUPID, CALENDAR_GROUPNAME, CALENDAR_GROUPCOLOR, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_CREATEUSERID, SY_CREATEORGID};

    public TbSysCalendarTableDef() {
        super("", "tb_sys_calendar");
    }

    private TbSysCalendarTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbSysCalendarTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbSysCalendarTableDef("", "tb_sys_calendar", alias));
    }

}
