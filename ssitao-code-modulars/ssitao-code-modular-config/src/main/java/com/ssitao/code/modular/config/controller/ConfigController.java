


package com.ssitao.code.modular.config.controller;

import io.swagger.annotations.Api;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.commons.entity.param.QueryParamEntity;
import com.ssitao.code.commons.controller.GenericEntityController;
import com.ssitao.code.modular.config.entity.ConfigEntity;
import com.ssitao.code.frame.logging.AccessLogger;
import com.ssitao.code.modular.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 完成注释
 *
 *
 */
@RestController
@RequestMapping("${tweb.web.mappings.config:config}")
@Authorize(permission = "config")
@AccessLogger("配置管理")
@Api(description = "配置管理，用于系统配置信息维护")
public class ConfigController implements GenericEntityController<ConfigEntity, String, QueryParamEntity,ConfigEntity> {

    private ConfigService configService;

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public ConfigService getService() {
        return configService;
    }

}
