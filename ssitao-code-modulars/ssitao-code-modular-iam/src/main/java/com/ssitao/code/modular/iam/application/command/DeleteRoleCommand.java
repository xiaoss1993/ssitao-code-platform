package com.ssitao.code.modular.iam.application.command;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除角色命令
 */
@Data
public class DeleteRoleCommand {

    /** 角色ID数组 */
    @NotEmpty(message = "角色ID不能为空")
    private Long[] roleIds;
}
