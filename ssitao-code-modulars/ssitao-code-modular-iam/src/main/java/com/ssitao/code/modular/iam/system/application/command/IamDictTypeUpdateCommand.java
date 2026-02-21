package com.ssitao.code.modular.iam.system.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * IAM字典类型更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamDictTypeUpdateCommand {

    /**
     * 字典类型ID
     */
    @NotNull(message = "字典类型ID不能为空")
    private Long id;

    /**
     * 字典类型名称
     */
    @Size(max = 128, message = "字典类型名称长度不能超过128")
    private String dictTypeName;

    /**
     * 字典类型描述
     */
    @Size(max = 512, message = "字典类型描述长度不能超过512")
    private String description;

    /**
     * 排序
     */
    private Integer sortOrder;

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
