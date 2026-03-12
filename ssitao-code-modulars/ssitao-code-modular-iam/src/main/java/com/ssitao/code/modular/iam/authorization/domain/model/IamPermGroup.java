package com.ssitao.code.modular.iam.authorization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * IAM权限组聚合根
 * Authorization领域的权限组管理
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamPermGroup {

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
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 权限ID集合
     */
    private Set<String> permissionIds;

    /**
     * 创建权限组
     *
     * @param groupCode   权限组编码
     * @param groupName   权限组名称
     * @return 权限组聚合根
     */
    public static IamPermGroup create(String groupCode, String groupName) {
        IamPermGroup group = new IamPermGroup();
        group.setGroupCode(groupCode);
        group.setGroupName(groupName);
        group.setStatus(true);
        group.setPermissionIds(new HashSet<>());
        group.setCreateTime(LocalDateTime.now());
        return group;
    }

    /**
     * 添加权限
     *
     * @param permissionId 权限ID
     */
    public void addPermission(String permissionId) {
        if (this.permissionIds == null) {
            this.permissionIds = new HashSet<>();
        }
        this.permissionIds.add(permissionId);
    }

    /**
     * 批量添加权限
     *
     * @param permissionIds 权限ID列表
     */
    public void addPermissions(Set<String> permissionIds) {
        if (permissionIds == null || permissionIds.isEmpty()) {
            return;
        }
        if (this.permissionIds == null) {
            this.permissionIds = new HashSet<>();
        }
        this.permissionIds.addAll(permissionIds);
    }

    /**
     * 移除权限
     *
     * @param permissionId 权限ID
     */
    public void removePermission(String permissionId) {
        if (this.permissionIds != null) {
            this.permissionIds.remove(permissionId);
        }
    }

    /**
     * 清空所有权限
     */
    public void clearPermissions() {
        if (this.permissionIds != null) {
            this.permissionIds.clear();
        }
    }

    /**
     * 判断是否拥有指定权限
     *
     * @param permissionId 权限ID
     * @return true-拥有，false-不拥有
     */
    public boolean hasPermission(String permissionId) {
        if (this.permissionIds == null) {
            return false;
        }
        return this.permissionIds.contains(permissionId);
    }

    /**
     * 禁用权限组
     */
    public void disable() {
        this.status = false;
    }

    /**
     * 启用权限组
     */
    public void enable() {
        this.status = true;
    }

    /**
     * 判断是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        return this.status;
    }

}
