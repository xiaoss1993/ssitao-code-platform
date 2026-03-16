


package com.ssitao.code.modular.config.entity;


import com.ssitao.code.commons.entity.GenericEntity;
import com.ssitao.code.commons.entity.RecordCreationEntity;

import java.util.List;

/**
 * TODO 完成注释
 *
 *
 */
public interface ConfigEntity extends GenericEntity<String>,RecordCreationEntity {

    /**
     * 获取 备注
     *
     * @return String 备注
     */
    String getRemark();

    /**
     * 设置 备注
     *
     * @param remark 备注
     */
    void setRemark(String remark);

    /**
     * 获取 配置内容
     *
     * @return String 配置内容
     */
    List<ConfigContent> getContent();

    /**
     * 设置 配置内容
     *
     * @param content 配置内容
     */
    void setContent(List<ConfigContent> content);

    /**
     * 获取 最后一次修改日期
     *
     * @return java.util.Date 最后一次修改日期
     */
    Long getUpdateTime();

    /**
     * 设置 最后一次修改日期
     *
     * @param updateDate 最后一次修改日期
     */
    void setUpdateTime(Long updateDate);

    /**
     * 获取分类ID
     *
     * @return 分类ID
     */
    String getClassifiedId();

    /**
     * 设置分类ID
     *
     * @param classifiedId 分类ID
     */
    void setClassifiedId(String classifiedId);

    ConfigEntity addContent(String key, Object value, String comment);

    ConfigContent get(String key);
}
