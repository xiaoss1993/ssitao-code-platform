package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.commons.entity.CloneableEntity;

/**
 *
 */
public interface UserRoleEntity extends CloneableEntity {

    String getUserId();

    void setUserId(String userId);

    String getRoleId();

    void setRoleId(String roleId);
}
