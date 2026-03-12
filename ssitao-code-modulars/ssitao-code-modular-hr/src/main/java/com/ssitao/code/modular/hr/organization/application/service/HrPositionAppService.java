package com.ssitao.code.modular.hr.organization.application.service;

import com.ssitao.code.modular.hr.organization.api.dto.HrPositionDTO;
import com.ssitao.code.modular.hr.organization.application.command.HrPositionCreateCommand;
import com.ssitao.code.modular.hr.organization.application.command.HrPositionUpdateCommand;

import java.util.List;

public interface HrPositionAppService {

    String createPosition(HrPositionCreateCommand command);

    void updatePosition(HrPositionUpdateCommand command);

    void deletePosition(String id, String tenantId);

    HrPositionDTO getPositionById(String id, String tenantId);

    List<HrPositionDTO> listPositions(String tenantId);

    List<HrPositionDTO> listPositionsByDept(String deptId, String tenantId);

    void enablePosition(String id, String tenantId);

    void disablePosition(String id, String tenantId);
}
