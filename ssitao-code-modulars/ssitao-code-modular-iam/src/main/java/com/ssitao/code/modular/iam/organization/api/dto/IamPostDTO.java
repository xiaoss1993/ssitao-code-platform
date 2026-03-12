package com.ssitao.code.modular.iam.organization.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * IAM岗位DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamPostDTO {

    /**
     * 岗位ID
     */
    private String id;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 岗位等级
     */
    private Integer postLevel;

    /**
     * 岗位类别
     */
    private String postCategory;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

}
