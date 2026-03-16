package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SimpleUserRoleEntity implements UserRoleEntity {

    private static final long serialVersionUID = -8831232608833695774L;

    private String userId;

    private String roleId;

    @Override
    public SimpleUserRoleEntity clone() {
        SimpleUserRoleEntity target = new SimpleUserRoleEntity();
        target.setRoleId(getRoleId());
        target.setUserId(getUserId());
        return target;
    }
}
