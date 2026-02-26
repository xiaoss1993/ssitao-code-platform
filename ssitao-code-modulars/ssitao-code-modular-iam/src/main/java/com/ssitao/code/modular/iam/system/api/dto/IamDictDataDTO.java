package com.ssitao.code.modular.iam.system.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IAM字典数据DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IamDictDataDTO {

    /**
     * 字典数据ID
     */
    private String id;

    /**
     * 字典类型ID
     */
    private String dictTypeId;

    /**
     * 字典类型编码
     */
    private String dictTypeCode;

    /**
     * 字典数据编码
     */
    private String dictDataCode;

    /**
     * 字典数据值
     */
    private String dictDataValue;

    /**
     * 字典数据标签
     */
    private String dictDataLabel;

    /**
     * 样式类
     */
    private String cssClass;

    /**
     * 列表样式
     */
    private String listClass;

    /**
     * 是否默认：1-是 0-否
     */
    private Boolean isDefault;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

    /**
     * 租户ID
     */
    private String tenantId;

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
     * 键（前端兼容）
     */
    private String key;

    /**
     * 值（前端兼容）
     */
    private String value;

    /**
     * 标签（前端兼容）
     */
    private String label;

    /**
     * 有效标识（前端兼容）
     */
    private String yx;

    /**
     * 字典类型（前端兼容）
     */
    private String dic;

    // ==================== 树形结构字段 ====================

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 层级
     */
    private Integer layer;

    /**
     * 子节点列表
     */
    private List<IamDictDataDTO> children;

}
