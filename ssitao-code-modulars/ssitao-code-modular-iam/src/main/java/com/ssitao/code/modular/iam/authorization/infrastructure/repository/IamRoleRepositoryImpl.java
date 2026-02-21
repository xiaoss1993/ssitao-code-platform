package com.ssitao.code.modular.iam.authorization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.authorization.domain.model.IamRole;
import com.ssitao.code.modular.iam.authorization.domain.repository.IamRoleRepository;
import com.ssitao.code.modular.iam.authorization.infrastructure.converter.IamRoleConverter;
import com.ssitao.code.modular.iam.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.dal.dataobject.IamRolePermissionDO;
import com.ssitao.code.modular.iam.dal.mapper.IamRoleMapper;
import com.ssitao.code.modular.iam.dal.mapper.IamRolePermissionMapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamRoleMapper roleMapper;

    @Resource
    private IamRoleConverter roleConverter;

    @Resource
    private IamRolePermissionMapper rolePermissionMapper;

    @Override
    public String save(IamRole role) {
        IamRoleDO roleDO = roleConverter.toDO(role);
        roleDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        roleMapper.insert(roleDO);
        return roleDO.getTbIamRoleId();
    }

    @Override
    public void update(IamRole role) {
        IamRoleDO roleDO = roleConverter.toDO(role);
        roleMapper.update(roleDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_role_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        roleMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamRole> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_role_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        IamRoleDO roleDO = roleMapper.selectOneByQuery(query);
        return Optional.ofNullable(roleConverter.toDomain(roleDO));
    }

    @Override
    public Optional<IamRole> findByRoleCode(String roleCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("role_code", roleCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamRoleDO roleDO = roleMapper.selectOneByQuery(query);
        return Optional.ofNullable(roleConverter.toDomain(roleDO));
    }

    @Override
    public List<IamRole> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
        List<IamRoleDO> list = roleMapper.selectListByQuery(query);
        return roleConverter.toDomainList(list);
    }

    @Override
    public List<IamRole> findTree(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_parent", true)
                .orderBy("sy_orderindex", true);
        List<IamRoleDO> list = roleMapper.selectListByQuery(query);
        return roleConverter.toDomainList(list);
    }

    @Override
    public List<IamRole> findByUserId(String userId, String tenantId) {
        // 通过关联表查询
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_userrole_id", userId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        List<IamRoleDO> list = roleMapper.selectListByQuery(query);
        return roleConverter.toDomainList(list);
    }

    @Override
    public boolean existsByRoleCode(String roleCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("role_code", roleCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_role_id", excludeId);
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
            rolePerm.setTbIamRoleId(roleId);
            rolePerm.setTbIamPermId(permissionId);
            rolePerm.setSyTenantId(tenantId);
            rolePerm.setSyCreatetime(java.time.LocalDateTime.now().format(DATE_FORMATTER));
            rolePerm.setSyStatus("1");
            rolePermissionMapper.insert(rolePerm);
        }
    }

    @Override
    public void removePermissions(String roleId, List<String> permissionIds, String tenantId) {
        // 实现移除权限逻辑
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_role_id", roleId);

        if (permissionIds != null && !permissionIds.isEmpty()) {
            query.and("tb_iam_perm_id", "in", permissionIds);
        }

        if (tenantId != null && !tenantId.isEmpty()) {
            query.and("sy_tenant_id", tenantId);
        }

        rolePermissionMapper.deleteByQuery(query);
    }

}
