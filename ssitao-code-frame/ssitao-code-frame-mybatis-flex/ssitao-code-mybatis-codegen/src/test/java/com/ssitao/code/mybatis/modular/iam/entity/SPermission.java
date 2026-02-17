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
 * 权限 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_permission")
public class SPermission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    @Id
    private String uId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 说明
     */
    private String describe;

    /**
     * 状态
     */
    private BigDecimal status;

    /**
     * 可选操作(按钮)
     */
    private String actions;

    /**
     * 支持的数据权限类型
     */
    private String sptDaTypes;

    /**
     * 可选字段
     */
    private String optionalFields;

    /**
     * 关联其他权限
     */
    private String parents;

    /**
     * 类型
     */
    private String type;

}
