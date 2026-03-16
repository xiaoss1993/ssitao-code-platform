package com.ssitao.code.modular.authorization.entity.bind;

import com.ssitao.code.modular.authorization.entity.UserEntity;

import java.util.List;

/**
 *
 */
public interface BindRoleUserEntity extends UserEntity {
    List<String> getRoles();

    void setRoles(List<String> roles);
}
