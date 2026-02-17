package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 工作日历 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_sys_calendar")
public class TbSysCalendar extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbSysCalendarId;

    /**
     * 日历任务类型
     */
    private String calendarCalendartypeCode;

    /**
     * 地点
     */
    private String calendarAddress;

    /**
     * 内容
     */
    private String calendarNotes;

    /**
     * 开始时间
     */
    private String calendarStarttime;

    /**
     * 结束时间
     */
    private String calendarEndtime;

    /**
     * 链接
     */
    private String calendarUrl;

    /**
     * 消息提醒_name
     */
    private String calendarMessagetypeName;

    /**
     * 全天_name
     */
    private String calendarYesornoName;

    /**
     * 全天
     */
    private String calendarYesornoCode;

    /**
     * 日历任务类型_name
     */
    private String calendarCalendartypeName;

    /**
     * 标题
     */
    private String calendarTitle;

    /**
     * 消息提醒
     */
    private String calendarMessagetypeCode;

    /**
     * 关联业务主键
     */
    private String calendarBusiPkvalue;

    /**
     * 颜色
     */
    private String calendarColor;

    /**
     * 来源
     */
    private String calendarSource;

    /**
     * 关联功能配置
     */
    private String calendarConfig;

    /**
     * 提醒时间
     */
    private String calendarRemindTime;

    /**
     * 提醒_name
     */
    private String calendarReminderName;

    /**
     * 提醒_code
     */
    private String calendarReminderCode;

    /**
     * 所属组id
     */
    private String calendarGroupid;

    /**
     * 所属组
     */
    private String calendarGroupname;

    /**
     * 所属组颜色
     */
    private String calendarGroupcolor;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

}
