package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
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
 * 功能数据标记 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_mark")
public class TbCoreMark extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreMarkId;

    /**
     * 功能id
     */
    private String markFuncid;

    /**
     * 功能编码
     */
    private String markFunccode;

    /**
     * 表名
     */
    private String markTablecode;

    /**
     * 数据id
     */
    private String markModelid;

    /**
     * 用户id
     */
    private String markUserid;

    /**
     * 1
     */
    @Column("mark_1")
    private String mark1;

    /**
     * 2
     */
    @Column("mark_2")
    private String mark2;

    /**
     * 3
     */
    @Column("mark_3")
    private String mark3;

    /**
     * 4
     */
    @Column("mark_4")
    private String mark4;

    /**
     * 5
     */
    @Column("mark_5")
    private String mark5;

    /**
     * 6
     */
    @Column("mark_6")
    private String mark6;

    /**
     * 7
     */
    @Column("mark_7")
    private String mark7;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

}
