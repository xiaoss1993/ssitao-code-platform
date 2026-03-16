
package com.ssitao.code.modular.organization.authorization.access;

import com.ssitao.code.modular.common.entity.Entity;

/**
 *
 */
public interface PositionAttachEntity extends Entity {
    String positionId = "positionId";

    String getPositionId();

    void setPositionId(String positionId);

    default String getPositionIdProperty() {
        return positionId;
    }
}
