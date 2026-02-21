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
 * 系统菜单 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_menu")
public class SMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    @Id
    private String uId;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 权限ID
     */
    private String permissionId;

    /**
     * 树编码
     */
    private String path;

    /**
     * 树编码
     */
    private BigDecimal sortIndex;

    /**
     * 备注
     */
    private String describe;

    /**
     * URL
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 状态
     */
    private BigDecimal status;

}
