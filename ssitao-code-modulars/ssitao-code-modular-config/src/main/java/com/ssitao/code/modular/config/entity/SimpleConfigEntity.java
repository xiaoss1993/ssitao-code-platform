

package com.ssitao.code.modular.config.entity;


import com.ssitao.code.commons.entity.SimpleGenericEntity;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class SimpleConfigEntity extends SimpleGenericEntity<String> implements ConfigEntity {

    //备注
    private String remark;

    //配置内容
    private List<ConfigContent> content;

    //创建日期
    @NotNull
    private Long createTime;

    //最后一次修改日期
    private Long updateTime;

    //配置分类ID
    private String classifiedId;

    @NotNull
    private String creatorId;

    private volatile Map<String, ConfigContent> cache;

    @Override
    public String getCreatorId() {
        return creatorId;
    }

    @Override
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public List<ConfigContent> getContent() {
        return content;
    }

    @Override
    public ConfigEntity addContent(String key, Object value, String comment) {
        if (content == null) {
            content = new ArrayList<>();
        }
        content.add(new ConfigContent(key, value, comment));
        return this;
    }

    @Override
    public ConfigContent get(String key) {
        if (cache == null) {
            synchronized (this) {
                if (cache == null) {
                    if (content == null || content.isEmpty()) {
                        return null;
                    }
                    cache = content.stream()
                            .collect(Collectors.toMap(ConfigContent::getKey, content -> content));
                }
            }
        }
        return cache.get(key);
    }

    @Override
    public void setContent(List<ConfigContent> content) {
        this.content = content;
    }

    @Override
    public Long getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public Long getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getClassifiedId() {
        return classifiedId;
    }

    @Override
    public void setClassifiedId(String classifiedId) {
        this.classifiedId = classifiedId;
    }

    @Override
    public SimpleConfigEntity clone() {
        SimpleConfigEntity cloned = new SimpleConfigEntity();
        cloned.setId(getId());
        cloned.setCreatorId(getCreatorId());
        this.setCreateTime(getCreateTime());
        cloned.content = getContent().stream().map(ConfigContent::clone).collect(Collectors.toList());
        return cloned;
    }
}
