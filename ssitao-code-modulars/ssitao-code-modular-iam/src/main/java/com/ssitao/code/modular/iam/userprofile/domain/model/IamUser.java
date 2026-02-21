package com.ssitao.code.modular.iam.userprofile.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM用户聚合根
 * UserProfile领域的核心聚合根
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamUser {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 性别：0-未知 1-男 2-女
     */
    private Integer gender;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 岗位ID
     */
    private String postId;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 超级管理员标记：1-是 0-否
     */
    private Boolean isAdmin;

    /**
     * 账号状态：NORMAL-正常 LOCKED-锁定 DISABLED-禁用
     */
    private String accountStatus;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 密码最后修改时间
     */
    private LocalDateTime passwordModifyTime;

    /**
     * 密码是否需要修改
     */
    private Boolean passwordNeedModify;

    /**
     * 初始化标记：0-未初始化 1-已初始化
     */
    private Boolean userInitedCode;

    /**
     * 审核标记
     */
    private String audFlag;

    /**
     * 创建组织编码
     */
    private String createOrg;

    /**
     * 创建组织名称
     */
    private String createOrgName;

    /**
     * 启用标记
     */
    private String flag;

    /**
     * 状态
     */
    private String status;

    /**
     * 修改组织编码
     */
    private String modifyOrg;

    /**
     * 修改组织名称
     */
    private String modifyOrgName;

    /**
     * 排序索引
     */
    private Integer orderIndex;

    /**
     * 流程实例ID
     */
    private String piid;

    /**
     * 流程定义ID
     */
    private String pdid;

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
     * 创建人编码
     */
    private String createUser;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 修改人编码
     */
    private String modifyUser;

    /**
     * 修改人姓名
     */
    private String modifyUserName;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建用户
     *
     * @param username 用户名
     * @param nickname 昵称
     * @param tenantId 租户ID
     * @return 用户聚合根
     */
    public static IamUser create(String username, String nickname, String tenantId) {
        IamUser user = new IamUser();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setTenantId(tenantId);
        user.setAccountStatus("NORMAL");
        user.setGender(0);
        user.setIsAdmin(false);
        user.setPasswordNeedModify(false);
        user.setUserInitedCode(false);
        user.setStatus("1");
        user.setFlag("1");
        user.setDeleted(false);
        user.setCreateTime(LocalDateTime.now());
        return user;
    }

    /**
     * 判断账号是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        return "NORMAL".equals(this.accountStatus) && "1".equals(this.status) && !this.deleted;
    }

    /**
     * 锁定账号
     */
    public void lock() {
        this.accountStatus = "LOCKED";
    }

    /**
     * 解锁账号
     */
    public void unlock() {
        this.accountStatus = "NORMAL";
    }

    /**
     * 禁用账号
     */
    public void disable() {
        this.accountStatus = "DISABLED";
    }

    /**
     * 启用账号
     */
    public void enable() {
        this.accountStatus = "NORMAL";
    }

    /**
     * 记录登录信息
     *
     * @param loginIp 登录IP
     */
    public void recordLogin(String loginIp) {
        this.lastLoginIp = loginIp;
        this.lastLoginTime = LocalDateTime.now();
    }

    /**
     * 设置密码已修改
     */
    public void passwordModified() {
        this.passwordModifyTime = LocalDateTime.now();
        this.passwordNeedModify = false;
    }

    /**
     * 标记需要修改密码
     */
    public void markPasswordNeedModify() {
        this.passwordNeedModify = true;
    }

    /**
     * 初始化用户
     */
    public void initialize() {
        this.userInitedCode = true;
    }

    /**
     * 判断是否为超级管理员
     *
     * @return true-是，false-否
     */
    public boolean isSuperAdmin() {
        return Boolean.TRUE.equals(this.isAdmin);
    }

    /**
     * 设置超级管理员
     */
    public void setAsSuperAdmin() {
        this.isAdmin = true;
    }

    /**
     * 更新部门信息
     *
     * @param deptId   部门ID
     * @param deptName 部门名称
     */
    public void updateDepartment(String deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    /**
     * 更新岗位信息
     *
     * @param postId   岗位ID
     * @param postName 岗位名称
     */
    public void updatePost(String postId, String postName) {
        this.postId = postId;
        this.postName = postName;
    }

}
