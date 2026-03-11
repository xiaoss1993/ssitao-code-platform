package com.ssitao.code.modular.hr.organization.infrastructure.converter;

import com.ssitao.code.modular.hr.organization.api.dto.HrPositionDTO;
import com.ssitao.code.modular.hr.organization.application.command.HrPositionCreateCommand;
import com.ssitao.code.modular.hr.organization.application.command.HrPositionUpdateCommand;
import com.ssitao.code.modular.hr.organization.domain.model.HrPosition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HrPositionConverter {

    public HrPositionDTO toDTO(HrPosition position) {
        if (position == null) return null;
        HrPositionDTO dto = new HrPositionDTO();
        dto.setId(position.getId());
        dto.setPositionCode(position.getPositionCode());
        dto.setPositionName(position.getPositionName());
        dto.setDeptId(position.getDeptId());
        dto.setPositionLevel(position.getPositionLevel());
        dto.setPositionType(position.getPositionType());
        dto.setPositionSummary(position.getPositionSummary());
        dto.setPositionRequirement(position.getPositionRequirement());
        dto.setPositionSort(position.getPositionSort());
        dto.setStatus(position.getStatus());
        dto.setLabel(position.getPositionName());
        dto.setValue(position.getId());
        return dto;
    }

    public List<HrPositionDTO> toDTOList(List<HrPosition> positions) {
        return positions.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public HrPosition toDomain(HrPositionCreateCommand command) {
        HrPosition position = HrPosition.create(command.getPositionCode(), command.getPositionName(), command.getTenantId());
        position.setDeptId(command.getDeptId());
        position.setPositionLevel(command.getPositionLevel());
        position.setPositionType(command.getPositionType());
        position.setPositionSummary(command.getPositionSummary());
        position.setPositionRequirement(command.getPositionRequirement());
        position.setPositionSort(command.getPositionSort() != null ? command.getPositionSort() : 0);
        position.setCreator(command.getCreator());
        return position;
    }

    public void toDomain(HrPositionUpdateCommand command, HrPosition position) {
        if (command.getPositionName() != null) position.setPositionName(command.getPositionName());
        if (command.getPositionCode() != null) position.setPositionCode(command.getPositionCode());
        position.setDeptId(command.getDeptId());
        position.setPositionLevel(command.getPositionLevel());
        position.setPositionType(command.getPositionType());
        position.setPositionSummary(command.getPositionSummary());
        position.setPositionRequirement(command.getPositionRequirement());
        position.setPositionSort(command.getPositionSort());
        position.setUpdater(command.getUpdater());
    }
}
