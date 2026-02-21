package com.ssitao.code.modular.iam.authorization.application.service.impl;

import com.ssitao.code.modular.iam.authorization.application.command.IamRoleAssignPermissionCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.service.IamRoleAppService;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.domain.model.IamRole;
import com.ssitao.code.modular.iam.authorization.domain.repository.IamRoleRepository;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamRoleConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM角色应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamRoleAppServiceImpl implements IamRoleAppService {

    @Resource
    private IamRoleRepository roleRepository;

    @Resource
    private IamRoleConverter roleConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createRole(IamRoleCreateCommand command) {
        // 检查角色编码是否已存在
        if (roleRepository.existsByRoleCode(command.getRoleCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("角色编码已存在: " + command.getRoleCode());
        }

        // 创建角色聚合根
        IamRole role = IamRole.create(
                command.getRoleCode(),
                command.getRoleName(),
                command.getRoleType()
        );

        // 设置ID
        role.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置其他属性
        role.setDataScope(command.getDataScope());
        if (command.getPermGroupId() != null) {
            role.setPermGroupId(command.getPermGroupId().toString());
        }
        if (command.getParentId() != null) {
            role.setParentId(command.getParentId().toString());
        }
        role.setNodeType(command.getNodeType());
        role.setSortOrder(command.getSortOrder());
        role.setTenantId(command.getTenantId());
        role.setRemark(command.getRemark());

        // 处理层级和路径
        if (command.getParentId() != null) {
            IamRole parent = roleRepository.findById(command.getParentId().toString(), command.getTenantId()).orElse(null);
            if (parent != null) {
                role.setLayer(parent.getLayer() + 1);
                role.buildPath(parent.getPath());
            }
        } else {
            role.buildPath(null);
        }

        String id = roleRepository.save(role);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(IamRoleUpdateCommand command) {
        IamRole role = roleRepository.findById(command.getId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("角色不存在: " + command.getId()));

        // 更新属性
        if (command.getRoleName() != null) {
            role.setRoleName(command.getRoleName());
        }
        role.setDataScope(command.getDataScope());
        if (command.getPermGroupId() != null) {
            role.setPermGroupId(command.getPermGroupId().toString());
        }
        role.setSortOrder(command.getSortOrder());
        role.setRemark(command.getRemark());
        role.setUpdateTime(LocalDateTime.now());

        roleRepository.update(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id, String tenantId) {
        roleRepository.deleteById(id.toString(), tenantId);
    }

    @Override
    public IamRoleDTO getRoleById(Long id, String tenantId) {
        IamRole role = roleRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("角色不存在: " + id));
        return roleConverter.toDTO(role);
    }

    @Override
    public List<IamRoleDTO> listRoles(String tenantId) {
        List<IamRole> roles = roleRepository.findAll(tenantId);
        return roleConverter.toDTOList(roles);
    }

    @Override
    public List<IamRoleDTO> getRoleTree(String tenantId) {
        List<IamRole> roles = roleRepository.findTree(tenantId);
        return roleConverter.toDTOList(roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignPermissions(IamRoleAssignPermissionCommand command) {
        IamRole role = roleRepository.findById(command.getRoleId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("角色不存在: " + command.getRoleId()));

        // 清空现有权限并重新分配
        role.clearPermissions();

        if (command.getPermissionIds() != null && !command.getPermissionIds().isEmpty()) {
            List<String> permissionIdStrings = command.getPermissionIds().stream()
                    .map(id -> String.valueOf(id))
                    .collect(Collectors.toList());
            role.addPermissions(permissionIdStrings);
        }

        roleRepository.update(role);

        // 同步更新权限关联
        List<String> permissionIds = command.getPermissionIds().stream()
                .map(id -> String.valueOf(id))
                .collect(Collectors.toList());
        roleRepository.assignPermissions(command.getRoleId().toString(), permissionIds, command.getTenantId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableRole(Long id, String tenantId) {
        IamRole role = roleRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("角色不存在: " + id));
        role.enable();
        roleRepository.update(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableRole(Long id, String tenantId) {
        IamRole role = roleRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("角色不存在: " + id));
        role.disable();
        roleRepository.update(role);
    }
}
