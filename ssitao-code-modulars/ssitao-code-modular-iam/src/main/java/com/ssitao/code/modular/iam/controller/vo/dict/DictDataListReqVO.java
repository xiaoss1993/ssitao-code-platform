package com.ssitao.code.modular.iam.controller.vo.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典数据列表查询请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 字典数据列表查询请求")
public class DictDataListReqVO {

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "字典标签")
    private String label;

    @Schema(description = "状态：1-启用 0-禁用")
    private Integer status;

}
