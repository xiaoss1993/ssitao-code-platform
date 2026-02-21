package com.ssitao.code.modular.iam.system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM字典类型聚合根
 * System领域的字典类型管理
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamDictType {

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
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建字典类型
     *
     * @param dictTypeCode  字典类型编码
     * @param dictTypeName  字典类型名称
     * @param tenantId      租户ID
     * @return 字典类型聚合根
     */
    public static IamDictType create(String dictTypeCode, String dictTypeName, String tenantId) {
        IamDictType dictType = new IamDictType();
        dictType.setDictTypeCode(dictTypeCode);
        dictType.setDictTypeName(dictTypeName);
        dictType.setTenantId(tenantId);
        dictType.setStatus(true);
        dictType.setDeleted(false);
        dictType.setCreateTime(LocalDateTime.now());
        return dictType;
    }

    /**
     * 启用字典类型
     */
    public void enable() {
        this.status = true;
    }

    /**
     * 禁用字典类型
     */
    public void disable() {
        this.status = false;
    }

    /**
     * 判断是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        return this.status && !this.deleted;
    }

}
