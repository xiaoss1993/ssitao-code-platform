
package com.ssitao.code.modular.dictionary.entity;

import com.ssitao.code.commons.entity.TreeSortSupportEntity;
import com.ssitao.code.commons.utils.dict.EnumDict;

import java.util.List;

/**
 * 数据字典选项 实体
 *
 *
 */
public interface DictionaryItemEntity extends TreeSortSupportEntity<String>, EnumDict<String> {
 /*-------------------------------------------
    |               属性名常量               |
    ===========================================*/
    /**
     * 字典id
     */
    String dictId = "dictId";
    /**
     * 名称
     */
    String name = "name";
    /**
     * 字典值
     */
    String value = "value";
    /**
     * 字典文本
     */
    String text = "text";
    /**
     * 字典值类型
     */
    String valueType = "valueType";
    /**
     * 是否启用
     */
    String status = "status";
    /**
     * 说明
     */
    String describe = "describe";
    /**
     * 父级选项
     */
    String parentId = "parentId";
    /**
     * 树编码
     */
    String path = "path";
    /**
     * 快速搜索码
     */
    String searchCode = "searchCode";
    /**
     * 排序索引
     */
    String sortIndex = "sortIndex";
    /**
     * 树结构层级
     */
    String level = "level";

    /**
     * @return 字典id
     */
    String getDictId();

    /**
     * 设置 字典id
     */
    void setDictId(String dictId);

    /**
     * @return 名称
     */
    String getName();

    /**
     * 设置 名称
     */
    void setName(String name);

    /**
     * @return 字典值
     */
    String getValue();

    /**
     * 设置 字典值
     */
    void setValue(String value);

    /**
     * @return 字典文本
     */
    String getText();

    /**
     * 设置 字典文本
     */
    void setText(String text);

    /**
     * @return 字典值类型
     */
    String getValueType();

    /**
     * 设置 字典值类型
     */
    void setValueType(String valueType);

    /**
     * @return 状态
     */
    Byte getStatus();

    /**
     * 设置 状态
     */
    void setStatus(Byte status);

    /**
     * @return 说明
     */
    String getDescribe();

    /**
     * 设置 说明
     */
    void setDescribe(String describe);

    /**
     * @return 快速搜索码
     */
    String getSearchCode();

    /**
     * 设置 快速搜索码
     */
    void setSearchCode(String searchCode);

    void setChildren(List<DictionaryItemEntity> children);

    Integer getOrdinal();

    void setOrdinal(Integer ordinal);

    @Override
    default int ordinal() {
        return getOrdinal() == null ? 0 : getOrdinal();
    }

    @Override
    default String getComments() {
        return getDescribe();
    }
}
