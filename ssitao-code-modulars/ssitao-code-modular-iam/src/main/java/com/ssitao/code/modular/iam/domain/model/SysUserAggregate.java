package com.ssitao.code.modular.iam.domain.model;

import com.ssitao.code.frame.aggregate.entity.AbstractAggregateRoot;
import com.ssitao.code.modular.iam.domain.event.PasswordChangedEvent;
import com.ssitao.code.modular.iam.domain.event.UserCreatedEvent;
import com.ssitao.code.modular.iam.domain.event.UserUpdatedEvent;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 用户聚合根
 */
@Getter
public class SysUserAggregate extends AbstractAggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    /** 版本号（用于乐观锁） */
    private long version = 1;

    /** 用户ID */
    private Long userId;

    /** 部门ID */
    private Long deptId;

    /** 部门父ID */
    private Long parentId;

    /** 角色ID */
    private Long roleId;

    /** 登录名称 */
    private String loginName;

    /** 用户名称 */
    private String userName;

    /** 用户类型 */
    private String userType;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phonenumber;

    /** 用户性别 */
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 盐加密 */
    private String salt;

    /** 账号状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录时间 */
    private Date loginDate;

    /** 密码最后更新时间 */
    private Date pwdUpdateDate;

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

    /** 部门名称 */
    private String deptName;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    /** 角色权限集合 */
    private Set<String> permissions;

    public SysUserAggregate() {
    }

    public SysUserAggregate(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    public void setId(Long id) {
        this.userId = id;
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
     * 创建用户
     */
    public void createUser(Long userId, String loginName, String userName, String email,
                          String phonenumber, Long deptId, String status, String createBy) {
        this.userId = userId;
        this.loginName = loginName;
        this.userName = userName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.deptId = deptId;
        this.status = status != null ? status : "0";
        this.delFlag = "0";
        this.createBy = createBy;
        this.createTime = new Date();
        this.pwdUpdateDate = new Date();

        apply(new UserCreatedEvent(userId, loginName, userName, deptId, createBy, createTime));
    }

    /**
     * 更新用户资料
     */
    public void updateProfile(String userName, String email, String phonenumber, String sex, String updateBy) {
        this.userName = userName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.sex = sex;
        this.updateBy = updateBy;
        this.updateTime = new Date();

        apply(new UserUpdatedEvent(userId, loginName, userName, updateBy, updateTime));
    }

    /**
     * 修改密码
     */
    public void changePassword(String newPassword, String newSalt, String updateBy) {
        this.password = newPassword;
        this.salt = newSalt;
        this.updateBy = updateBy;
        this.updateTime = new Date();
        this.pwdUpdateDate = new Date();

        apply(new PasswordChangedEvent(userId, loginName, pwdUpdateDate));
    }

    /**
     * 更新登录信息
     */
    public void updateLoginInfo(String loginIp) {
        this.loginIp = loginIp;
        this.loginDate = new Date();
    }

    /**
     * 修改用户状态
     */
    public void changeStatus(String status, String updateBy) {
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 分配角色
     */
    public void assignRoles(Long[] roleIds, String updateBy) {
        this.roleIds = roleIds;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 是否管理员
     */
    public boolean isAdmin() {
        return this.userId != null && 1L == this.userId;
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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getPwdUpdateDate() {
        return pwdUpdateDate;
    }

    public void setPwdUpdateDate(Date pwdUpdateDate) {
        this.pwdUpdateDate = pwdUpdateDate;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
