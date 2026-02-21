package com.ssitao.code.modular.iam.userprofile.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IAM用户DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamUserDTO {

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
     * 性别文本
     */
    private String genderText;

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
     * 账号状态文本
     */
    private String accountStatusText;

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
     * 角色ID列表
     */
    private List<String> roleIds;

    /**
     * 角色名称列表
     */
    private List<String> roleNames;

}
