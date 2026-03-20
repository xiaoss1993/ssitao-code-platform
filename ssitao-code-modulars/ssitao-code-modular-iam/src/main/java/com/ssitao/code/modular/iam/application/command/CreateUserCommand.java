package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * 创建用户命令
 */
@Data
public class CreateUserCommand {

    /** 部门ID */
    @NotNull(message = "部门ID不能为空")
    private Long deptId;

    /** 登录名称 */
    @NotBlank(message = "登录账号不能为空")
    @Size(min = 0, max = 30, message = "登录账号长度不能超过30个字符")
    private String loginName;

    /** 用户名称 */
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    private String userName;

    /** 用户邮箱 */
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    /** 手机号码 */
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    private String phonenumber;

    /** 用户性别 */
    private String sex;

    /** 密码 */
    private String password;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    /** 备注 */
    private String remark;
}
