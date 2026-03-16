
package com.ssitao.code.modular.organization.entity;

import java.util.Set;

/**
 *
 */
public interface PersonAuthBindEntity extends PersonEntity {
    PersonUserEntity getPersonUser();

    void setPersonUser(PersonUserEntity personUser);

    Set<String> getPositionIds();

    void setPositionIds(Set<String> positionIds);

}
