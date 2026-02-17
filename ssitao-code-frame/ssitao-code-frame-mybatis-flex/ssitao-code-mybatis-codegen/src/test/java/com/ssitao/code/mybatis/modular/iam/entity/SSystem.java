package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

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
 * 系统信息 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_system")
public class SSystem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统名称
     */
    private String name;

    /**
     * 主版本号
     */
    private BigDecimal majorVersion;

    /**
     * 次版本号
     */
    private BigDecimal minorVersion;

    /**
     * 修订版
     */
    private BigDecimal revisionVersion;

    /**
     * 是否快照版
     */
    private BigDecimal snapshot;

    /**
     * 系统说明
     */
    private String comment;

    /**
     * 系统网址
     */
    private String website;

    /**
     * 框架版本
     */
    private String frameworkVersion;

    /**
     * 依赖详情
     */
    private String dependencies;

}
