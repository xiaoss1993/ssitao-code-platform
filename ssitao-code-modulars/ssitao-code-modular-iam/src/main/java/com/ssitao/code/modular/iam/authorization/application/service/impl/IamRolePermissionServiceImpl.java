package com.ssitao.code.modular.iam.authorization.application.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.application.service.IamRolePermissionService;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermissionDO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRolePermissionDO;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamPermissionMapper;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamRoleMapper;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamRolePermissionMapper;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamPermissionConverter;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamRoleConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM角色权限关联服务实现
 * 管理 iam_role_permission 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamRolePermissionServiceImpl implements IamRolePermissionService {

    @Resource
    private IamRolePermissionMapper rolePermissionMapper;

    @Resource
    private IamPermissionMapper permissionMapper;

    @Resource
    private IamRoleMapper roleMapper;

    @Resource
    private IamPermissionConverter permissionConverter;

    @Resource
    private IamRoleConverter roleConverter;

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer IS_VALID = 1;
    private static final Integer NOT_DELETED = 0;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignPermissions(String roleId, List<String> permissionIds, String tenantId) {
        if (roleId == null || roleId.isEmpty()) {
            throw new IllegalArgumentException("角色ID不能为空");
        }
        if (CollectionUtils.isEmpty(permissionIds)) {
            throw new IllegalArgumentException("权限ID列表不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        // 验证角色是否存在
        IamRoleDO role = roleMapper.selectOneById(roleId);
        if (role == null) {
            throw new IllegalArgumentException("角色不存在: " + roleId);
        }

        // 查询权限信息并验证
        QueryWrapper permissionQueryWrapper = QueryWrapper.create()
                .eq("tenant_id", tenantId)
                .eq("permission_status", STATUS_ACTIVE)
                .eq("is_deleted", NOT_DELETED);
        List<IamPermissionDO> permissionDOList = permissionMapper.selectListByQuery(permissionQueryWrapper);
        List<String> validPermissionIds = permissionDOList.stream()
                .map(IamPermissionDO::getPermissionId)
                .collect(Collectors.toList());

        // 过滤掉无效的权限ID
        List<String> validAssignPermissionIds = permissionIds.stream()
                .filter(validPermissionIds::contains)
                .collect(Collectors.toList());

        if (validAssignPermissionIds.isEmpty()) {
            throw new IllegalArgumentException("没有有效的权限ID");
        }

        // 获取现有权限关联
        QueryWrapper existQueryWrapper = QueryWrapper.create()
                .eq("role_id", roleId)
                .eq("tenant_id", tenantId)
                .eq("is_valid", IS_VALID)
                .eq("is_deleted", NOT_DELETED);
        List<IamRolePermissionDO> existRelations = rolePermissionMapper.selectListByQuery(existQueryWrapper);
        List<String> existPermissionIds = existRelations.stream()
                .map(IamRolePermissionDO::getPermissionId)
                .collect(Collectors.toList());

        // 只插入新的关联关系
        List<IamRolePermissionDO> newRelations = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (String permissionId : validAssignPermissionIds) {
            if (!existPermissionIds.contains(permissionId)) {
                IamRolePermissionDO relation = new IamRolePermissionDO();
                relation.setId(UUID.randomUUID().toString().replace("-", ""));
                relation.setRoleId(roleId);
                relation.setPermissionId(permissionId);
                relation.setIsValid(IS_VALID);
                relation.setTenantId(tenantId);
                relation.setCreateTime(now);
                relation.setIsDeleted(NOT_DELETED);
                newRelations.add(relation);
            }
        }

        if (!newRelations.isEmpty()) {
            rolePermissionMapper.insertBatch(newRelations);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void revokePermissions(String roleId, List<String> permissionIds, String tenantId) {
        if (roleId == null || roleId.isEmpty()) {
            throw new IllegalArgumentException("角色ID不能为空");
        }
        if (CollectionUtils.isEmpty(permissionIds)) {
            throw new IllegalArgumentException("权限ID列表不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        // 构建删除条件
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("role_id", roleId)
                .in("permission_id", permissionIds)
                .eq("tenant_id", tenantId);

        rolePermissionMapper.deleteByQuery(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void revokeAllPermissions(String roleId, String tenantId) {
        if (roleId == null || roleId.isEmpty()) {
            throw new IllegalArgumentException("角色ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("role_id", roleId)
                .eq("tenant_id", tenantId);

        rolePermissionMapper.deleteByQuery(queryWrapper);
    }

    @Override
    public List<IamPermissionDTO> getRolePermissions(String roleId, String tenantId) {
        if (roleId == null || roleId.isEmpty()) {
            throw new IllegalArgumentException("角色ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("role_id", roleId)
                .eq("tenant_id", tenantId)
                .eq("is_valid", IS_VALID)
                .eq("is_deleted", NOT_DELETED);

        List<IamRolePermissionDO> rolePermissionList = rolePermissionMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(rolePermissionList)) {
            return new ArrayList<>();
        }

        List<String> permissionIds = rolePermissionList.stream()
                .map(IamRolePermissionDO::getPermissionId)
                .collect(Collectors.toList());

        QueryWrapper permissionQueryWrapper = QueryWrapper.create()
                .in("permission_id", permissionIds)
                .eq("tenant_id", tenantId)
                .eq("permission_status", STATUS_ACTIVE)
                .eq("is_deleted", NOT_DELETED);

        List<IamPermissionDO> permissionDOList = permissionMapper.selectListByQuery(permissionQueryWrapper);

        return permissionConverter.toDTOList(permissionConverter.toDomainList(permissionDOList));
    }

    @Override
    public List<IamRoleDTO> getPermissionRoles(String permissionId, String tenantId) {
        if (permissionId == null || permissionId.isEmpty()) {
            throw new IllegalArgumentException("权限ID不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("permission_id", permissionId)
                .eq("tenant_id", tenantId)
                .eq("is_valid", IS_VALID)
                .eq("is_deleted", NOT_DELETED);

        List<IamRolePermissionDO> rolePermissionList = rolePermissionMapper.selectListByQuery(queryWrapper);

        if (CollectionUtils.isEmpty(rolePermissionList)) {
            return new ArrayList<>();
        }

        List<String> roleIds = rolePermissionList.stream()
                .map(IamRolePermissionDO::getRoleId)
                .collect(Collectors.toList());

        QueryWrapper roleQueryWrapper = QueryWrapper.create()
                .in("role_id", roleIds)
                .eq("tenant_id", tenantId)
                .eq("role_status", STATUS_ACTIVE)
                .eq("is_deleted", NOT_DELETED);

        List<IamRoleDO> roleDOList = roleMapper.selectListByQuery(roleQueryWrapper);

        return roleConverter.toDTOList(roleConverter.toDomainList(roleDOList));
    }

    @Override
    public boolean checkPermission(String roleId, String permissionId, String tenantId) {
        if (roleId == null || roleId.isEmpty()) {
            return false;
        }
        if (permissionId == null || permissionId.isEmpty()) {
            return false;
        }
        if (tenantId == null || tenantId.isEmpty()) {
            return false;
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("role_id", roleId)
                .eq("permission_id", permissionId)
                .eq("tenant_id", tenantId)
                .eq("is_valid", IS_VALID)
                .eq("is_deleted", NOT_DELETED);

        IamRolePermissionDO relation = rolePermissionMapper.selectOneByQuery(queryWrapper);

        return relation != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAssignPermissions(List<String> roleIds, List<String> permissionIds, String tenantId) {
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new IllegalArgumentException("角色ID列表不能为空");
        }
        if (CollectionUtils.isEmpty(permissionIds)) {
            throw new IllegalArgumentException("权限ID列表不能为空");
        }
        if (tenantId == null || tenantId.isEmpty()) {
            throw new IllegalArgumentException("租户ID不能为空");
        }

        // 验证所有角色是否存在
        QueryWrapper roleQueryWrapper = QueryWrapper.create()
                .in("role_id", roleIds)
                .eq("tenant_id", tenantId)
                .eq("role_status", STATUS_ACTIVE)
                .eq("is_deleted", NOT_DELETED);
        List<IamRoleDO> roleDOList = roleMapper.selectListByQuery(roleQueryWrapper);
        if (roleDOList.size() != roleIds.size()) {
            throw new IllegalArgumentException("部分角色不存在或已被禁用");
        }

        // 验证权限是否存在
        QueryWrapper permissionQueryWrapper = QueryWrapper.create()
                .in("permission_id", permissionIds)
                .eq("tenant_id", tenantId)
                .eq("permission_status", STATUS_ACTIVE)
                .eq("is_deleted", NOT_DELETED);
        List<IamPermissionDO> permissionDOList = permissionMapper.selectListByQuery(permissionQueryWrapper);
        List<String> validPermissionIds = permissionDOList.stream()
                .map(IamPermissionDO::getPermissionId)
                .collect(Collectors.toList());

        if (validPermissionIds.isEmpty()) {
            throw new IllegalArgumentException("没有有效的权限ID");
        }

        // 获取所有现有的关联关系
        QueryWrapper existQueryWrapper = QueryWrapper.create()
                .in("role_id", roleIds)
                .eq("tenant_id", tenantId)
                .eq("is_valid", IS_VALID)
                .eq("is_deleted", NOT_DELETED);
        List<IamRolePermissionDO> existRelations = rolePermissionMapper.selectListByQuery(existQueryWrapper);

        // 构建已存在的关联关系key集合
        List<String> existKeys = existRelations.stream()
                .map(r -> r.getRoleId() + "_" + r.getPermissionId())
                .collect(Collectors.toList());

        // 批量创建新的关联关系
        List<IamRolePermissionDO> newRelations = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (String roleId : roleIds) {
            for (String permissionId : validPermissionIds) {
                String key = roleId + "_" + permissionId;
                if (!existKeys.contains(key)) {
                    IamRolePermissionDO relation = new IamRolePermissionDO();
                    relation.setId(UUID.randomUUID().toString().replace("-", ""));
                    relation.setRoleId(roleId);
                    relation.setPermissionId(permissionId);
                    relation.setIsValid(IS_VALID);
                    relation.setTenantId(tenantId);
                    relation.setCreateTime(now);
                    relation.setIsDeleted(NOT_DELETED);
                    newRelations.add(relation);
                }
            }
        }

        if (!newRelations.isEmpty()) {
            // 分批插入，每批1000条
            int batchSize = 1000;
            for (int i = 0; i < newRelations.size(); i += batchSize) {
                int end = Math.min(i + batchSize, newRelations.size());
                List<IamRolePermissionDO> batch = newRelations.subList(i, end);
                rolePermissionMapper.insertBatch(batch);
            }
        }
    }
}
