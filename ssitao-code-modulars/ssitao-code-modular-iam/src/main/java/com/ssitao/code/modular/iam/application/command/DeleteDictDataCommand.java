package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 删除字典数据命令
 */
@Data
public class DeleteDictDataCommand {

    /** 字典编码数组 */
    @NotEmpty(message = "字典编码不能为空")
    private Long[] dictCodes;
}
