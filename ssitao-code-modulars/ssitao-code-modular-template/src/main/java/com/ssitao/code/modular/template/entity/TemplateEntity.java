package com.ssitao.code.modular.template.entity;

import org.hibernate.validator.constraints.NotBlank;
import com.tweb.commons.entity.GenericEntity;
import com.tweb.commons.utils.validator.group.CreateGroup;

import javax.validation.constraints.NotNull;

/**
 * 模板 实体
 *
 *
 */
public interface TemplateEntity extends GenericEntity<String> {
 /*-------------------------------------------
    |               属性名常量               |
    ===========================================*/
    /**
     * 模板名称
     */
    String name       = "name";
    /**
     * 模板类型
     */
    String type       = "type";
    /**
     * 模板内容
     */
    String template   = "template";
    /**
     * 模板配置
     */
    String config     = "config";
    /**
     * 版本号
     */
    String version    = "version";
    /**
     * 模板分类
     */
    String classified = "classified";

    /**
     * @return 模板名称
     */
    @NotBlank(groups = CreateGroup.class)
    String getName();

    /**
     * @param name 模板名称
     */
    void setName(String name);

    /**
     * @return 模板类型
     */
    @NotBlank(groups = CreateGroup.class)
    String getType();

    /**
     * @param type 模板类型
     */
    void setType(String type);

    /**
     * @return 模板内容
     */
    String getTemplate();

    /**
     * @param template 模板内容
     */
    void setTemplate(String template);

    /**
     * @return 模板配置
     */
    String getConfig();

    /**
     * @param config 模板配置
     */
    void setConfig(String config);

    /**
     * @return 版本号
     */
    @NotNull(groups = CreateGroup.class)
    Long getVersion();

    /**
     * @param version 版本号
     */
    void setVersion(Long version);

    /**
     * @return 模板分类
     */
    String getClassified();

    /**
     * @param classified 模板分类
     */
    void setClassified(String classified);

}
