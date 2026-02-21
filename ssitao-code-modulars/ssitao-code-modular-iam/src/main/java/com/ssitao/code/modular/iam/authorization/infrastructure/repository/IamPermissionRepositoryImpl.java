package com.ssitao.code.modular.iam.authorization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.authorization.domain.model.IamPermission;
import com.ssitao.code.modular.iam.authorization.domain.repository.IamPermissionRepository;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamPermissionConverter;
import com.ssitao.code.modular.iam.dal.dataobject.IamPermissionDO;
import com.ssitao.code.modular.iam.dal.mapper.IamPermissionMapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamPermissionMapper permissionMapper;

    @Resource
    private IamPermissionConverter permissionConverter;

    @Override
    public String save(IamPermission permission) {
        IamPermissionDO permissionDO = permissionConverter.toDO(permission);
        permissionDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        permissionMapper.insert(permissionDO);
        return permissionDO.getTbIamPermId();
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
        permissionMapper.update(permissionDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_perm_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        permissionMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamPermission> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_perm_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        IamPermissionDO permissionDO = permissionMapper.selectOneByQuery(query);
        return Optional.ofNullable(permissionConverter.toDomain(permissionDO));
    }

    @Override
    public Optional<IamPermission> findByPermCode(String permCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("perm_code", permCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamPermissionDO permissionDO = permissionMapper.selectOneByQuery(query);
        return Optional.ofNullable(permissionConverter.toDomain(permissionDO));
    }

    @Override
    public List<IamPermission> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public List<IamPermission> findTree(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public List<IamPermission> findByType(String permType, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("perm_type_code", permType);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public List<IamPermission> findByParentId(String parentId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("perm_target_id", parentId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public List<IamPermission> findByRoleId(String roleId, String tenantId) {
        // 通过角色权限关联表查询
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_role_id", roleId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public List<IamPermission> findByUserId(String userId, String tenantId) {
        // 通过用户-角色-权限关联查询
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_user_id", userId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
        List<IamPermissionDO> list = permissionMapper.selectListByQuery(query);
        return permissionConverter.toDomainList(list);
    }

    @Override
    public boolean existsByPermCode(String permCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("perm_code", permCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_perm_id", excludeId);
        }
        return permissionMapper.selectCountByQuery(query) > 0;
    }

}
