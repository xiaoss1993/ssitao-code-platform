package com.ssitao.code.modular.iam.controller.vo.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典类型列表查询请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 字典类型列表查询请求")
public class DictTypeListReqVO {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典类型")
    private String type;

    @Schema(description = "状态：1-启用 0-禁用")
    private Integer status;

}
