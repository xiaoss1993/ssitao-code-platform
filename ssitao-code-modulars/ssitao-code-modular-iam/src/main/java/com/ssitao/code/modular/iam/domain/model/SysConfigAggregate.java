package com.ssitao.code.modular.iam.domain.model;

import com.ssitao.code.frame.aggregate.entity.AbstractAggregateRoot;
import lombok.Getter;

import java.util.Date;

/**
 * 参数配置聚合根
 */
@Getter
public class SysConfigAggregate extends AbstractAggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    /** 版本号（用于乐观锁） */
    private long version = 1;

    /** 参数主键 */
    private Long configId;

    /** 参数名称 */
    private String configName;

    /** 参数键名 */
    private String configKey;

    /** 参数键值 */
    private String configValue;

    /** 系统内置（Y是 N否） */
    private String configType;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;

    public SysConfigAggregate() {
    }

    public SysConfigAggregate(Long configId) {
        this.configId = configId;
    }

    @Override
    public Long getId() {
        return configId;
    }

    @Override
    public void setId(Long id) {
        this.configId = id;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public Date getLastUpdateTime() {
        return updateTime;
    }

    /**
     * 创建参数配置
     */
    public void createConfig(String configName, String configKey, String configValue,
                            String configType, String createBy) {
        this.configName = configName;
        this.configKey = configKey;
        this.configValue = configValue;
        this.configType = configType != null ? configType : "N";
        this.createBy = createBy;
        this.createTime = new Date();
    }

    /**
     * 更新参数配置
     */
    public void updateConfig(String configName, String configKey, String configValue,
                            String configType, String updateBy) {
        this.configName = configName;
        this.configKey = configKey;
        this.configValue = configValue;
        this.configType = configType;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 是否为系统内置
     */
    public boolean isSystem() {
        return "Y".equals(this.configType);
    }

    // Setters for aggregate operations
    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public String getRemark() {
        return remark;
    }
}
