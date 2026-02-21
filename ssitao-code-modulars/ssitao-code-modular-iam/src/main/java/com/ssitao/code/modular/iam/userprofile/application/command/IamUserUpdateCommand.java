package com.ssitao.code.modular.iam.userprofile.application.command;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * IAM用户更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamUserUpdateCommand {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long id;

    /**
     * 昵称
     */
    @Size(max = 128, message = "昵称长度不能超过128")
    private String nickname;

    /**
     * 真实姓名
     */
    @Size(max = 128, message = "真实姓名长度不能超过128")
    private String realName;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 128, message = "邮箱长度不能超过128")
    private String email;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    /**
     * 头像URL
     */
    @Size(max = 512, message = "头像URL长度不能超过512")
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
    private Long deptId;

    /**
     * 岗位ID
     */
    private Long postId;

    /**
     * 备注
     */
    @Size(max = 512, message = "备注长度不能超过512")
    private String remark;

    /**
     * 租户ID
     */
    private String tenantId;

}
