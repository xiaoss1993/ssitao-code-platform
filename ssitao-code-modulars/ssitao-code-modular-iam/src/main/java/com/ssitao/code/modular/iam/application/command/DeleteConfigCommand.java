package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 删除参数配置命令
 */
@Data
public class DeleteConfigCommand {

    /** 参数ID数组 */
    @NotEmpty(message = "参数ID不能为空")
    private Long[] configIds;
}
