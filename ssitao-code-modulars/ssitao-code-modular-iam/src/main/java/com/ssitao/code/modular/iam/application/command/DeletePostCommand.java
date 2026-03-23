package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 删除岗位命令
 */
@Data
public class DeletePostCommand {

    /** 岗位ID数组 */
    @NotEmpty(message = "岗位ID不能为空")
    private Long[] postIds;
}
