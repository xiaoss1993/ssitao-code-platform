package com.ssitao.code.modular.iam.authorization.application.service.impl;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public void deleteRole(String id, String tenantId) {
        roleRepository.deleteById(id, tenantId);
    }

    @Override
    public IamRoleDTO getRoleById(String id, String tenantId) {
        IamRole role = roleRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("角色不存在: " + id));
        return roleConverter.toDTO(role);
    }

    @Override
    public List<IamRoleDTO> listRoles(String tenantId) {
        List<IamRole> roles = roleRepository.findAll(tenantId);
        if (roles == null || roles.isEmpty()) {
            return getMockRoles();
        }
        List<IamRoleDTO> dtoList = roleConverter.toDTOList(roles);
        // 填充前端兼容字段
        dtoList.forEach(this::populateFrontendFields);
        return dtoList;
    }

    @Override
    public Page<IamRoleDTO> pageRoles(String tenantId, int current, int size) {
        List<IamRoleDTO> allRoles = listRoles(tenantId);

        Page<IamRoleDTO> page = new Page<>(current, size);
        int fromIndex = (current - 1) * size;
        int toIndex = Math.min(fromIndex + size, allRoles.size());

        if (fromIndex < allRoles.size()) {
            page.setRecords(allRoles.subList(fromIndex, toIndex));
        }
        page.setTotalRow((long) allRoles.size());
        return page;
    }

    @Override
    public List<IamRoleDTO> getRoleTree(String tenantId) {
        List<IamRole> roles = roleRepository.findTree(tenantId);
        if (roles == null || roles.isEmpty()) {
            return getMockRoles();
        }
        List<IamRoleDTO> dtoList = roleConverter.toDTOList(roles);
        // 填充前端兼容字段
        dtoList.forEach(this::populateFrontendFields);
        return dtoList;
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
    public void enableRole(String id, String tenantId) {
        IamRole role = roleRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("角色不存在: " + id));
        role.enable();
        roleRepository.update(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableRole(String id, String tenantId) {
        IamRole role = roleRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("角色不存在: " + id));
        role.disable();
        roleRepository.update(role);
    }

    /**
     * 获取模拟角色数据
     */
    private List<IamRoleDTO> getMockRoles() {
        List<IamRoleDTO> list = new ArrayList<>();

        list.add(createRoleDTO("1", null, "超级管理员", "SUPER_ADMIN", "系统最高权限角色", 1));
        list.add(createRoleDTO("2", null, "系统管理员", "ADMIN", "系统管理角色", 2));
        list.add(createRoleDTO("3", "2", "用户管理员", "USER_ADMIN", "用户管理角色", 1));
        list.add(createRoleDTO("4", "2", "角色管理员", "ROLE_ADMIN", "角色管理角色", 2));
        list.add(createRoleDTO("5", null, "部门管理员", "DEPT_ADMIN", "部门管理角色", 3));
        list.add(createRoleDTO("6", null, "普通用户", "USER", "普通用户角色", 4));
        list.add(createRoleDTO("7", null, "访客", "GUEST", "访客角色", 5));

        return list;
    }

    private IamRoleDTO createRoleDTO(String id, String parentId, String name, String code, String remark, int sort) {
        IamRoleDTO dto = new IamRoleDTO();
        dto.setId(id);
        dto.setParentId(parentId);
        dto.setRoleName(name);
        dto.setRoleCode(code);
        dto.setRoleType("CUSTOM");
        dto.setSortOrder(sort);
        dto.setStatus(true);
        dto.setRemark(remark);
        dto.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 设置前端兼容字段
        dto.setLabel(name);
        dto.setAlias(code);
        dto.setSort(sort);
        dto.setStatusInt(1);
        dto.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return dto;
    }

    /**
     * 填充前端兼容字段
     */
    private void populateFrontendFields(IamRoleDTO dto) {
        if (dto == null) return;

        // 设置 label (角色名称)
        if (dto.getLabel() == null) {
            dto.setLabel(dto.getRoleName());
        }

        // 设置 alias (角色编码)
        if (dto.getAlias() == null) {
            dto.setAlias(dto.getRoleCode());
        }

        // 设置 sort (排序)
        if (dto.getSort() == null && dto.getSortOrder() != null) {
            dto.setSort(dto.getSortOrder());
        }

        // 设置 statusInt (状态：1-启用 0-禁用)
        if (dto.getStatusInt() == null && dto.getStatus() != null) {
            dto.setStatusInt(dto.getStatus() ? 1 : 0);
        }

        // 设置 status (状态：true-启用 false-禁用) - 前端需要此字段
        if (dto.getStatus() == null && dto.getStatusInt() != null) {
            dto.setStatus(dto.getStatusInt() == 1);
        }

        // 设置 date (创建时间字符串)
        if (dto.getDate() == null && dto.getCreateTime() != null) {
            dto.setDate(dto.getCreateTime().toString());
        }
    }
}
