package com.ssitao.code.modular.iam.controller.vo.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统参数列表查询请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 系统参数列表查询请求")
public class ConfigListReqVO {

    @Schema(description = "参数名称")
    private String name;

    @Schema(description = "参数键名")
    private String key;

    @Schema(description = "是否系统内置：1-是 0-否")
    private Integer isSystem;

}
