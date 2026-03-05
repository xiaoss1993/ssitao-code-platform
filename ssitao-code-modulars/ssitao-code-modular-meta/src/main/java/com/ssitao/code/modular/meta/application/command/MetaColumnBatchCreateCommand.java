package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 批量创建元数据字段命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "批量创建元数据字段命令")
public class MetaColumnBatchCreateCommand {

    @Schema(description = "表ID")
    @NotBlank(message = "表ID不能为空")
    private String tableId;

    @Schema(description = "字段列表")
    @NotEmpty(message = "字段列表不能为空")
    private List<MetaColumnCreateCommand> columns;
}
