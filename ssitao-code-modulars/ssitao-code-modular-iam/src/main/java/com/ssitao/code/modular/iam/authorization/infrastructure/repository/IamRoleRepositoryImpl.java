package com.ssitao.code.modular.iam.authorization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRolePermissionDO;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamRoleMapper;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamRolePermissionMapper;
import com.ssitao.code.modular.iam.authorization.domain.model.IamRole;
import com.ssitao.code.modular.iam.authorization.domain.repository.IamRoleRepository;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamRoleConverter;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * IAM角色仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamRoleRepositoryImpl implements IamRoleRepository {

    /**
     * 未删除标记
     */
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamRoleMapper roleMapper;

    @Resource
    private IamRoleConverter roleConverter;

    @Resource
    private IamRolePermissionMapper rolePermissionMapper;

    @Override
    public String save(IamRole role) {
        IamRoleDO roleDO = roleConverter.toDO(role);
        roleDO.setCreateTime(LocalDateTime.now());
        roleMapper.insert(roleDO);
        return roleDO.getRoleId();
    }

    @Override
    public void update(IamRole role) {
        IamRoleDO roleDO = roleConverter.toDO(role);
        roleDO.setModifyTime(LocalDateTime.now());
        roleMapper.update(roleDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("role_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }
        roleMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamRole> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("is_deleted", NOT_DELETED)
                .eq("role_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }
        IamRoleDO roleDO = roleMapper.selectOneByQuery(query);
        return Optional.ofNullable(roleConverter.toDomain(roleDO));
    }

    @Override
    public Optional<IamRole> findByRoleCode(String roleCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("is_deleted", NOT_DELETED)
                .eq("role_code", roleCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("role_status", 1);
        IamRoleDO roleDO = roleMapper.selectOneByQuery(query);
        return Optional.ofNullable(roleConverter.toDomain(roleDO));
    }

    @Override
    public List<IamRole> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        query.eq("is_deleted", NOT_DELETED);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("role_status", 1)
                .orderBy("role_sort", true);
        List<IamRoleDO> list = roleMapper.selectListByQuery(query);
        return roleConverter.toDomainList(list);
    }

    @Override
    public List<IamRole> findTree(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        query.eq("is_deleted", NOT_DELETED);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("role_status", 1)
                .orderBy("role_level", true)
                .orderBy("role_sort", true);
        List<IamRoleDO> list = roleMapper.selectListByQuery(query);
        return roleConverter.toDomainList(list);
    }

    @Override
    public List<IamRole> findByUserId(String userId, String tenantId) {
        // 通过关联表查询
        QueryWrapper query = QueryWrapper.create()
                .eq("is_deleted", NOT_DELETED)
                .eq("account_id", userId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("role_status", 1);
        List<IamRoleDO> list = roleMapper.selectListByQuery(query);
        return roleConverter.toDomainList(list);
    }

    @Override
    public boolean existsByRoleCode(String roleCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("role_code", roleCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", 0);
        if (excludeId != null) {
            query.ne("role_id", excludeId);
        }
        return roleMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public void assignPermissions(String roleId, List<String> permissionIds, String tenantId) {
        // 实现分配权限逻辑
        if (permissionIds == null || permissionIds.isEmpty()) {
            return;
        }

        // 先删除该角色的所有权限
        removePermissions(roleId, null, tenantId);

        // 批量插入新的权限关联
        for (String permissionId : permissionIds) {
            IamRolePermissionDO rolePerm = new IamRolePermissionDO();
            rolePerm.setRoleId(roleId);
            rolePerm.setPermissionId(permissionId);
            rolePerm.setTenantId(tenantId);
            rolePerm.setCreateTime(LocalDateTime.now());
            rolePerm.setIsValid(1);
            rolePerm.setIsDeleted(0);
            rolePermissionMapper.insert(rolePerm);
        }
    }

    @Override
    public void removePermissions(String roleId, List<String> permissionIds, String tenantId) {
        // 实现移除权限逻辑
        QueryWrapper query = QueryWrapper.create()
                .eq("role_id", roleId);

        if (permissionIds != null && !permissionIds.isEmpty()) {
            query.and("permission_id", "in", permissionIds);
        }

        if (tenantId != null && !tenantId.isEmpty()) {
            query.and("tenant_id", tenantId);
        }

        rolePermissionMapper.deleteByQuery(query);
    }

}
