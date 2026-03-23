package com.ssitao.code.modular.iam.infrastructure.persistence.repository;

import com.ssitao.code.frame.aggregate.repository.AbstractAggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysDeptAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysDeptRepository;
import com.ssitao.code.modular.iam.infrastructure.persistence.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 部门仓储实现
 */
@Repository
public class SysDeptRepositoryImpl extends AbstractAggregateRepository<SysDeptAggregate, Long> implements SysDeptRepository {

    @Autowired
    private SysDeptMapper deptMapper;

    public SysDeptRepositoryImpl() {
        super(SysDeptAggregate.class);
    }

    @Override
    protected Collection<SysDeptAggregate> doSave(Collection<SysDeptAggregate> aggregates) {
        List<SysDeptAggregate> saved = new ArrayList<>();
        for (SysDeptAggregate aggregate : aggregates) {
            if (aggregate.isNew()) {
                deptMapper.insertDept(toEntity(aggregate));
            } else {
                deptMapper.updateDept(toEntity(aggregate));
            }
            saved.add(aggregate);
        }
        return saved;
    }

    @Override
    protected void doRemove(Collection<SysDeptAggregate> aggregates) {
        for (SysDeptAggregate aggregate : aggregates) {
            deptMapper.deleteDeptById(aggregate.getDeptId());
        }
    }

    @Override
    protected SysDeptAggregate doFindOne(Long id) {
        com.ssitao.code.common.core.domain.entity.SysDept dept = deptMapper.selectDeptById(id);
        if (dept == null) {
            return null;
        }
        return toAggregate(dept);
    }

    @Override
    protected boolean doExists(Long id) {
        return deptMapper.selectDeptById(id) != null;
    }

    @Override
    protected List<Long> doFindAllIds() {
        List<com.ssitao.code.common.core.domain.entity.SysDept> depts = deptMapper.selectDeptList(new com.ssitao.code.common.core.domain.entity.SysDept());
        List<Long> ids = new ArrayList<>();
        if (depts != null) {
            for (com.ssitao.code.common.core.domain.entity.SysDept dept : depts) {
                ids.add(dept.getDeptId());
            }
        }
        return ids;
    }

    @Override
    protected List<SysDeptAggregate> doFindAll(Collection<Long> ids) {
        List<SysDeptAggregate> result = new ArrayList<>();
        for (Long id : ids) {
            SysDeptAggregate aggregate = doFindOne(id);
            if (aggregate != null) {
                result.add(aggregate);
            }
        }
        return result;
    }

    @Override
    protected long doCount() {
        return deptMapper.selectDeptList(new com.ssitao.code.common.core.domain.entity.SysDept()).size();
    }

    @Override
    public List<SysDeptAggregate> findByParentId(Long parentId) {
        List<com.ssitao.code.common.core.domain.entity.SysDept> depts = deptMapper.selectChildrenDeptById(parentId);
        List<SysDeptAggregate> aggregates = new ArrayList<>();
        if (depts != null) {
            for (com.ssitao.code.common.core.domain.entity.SysDept dept : depts) {
                aggregates.add(toAggregate(dept));
            }
        }
        return aggregates;
    }

    @Override
    public List<SysDeptAggregate> findAllNormal() {
        List<com.ssitao.code.common.core.domain.entity.SysDept> depts = deptMapper.selectDeptList(new com.ssitao.code.common.core.domain.entity.SysDept());
        List<SysDeptAggregate> aggregates = new ArrayList<>();
        if (depts != null) {
            for (com.ssitao.code.common.core.domain.entity.SysDept dept : depts) {
                aggregates.add(toAggregate(dept));
            }
        }
        return aggregates;
    }

    @Override
    public boolean existsByDeptNameAndParentId(String deptName, Long parentId) {
        return deptMapper.checkDeptNameUnique(deptName, parentId) != null;
    }

    /**
     * 将聚合根转换为实体
     */
    private com.ssitao.code.common.core.domain.entity.SysDept toEntity(SysDeptAggregate aggregate) {
        com.ssitao.code.common.core.domain.entity.SysDept dept = new com.ssitao.code.common.core.domain.entity.SysDept();
        dept.setDeptId(aggregate.getDeptId());
        dept.setParentId(aggregate.getParentId());
        dept.setAncestors(aggregate.getAncestors());
        dept.setDeptName(aggregate.getDeptName());
        dept.setOrderNum(aggregate.getOrderNum());
        dept.setLeader(aggregate.getLeader());
        dept.setPhone(aggregate.getPhone());
        dept.setEmail(aggregate.getEmail());
        dept.setStatus(aggregate.getStatus());
        dept.setDelFlag(aggregate.getDelFlag());
        dept.setCreateBy(aggregate.getCreateBy());
        dept.setCreateTime(aggregate.getCreateTime());
        dept.setUpdateBy(aggregate.getUpdateBy());
        dept.setUpdateTime(aggregate.getUpdateTime());
        dept.setRemark(aggregate.getRemark());
        return dept;
    }

    /**
     * 将实体转换为聚合根
     */
    private SysDeptAggregate toAggregate(com.ssitao.code.common.core.domain.entity.SysDept dept) {
        SysDeptAggregate aggregate = new SysDeptAggregate();
        aggregate.setDeptId(dept.getDeptId());
        aggregate.setParentId(dept.getParentId());
        aggregate.setAncestors(dept.getAncestors());
        aggregate.setDeptName(dept.getDeptName());
        aggregate.setOrderNum(dept.getOrderNum());
        aggregate.setLeader(dept.getLeader());
        aggregate.setPhone(dept.getPhone());
        aggregate.setEmail(dept.getEmail());
        aggregate.setStatus(dept.getStatus());
        aggregate.setDelFlag(dept.getDelFlag());
        aggregate.setCreateBy(dept.getCreateBy());
        aggregate.setCreateTime(dept.getCreateTime());
        aggregate.setUpdateBy(dept.getUpdateBy());
        aggregate.setUpdateTime(dept.getUpdateTime());
        aggregate.setRemark(dept.getRemark());
        return aggregate;
    }
}
