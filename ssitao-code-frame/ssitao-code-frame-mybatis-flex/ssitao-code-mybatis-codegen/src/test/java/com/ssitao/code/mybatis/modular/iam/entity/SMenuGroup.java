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
 * 菜单分组 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_menu_group")
public class SMenuGroup extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组描述
     */
    private String describe;

    /**
     * 是否默认
     */
    private BigDecimal defaultGroup;

    /**
     * 树路径
     */
    private String path;

    /**
     * 树层级
     */
    private BigDecimal level;

    /**
     * 排序序号
     */
    private BigDecimal sortIndex;

    /**
     * 状态
     */
    private BigDecimal status;

}
