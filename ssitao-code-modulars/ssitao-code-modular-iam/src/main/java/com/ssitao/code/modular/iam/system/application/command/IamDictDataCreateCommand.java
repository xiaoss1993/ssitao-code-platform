package com.ssitao.code.modular.iam.system.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * IAM字典数据创建命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamDictDataCreateCommand {

    /**
     * 字典类型ID
     */
    @NotNull(message = "字典类型ID不能为空")
    private Long dictTypeId;

    /**
     * 字典类型编码
     */
    @NotBlank(message = "字典类型编码不能为空")
    private String dictTypeCode;

    /**
     * 字典数据编码
     */
    @NotBlank(message = "字典数据编码不能为空")
    @Size(max = 64, message = "字典数据编码长度不能超过64")
    private String dictDataCode;

    /**
     * 字典数据值
     */
    @NotBlank(message = "字典数据值不能为空")
    @Size(max = 256, message = "字典数据值长度不能超过256")
    private String dictDataValue;

    /**
     * 字典数据标签
     */
    @NotBlank(message = "字典数据标签不能为空")
    @Size(max = 128, message = "字典数据标签长度不能超过128")
    private String dictDataLabel;

    /**
     * 样式类
     */
    @Size(max = 128, message = "样式类长度不能超过128")
    private String cssClass;

    /**
     * 列表样式
     */
    @Size(max = 128, message = "列表样式长度不能超过128")
    private String listClass;

    /**
     * 是否默认
     */
    private Boolean isDefault;

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
