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
 * 仪盘配置 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_dashboard_conf")
public class SDashboardConf extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置类型
     */
    private String type;

    /**
     * 模板
     */
    private String template;

    /**
     * 脚本
     */
    private String script;

    /**
     * 脚本语言
     */
    private String scriptLang;

    /**
     * 权限设置
     */
    private String permission;

    /**
     * 创建人
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private BigDecimal createTime;

    /**
     * 排序
     */
    private BigDecimal sortIndex;

    /**
     * 状态
     */
    private BigDecimal status;

    /**
     * 是否默认
     */
    private BigDecimal isDefault;

}
