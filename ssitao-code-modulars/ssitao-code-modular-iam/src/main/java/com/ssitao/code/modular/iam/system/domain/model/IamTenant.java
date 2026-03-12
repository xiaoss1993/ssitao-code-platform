package com.ssitao.code.modular.iam.system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM租户聚合根
 * System领域的租户管理
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamTenant {

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
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 用户数量限制
     */
    private Integer userLimit;

    /**
     * 当前用户数量
     */
    private Integer currentUserCount;

    /**
     * 存储空间限制(MB)
     */
    private Long storageLimit;

    /**
     * 已使用存储空间(MB)
     */
    private Long usedStorage;

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

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建租户
     *
     * @param tenantCode 租户编码
     * @param tenantName 租户名称
     * @param tenantType 租户类型
     * @return 租户聚合根
     */
    public static IamTenant create(String tenantCode, String tenantName, String tenantType) {
        IamTenant tenant = new IamTenant();
        tenant.setTenantCode(tenantCode);
        tenant.setTenantName(tenantName);
        tenant.setTenantType(tenantType);
        tenant.setTenantStatus("NORMAL");
        tenant.setUserLimit(100);
        tenant.setCurrentUserCount(0);
        tenant.setStorageLimit(10240L);
        tenant.setUsedStorage(0L);
        tenant.setDeleted(false);
        tenant.setCreateTime(LocalDateTime.now());
        return tenant;
    }

    /**
     * 判断是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        if (!"NORMAL".equals(this.tenantStatus) || this.deleted) {
            return false;
        }
        if (this.expireTime != null && LocalDateTime.now().isAfter(this.expireTime)) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否过期
     *
     * @return true-已过期，false-未过期
     */
    public boolean isExpired() {
        return this.expireTime != null && LocalDateTime.now().isAfter(this.expireTime);
    }

    /**
     * 禁用租户
     */
    public void disable() {
        this.tenantStatus = "DISABLED";
    }

    /**
     * 启用租户
     */
    public void enable() {
        this.tenantStatus = "NORMAL";
    }

    /**
     * 标记为过期
     */
    public void markExpired() {
        this.tenantStatus = "EXPIRED";
    }

    /**
     * 增加用户数量
     *
     * @param count 增加的数量
     */
    public void addUserCount(int count) {
        this.currentUserCount = (this.currentUserCount != null ? this.currentUserCount : 0) + count;
    }

    /**
     * 减少用户数量
     *
     * @param count 减少的数量
     */
    public void reduceUserCount(int count) {
        this.currentUserCount = Math.max(0, (this.currentUserCount != null ? this.currentUserCount : 0) - count);
    }

    /**
     * 增加存储使用量
     *
     * @param size 增加的大小(MB)
     */
    public void addStorage(long size) {
        this.usedStorage = (this.usedStorage != null ? this.usedStorage : 0L) + size;
    }

    /**
     * 判断是否达到用户限制
     *
     * @return true-已达到限制，false-未达到
     */
    public boolean isUserLimitReached() {
        return this.userLimit != null && this.currentUserCount != null
                && this.currentUserCount >= this.userLimit;
    }

    /**
     * 判断是否达到存储限制
     *
     * @return true-已达到限制，false-未达到
     */
    public boolean isStorageLimitReached() {
        return this.storageLimit != null && this.usedStorage != null
                && this.usedStorage >= this.storageLimit;
    }

}
