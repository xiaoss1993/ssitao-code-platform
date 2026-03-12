package com.ssitao.code.modular.hr.organization.infrastructure.converter;

import com.ssitao.code.modular.hr.organization.api.dto.HrDepartmentDTO;
import com.ssitao.code.modular.hr.organization.application.command.HrDepartmentCreateCommand;
import com.ssitao.code.modular.hr.organization.application.command.HrDepartmentUpdateCommand;
import com.ssitao.code.modular.hr.organization.domain.model.HrDepartment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门转换器
 *
 * @author ssitao
 */
@Component
public class HrDepartmentConverter {

    /**
     * Domain转DTO
     */
    public HrDepartmentDTO toDTO(HrDepartment department) {
        if (department == null) {
            return null;
        }
        HrDepartmentDTO dto = new HrDepartmentDTO();
        dto.setId(department.getId());
        dto.setDeptCode(department.getDeptCode());
        dto.setDeptName(department.getDeptName());
        dto.setParentId(department.getParentId());
        dto.setDeptLevel(department.getDeptLevel());
        dto.setDeptPath(department.getDeptPath());
        dto.setDeptSort(department.getDeptSort());
        dto.setDeptLeader(department.getDeptLeader());
        dto.setPhone(department.getPhone());
        dto.setEmail(department.getEmail());
        dto.setStatus(department.getStatus());
        dto.setLabel(department.getDeptName());
        dto.setValue(department.getId());
        return dto;
    }

    /**
     * Domain列表转DTO列表
     */
    public List<HrDepartmentDTO> toDTOList(List<HrDepartment> departments) {
        return departments.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Domain转树形DTO
     */
    public HrDepartmentDTO toTreeDTO(HrDepartment department) {
        HrDepartmentDTO dto = toDTO(department);
        if (department.getChildren() != null && !department.getChildren().isEmpty()) {
            dto.setChildren(toTreeDTOList(department.getChildren()));
        }
        return dto;
    }

    /**
     * 树形DTO列表
     */
    public List<HrDepartmentDTO> toTreeDTOList(List<HrDepartment> departments) {
        return departments.stream().map(this::toTreeDTO).collect(Collectors.toList());
    }

    /**
     * Command转Domain
     */
    public HrDepartment toDomain(HrDepartmentCreateCommand command) {
        HrDepartment department = HrDepartment.create(
                command.getDeptCode(),
                command.getDeptName(),
                command.getTenantId()
        );
        department.setParentId(command.getParentId() != null ? command.getParentId() : "0");
        department.setDeptLeader(command.getDeptLeader());
        department.setPhone(command.getPhone());
        department.setEmail(command.getEmail());
        department.setDeptSort(command.getDeptSort() != null ? command.getDeptSort() : 0);
        department.setCreator(command.getCreator());
        return department;
    }

    /**
     * UpdateCommand转Domain
     */
    public void toDomain(HrDepartmentUpdateCommand command, HrDepartment department) {
        if (command.getDeptName() != null) {
            department.setDeptName(command.getDeptName());
        }
        if (command.getDeptLeader() != null) {
            department.setDeptLeader(command.getDeptLeader());
        }
        department.setPhone(command.getPhone());
        department.setEmail(command.getEmail());
        department.setDeptSort(command.getDeptSort());
        department.setUpdater(command.getUpdater());
    }
}
