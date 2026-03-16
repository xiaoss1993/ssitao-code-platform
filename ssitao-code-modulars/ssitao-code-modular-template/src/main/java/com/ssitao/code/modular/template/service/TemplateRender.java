package com.ssitao.code.modular.template.service;

import com.ssitao.code.modular.template.entity.TemplateEntity;

/**
 * 模板渲染器
 *
 *
 * @since 3.0
 */
public interface TemplateRender {
    String render(Object context);
}
