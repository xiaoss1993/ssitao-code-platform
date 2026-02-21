package com.ssitao.code.modular.iam.system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM字典数据聚合根
 * System领域的字典数据管理
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamDictData {

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

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建字典数据
     *
     * @param dictTypeId    字典类型ID
     * @param dictTypeCode  字典类型编码
     * @param dictDataCode  字典数据编码
     * @param dictDataLabel 字典数据标签
     * @param dictDataValue 字典数据值
     * @param tenantId      租户ID
     * @return 字典数据聚合根
     */
    public static IamDictData create(String dictTypeId, String dictTypeCode, String dictDataCode,
                                      String dictDataLabel, String dictDataValue, String tenantId) {
        IamDictData dictData = new IamDictData();
        dictData.setDictTypeId(dictTypeId);
        dictData.setDictTypeCode(dictTypeCode);
        dictData.setDictDataCode(dictDataCode);
        dictData.setDictDataLabel(dictDataLabel);
        dictData.setDictDataValue(dictDataValue);
        dictData.setTenantId(tenantId);
        dictData.setStatus(true);
        dictData.setIsDefault(false);
        dictData.setDeleted(false);
        dictData.setCreateTime(LocalDateTime.now());
        return dictData;
    }

    /**
     * 设置为默认
     */
    public void setAsDefault() {
        this.isDefault = true;
    }

    /**
     * 取消默认
     */
    public void cancelDefault() {
        this.isDefault = false;
    }

    /**
     * 启用字典数据
     */
    public void enable() {
        this.status = true;
    }

    /**
     * 禁用字典数据
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

    /**
     * 判断是否为默认值
     *
     * @return true-是，false-否
     */
    public boolean isDefaultData() {
        return Boolean.TRUE.equals(this.isDefault);
    }

}
