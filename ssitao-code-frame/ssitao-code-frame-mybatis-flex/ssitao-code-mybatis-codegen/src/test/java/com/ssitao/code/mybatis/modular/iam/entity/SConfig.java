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
 * 系统配置文件 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_config")
public class SConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    @Id
    private String uId;

    /**
     * 配置内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private String creatorId;

    /**
     * 创建日期
     */
    private BigDecimal createTime;

    /**
     * 修改日期
     */
    private BigDecimal updateTime;

    /**
     * 分类id
     */
    private String classifiedId;

}
