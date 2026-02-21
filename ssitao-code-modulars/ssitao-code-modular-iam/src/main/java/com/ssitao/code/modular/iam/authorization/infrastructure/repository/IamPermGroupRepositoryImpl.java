package com.ssitao.code.modular.iam.authorization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.authorization.domain.model.IamPermGroup;
import com.ssitao.code.modular.iam.authorization.domain.repository.IamPermGroupRepository;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamPermGroupConverter;
import com.ssitao.code.modular.iam.dal.dataobject.IamPermGroupDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamPermGroupPermDO;
import com.ssitao.code.modular.iam.dal.mapper.IamPermGroupMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamPermGroupPermMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * IAM权限组仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamPermGroupRepositoryImpl implements IamPermGroupRepository {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamPermGroupMapper permGroupMapper;

    @Resource
    private IamPermGroupPermMapper permGroupPermMapper;

    @Resource
    private IamPermGroupConverter permGroupConverter;

    @Override
    public String save(IamPermGroup permGroup) {
        IamPermGroupDO permGroupDO = permGroupConverter.toDO(permGroup);
        permGroupDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        permGroupDO.setSyStatus(Boolean.TRUE.equals(permGroup.getStatus()) ? "1" : "0");
        permGroupMapper.insert(permGroupDO);
        return permGroupDO.getTbIamPermgroupId();
    }

    @Override
    public void update(IamPermGroup permGroup) {
        IamPermGroupDO permGroupDO = permGroupConverter.toDO(permGroup);
        permGroupMapper.update(permGroupDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_permgroup_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        permGroupMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamPermGroup> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_permgroup_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
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
                .eq("permgroup_code", groupCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamPermGroupDO permGroupDO = permGroupMapper.selectOneByQuery(query);
        if (permGroupDO != null) {
            IamPermGroup permGroup = permGroupConverter.toDomain(permGroupDO);
            Set<String> permissionIds = getPermissionIds(permGroupDO.getTbIamPermgroupId(), tenantId);
            permGroup.setPermissionIds(permissionIds);
            return Optional.of(permGroup);
        }
        return Optional.empty();
    }

    @Override
    public List<IamPermGroup> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
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
                .eq("permgroup_code", groupCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_permgroup_id", excludeId);
        }
        return permGroupMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public Set<String> getPermissionIds(String groupId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_permgroup_id", groupId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        List<IamPermGroupPermDO> list = permGroupPermMapper.selectListByQuery(query);
        Set<String> permissionIds = new HashSet<>();
        for (IamPermGroupPermDO permGroupPermDO : list) {
            permissionIds.add(permGroupPermDO.getTbIamPermId());
        }
        return permissionIds;
    }

    @Override
    public void assignPermissions(String groupId, Set<String> permissionIds, String tenantId) {
        // 先删除已有关联
        QueryWrapper deleteQuery = QueryWrapper.create()
                .eq("tb_iam_permgroup_id", groupId);
        if (tenantId != null && !tenantId.isEmpty()) {
            deleteQuery.eq("sy_tenant_id", tenantId);
        }
        permGroupPermMapper.deleteByQuery(deleteQuery);

        // 批量插入新关联
        if (permissionIds != null && !permissionIds.isEmpty()) {
            String now = LocalDateTime.now().format(DATE_FORMATTER);
            for (String permissionId : permissionIds) {
                IamPermGroupPermDO permGroupPermDO = new IamPermGroupPermDO();
                permGroupPermDO.setTbIamPermgroupId(groupId);
                permGroupPermDO.setTbIamPermId(permissionId);
                permGroupPermDO.setSyTenantId(tenantId);
                permGroupPermDO.setSyCreatetime(now);
                permGroupPermDO.setSyStatus("1");
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
                .eq("tb_iam_permgroup_id", groupId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.in("tb_iam_perm_id", permissionIds);
        permGroupPermMapper.deleteByQuery(query);
    }
}
