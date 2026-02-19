package com.ssitao.code.modular.iam.controller.vo.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 系统参数更新请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 系统参数更新请求")
public class ConfigUpdateReqVO {

    @Schema(description = "参数ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "参数ID不能为空")
    private Long id;

    @Schema(description = "参数名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "参数名称不能为空")
    @Size(max = 100, message = "参数名称长度不能超过100个字符")
    private String name;

    @Schema(description = "参数键名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "参数键名不能为空")
    @Size(max = 100, message = "参数键名长度不能超过100个字符")
    private String key;

    @Schema(description = "参数键值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "参数键值不能为空")
    private String value;

    @Schema(description = "是否系统内置：1-是 0-否", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isSystem;

    @Schema(description = "备注")
    private String remark;

}
