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
 * 部门 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_department")
public class SDepartment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 名称
     */
    private String name;

    /**
     * 所在组织id
     */
    private String orgId;

    /**
     * 部门编码
     */
    private String code;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 树结构编码
     */
    private String path;

    /**
     * 排序序号
     */
    private BigDecimal sortIndex;

    /**
     * 状态
     */
    private BigDecimal status;

    /**
     * 级别
     */
    private BigDecimal level;

}
