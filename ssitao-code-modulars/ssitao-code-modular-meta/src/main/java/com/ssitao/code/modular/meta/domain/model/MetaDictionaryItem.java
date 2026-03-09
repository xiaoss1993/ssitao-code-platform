package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 字典数据实体
 * Meta领域的字典数据，用于定义具体的字典项
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaDictionaryItem {

    /**
     * 字典数据ID
     */
    private String dictId;

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
    private Integer isDefault;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态：1-启用 0-禁用
     */
    private Integer status;

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

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer deleted;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 层级
     */
    private Integer layer;
}
