package com.ssitao.code.modular.authorization.service;

import com.ssitao.code.commons.service.CrudService;
import com.ssitao.code.modular.authorization.entity.UserSettingEntity;

import java.util.List;

/**
 *
 * @since 3.0
 */
public interface UserSettingService extends CrudService<UserSettingEntity, String> {
    List<UserSettingEntity> selectByUser(String userId, String key);

    UserSettingEntity selectByUser(String userId, String key, String settingId);
}
