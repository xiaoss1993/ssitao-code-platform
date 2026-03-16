package com.ssitao.code.modular.authorization.entity.bind;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.ssitao.code.modular.authorization.entity.SimpleUserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SimpleBindRoleUserEntity extends SimpleUserEntity implements BindRoleUserEntity {

    private List<String> roles;

    @Override
    public SimpleBindRoleUserEntity clone() {
        SimpleBindRoleUserEntity target = ((SimpleBindRoleUserEntity) super.clone());
        if (roles != null) {
            target.setRoles(new ArrayList<>(getRoles()));
        }
        return target;
    }
}
