package com.ssitao.code.modular.hr.organization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.hr.organization.dal.dataobject.HrDepartmentDO;
import com.ssitao.code.modular.hr.organization.dal.mapper.HrDepartmentMapper;
import com.ssitao.code.modular.hr.organization.domain.model.HrDepartment;
import com.ssitao.code.modular.hr.organization.domain.repository.HrDepartmentRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 部门仓储实现
 *
 * @author ssitao
 */
@Repository
public class HrDepartmentRepositoryImpl implements HrDepartmentRepository {

    @Resource
    private HrDepartmentMapper departmentMapper;

    @Override
    public Optional<HrDepartment> findById(String id, String tenantId) {
        HrDepartmentDO hrDepartmentDO = departmentMapper.selectOneById(id);
        return Optional.ofNullable(toDomain(hrDepartmentDO));
    }

    @Override
    public List<HrDepartment> findAll(String tenantId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("tenant_id", tenantId).eq("deleted", 0);
        wrapper.orderBy("dept_sort", true);
        List<HrDepartmentDO> dos = departmentMapper.selectListByQuery(wrapper);
        return dos.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<HrDepartment> findTree(String tenantId) {
        List<HrDepartment> allDepts = findAll(tenantId);
        return buildTree(allDepts, "0");
    }

    @Override
    public boolean existsByDeptCode(String deptCode, String tenantId, String excludeId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("dept_code", deptCode).eq("tenant_id", tenantId).eq("deleted", 0);
        if (excludeId != null) {
            wrapper.ne("id", excludeId);
        }
        return departmentMapper.selectCountByQuery(wrapper) > 0;
    }

    @Override
    public String save(HrDepartment department) {
        HrDepartmentDO hrDepartmentDO = toDO(department);
        departmentMapper.insert(hrDepartmentDO);
        return hrDepartmentDO.getId();
    }

    @Override
    public void update(HrDepartment department) {
        HrDepartmentDO hrDepartmentDO = toDO(department);
        departmentMapper.update(hrDepartmentDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        HrDepartmentDO hrDepartmentDO = new HrDepartmentDO();
        hrDepartmentDO.setId(id);
        hrDepartmentDO.setDeleted(1);
        departmentMapper.update(hrDepartmentDO);
    }

    @Override
    public List<HrDepartment> findByParentId(String parentId, String tenantId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("parent_id", parentId).eq("tenant_id", tenantId).eq("deleted", 0);
        wrapper.orderBy("dept_sort", true);
        List<HrDepartmentDO> dos = departmentMapper.selectListByQuery(wrapper);
        return dos.stream().map(this::toDomain).collect(Collectors.toList());
    }

    /**
     * DO转Domain
     */
    private HrDepartment toDomain(HrDepartmentDO hrDepartmentDO) {
        if (hrDepartmentDO == null) {
            return null;
        }
        HrDepartment dept = new HrDepartment();
        dept.setId(hrDepartmentDO.getId());
        dept.setDeptCode(hrDepartmentDO.getDeptCode());
        dept.setDeptName(hrDepartmentDO.getDeptName());
        dept.setParentId(hrDepartmentDO.getParentId());
        dept.setDeptLevel(hrDepartmentDO.getDeptLevel());
        dept.setDeptPath(hrDepartmentDO.getDeptPath());
        dept.setDeptSort(hrDepartmentDO.getDeptSort());
        dept.setDeptLeader(hrDepartmentDO.getDeptLeader());
        dept.setPhone(hrDepartmentDO.getPhone());
        dept.setEmail(hrDepartmentDO.getEmail());
        dept.setStatus(hrDepartmentDO.getStatus());
        dept.setTenantId(hrDepartmentDO.getTenantId());
        dept.setCreator(hrDepartmentDO.getCreator());
        dept.setCreateTime(hrDepartmentDO.getCreateTime());
        dept.setUpdater(hrDepartmentDO.getUpdater());
        dept.setUpdateTime(hrDepartmentDO.getUpdateTime());
        dept.setDeleted(hrDepartmentDO.getDeleted());
        return dept;
    }

    /**
     * Domain转DO
     */
    private HrDepartmentDO toDO(HrDepartment department) {
        HrDepartmentDO hrDepartmentDO = new HrDepartmentDO();
        hrDepartmentDO.setId(department.getId());
        hrDepartmentDO.setDeptCode(department.getDeptCode());
        hrDepartmentDO.setDeptName(department.getDeptName());
        hrDepartmentDO.setParentId(department.getParentId());
        hrDepartmentDO.setDeptLevel(department.getDeptLevel());
        hrDepartmentDO.setDeptPath(department.getDeptPath());
        hrDepartmentDO.setDeptSort(department.getDeptSort());
        hrDepartmentDO.setDeptLeader(department.getDeptLeader());
        hrDepartmentDO.setPhone(department.getPhone());
        hrDepartmentDO.setEmail(department.getEmail());
        hrDepartmentDO.setStatus(department.getStatus());
        hrDepartmentDO.setTenantId(department.getTenantId());
        hrDepartmentDO.setCreator(department.getCreator());
        hrDepartmentDO.setCreateTime(department.getCreateTime());
        hrDepartmentDO.setUpdater(department.getUpdater());
        hrDepartmentDO.setUpdateTime(department.getUpdateTime());
        hrDepartmentDO.setDeleted(department.getDeleted());
        return hrDepartmentDO;
    }

    /**
     * 构建树形结构
     */
    private List<HrDepartment> buildTree(List<HrDepartment> allDepts, String parentId) {
        return allDepts.stream()
                .filter(dept -> parentId.equals(dept.getParentId()))
                .peek(dept -> dept.setChildren(buildTree(allDepts, dept.getId())))
                .collect(Collectors.toList());
    }
}
