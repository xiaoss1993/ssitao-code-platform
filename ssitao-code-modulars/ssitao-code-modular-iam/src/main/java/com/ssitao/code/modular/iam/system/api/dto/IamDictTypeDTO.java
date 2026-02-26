package com.ssitao.code.modular.iam.system.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IAM字典类型DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IamDictTypeDTO {

    /**
     * 字典类型ID
     */
    private String id;

    /**
     * 字典类型编码
     */
    private String dictTypeCode;

    /**
     * 字典类型名称
     */
    private String dictTypeName;

    /**
     * 字典类型描述
     */
    private String description;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 字典数据列表
     */
    private List<IamDictDataDTO> dictDataList;

    // ==================== 前端兼容字段 ====================

    /**
     * 编码（前端兼容）
     */
    private String code;

    /**
     * 名称（前端兼容）
     */
    private String name;

    /**
     * 标签（前端兼容，用于树形控件）
     */
    private String label;

}
