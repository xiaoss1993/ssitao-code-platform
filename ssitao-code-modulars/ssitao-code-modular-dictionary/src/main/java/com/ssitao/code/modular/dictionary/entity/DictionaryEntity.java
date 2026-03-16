
package com.ssitao.code.modular.dictionary.entity;

import com.ssitao.code.commons.entity.GenericEntity;
import com.ssitao.code.commons.entity.RecordCreationEntity;

import java.util.List;

/**
 * 数据字典 实体
 *
 *
 */
public interface DictionaryEntity extends GenericEntity<String>, RecordCreationEntity {
 /*-------------------------------------------
    |               属性名常量               |
    ===========================================*/
    /**
     * 字典名称
     */
    String name         = "name";
    /**
     * 分类id
     */
    String classifiedId = "classifiedId";
    /**
     * 说明
     */
    String describe     = "describe";
    /**
     * 创建时间
     */
    String createTime   = "createTime";
    /**
     * 创建人id
     */
    String creatorId    = "creatorId";
    /**
     * 状态
     */
    String status       = "status";

    /**
     * @return 字典名称
     */
    String getName();

    /**
     * 设置 字典名称
     */
    void setName(String name);

    /**
     * @return 分类id
     */
    String getClassifiedId();

    /**
     * 设置 分类id
     */
    void setClassifiedId(String classifiedId);

    /**
     * @return 说明
     */
    String getDescribe();

    /**
     * 设置 说明
     */
    void setDescribe(String describe);

    /**
     * @return 状态
     */
    Byte getStatus();

    /**
     * 设置 状态
     */
    void setStatus(Byte enabled);

    List<DictionaryItemEntity> getItems();

    void setItems(List<DictionaryItemEntity> items);

}
