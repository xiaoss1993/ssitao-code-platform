package com.ssitao.code.modular.iam.domain.model;

import com.ssitao.code.frame.aggregate.entity.AbstractAggregateRoot;
import lombok.Getter;

import java.util.Date;

/**
 * 字典数据聚合根
 */
@Getter
public class SysDictDataAggregate extends AbstractAggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    private long version = 1;
    private Long dictCode;
    private Long dictSort;
    private String dictLabel;
    private String dictValue;
    private String dictType;
    private String cssClass;
    private String listClass;
    private String isDefault;
    private String status;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;

    public SysDictDataAggregate() {
    }

    public SysDictDataAggregate(Long dictCode) {
        this.dictCode = dictCode;
    }

    @Override
    public Long getId() {
        return dictCode;
    }

    @Override
    public void setId(Long id) {
        this.dictCode = id;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public Date getLastUpdateTime() {
        return updateTime;
    }

    public void createDictData(Long dictSort, String dictLabel, String dictValue, String dictType,
                               String cssClass, String listClass, String isDefault, String status,
                               String createBy) {
        this.dictSort = dictSort;
        this.dictLabel = dictLabel;
        this.dictValue = dictValue;
        this.dictType = dictType;
        this.cssClass = cssClass;
        this.listClass = listClass;
        this.isDefault = isDefault != null ? isDefault : "N";
        this.status = status != null ? status : "0";
        this.createBy = createBy;
        this.createTime = new Date();
    }

    public void updateDictData(Long dictSort, String dictLabel, String dictValue, String dictType,
                               String cssClass, String listClass, String isDefault, String status,
                               String updateBy) {
        this.dictSort = dictSort;
        this.dictLabel = dictLabel;
        this.dictValue = dictValue;
        this.dictType = dictType;
        this.cssClass = cssClass;
        this.listClass = listClass;
        this.isDefault = isDefault;
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    public void changeStatus(String status, String updateBy) {
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    public boolean isNormal() {
        return "0".equals(this.status);
    }

    // Setters
    public void setDictCode(Long dictCode) { this.dictCode = dictCode; }
    public void setDictSort(Long dictSort) { this.dictSort = dictSort; }
    public void setDictLabel(String dictLabel) { this.dictLabel = dictLabel; }
    public void setDictValue(String dictValue) { this.dictValue = dictValue; }
    public void setDictType(String dictType) { this.dictType = dictType; }
    public void setCssClass(String cssClass) { this.cssClass = cssClass; }
    public void setListClass(String listClass) { this.listClass = listClass; }
    public void setIsDefault(String isDefault) { this.isDefault = isDefault; }
    public void setStatus(String status) { this.status = status; }
    public void setCreateBy(String createBy) { this.createBy = createBy; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    public void setRemark(String remark) { this.remark = remark; }
    public String getCreateBy() { return createBy; }
    public String getUpdateBy() { return updateBy; }
    public String getRemark() { return remark; }
}
