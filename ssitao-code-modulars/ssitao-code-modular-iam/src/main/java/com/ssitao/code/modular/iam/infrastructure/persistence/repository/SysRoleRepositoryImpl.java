package com.ssitao.code.modular.iam.infrastructure.persistence.repository;

import com.ssitao.code.frame.aggregate.repository.AbstractAggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysRoleAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysRoleRepository;
import com.ssitao.code.modular.iam.infrastructure.persistence.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 角色仓储实现
 */
@Repository
public class SysRoleRepositoryImpl extends AbstractAggregateRepository<SysRoleAggregate, Long> implements SysRoleRepository {

    @Autowired
    private SysRoleMapper roleMapper;

    public SysRoleRepositoryImpl() {
        super(SysRoleAggregate.class);
    }

    @Override
    protected Collection<SysRoleAggregate> doSave(Collection<SysRoleAggregate> aggregates) {
        List<SysRoleAggregate> saved = new ArrayList<>();
        for (SysRoleAggregate aggregate : aggregates) {
            if (aggregate.isNew()) {
                roleMapper.insertRole(toEntity(aggregate));
            } else {
                roleMapper.updateRole(toEntity(aggregate));
            }
            saved.add(aggregate);
        }
        return saved;
    }

    @Override
    protected void doRemove(Collection<SysRoleAggregate> aggregates) {
        for (SysRoleAggregate aggregate : aggregates) {
            roleMapper.deleteRoleById(aggregate.getRoleId());
        }
    }

    @Override
    protected SysRoleAggregate doFindOne(Long id) {
        com.ssitao.code.common.core.domain.entity.SysRole role = roleMapper.selectRoleById(id);
        if (role == null) {
            return null;
        }
        return toAggregate(role);
    }

    @Override
    protected boolean doExists(Long id) {
        return roleMapper.selectRoleById(id) != null;
    }

    @Override
    protected List<Long> doFindAllIds() {
        List<com.ssitao.code.common.core.domain.entity.SysRole> roles = roleMapper.selectRoleList(new com.ssitao.code.common.core.domain.entity.SysRole());
        List<Long> ids = new ArrayList<>();
        if (roles != null) {
            for (com.ssitao.code.common.core.domain.entity.SysRole role : roles) {
                ids.add(role.getRoleId());
            }
        }
        return ids;
    }

    @Override
    protected List<SysRoleAggregate> doFindAll(Collection<Long> ids) {
        List<SysRoleAggregate> result = new ArrayList<>();
        for (Long id : ids) {
            SysRoleAggregate aggregate = doFindOne(id);
            if (aggregate != null) {
                result.add(aggregate);
            }
        }
        return result;
    }

    @Override
    protected long doCount() {
        return roleMapper.selectRoleList(new com.ssitao.code.common.core.domain.entity.SysRole()).size();
    }

    @Override
    public SysRoleAggregate findByRoleKey(String roleKey) {
        com.ssitao.code.common.core.domain.entity.SysRole role = roleMapper.checkRoleKeyUnique(roleKey);
        if (role == null) {
            return null;
        }
        return toAggregate(role);
    }

    @Override
    public boolean existsByRoleKey(String roleKey) {
        return roleMapper.checkRoleKeyUnique(roleKey) != null;
    }

    /**
     * 将聚合根转换为实体
     */
    private com.ssitao.code.common.core.domain.entity.SysRole toEntity(SysRoleAggregate aggregate) {
        com.ssitao.code.common.core.domain.entity.SysRole role = new com.ssitao.code.common.core.domain.entity.SysRole();
        role.setRoleId(aggregate.getRoleId());
        role.setRoleName(aggregate.getRoleName());
        role.setRoleKey(aggregate.getRoleKey());
        role.setRoleSort(aggregate.getRoleSort());
        role.setDataScope(aggregate.getDataScope());
        role.setStatus(aggregate.getStatus());
        role.setDelFlag(aggregate.getDelFlag());
        role.setCreateBy(aggregate.getCreateBy());
        role.setCreateTime(aggregate.getCreateTime());
        role.setUpdateBy(aggregate.getUpdateBy());
        role.setUpdateTime(aggregate.getUpdateTime());
        role.setRemark(aggregate.getRemark());
        role.setMenuIds(aggregate.getMenuIds());
        role.setDeptIds(aggregate.getDeptIds());
        return role;
    }

    /**
     * 将实体转换为聚合根
     */
    private SysRoleAggregate toAggregate(com.ssitao.code.common.core.domain.entity.SysRole role) {
        SysRoleAggregate aggregate = new SysRoleAggregate();
        aggregate.setRoleId(role.getRoleId());
        aggregate.setRoleName(role.getRoleName());
        aggregate.setRoleKey(role.getRoleKey());
        aggregate.setRoleSort(role.getRoleSort());
        aggregate.setDataScope(role.getDataScope());
        aggregate.setStatus(role.getStatus());
        aggregate.setDelFlag(role.getDelFlag());
        aggregate.setCreateBy(role.getCreateBy());
        aggregate.setCreateTime(role.getCreateTime());
        aggregate.setUpdateBy(role.getUpdateBy());
        aggregate.setUpdateTime(role.getUpdateTime());
        aggregate.setRemark(role.getRemark());
        aggregate.setMenuIds(role.getMenuIds());
        aggregate.setDeptIds(role.getDeptIds());
        aggregate.setPermissions(role.getPermissions());
        return aggregate;
    }
}
