package com.ssitao.code.modular.iam.application.command;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改密码命令
 */
@Data
public class ChangePasswordCommand {

    /** 用户ID */
    private Long userId;

    /** 旧密码 */
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    /** 新密码 */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
