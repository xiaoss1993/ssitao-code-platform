package com.ssitao.code.modular.iam.organization.application.service.impl;

import com.ssitao.code.modular.iam.organization.application.command.IamDepartmentCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamDepartmentUpdateCommand;
import com.ssitao.code.modular.iam.organization.application.service.IamDepartmentAppService;
import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.organization.domain.model.IamDepartment;
import com.ssitao.code.modular.iam.organization.domain.repository.IamDepartmentRepository;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamDepartmentConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM部门应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamDepartmentAppServiceImpl implements IamDepartmentAppService {

    @Resource
    private IamDepartmentRepository departmentRepository;

    @Resource
    private IamDepartmentConverter departmentConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDepartment(IamDepartmentCreateCommand command) {
        // 检查部门编码是否已存在
        if (departmentRepository.existsByDeptCode(command.getDeptCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("部门编码已存在: " + command.getDeptCode());
        }

        // 创建部门聚合根
        IamDepartment department = IamDepartment.create(
                command.getDeptCode(),
                command.getDeptName(),
                command.getTenantId()
        );

        // 设置ID
        department.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置父部门ID
        if (command.getParentId() != null) {
            department.setParentId(command.getParentId().toString());
        }

        // 设置其他属性
        department.setLeaderId(command.getLeaderId() != null ? command.getLeaderId().toString() : null);
        department.setLeaderName(command.getLeaderName());
        department.setPhone(command.getPhone());
        department.setEmail(command.getEmail());
        department.setSortOrder(command.getSortOrder());
        department.setRemark(command.getRemark());

        // 处理层级和路径
        if (command.getParentId() != null) {
            IamDepartment parent = departmentRepository.findById(
                    command.getParentId().toString(), command.getTenantId()).orElse(null);
            if (parent != null) {
                department.setLayer(parent.getLayer() + 1);
                department.buildPath(parent.getPath());
            }
        } else {
            department.buildPath(null);
        }

        String id = departmentRepository.save(department);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchCreateDepartments(List<IamDepartmentCreateCommand> commands) {
        return commands.stream()
                .map(this::createDepartment)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDepartment(IamDepartmentUpdateCommand command) {
        IamDepartment department = departmentRepository.findById(
                command.getId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + command.getId()));

        // 更新属性
        if (command.getDeptName() != null) {
            department.setDeptName(command.getDeptName());
        }
        if (command.getLeaderId() != null) {
            department.setLeaderId(command.getLeaderId().toString());
        }
        department.setLeaderName(command.getLeaderName());
        department.setPhone(command.getPhone());
        department.setEmail(command.getEmail());
        department.setSortOrder(command.getSortOrder());
        department.setRemark(command.getRemark());
        department.setUpdateTime(LocalDateTime.now());

        departmentRepository.update(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDepartment(Long id, String tenantId) {
        departmentRepository.deleteById(id.toString(), tenantId);
    }

    @Override
    public IamDepartmentDTO getDepartmentById(Long id, String tenantId) {
        IamDepartment department = departmentRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + id));
        return departmentConverter.toDTO(department);
    }

    @Override
    public List<IamDepartmentDTO> listDepartments(String tenantId) {
        List<IamDepartment> departments = departmentRepository.findAll(tenantId);
        return departmentConverter.toDTOList(departments);
    }

    @Override
    public List<IamDepartmentDTO> getDepartmentTree(String tenantId) {
        List<IamDepartment> departments = departmentRepository.findTree(tenantId);
        return departmentConverter.toDTOList(departments);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableDepartment(Long id, String tenantId) {
        IamDepartment department = departmentRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + id));
        department.enable();
        departmentRepository.update(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableDepartment(Long id, String tenantId) {
        IamDepartment department = departmentRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + id));
        department.disable();
        departmentRepository.update(department);
    }
}
