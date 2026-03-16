package com.ssitao.code.modular.template.service;

import com.ssitao.code.modular.template.entity.TemplateEntity;

/**
 * 模板渲染器工厂
 *
 *
 * @since 3.0
 */
public interface TemplateRendeFacotry {
    boolean isSupport(String type);

    TemplateRender create(TemplateEntity templateEntity);
}
