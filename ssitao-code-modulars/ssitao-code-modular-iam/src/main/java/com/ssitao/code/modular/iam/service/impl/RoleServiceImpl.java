package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.role.RoleCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.role.RoleListReqVO;
import com.ssitao.code.modular.iam.controller.vo.role.RoleUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.RoleDO;
import com.ssitao.code.modular.iam.dal.dataobject.RolePermissionDO;
import com.ssitao.code.modular.iam.dal.mapper.RoleMapper;
import com.ssitao.code.modular.iam.dal.mapper.RolePermissionMapper;
import com.ssitao.code.modular.iam.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createRole(RoleCreateReqVO createReqVO) {
        // 检查角色编码是否已存在
        RoleDO existRole = roleMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("code", createReqVO.getCode())
                        .eq("deleted", 0)
        );
        if (existRole != null) {
            throw new BusinessException("角色编码已存在: " + createReqVO.getCode());
        }

        // 创建角色
        RoleDO role = new RoleDO();
        role.setCode(createReqVO.getCode());
        role.setName(createReqVO.getName());
        role.setSort(createReqVO.getSort());
        role.setStatus(createReqVO.getStatus() != null ? createReqVO.getStatus() : 1);
        role.setRemark(createReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            role.setTenantId(loginUser.getTenantId());
            role.setCreator(loginUser.getUsername());
        }
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        role.setDeleted(0);

        roleMapper.insert(role);
        log.info("创建角色成功: code={}, name={}", role.getCode(), role.getName());
        return role.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleUpdateReqVO updateReqVO) {
        RoleDO role = roleMapper.selectOneById(updateReqVO.getId());
        if (role == null || role.getDeleted() == 1) {
            throw new NotFoundException("角色不存在");
        }

        // 检查角色编码是否已被其他角色使用
        if (!role.getCode().equals(updateReqVO.getCode())) {
            RoleDO existRole = roleMapper.selectOneByQuery(
                    QueryWrapper.create()
                            .eq("code", updateReqVO.getCode())
                            .ne("id", updateReqVO.getId())
                            .eq("deleted", 0)
            );
            if (existRole != null) {
                throw new BusinessException("角色编码已存在: " + updateReqVO.getCode());
            }
        }

        // 更新角色
        role.setCode(updateReqVO.getCode());
        role.setName(updateReqVO.getName());
        role.setSort(updateReqVO.getSort());
        if (updateReqVO.getStatus() != null) {
            role.setStatus(updateReqVO.getStatus());
        }
        role.setRemark(updateReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            role.setUpdater(loginUser.getUsername());
        }
        role.setUpdateTime(LocalDateTime.now());

        roleMapper.update(role);
        log.info("更新角色成功: id={}, code={}, name={}", role.getId(), role.getCode(), role.getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id) {
        RoleDO role = roleMapper.selectOneById(id);
        if (role == null || role.getDeleted() == 1) {
            throw new NotFoundException("角色不存在");
        }

        // 检查是否有用户关联此角色
        // TODO: 添加用户角色关联检查

        // 逻辑删除
        role.setDeleted(1);
        role.setUpdateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            role.setUpdater(loginUser.getUsername());
        }

        roleMapper.update(role);
        log.info("删除角色成功: id={}, code={}, name={}", role.getId(), role.getCode(), role.getName());
    }

    @Override
    public RoleDO getRole(Long id) {
        RoleDO role = roleMapper.selectOneById(id);
        if (role == null || role.getDeleted() == 1) {
            throw new NotFoundException("角色不存在");
        }
        return role;
    }

    @Override
    public List<RoleDO> getRoleList(RoleListReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("deleted", 0);

        if (reqVO.getCode() != null && !reqVO.getCode().isEmpty()) {
            queryWrapper.like("code", reqVO.getCode());
        }
        if (reqVO.getName() != null && !reqVO.getName().isEmpty()) {
            queryWrapper.like("name", reqVO.getName());
        }
        if (reqVO.getStatus() != null) {
            queryWrapper.eq("status", reqVO.getStatus());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("sort", true).orderBy("id", true);
        return roleMapper.selectListByQuery(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignPermissions(Long roleId, List<Long> permissionIds) {
        // 检查角色是否存在
        RoleDO role = roleMapper.selectOneById(roleId);
        if (role == null || role.getDeleted() == 1) {
            throw new NotFoundException("角色不存在");
        }

        // 删除角色原有权限关联
        rolePermissionMapper.deleteByQuery(
                QueryWrapper.create()
                        .eq("role_id", roleId)
        );

        // 批量插入新的权限关联
        if (permissionIds != null && !permissionIds.isEmpty()) {
            String tenantId = role.getTenantId();
            LocalDateTime now = LocalDateTime.now();

            for (Long permissionId : permissionIds) {
                RolePermissionDO rolePermission = new RolePermissionDO();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(permissionId);
                rolePermission.setTenantId(tenantId);
                rolePermission.setCreateTime(now);
                rolePermissionMapper.insert(rolePermission);
            }
        }

        log.info("分配角色权限成功: roleId={}, permissionCount={}", roleId,
                permissionIds != null ? permissionIds.size() : 0);
    }

    @Override
    public List<Long> getRolePermissions(Long roleId) {
        // 检查角色是否存在
        RoleDO role = roleMapper.selectOneById(roleId);
        if (role == null || role.getDeleted() == 1) {
            throw new NotFoundException("角色不存在");
        }

        // 查询角色权限关联
        List<RolePermissionDO> rolePermissions = rolePermissionMapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("role_id", roleId)
        );

        return rolePermissions.stream()
                .map(RolePermissionDO::getPermissionId)
                .collect(Collectors.toList());
    }

}
