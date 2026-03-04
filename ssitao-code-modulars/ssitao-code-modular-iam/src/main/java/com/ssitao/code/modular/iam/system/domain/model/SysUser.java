package com.ssitao.code.modular.iam.system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统用户领域模型
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String picUrl;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 用户性别(0=女,1=男)
     */
    private Integer gender;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 职位ID
     */
    private Long positionId;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    /**
     * 角色ID字符串(多个角色用逗号分隔)
     */
    private String roleIds;

    /**
     * 皮肤主题
     */
    private String skin;

    /**
     * 主题模式
     */
    private String theme;

    /**
     * 用户状态(0=禁用,1=正常)
     */
    private Integer status;

    /**
     * 删除标志(0=正常,1=删除)
     */
    private Integer deleted;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者ID
     */
    private Long updateId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建用户
     *
     * @param username 用户名
     * @param password 密码
     * @param name     姓名
     * @return 用户实体
     */
    public static SysUser create(String username, String password, String name) {
        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setStatus(1);
        user.setDeleted(0);
        user.setSkin("blue");
        user.setTheme("light");
        user.setGender(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return user;
    }

    /**
     * 修改密码
     *
     * @param newPassword 新密码
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 重置密码
     *
     * @param newPassword 新密码
     */
    public void resetPassword(String newPassword) {
        this.password = newPassword;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 启用用户
     */
    public void enable() {
        this.status = 1;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 禁用用户
     */
    public void disable() {
        this.status = 0;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 判断用户是否启用
     *
     * @return true-启用，false-禁用
     */
    public boolean isEnabled() {
        return this.status != null && this.status == 1;
    }

    /**
     * 判断用户是否删除
     *
     * @return true-已删除，false-未删除
     */
    public boolean isDeleted() {
        return this.deleted != null && this.deleted == 1;
    }

    /**
     * 更新登录信息
     *
     * @param loginIp 登录IP
     */
    public void updateLoginInfo(String loginIp) {
        this.lastLoginIp = loginIp;
        this.lastLoginTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 转换角色ID列表为字符串
     */
    public void convertRoleIdListToString() {
        if (this.roleIdList != null && !this.roleIdList.isEmpty()) {
            this.roleIds = String.join(",", this.roleIdList.stream()
                    .map(String::valueOf)
                    .toArray(String[]::new));
        }
    }

}
