package com.ssitao.code.modular.iam.system.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * IAM租户DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamTenantDTO {

    /**
     * 租户ID
     */
    private String id;

    /**
     * 租户编码
     */
    private String tenantCode;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 租户类型：TRIAL-试用 STANDARD-标准 ENTERPRISE-企业
     */
    private String tenantType;

    /**
     * 租户类型文本
     */
    private String tenantTypeText;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 租户状态：NORMAL-正常 EXPIRED-过期 DISABLED-禁用
     */
    private String tenantStatus;

    /**
     * 租户状态文本
     */
    private String tenantStatusText;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 是否已过期
     */
    private Boolean expired;

    /**
     * 用户数量限制
     */
    private Integer userLimit;

    /**
     * 当前用户数量
     */
    private Integer currentUserCount;

    /**
     * 用户数量使用百分比
     */
    private Integer userUsagePercent;

    /**
     * 存储空间限制(MB)
     */
    private Long storageLimit;

    /**
     * 已使用存储空间(MB)
     */
    private Long usedStorage;

    /**
     * 存储空间使用百分比
     */
    private Integer storageUsagePercent;

    /**
     * Logo URL
     */
    private String logoUrl;

    /**
     * 网站域名
     */
    private String domain;

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
