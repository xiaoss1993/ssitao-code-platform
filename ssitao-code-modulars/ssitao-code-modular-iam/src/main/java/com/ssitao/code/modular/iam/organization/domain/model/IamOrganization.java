package com.ssitao.code.modular.iam.organization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM组织聚合根
 * Organization领域的组织管理
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamOrganization {

    /**
     * 组织ID
     */
    private String id;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织类型：COMPANY-公司 DEPARTMENT-部门 GROUP-小组
     */
    private String orgType;

    /**
     * 父组织ID
     */
    private String parentId;

    /**
     * 层级
     */
    private Integer layer;

    /**
     * 路径
     */
    private String path;

    /**
     * 负责人ID
     */
    private String leaderId;

    /**
     * 负责人姓名
     */
    private String leaderName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

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

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建组织
     *
     * @param orgCode 组织编码
     * @param orgName 组织名称
     * @param orgType 组织类型
     * @param tenantId 租户ID
     * @return 组织聚合根
     */
    public static IamOrganization create(String orgCode, String orgName, String orgType, String tenantId) {
        IamOrganization organization = new IamOrganization();
        organization.setOrgCode(orgCode);
        organization.setOrgName(orgName);
        organization.setOrgType(orgType);
        organization.setTenantId(tenantId);
        organization.setStatus(true);
        organization.setLayer(0);
        organization.setDeleted(false);
        organization.setCreateTime(LocalDateTime.now());
        return organization;
    }

    /**
     * 设置负责人
     *
     * @param leaderId   负责人ID
     * @param leaderName 负责人姓名
     */
    public void setLeader(String leaderId, String leaderName) {
        this.leaderId = leaderId;
        this.leaderName = leaderName;
    }

    /**
     * 启用组织
     */
    public void enable() {
        this.status = true;
    }

    /**
     * 禁用组织
     */
    public void disable() {
        this.status = false;
    }

    /**
     * 判断是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        return this.status && !this.deleted;
    }

    /**
     * 判断是否为公司
     *
     * @return true-是，false-否
     */
    public boolean isCompany() {
        return "COMPANY".equals(this.orgType);
    }

    /**
     * 判断是否为部门
     *
     * @return true-是，false-否
     */
    public boolean isDepartment() {
        return "DEPARTMENT".equals(this.orgType);
    }

}
