package com.ssitao.code.modular.hr.organization.domain.repository;

import com.ssitao.code.modular.hr.organization.domain.model.HrPosition;

import java.util.List;
import java.util.Optional;

public interface HrPositionRepository {

    Optional<HrPosition> findById(String id, String tenantId);

    List<HrPosition> findAll(String tenantId);

    List<HrPosition> findByDeptId(String deptId, String tenantId);

    boolean existsByPositionCode(String positionCode, String tenantId, String excludeId);

    String save(HrPosition position);

    void update(HrPosition position);

    void deleteById(String id, String tenantId);
}
