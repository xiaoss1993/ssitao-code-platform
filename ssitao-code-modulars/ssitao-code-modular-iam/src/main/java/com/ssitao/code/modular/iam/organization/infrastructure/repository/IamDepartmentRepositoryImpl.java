package com.ssitao.code.modular.iam.organization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamDepartmentDO;
import com.ssitao.code.modular.iam.dal.mapper.IamDepartmentMapper;
import com.ssitao.code.modular.iam.organization.domain.model.IamDepartment;
import com.ssitao.code.modular.iam.organization.domain.repository.IamDepartmentRepository;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamDepartmentConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * IAM部门仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamDepartmentRepositoryImpl implements IamDepartmentRepository {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamDepartmentMapper departmentMapper;

    @Resource
    private IamDepartmentConverter departmentConverter;

    @Override
    public String save(IamDepartment department) {
        IamDepartmentDO departmentDO = departmentConverter.toDO(department);
        departmentDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        departmentDO.setSyStatus("1");
        departmentMapper.insert(departmentDO);
        return departmentDO.getTbIamDepartmentId();
    }

    @Override
    public List<String> saveBatch(List<IamDepartment> departments) {
        List<String> ids = new ArrayList<>();
        if (departments == null || departments.isEmpty()) {
            return ids;
        }
        String now = LocalDateTime.now().format(DATE_FORMATTER);
        for (IamDepartment department : departments) {
            IamDepartmentDO departmentDO = departmentConverter.toDO(department);
            departmentDO.setSyCreatetime(now);
            departmentDO.setSyStatus("1");
            departmentMapper.insert(departmentDO);
            ids.add(departmentDO.getTbIamDepartmentId());
        }
        return ids;
    }

    @Override
    public void update(IamDepartment department) {
        IamDepartmentDO departmentDO = departmentConverter.toDO(department);
        departmentDO.setSyModifytime(LocalDateTime.now().format(DATE_FORMATTER));
        departmentMapper.update(departmentDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_department_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        departmentMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamDepartment> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_department_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamDepartmentDO departmentDO = departmentMapper.selectOneByQuery(query);
        return Optional.ofNullable(departmentConverter.toDomain(departmentDO));
    }

    @Override
    public Optional<IamDepartment> findByDeptCode(String deptCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("department_code", deptCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamDepartmentDO departmentDO = departmentMapper.selectOneByQuery(query);
        return Optional.ofNullable(departmentConverter.toDomain(departmentDO));
    }

    @Override
    public List<IamDepartment> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
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
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (parentId == null || parentId.isEmpty()) {
            query.eq("sy_parent", "");
        } else {
            query.eq("sy_parent", parentId);
        }
        query.orderBy("sy_orderindex", true);
        List<IamDepartmentDO> list = departmentMapper.selectListByQuery(query);
        return departmentConverter.toDomainList(list);
    }

    @Override
    public boolean existsByDeptCode(String deptCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("department_code", deptCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_department_id", excludeId);
        }
        return departmentMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public List<IamDepartment> findByLeaderId(String leaderId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("department_major_id", leaderId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_orderindex", true);
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
}
