package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.ssitao.code.commons.entity.SimpleGenericEntity;

/**
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SimpleRoleEntity extends SimpleGenericEntity<String> implements RoleEntity {
    private static final long serialVersionUID = -2857131363164004807L;
    private String name;

    private String describe;

    private Byte status;

    @Override
    public SimpleRoleEntity clone() {
        return ((SimpleRoleEntity) super.clone());
    }
}
