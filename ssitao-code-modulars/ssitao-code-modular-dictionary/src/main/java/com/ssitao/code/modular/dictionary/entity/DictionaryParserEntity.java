
package com.ssitao.code.modular.dictionary.entity;

import com.ssitao.code.commons.entity.GenericEntity;
import com.ssitao.code.commons.entity.RecordCreationEntity;

/**
 * 数据字典解析配置 实体
 *
 *
 */
public interface DictionaryParserEntity extends GenericEntity<String>, RecordCreationEntity {
 /*-------------------------------------------
    |               属性名常量               |
    ===========================================*/
    /**
     * 值到文本转换方式
     */
    String valueToTextParser = "valueToTextParser";
    /**
     * 文本到值转换方式
     */
    String textToValueParser = "textToValueParser";
    /**
     * 转换失败时的操作
     */
    String onError           = "onError";
    /**
     * 创建时间
     */
    String createTime        = "createTime";
    /**
     * 创建人id
     */
    String creatorId         = "creatorId";
    /**
     * 更新时间
     */
    String updateTime        = "updateTime";
    /**
     * 名称
     */
    String name              = "name";
    /**
     * 说明
     */
    String describe          = "describe";
    /**
     * 分类id
     */
    String classifiedId      = "classifiedId";

    /**
     * @return 值到文本转换方式
     */
    String getValueToTextParser();

    /**
     * 设置 值到文本转换方式
     */
    void setValueToTextParser(String valueToTextParser);

    /**
     * @return 文本到值转换方式
     */
    String getTextToValueParser();

    /**
     * 设置 文本到值转换方式
     */
    void setTextToValueParser(String textToValueParser);

    /**
     * @return 转换失败时的操作
     */
    String getOnError();

    /**
     * 设置 转换失败时的操作
     */
    void setOnError(String onError);

    /**
     * @return 更新时间
     */
    Long getUpdateTime();

    /**
     * 设置 更新时间
     */
    void setUpdateTime(Long updateTime);

    /**
     * @return 名称
     */
    String getName();

    /**
     * 设置 名称
     */
    void setName(String name);

    /**
     * @return 说明
     */
    String getDescribe();

    /**
     * 设置 说明
     */
    void setDescribe(String describe);

    /**
     * @return 分类id
     */
    String getClassifiedId();

    /**
     * 设置 分类id
     */
    void setClassifiedId(String classifiedId);

}
