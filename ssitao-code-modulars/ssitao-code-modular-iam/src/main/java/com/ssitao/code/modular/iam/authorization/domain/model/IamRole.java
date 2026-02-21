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
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamRole {

    /**
     * 角色ID
     */
    private String id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型：SYSTEM-系统角色 CUSTOM-自定义角色
     */
    private String roleType;

    /**
     * 数据权限范围：ALL-全部 DEPT-本部门 DEPT_AND_CHILD-本部门及子部门 SELF-本人
     */
    private String dataScope;

    /**
     * 图标样式
     */
    private String iconCls;

    /**
     * 权限组ID
     */
    private String permGroupId;

    /**
     * 父角色ID
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
     * 节点类型：FOLDER-文件夹 ITEM-项目
     */
    private String nodeType;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

    /**
     * 排序
     */
    private Integer sortOrder;

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
     * @param roleType   角色类型
     * @return 角色聚合根
     */
    public static IamRole create(String roleCode, String roleName, String roleType) {
        IamRole role = new IamRole();
        role.setRoleCode(roleCode);
        role.setRoleName(roleName);
        role.setRoleType(roleType);
        role.setStatus(true);
        role.setNodeType("ITEM");
        role.setLayer(0);
        role.setPermissions(new HashSet<>());
        role.setChildren(new ArrayList<>());
        role.setCreateTime(LocalDateTime.now());
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
        permission.setId(permissionId);
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
            permission.setId(permissionId);
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
            this.permissions.removeIf(p -> p.getId().equals(permissionId));
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
        return this.permissions.stream().anyMatch(p -> p.getId().equals(permissionId));
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
            ids.add(permission.getId());
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
        child.setParentId(this.id);
        child.setLayer(this.layer + 1);
    }

    /**
     * 禁用角色
     */
    public void disable() {
        this.status = false;
    }

    /**
     * 启用角色
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

    /**
     * 判断是否为系统角色
     *
     * @return true-系统角色，false-自定义角色
     */
    public boolean isSystemRole() {
        return "SYSTEM".equals(this.roleType);
    }

    /**
     * 构建树形路径
     *
     * @param parentPath 父路径
     */
    public void buildPath(String parentPath) {
        this.parentId = parentPath;
        if (parentPath == null || parentPath.isEmpty()) {
            this.path = String.valueOf(this.id);
        } else {
            this.path = parentPath + "/" + this.id;
        }

        if (this.children != null && !this.children.isEmpty()) {
            for (IamRole child : this.children) {
                child.buildPath(this.path);
            }
        }
    }

    /**
     * 权限实体
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IamPermission {
        private String id;
        private String permCode;
        private String permName;
    }

}
