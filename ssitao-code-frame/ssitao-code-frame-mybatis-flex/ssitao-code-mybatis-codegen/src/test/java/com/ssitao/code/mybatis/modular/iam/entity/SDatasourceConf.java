package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 数据源配置 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_datasource_conf")
public class SDatasourceConf extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 是否启用
     */
    private BigDecimal enabled;

    /**
     * 创建日期
     */
    private LocalDateTime createDate;

    /**
     * 数据源配置
     */
    private String properties;

    /**
     * 备注
     */
    private String describe;

}
