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
 * 权限设置菜单 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_autz_menu")
public class SAutzMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    @Id
    private String uId;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 设置ID
     */
    private String settingId;

    /**
     * 树编码
     */
    private String path;

    /**
     * 树编码
     */
    private BigDecimal sortIndex;

    /**
     * 状态
     */
    private BigDecimal status;

    /**
     * 树深度
     */
    private BigDecimal level;

    /**
     * 其他配置
     */
    private String config;

}
