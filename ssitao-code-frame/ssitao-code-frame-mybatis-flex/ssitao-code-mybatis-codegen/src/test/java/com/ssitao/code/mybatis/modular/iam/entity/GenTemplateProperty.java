package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 模板属性配置 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("gen_template_property")
public class GenTemplateProperty extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 模板组标识
     */
    private String groupKey;

    /**
     * 标题
     */
    private String title;

    /**
     * 属性键
     */
    private String propKey;

    /**
     * 属性类型：1=配置属性，2=计算属性
     */
    private Boolean propType;

    /**
     * 表达式内容，仅计算属性使用
     */
    private String expression;

    /**
     * 模板引擎类型：0=普通文本，1=Velocity，2=Freemarker
     */
    private Boolean engineType;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 组件类型，前端显示的组件类型，input、select、radio
     */
    private String componentType;

    /**
     * 组件选项，多选组件的选项配置
     */
    private String componentOptions;

    /**
     * 必填，1：是，0：否
     */
    private Boolean required;

    /**
     * 排序值，值越小越靠前
     */
    private Integer orderValue;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 逻辑删除
     */
    private Long deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}
