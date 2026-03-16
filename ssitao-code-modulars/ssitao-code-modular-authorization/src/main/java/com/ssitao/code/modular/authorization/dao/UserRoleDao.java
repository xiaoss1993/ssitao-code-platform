package com.ssitao.code.modular.authorization.dao;

import com.ssitao.code.commons.dao.Dao;
import com.ssitao.code.modular.authorization.entity.UserRoleEntity;

import java.util.List;

/**
 *
 */
public interface UserRoleDao extends Dao {
    int deleteByUserId(String userId);

    int deleteByRoleId(String roleId);

    void insert(UserRoleEntity userRoleBean);

    List<UserRoleEntity> selectByUserId(String userId);

    List<UserRoleEntity> selectByRoleId(String roleId);

}
