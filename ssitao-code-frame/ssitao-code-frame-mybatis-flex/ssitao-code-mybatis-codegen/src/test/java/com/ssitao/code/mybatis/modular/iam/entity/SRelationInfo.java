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
 * 关系信息 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_relation_info")
public class SRelationInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 关系定义id
     */
    private String relationId;

    /**
     * 关系从
     */
    private String relationFrom;

    /**
     * 关系至
     */
    private String relationTo;

    /**
     * 关系类型从,如:人员
     */
    private String relationTypeFrom;

    /**
     * 关系类型至,如:部门
     */
    private String relationTypeTo;

    /**
     * 状态
     */
    private BigDecimal status;

}
