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
 * 组织,公司 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_organization")
public class SOrganization extends BaseEntity implements Serializable {

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
     * 全称
     */
    private String fullName;

    /**
     * 机构编码
     */
    private String code;

    /**
     * 所在行政区域ID
     */
    private String districtId;

    /**
     * 可选角色
     */
    private String optionalRoles;

    /**
     * 上级机构id
     */
    private String parentId;

    /**
     * 树定位码
     */
    private String path;

    /**
     * 树结构编码
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
