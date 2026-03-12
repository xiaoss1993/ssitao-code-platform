package com.ssitao.code.modular.iam.authorization.application.service.impl;

import com.ssitao.code.modular.iam.authorization.application.command.IamPermGroupAssignPermissionCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermGroupCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermGroupUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.service.IamPermGroupAppService;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermGroupDTO;
import com.ssitao.code.modular.iam.authorization.domain.model.IamPermGroup;
import com.ssitao.code.modular.iam.authorization.domain.repository.IamPermGroupRepository;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamPermGroupConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM权限组应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamPermGroupAppServiceImpl implements IamPermGroupAppService {

    @Resource
    private IamPermGroupRepository permGroupRepository;

    @Resource
    private IamPermGroupConverter permGroupConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPermGroup(IamPermGroupCreateCommand command) {
        // 检查权限组编码是否已存在
        if (permGroupRepository.existsByGroupCode(command.getGroupCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("权限组编码已存在: " + command.getGroupCode());
        }

        // 创建权限组聚合根
        IamPermGroup permGroup = IamPermGroup.create(
                command.getGroupCode(),
                command.getGroupName()
        );

        // 设置ID
        permGroup.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置其他属性
        permGroup.setDescription(command.getDescription());
        permGroup.setTenantId(command.getTenantId());
        permGroup.setSortOrder(command.getSortOrder());
        permGroup.setRemark(command.getRemark());

        // 设置权限
        if (command.getPermissionIds() != null && !command.getPermissionIds().isEmpty()) {
            Set<String> permissionIdStrings = command.getPermissionIds().stream()
                    .map(id -> String.valueOf(id))
                    .collect(Collectors.toSet());
            permGroup.addPermissions(permissionIdStrings);
        }

        String id = permGroupRepository.save(permGroup);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePermGroup(IamPermGroupUpdateCommand command) {
        IamPermGroup permGroup = permGroupRepository.findById(command.getId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("权限组不存在: " + command.getId()));

        // 更新属性
        if (command.getGroupName() != null) {
            permGroup.setGroupName(command.getGroupName());
        }
        permGroup.setDescription(command.getDescription());
        permGroup.setSortOrder(command.getSortOrder());
        permGroup.setRemark(command.getRemark());
        permGroup.setUpdateTime(LocalDateTime.now());

        permGroupRepository.update(permGroup);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePermGroup(Long id, String tenantId) {
        permGroupRepository.deleteById(id.toString(), tenantId);
    }

    @Override
    public IamPermGroupDTO getPermGroupById(Long id, String tenantId) {
        IamPermGroup permGroup = permGroupRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("权限组不存在: " + id));
        return convertToDTO(permGroup);
    }

    @Override
    public List<IamPermGroupDTO> listPermGroups(String tenantId) {
        List<IamPermGroup> permGroups = permGroupRepository.findAll(tenantId);
        return permGroups.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignPermissions(IamPermGroupAssignPermissionCommand command) {
        IamPermGroup permGroup = permGroupRepository.findById(command.getGroupId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("权限组不存在: " + command.getGroupId()));

        // 清空现有权限并重新分配
        permGroup.clearPermissions();

        if (command.getPermissionIds() != null && !command.getPermissionIds().isEmpty()) {
            Set<String> permissionIdStrings = command.getPermissionIds().stream()
                    .map(id -> String.valueOf(id))
                    .collect(Collectors.toSet());
            permGroup.addPermissions(permissionIdStrings);
        }

        permGroupRepository.update(permGroup);

        // 同步更新权限关联
        permGroupRepository.assignPermissions(
                command.getGroupId().toString(),
                permGroup.getPermissionIds(),
                command.getTenantId()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enablePermGroup(Long id, String tenantId) {
        IamPermGroup permGroup = permGroupRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("权限组不存在: " + id));
        permGroup.enable();
        permGroupRepository.update(permGroup);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disablePermGroup(Long id, String tenantId) {
        IamPermGroup permGroup = permGroupRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("权限组不存在: " + id));
        permGroup.disable();
        permGroupRepository.update(permGroup);
    }

    /**
     * 转换权限组为DTO
     */
    private IamPermGroupDTO convertToDTO(IamPermGroup permGroup) {
        IamPermGroupDTO dto = new IamPermGroupDTO();
        dto.setId(permGroup.getId());
        dto.setGroupCode(permGroup.getGroupCode());
        dto.setGroupName(permGroup.getGroupName());
        dto.setDescription(permGroup.getDescription());
        dto.setStatus(permGroup.getStatus());
        dto.setTenantId(permGroup.getTenantId());
        dto.setSortOrder(permGroup.getSortOrder());
        dto.setRemark(permGroup.getRemark());
        dto.setCreateTime(permGroup.getCreateTime());
        dto.setUpdateTime(permGroup.getUpdateTime());
        dto.setCreator(permGroup.getCreator());
        dto.setUpdater(permGroup.getUpdater());

        // 获取权限ID列表
        Set<String> permissionIds = permGroupRepository.getPermissionIds(permGroup.getId(), permGroup.getTenantId());
        dto.setPermissionIds(permissionIds);

        return dto;
    }
}
