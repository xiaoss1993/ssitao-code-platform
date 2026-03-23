package com.ssitao.code.modular.iam.domain.model;

import com.ssitao.code.frame.aggregate.entity.AbstractAggregateRoot;
import lombok.Getter;

import java.util.Date;

/**
 * 字典类型聚合根
 */
@Getter
public class SysDictTypeAggregate extends AbstractAggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    private long version = 1;
    private Long dictId;
    private String dictName;
    private String dictType;
    private String status;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;

    public SysDictTypeAggregate() {
    }

    public SysDictTypeAggregate(Long dictId) {
        this.dictId = dictId;
    }

    @Override
    public Long getId() {
        return dictId;
    }

    @Override
    public void setId(Long id) {
        this.dictId = id;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public Date getLastUpdateTime() {
        return updateTime;
    }

    public void createDictType(String dictName, String dictType, String status, String createBy) {
        this.dictName = dictName;
        this.dictType = dictType;
        this.status = status != null ? status : "0";
        this.createBy = createBy;
        this.createTime = new Date();
    }

    public void updateDictType(String dictName, String dictType, String status, String updateBy) {
        this.dictName = dictName;
        this.dictType = dictType;
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
    public void setDictId(Long dictId) { this.dictId = dictId; }
    public void setDictName(String dictName) { this.dictName = dictName; }
    public void setDictType(String dictType) { this.dictType = dictType; }
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
