package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 更新字典类型命令
 */
@Data
public class UpdateDictTypeCommand {

    /** 字典类型ID */
    @NotNull(message = "字典类型ID不能为空")
    private Long dictId;

    /** 字典名称 */
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典名称长度不能超过100个字符")
    private String dictName;

    /** 字典类型 */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
    private String dictType;

    /** 状态（0正常 1停用） */
    private String status;

    /** 备注 */
    private String remark;
}
