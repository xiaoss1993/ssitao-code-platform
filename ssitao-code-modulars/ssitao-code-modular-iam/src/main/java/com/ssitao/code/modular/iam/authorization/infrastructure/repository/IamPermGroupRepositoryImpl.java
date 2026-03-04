package com.ssitao.code.modular.iam.authorization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermGroupDO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermGroupPermDO;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamPermGroupMapper;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamPermGroupPermMapper;
import com.ssitao.code.modular.iam.authorization.domain.model.IamPermGroup;
import com.ssitao.code.modular.iam.authorization.domain.repository.IamPermGroupRepository;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamPermGroupConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * IAM权限组仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamPermGroupRepositoryImpl implements IamPermGroupRepository {

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer IS_VALID = 1;
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamPermGroupMapper permGroupMapper;

    @Resource
    private IamPermGroupPermMapper permGroupPermMapper;

    @Resource
    private IamPermGroupConverter permGroupConverter;

    @Override
    public String save(IamPermGroup permGroup) {
        IamPermGroupDO permGroupDO = permGroupConverter.toDO(permGroup);
        permGroupDO.setCreateTime(LocalDateTime.now());
        permGroupDO.setGroupStatus(Boolean.TRUE.equals(permGroup.getStatus()) ? STATUS_ACTIVE : 0);
        permGroupDO.setIsDeleted(NOT_DELETED);
        permGroupMapper.insert(permGroupDO);
        return permGroupDO.getGroupId();
    }

    @Override
    public void update(IamPermGroup permGroup) {
        IamPermGroupDO permGroupDO = permGroupConverter.toDO(permGroup);
        permGroupDO.setModifyTime(LocalDateTime.now());
        permGroupMapper.update(permGroupDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("group_id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        permGroupMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamPermGroup> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("group_id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("group_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        IamPermGroupDO permGroupDO = permGroupMapper.selectOneByQuery(query);
        if (permGroupDO != null) {
            IamPermGroup permGroup = permGroupConverter.toDomain(permGroupDO);
            // 加载权限ID集合
            Set<String> permissionIds = getPermissionIds(id, tenantId);
            permGroup.setPermissionIds(permissionIds);
            return Optional.of(permGroup);
        }
        return Optional.empty();
    }

    @Override
    public Optional<IamPermGroup> findByGroupCode(String groupCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("group_code", groupCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("group_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        IamPermGroupDO permGroupDO = permGroupMapper.selectOneByQuery(query);
        if (permGroupDO != null) {
            IamPermGroup permGroup = permGroupConverter.toDomain(permGroupDO);
            Set<String> permissionIds = getPermissionIds(permGroupDO.getGroupId(), tenantId);
            permGroup.setPermissionIds(permissionIds);
            return Optional.of(permGroup);
        }
        return Optional.empty();
    }

    @Override
    public List<IamPermGroup> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("group_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED)
             .orderBy("group_sort", true);
        List<IamPermGroupDO> list = permGroupMapper.selectListByQuery(query);
        List<IamPermGroup> result = permGroupConverter.toDomainList(list);
        // 加载每个权限组的权限ID集合
        for (IamPermGroup permGroup : result) {
            Set<String> permissionIds = getPermissionIds(permGroup.getId(), tenantId);
            permGroup.setPermissionIds(permissionIds);
        }
        return result;
    }

    @Override
    public boolean existsByGroupCode(String groupCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("group_code", groupCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("group_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        if (excludeId != null) {
            query.ne("group_id", excludeId);
        }
        return permGroupMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public Set<String> getPermissionIds(String groupId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("group_id", groupId);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_valid", IS_VALID)
             .eq("is_deleted", NOT_DELETED);
        List<IamPermGroupPermDO> list = permGroupPermMapper.selectListByQuery(query);
        Set<String> permissionIds = new HashSet<>();
        for (IamPermGroupPermDO permGroupPermDO : list) {
            permissionIds.add(permGroupPermDO.getPermissionId());
        }
        return permissionIds;
    }

    @Override
    public void assignPermissions(String groupId, Set<String> permissionIds, String tenantId) {
        // 先删除已有关联
        QueryWrapper deleteQuery = QueryWrapper.create()
                .eq("group_id", groupId);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            deleteQuery.eq("tenant_id", tenantId);
        }
        permGroupPermMapper.deleteByQuery(deleteQuery);

        // 批量插入新关联
        if (permissionIds != null && !permissionIds.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            for (String permissionId : permissionIds) {
                IamPermGroupPermDO permGroupPermDO = new IamPermGroupPermDO();
                permGroupPermDO.setId(UUID.randomUUID().toString().replace("-", ""));
                permGroupPermDO.setGroupId(groupId);
                permGroupPermDO.setPermissionId(permissionId);
                permGroupPermDO.setTenantId(tenantId);
                permGroupPermDO.setCreateTime(now);
                permGroupPermDO.setIsValid(IS_VALID);
                permGroupPermDO.setIsDeleted(NOT_DELETED);
                permGroupPermMapper.insert(permGroupPermDO);
            }
        }
    }

    @Override
    public void removePermissions(String groupId, Set<String> permissionIds, String tenantId) {
        if (permissionIds == null || permissionIds.isEmpty()) {
            return;
        }
        QueryWrapper query = QueryWrapper.create()
                .eq("group_id", groupId);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.in("permission_id", permissionIds);
        permGroupPermMapper.deleteByQuery(query);
    }
}
