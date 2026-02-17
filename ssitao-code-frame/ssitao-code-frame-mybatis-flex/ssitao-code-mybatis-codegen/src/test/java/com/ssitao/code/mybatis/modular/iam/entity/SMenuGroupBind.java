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
 * 菜单分组关联 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_menu_group_bind")
public class SMenuGroupBind extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 分组id
     */
    private String groupId;

    /**
     * 树结构编码
     */
    private String path;

    /**
     * 父级id
     */
    private String parentId;

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
