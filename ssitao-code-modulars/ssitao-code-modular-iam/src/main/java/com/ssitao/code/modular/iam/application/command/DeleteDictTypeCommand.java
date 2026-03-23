package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 删除字典类型命令
 */
@Data
public class DeleteDictTypeCommand {

    /** 字典类型ID数组 */
    @NotEmpty(message = "字典类型ID不能为空")
    private Long[] dictIds;
}
