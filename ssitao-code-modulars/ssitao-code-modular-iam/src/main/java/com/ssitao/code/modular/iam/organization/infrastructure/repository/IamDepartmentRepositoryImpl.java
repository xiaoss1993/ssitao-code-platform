package com.ssitao.code.modular.iam.organization.infrastructure.repository;

import com.ssitao.code.frame.aggregate.domain.event.AbstractDomainEvent;
import com.ssitao.code.frame.aggregate.eventstore.EventStore;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamDepartmentDO;
import com.ssitao.code.modular.iam.organization.dal.mapper.IamDepartmentMapper;
import com.ssitao.code.modular.iam.organization.domain.model.IamDepartment;
import com.ssitao.code.modular.iam.organization.domain.repository.IamDepartmentRepository;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamDepartmentConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * IAM部门仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@Repository
public class IamDepartmentRepositoryImpl implements IamDepartmentRepository {

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamDepartmentMapper departmentMapper;

    @Resource
    private IamDepartmentConverter departmentConverter;

    @Resource
    private EventStore eventStore;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(IamDepartment department) {
        // 保存聚合根
        IamDepartmentDO departmentDO = departmentConverter.toDO(department);
        departmentDO.setCreateTime(LocalDateTime.now());
        departmentDO.setDeptStatus(STATUS_ACTIVE);
        departmentDO.setIsDeleted(NOT_DELETED);
        departmentMapper.insert(departmentDO);

        String deptId = departmentDO.getDeptId();
        department.setId(deptId);

        // 获取并保存领域事件
        saveDomainEvents(department);

        log.debug("保存部门聚合根 - 部门ID: {}, 部门编码: {}", deptId, department.getDeptCode());

        return deptId;
    }

    @Override
    public List<String> saveBatch(List<IamDepartment> departments) {
        List<String> ids = new ArrayList<>();
        if (departments == null || departments.isEmpty()) {
            return ids;
        }
        LocalDateTime now = LocalDateTime.now();
        for (IamDepartment department : departments) {
            IamDepartmentDO departmentDO = departmentConverter.toDO(department);
            departmentDO.setCreateTime(now);
            departmentDO.setDeptStatus(STATUS_ACTIVE);
            departmentDO.setIsDeleted(NOT_DELETED);
            departmentMapper.insert(departmentDO);
            ids.add(departmentDO.getDeptId());
        }
        return ids;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(IamDepartment department) {
        // 更新聚合根
        IamDepartmentDO departmentDO = departmentConverter.toDO(department);
        departmentDO.setModifyTime(LocalDateTime.now());
        departmentMapper.update(departmentDO);

        // 获取并保存领域事件
        saveDomainEvents(department);

        log.debug("更新部门聚合根 - 部门ID: {}, 部门编码: {}", department.getId(), department.getDeptCode());
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dept_id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        departmentMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamDepartment> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dept_id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        IamDepartmentDO departmentDO = departmentMapper.selectOneByQuery(query);
        return Optional.ofNullable(departmentConverter.toDomain(departmentDO));
    }

    @Override
    public Optional<IamDepartment> findByDeptCode(String deptCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dept_code", deptCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        IamDepartmentDO departmentDO = departmentMapper.selectOneByQuery(query);
        return Optional.ofNullable(departmentConverter.toDomain(departmentDO));
    }

    @Override
    public List<IamDepartment> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED)
             .orderBy("dept_sort", true);
        List<IamDepartmentDO> list = departmentMapper.selectListByQuery(query);
        return departmentConverter.toDomainList(list);
    }

    @Override
    public List<IamDepartment> findTree(String tenantId) {
        List<IamDepartment> allDepartments = findAll(tenantId);
        return buildTree(allDepartments, null);
    }

    @Override
    public List<IamDepartment> findByParentId(String parentId, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        if (parentId == null || parentId.isEmpty()) {
            query.eq("dept_parent_id", "");
        } else {
            query.eq("dept_parent_id", parentId);
        }
        query.orderBy("dept_sort", true);
        List<IamDepartmentDO> list = departmentMapper.selectListByQuery(query);
        return departmentConverter.toDomainList(list);
    }

    @Override
    public boolean existsByDeptCode(String deptCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dept_code", deptCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        if (excludeId != null) {
            query.ne("dept_id", excludeId);
        }
        return departmentMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public List<IamDepartment> findByLeaderId(String leaderId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dept_leader_id", leaderId);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED)
             .orderBy("dept_sort", true);
        List<IamDepartmentDO> list = departmentMapper.selectListByQuery(query);
        return departmentConverter.toDomainList(list);
    }

    /**
     * 构建部门树
     */
    private List<IamDepartment> buildTree(List<IamDepartment> departments, String parentId) {
        List<IamDepartment> tree = new ArrayList<>();
        for (IamDepartment dept : departments) {
            if ((parentId == null || parentId.isEmpty()) &&
                (dept.getParentId() == null || dept.getParentId().isEmpty())) {
                tree.add(dept);
            } else if (parentId != null && parentId.equals(dept.getParentId())) {
                tree.add(dept);
            }
        }
        for (IamDepartment parent : tree) {
            List<IamDepartment> children = buildTree(departments, parent.getId());
            parent.setChildren(children);
        }
        return tree;
    }

    /**
     * 保存领域事件
     *
     * @param aggregate 聚合根
     */
    private void saveDomainEvents(IamDepartment aggregate) {
        List<AbstractDomainEvent> events = aggregate.getAndClearDomainEvents();
        if (events != null && !events.isEmpty()) {
            eventStore.save(events);
            log.debug("保存 {} 个领域事件", events.size());
        }
    }
}
