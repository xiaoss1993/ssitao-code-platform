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
 * 职位 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_position")
public class SPosition extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 职位名称
     */
    private String name;

    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 持有的角色
     */
    private String roles;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 树结构编码
     */
    private String path;

    /**
     * 排序索引
     */
    private BigDecimal sortIndex;

    /**
     * 级别
     */
    private BigDecimal level;

}
