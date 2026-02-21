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
 * 数据字典选项配置 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_dict_item")
public class SDictItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 字典id
     */
    private String dictId;

    /**
     * 名称
     */
    private String name;

    /**
     * 字典值
     */
    private String value;

    /**
     * 字典文本
     */
    private String text;

    /**
     * 字典值类型
     */
    private String valueType;

    /**
     * 状态
     */
    private BigDecimal status;

    /**
     * 说明
     */
    private String describe;

    /**
     * 父级选项
     */
    private String parentId;

    /**
     * 树编码
     */
    private String path;

    /**
     * 快速搜索码
     */
    private String searchCode;

    /**
     * 排序索引
     */
    private BigDecimal sortIndex;

    /**
     * 树结构层级
     */
    @Column("level_")
    private BigDecimal level;

    /**
     * 识别码
     */
    private BigDecimal ordinal;

    /**
     * 其他自定义属性
     */
    private String properties;

}
