package com.ssitao.code.modular.iam.domain.model;

import com.ssitao.code.frame.aggregate.entity.AbstractAggregateRoot;
import com.ssitao.code.modular.iam.domain.event.PermissionsChangedEvent;
import com.ssitao.code.modular.iam.domain.event.RoleCreatedEvent;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

/**
 * 角色聚合根
 */
@Getter
public class SysRoleAggregate extends AbstractAggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    /** 版本号（用于乐观锁） */
    private long version = 1;

    /** 角色ID */
    private Long roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色权限 */
    private String roleKey;

    /** 角色排序 */
    private String roleSort;

    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限） */
    private String dataScope;

    /** 角色状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 菜单组 */
    private Long[] menuIds;

    /** 部门组（数据权限） */
    private Long[] deptIds;

    /** 角色菜单权限 */
    private Set<String> permissions;

    public SysRoleAggregate() {
    }

    public SysRoleAggregate(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public Long getId() {
        return roleId;
    }

    @Override
    public void setId(Long id) {
        this.roleId = id;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public Date getLastUpdateTime() {
        return updateTime;
    }

    /**
     * 创建角色
     */
    public void createRole(Long roleId, String roleName, String roleKey, String roleSort,
                          String dataScope, String status, String createBy) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.roleSort = roleSort;
        this.dataScope = dataScope != null ? dataScope : "1";
        this.status = status != null ? status : "0";
        this.delFlag = "0";
        this.createBy = createBy;
        this.createTime = new Date();

        apply(new RoleCreatedEvent(roleId, roleName, roleKey, createBy, createTime));
    }

    /**
     * 更新角色信息
     */
    public void updateRole(String roleName, String roleKey, String roleSort,
                          String dataScope, String status, String updateBy) {
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.roleSort = roleSort;
        this.dataScope = dataScope;
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 分配菜单权限
     */
    public void assignMenus(Long[] menuIds, String updateBy) {
        this.menuIds = menuIds;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 分配数据权限
     */
    public void assignDataScope(Long[] deptIds, String updateBy) {
        this.deptIds = deptIds;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 更新菜单权限
     */
    public void updatePermissions(Set<String> permissions, String updateBy) {
        this.permissions = permissions;
        this.updateBy = updateBy;
        this.updateTime = new Date();

        apply(new PermissionsChangedEvent(roleId, roleKey, permissions, updateBy, updateTime));
    }

    /**
     * 修改角色状态
     */
    public void changeStatus(String status, String updateBy) {
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 是否管理员
     */
    public boolean isAdmin() {
        return this.roleId != null && 1L == this.roleId;
    }

    /**
     * 是否为正常状态
     */
    public boolean isNormal() {
        return "0".equals(this.status);
    }

    /**
     * 是否已删除
     */
    public boolean isDeleted() {
        return "2".equals(this.delFlag);
    }

    /**
     * 标记删除
     */
    public void markDeleted(String updateBy) {
        this.delFlag = "2";
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    public Long[] getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds) {
        this.deptIds = deptIds;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
