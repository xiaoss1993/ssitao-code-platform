package com.ssitao.code.modular.iam.controller.vo.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 字典数据更新请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 字典数据更新请求")
public class DictDataUpdateReqVO {

    @Schema(description = "字典数据ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "字典数据ID不能为空")
    private Long id;

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "字典类型不能为空")
    private String dictType;

    @Schema(description = "字典标签", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "字典标签不能为空")
    private String label;

    @Schema(description = "字典键值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "字典键值不能为空")
    private String value;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "样式类名")
    private String cssClass;

    @Schema(description = "是否默认：1-是 0-否")
    private Integer isDefault;

    @Schema(description = "状态：1-启用 0-禁用", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    @Schema(description = "备注")
    private String remark;

}
