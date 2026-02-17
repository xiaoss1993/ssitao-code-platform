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
 * 动态单列 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_dyn_form_column")
public class SDynFormColumn extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 表单ID
     */
    private String formId;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 数据库列
     */
    private String columnName;

    /**
     * 备注
     */
    private String describe;

    /**
     * 别名
     */
    private String alias;

    /**
     * java类型
     */
    private String javaType;

    /**
     * jdbc类型
     */
    private String jdbcType;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 长度
     */
    private BigDecimal length;

    /**
     * 精度
     */
    private BigDecimal precision;

    /**
     * 小数点位数
     */
    private BigDecimal scale;

    /**
     * 其他配置
     */
    private String properties;

    /**
     * 字典配置
     */
    private String dictConfig;

    /**
     * 排序序号
     */
    private BigDecimal sortIndex;

    /**
     * 验证器配置
     */
    private String validator;

}
