package com.ssitao.code.modular.iam.authorization.application.service.impl;

import com.ssitao.code.modular.iam.authorization.application.command.IamPermissionCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermissionUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.service.IamPermissionAppService;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;
import com.ssitao.code.modular.iam.authorization.domain.model.IamPermission;
import com.ssitao.code.modular.iam.authorization.domain.repository.IamPermissionRepository;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamPermissionConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM权限应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamPermissionAppServiceImpl implements IamPermissionAppService {

    @Resource
    private IamPermissionRepository permissionRepository;

    @Resource
    private IamPermissionConverter permissionConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPermission(IamPermissionCreateCommand command) {
        // 检查权限编码是否已存在
        if (permissionRepository.existsByPermCode(command.getPermCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("权限编码已存在: " + command.getPermCode());
        }

        // 根据权限类型创建权限
        IamPermission permission;
        switch (command.getPermType()) {
            case "MENU":
                permission = IamPermission.createMenu(command.getPermCode(), command.getPermName());
                break;
            case "BUTTON":
                permission = IamPermission.createButton(command.getPermCode(), command.getPermName());
                break;
            case "API":
                permission = IamPermission.createApi(command.getPermCode(), command.getPermName());
                break;
            case "DATA":
                permission = IamPermission.createData(command.getPermCode(), command.getPermName());
                break;
            default:
                throw new IllegalArgumentException("不支持的权限类型: " + command.getPermType());
        }

        // 设置ID
        permission.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置其他属性
        if (command.getParentId() != null) {
            permission.setParentId(command.getParentId().toString());
        }
        permission.setPath(command.getPath());
        permission.setComponent(command.getComponent());
        permission.setPerms(command.getPerms());
        permission.setIcon(command.getIcon());
        permission.setIsFrame(command.getIsFrame());
        permission.setIsCache(command.getIsCache());
        permission.setVisible(command.getVisible());
        permission.setRedirect(command.getRedirect());
        permission.setSortOrder(command.getSortOrder());
        permission.setTenantId(command.getTenantId());
        permission.setRemark(command.getRemark());

        String id = permissionRepository.save(permission);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchCreatePermissions(List<IamPermissionCreateCommand> commands) {
        return commands.stream()
                .map(this::createPermission)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePermission(IamPermissionUpdateCommand command) {
        IamPermission permission = permissionRepository.findById(command.getId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("权限不存在: " + command.getId()));

        // 更新属性
        if (command.getPermName() != null) {
            permission.setPermName(command.getPermName());
        }
        permission.setPath(command.getPath());
        permission.setComponent(command.getComponent());
        permission.setPerms(command.getPerms());
        permission.setIcon(command.getIcon());
        permission.setIsFrame(command.getIsFrame());
        permission.setIsCache(command.getIsCache());
        permission.setVisible(command.getVisible());
        permission.setRedirect(command.getRedirect());
        permission.setSortOrder(command.getSortOrder());
        permission.setRemark(command.getRemark());
        permission.setUpdateTime(LocalDateTime.now());

        permissionRepository.update(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePermission(Long id, String tenantId) {
        permissionRepository.deleteById(id.toString(), tenantId);
    }

    @Override
    public IamPermissionDTO getPermissionById(Long id, String tenantId) {
        IamPermission permission = permissionRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("权限不存在: " + id));
        return permissionConverter.toDTO(permission);
    }

    @Override
    public List<IamPermissionDTO> listPermissions(String tenantId) {
        List<IamPermission> permissions = permissionRepository.findAll(tenantId);
        return permissionConverter.toDTOList(permissions);
    }

    @Override
    public List<IamPermissionDTO> getPermissionTree(String tenantId) {
        List<IamPermission> permissions = permissionRepository.findTree(tenantId);
        return permissionConverter.toDTOList(permissions);
    }

    @Override
    public List<IamPermissionDTO> listPermissionsByType(String permType, String tenantId) {
        List<IamPermission> permissions = permissionRepository.findByType(permType, tenantId);
        return permissionConverter.toDTOList(permissions);
    }

    @Override
    public List<IamPermissionDTO> listPermissionsByUserId(Long userId, String tenantId) {
        List<IamPermission> permissions = permissionRepository.findByUserId(userId.toString(), tenantId);
        return permissionConverter.toDTOList(permissions);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enablePermission(Long id, String tenantId) {
        IamPermission permission = permissionRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("权限不存在: " + id));
        permission.enable();
        permissionRepository.update(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disablePermission(Long id, String tenantId) {
        IamPermission permission = permissionRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("权限不存在: " + id));
        permission.disable();
        permissionRepository.update(permission);
    }
}
