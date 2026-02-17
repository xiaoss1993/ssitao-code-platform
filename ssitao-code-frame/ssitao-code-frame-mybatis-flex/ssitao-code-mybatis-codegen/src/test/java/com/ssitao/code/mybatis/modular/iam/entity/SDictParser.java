package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 数据字典解析配置 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_dict_parser")
public class SDictParser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 值到文本转换方式
     */
    @Column("v_t_parser")
    private String vTParser;

    /**
     * 文本到值转换方式
     */
    @Column("t_v_parser")
    private String tVParser;

    /**
     * 转换失败时的操作
     */
    private String onError;

    /**
     * 创建时间
     */
    private BigDecimal createTime;

    /**
     * 创建人id
     */
    private String creatorId;

    /**
     * 更新时间
     */
    private BigDecimal updateTime;

    /**
     * 名称
     */
    private String name;

    /**
     * 说明
     */
    private String describe;

    /**
     * 分类id
     */
    private String classifiedId;

}
