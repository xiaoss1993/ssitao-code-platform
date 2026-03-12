package com.ssitao.code.modular.hr.organization.application.service.impl;

import com.ssitao.code.modular.hr.organization.api.dto.HrDepartmentDTO;
import com.ssitao.code.modular.hr.organization.application.command.HrDepartmentCreateCommand;
import com.ssitao.code.modular.hr.organization.application.command.HrDepartmentUpdateCommand;
import com.ssitao.code.modular.hr.organization.application.service.HrDepartmentAppService;
import com.ssitao.code.modular.hr.organization.domain.model.HrDepartment;
import com.ssitao.code.modular.hr.organization.domain.repository.HrDepartmentRepository;
import com.ssitao.code.modular.hr.organization.infrastructure.converter.HrDepartmentConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门应用服务实现
 *
 * @author ssitao
 */
@Service
public class HrDepartmentAppServiceImpl implements HrDepartmentAppService {

    @Resource
    private HrDepartmentRepository departmentRepository;

    @Resource
    private HrDepartmentConverter departmentConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createDepartment(HrDepartmentCreateCommand command) {
        // 检查部门编码是否已存在
        if (departmentRepository.existsByDeptCode(command.getDeptCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("部门编码已存在: " + command.getDeptCode());
        }

        // 创建部门
        HrDepartment department = departmentConverter.toDomain(command);

        // 处理层级和路径
        if (command.getParentId() != null && !"0".equals(command.getParentId())) {
            departmentRepository.findById(command.getParentId(), command.getTenantId())
                    .ifPresent(parent -> {
                        department.setDeptLevel(parent.getDeptLevel() + 1);
                        department.buildPath(parent.getDeptPath());
                    });
        } else {
            department.setParentId("0");
            department.setDeptLevel(1);
            department.buildPath(null);
        }

        return departmentRepository.save(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDepartment(HrDepartmentUpdateCommand command) {
        HrDepartment department = departmentRepository.findById(command.getId(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + command.getId()));

        // 检查编码冲突
        if (command.getDeptCode() != null && !command.getDeptCode().equals(department.getDeptCode())) {
            if (departmentRepository.existsByDeptCode(command.getDeptCode(), command.getTenantId(), command.getId())) {
                throw new IllegalArgumentException("部门编码已存在: " + command.getDeptCode());
            }
            department.setDeptCode(command.getDeptCode());
        }

        departmentConverter.toDomain(command, department);
        department.setUpdateTime(LocalDateTime.now());
        departmentRepository.update(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDepartment(String id, String tenantId) {
        // 检查是否有子部门
        List<HrDepartment> children = departmentRepository.findByParentId(id, tenantId);
        if (children != null && !children.isEmpty()) {
            throw new IllegalArgumentException("请先删除子部门");
        }
        departmentRepository.deleteById(id, tenantId);
    }

    @Override
    public HrDepartmentDTO getDepartmentById(String id, String tenantId) {
        HrDepartment department = departmentRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + id));
        return departmentConverter.toTreeDTO(department);
    }

    @Override
    public List<HrDepartmentDTO> listDepartments(String tenantId) {
        List<HrDepartment> departments = departmentRepository.findAll(tenantId);
        return departmentConverter.toDTOList(departments);
    }

    @Override
    public List<HrDepartmentDTO> getDepartmentTree(String tenantId) {
        List<HrDepartment> departments = departmentRepository.findTree(tenantId);
        return departmentConverter.toTreeDTOList(departments);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableDepartment(String id, String tenantId) {
        HrDepartment department = departmentRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + id));
        department.enable();
        departmentRepository.update(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableDepartment(String id, String tenantId) {
        HrDepartment department = departmentRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + id));
        department.disable();
        departmentRepository.update(department);
    }
}
