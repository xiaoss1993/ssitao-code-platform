package com.ssitao.code.modular.iam.controller.vo.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统参数响应VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 系统参数响应")
public class ConfigRespVO {

    @Schema(description = "参数ID")
    private Long id;

    @Schema(description = "参数名称")
    private String name;

    @Schema(description = "参数键名")
    private String key;

    @Schema(description = "参数键值")
    private String value;

    @Schema(description = "是否系统内置：1-是 0-否")
    private Integer isSystem;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "更新人")
    private String updater;

}
