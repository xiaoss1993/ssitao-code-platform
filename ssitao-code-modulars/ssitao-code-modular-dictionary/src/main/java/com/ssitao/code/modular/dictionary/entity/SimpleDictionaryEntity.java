
package com.ssitao.code.modular.dictionary.entity;

import com.ssitao.code.commons.entity.SimpleGenericEntity;

import java.util.List;

/**
 * 数据字典
 *
 *
 */
public class SimpleDictionaryEntity extends SimpleGenericEntity<String> implements DictionaryEntity {
    //字典名称
    private String                     name;
    //分类id
    private String                     classifiedId;
    //说明
    private String                     describe;
    //创建时间
    private Long                       createTime;
    //创建人id
    private String                     creatorId;
    //状态
    private Byte                       status;
    //字段选项
    private List<DictionaryItemEntity> items;

    /**
     * @return 字典名称
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 设置 字典名称
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 分类id
     */
    @Override
    public String getClassifiedId() {
        return this.classifiedId;
    }

    /**
     * 设置 分类id
     */
    @Override
    public void setClassifiedId(String classifiedId) {
        this.classifiedId = classifiedId;
    }

    /**
     * @return 说明
     */
    @Override
    public String getDescribe() {
        return this.describe;
    }

    /**
     * 设置 说明
     */
    @Override
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * @return 创建时间
     */
    @Override
    public Long getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建时间
     */
    @Override
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 创建人id
     */
    @Override
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * 设置 创建人id
     */
    @Override
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return 状态
     */
    @Override
    public Byte getStatus() {
        return this.status;
    }

    /**
     * 设置 状态
     */
    @Override
    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public List<DictionaryItemEntity> getItems() {
        return items;
    }

    @Override
    public void setItems(List<DictionaryItemEntity> items) {
        this.items = items;
    }
}
