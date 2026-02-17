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
 * 平台组件事件定义 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_viewevents")
public class TbCoreViewevents extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事件编码
     */
    private String vieweventsCode;

    /**
     * 方法体
     */
    private String vieweventsFunc;

    /**
     * 事件名称
     */
    private String vieweventsName;

    /**
     * 类型
     */
    private String vieweventsType;

    @Id
    private String tbCoreVieweventsId;

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
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * js监听事件类型
     */
    private String vieweventsTypeCode;

    /**
     * js监听事件类型_name
     */
    private String vieweventsTypeName;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 说明
     */
    private String vieweventsSm;

    /**
     * 禁用
     */
    private String vieweventsJy;

    /**
     * 异步事件
     */
    private String vieweventsYbsj;

}
