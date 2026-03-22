package com.ssitao.code.modular.iam.application.service;

import com.ssitao.code.common.core.domain.entity.SysRole;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.common.utils.StringUtils;
import com.ssitao.code.common.utils.uuid.IdUtils;
import com.ssitao.code.modular.iam.api.dto.SysRoleDTO;
import com.ssitao.code.modular.iam.application.command.CreateRoleCommand;
import com.ssitao.code.modular.iam.application.command.DeleteRoleCommand;
import com.ssitao.code.modular.iam.application.command.UpdateRoleCommand;
import com.ssitao.code.modular.iam.domain.model.SysRoleAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysRoleRepository;
import com.ssitao.code.modular.iam.infrastructure.converter.SysRoleConverter;
import com.ssitao.code.modular.iam.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色应用服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleApplicationService {

    private final SysRoleRepository roleRepository;
    private final SysRoleConverter roleConverter;
    private final ISysRoleService roleService;

    /**
     * 查询角色列表
     */
    public List<SysRoleDTO> listRoles(SysRoleDTO query) {
        SysRole role = new SysRole();
        if (query != null && StringUtils.isNotEmpty(query.getRoleName())) {
            role.setRoleName(query.getRoleName());
        }
        if (query != null && StringUtils.isNotEmpty(query.getRoleKey())) {
            role.setRoleKey(query.getRoleKey());
        }
        if (query != null && StringUtils.isNotEmpty(query.getStatus())) {
            role.setStatus(query.getStatus());
        }
        List<SysRole> list = roleService.selectRoleList(role);
        return roleConverter.toDTOListFromRole(list);
    }

    /**
     * 根据ID查询角色
     */
    public SysRoleDTO getRoleById(Long roleId) {
        SysRoleAggregate aggregate = roleRepository.findOne(roleId);
        if (aggregate == null) {
            throw new ServiceException("角色不存在");
        }
        return roleConverter.toDTO(aggregate);
    }

    /**
     * 根据角色Key查询角色
     */
    public SysRoleDTO getRoleByRoleKey(String roleKey) {
        SysRoleAggregate aggregate = roleRepository.findByRoleKey(roleKey);
        if (aggregate == null) {
            return null;
        }
        return roleConverter.toDTO(aggregate);
    }

    /**
     * 创建角色
     */
    @Transactional
    public Long createRole(CreateRoleCommand command) {
        // 检查角色Key是否已存在
        if (roleRepository.existsByRoleKey(command.getRoleKey())) {
            throw new ServiceException("权限字符已存在");
        }

        // 生成角色ID
        Long roleId = System.nanoTime();

        // 创建角色聚合根
        SysRoleAggregate aggregate = new SysRoleAggregate();
        aggregate.createRole(
                roleId,
                command.getRoleName(),
                command.getRoleKey(),
                command.getRoleSort(),
                command.getDataScope(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );

        // 分配菜单权限
        if (command.getMenuIds() != null) {
            aggregate.assignMenus(command.getMenuIds(), ShiroUtils.getLoginName());
        }

        // 分配数据权限
        if (command.getDeptIds() != null) {
            aggregate.assignDataScope(command.getDeptIds(), ShiroUtils.getLoginName());
        }

        // 保存
        roleRepository.save(aggregate);

        log.info("创建角色: {}", roleId);
        return roleId;
    }

    /**
     * 更新角色
     */
    @Transactional
    public void updateRole(UpdateRoleCommand command) {
        SysRoleAggregate aggregate = roleRepository.findOne(command.getRoleId());
        if (aggregate == null) {
            throw new ServiceException("角色不存在");
        }

        // 更新角色信息
        aggregate.updateRole(
                command.getRoleName(),
                command.getRoleKey(),
                command.getRoleSort(),
                command.getDataScope(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );

        // 分配菜单权限
        if (command.getMenuIds() != null) {
            aggregate.assignMenus(command.getMenuIds(), ShiroUtils.getLoginName());
        }

        // 分配数据权限
        if (command.getDeptIds() != null) {
            aggregate.assignDataScope(command.getDeptIds(), ShiroUtils.getLoginName());
        }

        // 保存
        roleRepository.save(aggregate);

        log.info("更新角色: {}", command.getRoleId());
    }

    /**
     * 删除角色
     */
    @Transactional
    public void deleteRoles(DeleteRoleCommand command) {
        for (Long roleId : command.getRoleIds()) {
            SysRoleAggregate aggregate = roleRepository.findOne(roleId);
            if (aggregate != null) {
                if (aggregate.isAdmin()) {
                    throw new ServiceException("不允许删除管理员角色");
                }
                aggregate.markDeleted(ShiroUtils.getLoginName());
                roleRepository.save(aggregate);
                log.info("删除角色: {}", roleId);
            }
        }
    }

    /**
     * 修改角色状态
     */
    @Transactional
    public void changeStatus(Long roleId, String status) {
        SysRoleAggregate aggregate = roleRepository.findOne(roleId);
        if (aggregate == null) {
            throw new ServiceException("角色不存在");
        }

        if (aggregate.isAdmin() && "1".equals(status)) {
            throw new ServiceException("不允许停用管理员角色");
        }

        aggregate.changeStatus(status, ShiroUtils.getLoginName());
        roleRepository.save(aggregate);

        log.info("修改角色状态: {} -> {}", roleId, status);
    }

    /**
     * 分配菜单权限
     */
    @Transactional
    public void assignMenus(Long roleId, Long[] menuIds) {
        SysRoleAggregate aggregate = roleRepository.findOne(roleId);
        if (aggregate == null) {
            throw new ServiceException("角色不存在");
        }

        aggregate.assignMenus(menuIds, ShiroUtils.getLoginName());
        roleRepository.save(aggregate);

        log.info("分配菜单权限: {}", roleId);
    }
}
