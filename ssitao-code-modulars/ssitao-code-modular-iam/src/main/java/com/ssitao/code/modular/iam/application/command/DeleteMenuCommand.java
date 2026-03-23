package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 删除菜单命令
 */
@Data
public class DeleteMenuCommand {

    /** 菜单ID数组 */
    @NotEmpty(message = "菜单ID不能为空")
    private Long[] menuIds;
}
