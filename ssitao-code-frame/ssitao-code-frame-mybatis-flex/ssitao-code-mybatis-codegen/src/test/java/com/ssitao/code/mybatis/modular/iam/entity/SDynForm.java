package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

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
 * 动态单 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_dyn_form")
public class SDynForm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 表单名称
     */
    private String name;

    /**
     * 数据库表名
     */
    private String tName;

    /**
     * 备注
     */
    private String describe;

    /**
     * 表单类型
     */
    private String type;

    /**
     * 版本
     */
    private BigDecimal version;

    /**
     * 是否已发布
     */
    private BigDecimal isDeployed;

    /**
     * 别名
     */
    private String alias;

    /**
     * 触发器
     */
    private String triggers;

    /**
     * 表链接
     */
    private String correlations;

    /**
     * 数据源id,为空使用默认数据源
     */
    private String dataSourceId;

    /**
     * 创建人id
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private BigDecimal createTime;

    /**
     * 修改时间
     */
    private BigDecimal updateTime;

    /**
     * 其他配置
     */
    private String properties;

    /**
     * 标签
     */
    private String tags;

    /**
     * 数据库名
     */
    private String dbName;

}
