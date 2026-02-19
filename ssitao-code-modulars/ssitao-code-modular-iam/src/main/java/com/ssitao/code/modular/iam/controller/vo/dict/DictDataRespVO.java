package com.ssitao.code.modular.iam.controller.vo.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 字典数据响应VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 字典数据响应")
public class DictDataRespVO {

    @Schema(description = "字典数据ID")
    private Long id;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "字典标签")
    private String label;

    @Schema(description = "字典键值")
    private String value;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "样式类名")
    private String cssClass;

    @Schema(description = "是否默认：1-是 0-否")
    private Integer isDefault;

    @Schema(description = "状态：1-启用 0-禁用")
    private Integer status;

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
