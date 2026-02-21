package com.ssitao.code.modular.iam.system.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * IAM字典类型创建命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamDictTypeCreateCommand {

    /**
     * 字典类型编码
     */
    @NotBlank(message = "字典类型编码不能为空")
    @Size(max = 64, message = "字典类型编码长度不能超过64")
    private String dictTypeCode;

    /**
     * 字典类型名称
     */
    @NotBlank(message = "字典类型名称不能为空")
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
     * 租户ID
     */
    private String tenantId;

    /**
     * 备注
     */
    @Size(max = 512, message = "备注长度不能超过512")
    private String remark;

}
