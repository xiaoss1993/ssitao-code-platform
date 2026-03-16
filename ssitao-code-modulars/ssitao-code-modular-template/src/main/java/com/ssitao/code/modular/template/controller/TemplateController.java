package com.ssitao.code.modular.template.controller;

import com.tweb.commons.controller.SimpleGenericEntityController;
import io.swagger.annotations.Api;
import com.tweb.frame.authorization.annotation.Authorize;
import com.tweb.commons.entity.param.QueryParamEntity;
import com.ssitao.code.modular.template.entity.TemplateEntity;
import com.ssitao.code.modular.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模板
 *
 *
 */
@RestController
@RequestMapping("${tweb.web.mappings.template:template}")
@Authorize(permission = "template", description = "模版管理")
@Api(tags = "模版管理", value = "模版管理")
public class TemplateController implements SimpleGenericEntityController<TemplateEntity, String, QueryParamEntity> {

    private TemplateService templateService;

    @Autowired
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }

    @Override
    public TemplateService getService() {
        return templateService;
    }
}
