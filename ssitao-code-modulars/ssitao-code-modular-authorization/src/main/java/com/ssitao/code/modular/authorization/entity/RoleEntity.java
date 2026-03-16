package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.commons.entity.GenericEntity;

/**
 * 角色实体
 *
 *
 */
public interface RoleEntity extends GenericEntity<String> {

    String name     = "name";
    String describe = "describe";
    String status   = "status";

    String getName();

    void setName(String name);

    String getDescribe();

    void setDescribe(String describe);

    void setStatus(Byte status);

    Byte getStatus();
}
