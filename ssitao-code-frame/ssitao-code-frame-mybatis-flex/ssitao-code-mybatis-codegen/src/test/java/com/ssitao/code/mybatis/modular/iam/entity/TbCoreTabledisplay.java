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
 * 资源展示信息 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_tabledisplay")
public class TbCoreTabledisplay extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreTabledisplayId;

    /**
     * 高度
     */
    private Integer tabledisplayHeight;

    /**
     * x
     */
    private Integer tabledisplayX;

    /**
     * 表主键
     */
    private String tabledisplayTableId;

    /**
     * 宽度
     */
    private Integer tabledisplayWidth;

    /**
     * 表编码
     */
    private String tabledisplayTablecode;

    /**
     * 类型
     */
    private String tabledisplayType;

    /**
     * 表名
     */
    private String tabledisplayTablename;

    /**
     * y
     */
    private Integer tabledisplayY;

    /**
     * 登记者所在部门主键
     */
    private String syCreateorgid;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人主键
     */
    private String syCreateuserid;

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

}
