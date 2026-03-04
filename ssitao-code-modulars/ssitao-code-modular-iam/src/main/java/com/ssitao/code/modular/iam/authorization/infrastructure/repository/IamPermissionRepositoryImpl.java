package com.ssitao.code.modular.iam.authorization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermissionDO;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamPermissionMapper;
import com.ssitao.code.modular.iam.authorization.domain.model.IamPermission;
import com.ssitao.code.modular.iam.authorization.domain.repository.IamPermissionRepository;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamPermissionConverter;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * IAM权限仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamPermissionRepositoryImpl implements IamPermissionRepository {

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamPermissionMapper permissionMapper;

    @Resource
    private IamPermissionConverter permissionConverter;

    @Override
    public String save(IamPermission permission) {
        IamPermissionDO permissionDO = permissionConverter.toDO(permission);
        permissionDO.setCreateTime(LocalDateTime.now());
        permissionDO.setIsDeleted(NOT_DELETED);
        permissionMapper.insert(permissionDO);
        return permissionDO.getPermissionId();
    }

    @Override
    public List<String> saveBatch(List<IamPermission> permissions) {
        List<String> ids = new java.util.ArrayList<>();
        for (IamPermission permission : permissions) {
            ids.add(save(permission));
        }
        return ids;
    }

    @Override
    public void update(IamPermission permission) {
        IamPermissionDO permissionDO = permissionConverter.toDO(permission);
        permissionDO.setModifyTime(LocalDateTime.now());
        permissionMapper.update(permissionDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("permission_id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        permissionMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamPermission> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("permission_id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        IamPermissionDO permissionDO = permissionMapper.selectOneByQuery(query);
        return Optional.ofNullable(permissionConverter.toDomain(permissionDO));
    }

    @Override
    public Optional<IamPermission> findByPermCode(String permCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("permission_code", permCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("permission_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        IamPermissionDO permissionDO = permissionMapper.selectOneByQuery(query);
        return Optional.ofNullable(permissionConverter.toDomain(permissionDO));
    }

    @Override
    public List<IamPermission> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("permission_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED)
             .orderBy("permission_sort", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public List<IamPermission> findTree(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("permission_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED)
             .orderBy("permission_sort", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public List<IamPermission> findByType(String permType, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("permission_type", permType);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("permission_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED)
             .orderBy("permission_sort", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public List<IamPermission> findByParentId(String parentId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("permission_resource", parentId);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("permission_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED)
             .orderBy("permission_sort", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public List<IamPermission> findByRoleId(String roleId, String tenantId) {
        // 通过角色权限关联表查询 - 简化实现，直接返回空列表
        // 实际查询应该通过关联表
        return new java.util.ArrayList<>();
    }

    @Override
    public List<IamPermission> findByUserId(String userId, String tenantId) {
        // 通过用户-角色-权限关联查询 - 简化实现，直接返回空列表
        // 实际查询应该通过关联表
        return new java.util.ArrayList<>();
    }

    @Override
    public boolean existsByPermCode(String permCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("permission_code", permCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("permission_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        if (excludeId != null) {
            query.ne("permission_id", excludeId);
        }
        return permissionMapper.selectCountByQuery(query) > 0;
    }

}
