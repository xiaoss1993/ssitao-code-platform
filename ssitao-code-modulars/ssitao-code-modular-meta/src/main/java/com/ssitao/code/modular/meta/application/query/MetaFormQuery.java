package com.ssitao.code.modular.meta.application.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 元数据表单查询条件
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Schema(description = "元数据表单查询条件")
public class MetaFormQuery {

    @Schema(description = "表单编码(模糊查询)")
    private String formCode;

    @Schema(description = "表单名称(模糊查询)")
    private String formName;

    @Schema(description = "关联的实体ID")
    private String entityId;

    @Schema(description = "表单类型")
    private String formType;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "状态：1-启用 0-禁用")
    private Integer status;

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页大小", example = "20")
    private Integer pageSize = 20;
}
