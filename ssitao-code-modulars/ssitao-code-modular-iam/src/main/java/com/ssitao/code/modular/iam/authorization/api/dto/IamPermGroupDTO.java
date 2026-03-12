package com.ssitao.code.modular.iam.authorization.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * IAM权限组DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamPermGroupDTO {

    /**
     * 权限组ID
     */
    private String id;

    /**
     * 权限组编码
     */
    private String groupCode;

    /**
     * 权限组名称
     */
    private String groupName;

    /**
     * 权限组描述
     */
    private String description;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 排序
     */
    private Integer sortOrder;

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

    /**
     * 权限ID集合
     */
    private Set<String> permissionIds;

}
