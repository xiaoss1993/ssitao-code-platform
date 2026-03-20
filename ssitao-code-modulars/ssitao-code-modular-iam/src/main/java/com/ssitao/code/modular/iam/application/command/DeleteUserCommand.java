package com.ssitao.code.modular.iam.application.command;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除用户命令
 */
@Data
public class DeleteUserCommand {

    /** 用户ID数组 */
    @NotEmpty(message = "用户ID不能为空")
    private Long[] userIds;
}
