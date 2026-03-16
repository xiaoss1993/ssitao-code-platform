


package com.ssitao.code.modular.config.service;

import com.ssitao.code.modular.config.entity.ConfigEntity;
import com.ssitao.code.commons.service.CrudService;

/**
 *
 */
public interface ConfigService extends CrudService<ConfigEntity, String> {
    Number getNumber(String configId, String key, Number defaultValue);

    String getString(String configId, String key, String defaultValue);

    boolean getBoolean(String configId, String key, boolean defaultValue);

}
