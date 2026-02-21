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
 * 行政区域 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_district")
public class SDistrict extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 区域名称,如重庆市
     */
    private String name;

    /**
     * 区域全程,如重庆市江津区
     */
    private String fullName;

    /**
     * 区域级别名称,如:省
     */
    private String levelName;

    /**
     * 区域级别编码,如:province
     */
    private String levelCode;

    /**
     * 行政区域代码,如:500000
     */
    private String code;

    /**
     * 父级行政区域
     */
    private String parentId;

    /**
     * 树路径,如: asb3-lsat
     */
    private String path;

    /**
     * 说明
     */
    private String describe;

    /**
     * 状态
     */
    private BigDecimal status;

    /**
     * 排序索引
     */
    private BigDecimal sortIndex;

}
