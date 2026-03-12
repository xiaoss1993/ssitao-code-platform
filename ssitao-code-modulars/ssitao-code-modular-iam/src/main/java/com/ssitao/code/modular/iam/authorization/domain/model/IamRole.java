package com.ssitao.code.modular.iam.authorization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * IAM角色聚合根
 * Authorization领域的核心聚合根
 * 对应表：iam_role
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class IamRole {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型: SYSTEM-系统角色, BUSINESS-业务角色
     */
    private String roleType;

    /**
     * 角色级别
     */
    private Integer roleLevel;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色状态: 0-禁用, 1-启用
     */
    private Integer roleStatus;

    /**
     * 是否内置: 0-否, 1-是
     */
    private Integer roleIsBuiltin;

    /**
     * 排序号
     */
    private Integer roleSort;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 修改人ID
     */
    private String modifyUserId;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer isDeleted;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 权限集合
     */
    private Set<IamPermission> permissions;

    /**
     * 子角色列表（树形结构）
     */
    private List<IamRole> children;

    /**
     * 创建角色
     *
     * @param roleCode   角色编码
     * @param roleName   角色名称
     * @param tenantId   租户ID
     * @return 角色聚合根
     */
    public static IamRole create(String roleCode, String roleName, String tenantId) {
        IamRole role = new IamRole();
        role.setRoleCode(roleCode);
        role.setRoleName(roleName);
        role.setTenantId(tenantId);
        role.setRoleType("BUSINESS");
        role.setRoleLevel(0);
        role.setRoleStatus(1);
        role.setRoleIsBuiltin(0);
        role.setRoleSort(0);
        role.setCreateTime(LocalDateTime.now());
        role.setModifyTime(LocalDateTime.now());
        role.setIsDeleted(0);
        role.setVersion(0);
        role.setPermissions(new HashSet<>());
        role.setChildren(new ArrayList<>());
        return role;
    }

    /**
     * 创建系统角色
     *
     * @param roleCode   角色编码
     * @param roleName   角色名称
     * @param tenantId   租户ID
     * @return 角色聚合根
     */
    public static IamRole createSystemRole(String roleCode, String roleName, String tenantId) {
        IamRole role = create(roleCode, roleName, tenantId);
        role.setRoleType("SYSTEM");
        role.setRoleIsBuiltin(1);
        return role;
    }

    /**
     * 添加权限
     *
     * @param permissionId 权限ID
     */
    public void addPermission(String permissionId) {
        if (this.permissions == null) {
            this.permissions = new HashSet<>();
        }
        IamPermission permission = new IamPermission();
        permission.setPermissionId(permissionId);
        this.permissions.add(permission);
    }

    /**
     * 批量添加权限
     *
     * @param permissionIds 权限ID列表
     */
    public void addPermissions(List<String> permissionIds) {
        if (permissionIds == null || permissionIds.isEmpty()) {
            return;
        }
        if (this.permissions == null) {
            this.permissions = new HashSet<>();
        }
        for (String permissionId : permissionIds) {
            IamPermission permission = new IamPermission();
            permission.setPermissionId(permissionId);
            this.permissions.add(permission);
        }
    }

    /**
     * 移除权限
     *
     * @param permissionId 权限ID
     */
    public void removePermission(String permissionId) {
        if (this.permissions != null) {
            this.permissions.removeIf(p -> p.getPermissionId().equals(permissionId));
        }
    }

    /**
     * 清空所有权限
     */
    public void clearPermissions() {
        if (this.permissions != null) {
            this.permissions.clear();
        }
    }

    /**
     * 判断是否拥有指定权限
     *
     * @param permissionId 权限ID
     * @return true-拥有，false-不拥有
     */
    public boolean hasPermission(String permissionId) {
        if (this.permissions == null) {
            return false;
        }
        return this.permissions.stream().anyMatch(p -> p.getPermissionId().equals(permissionId));
    }

    /**
     * 获取权限ID列表
     *
     * @return 权限ID列表
     */
    public List<String> getPermissionIds() {
        if (this.permissions == null) {
            return new ArrayList<>();
        }
        List<String> ids = new ArrayList<>();
        for (IamPermission permission : this.permissions) {
            ids.add(permission.getPermissionId());
        }
        return ids;
    }

    /**
     * 添加子角色
     *
     * @param child 子角色
     */
    public void addChild(IamRole child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
        child.setRoleLevel(this.roleLevel + 1);
    }

    /**
     * 禁用角色
     */
    public void disable() {
        this.roleStatus = 0;
        this.modifyTime = LocalDateTime.now();
    }

    /**
     * 启用角色
     */
    public void enable() {
        this.roleStatus = 1;
        this.modifyTime = LocalDateTime.now();
    }

    /**
     * 判断是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        return this.roleStatus != null && this.roleStatus == 1;
    }

    /**
     * 判断是否为系统角色
     *
     * @return true-系统角色，false-自定义角色
     */
    public boolean isSystemRole() {
        return "SYSTEM".equals(this.roleType);
    }

    /**
     * 判断是否为内置角色
     *
     * @return true-内置角色，false-非内置角色
     */
    public boolean isBuiltin() {
        return this.roleIsBuiltin != null && this.roleIsBuiltin == 1;
    }

    // ==================== 别名方法（兼容AppService） ====================

    /**
     * 获取ID（别名）
     */
    public String getId() {
        return this.roleId;
    }

    /**
     * 设置ID（别名）
     */
    public void setId(String id) {
        this.roleId = id;
    }

    /**
     * 获取描述（别名）
     */
    public String getRemark() {
        return this.roleDesc;
    }

    /**
     * 设置描述（别名）
     */
    public void setRemark(String remark) {
        this.roleDesc = remark;
    }

    /**
     * 获取排序（别名）
     */
    public Integer getSortOrder() {
        return this.roleSort;
    }

    /**
     * 设置排序（别名）
     */
    public void setSortOrder(Integer sortOrder) {
        this.roleSort = sortOrder;
    }

    /**
     * 获取更新时间（别名）
     */
    public LocalDateTime getUpdateTime() {
        return this.modifyTime;
    }

    /**
     * 设置更新时间（别名）
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.modifyTime = updateTime;
    }

    /**
     * 设置数据范围
     */
    public void setDataScope(String dataScope) {
        // 暂不实现，保留方法签名
    }

    /**
     * 设置权限组ID
     */
    public void setPermGroupId(String permGroupId) {
        // 暂不实现，保留方法签名
    }

    /**
     * 构建路径
     */
    public void buildPath(String parentPath) {
        // 暂不实现，保留方法签名
    }

    /**
     * 设置父ID（兼容方法）
     */
    public void setParentId(String parentId) {
        // 暂不实现，保留方法签名
    }

    /**
     * 设置节点类型（兼容方法）
     */
    public void setNodeType(String nodeType) {
        // 暂不实现，保留方法签名
    }

    /**
     * 获取层级（兼容方法）
     */
    public Integer getLayer() {
        return this.roleLevel;
    }

    /**
     * 设置层级（兼容方法）
     */
    public void setLayer(Integer layer) {
        this.roleLevel = layer;
    }

    /**
     * 设置角色状态
     */
    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    /**
     * 获取路径（兼容方法）
     */
    public String getPath() {
        return "";
    }

    /**
     * 权限实体
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IamPermission {
        private String permissionId;
        private String permissionCode;
        private String permissionName;
    }

}
