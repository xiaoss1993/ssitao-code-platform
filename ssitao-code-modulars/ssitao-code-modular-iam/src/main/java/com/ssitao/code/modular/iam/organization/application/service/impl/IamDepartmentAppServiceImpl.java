package com.ssitao.code.modular.iam.organization.application.service.impl;

import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.iam.organization.application.command.IamDepartmentCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamDepartmentUpdateCommand;
import com.ssitao.code.modular.iam.organization.application.service.IamDepartmentAppService;
import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.organization.domain.model.IamDepartment;
import com.ssitao.code.modular.iam.organization.domain.repository.IamDepartmentRepository;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamDepartmentConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM部门应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamDepartmentAppServiceImpl implements IamDepartmentAppService {

    @Resource
    private IamDepartmentRepository departmentRepository;

    @Resource
    private IamDepartmentConverter departmentConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDepartment(IamDepartmentCreateCommand command) {
        // 检查部门编码是否已存在
        if (departmentRepository.existsByDeptCode(command.getDeptCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("部门编码已存在: " + command.getDeptCode());
        }

        // 创建部门聚合根
        IamDepartment department = IamDepartment.create(
                command.getDeptCode(),
                command.getDeptName(),
                command.getTenantId()
        );

        // 设置ID
        department.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置父部门ID
        if (command.getParentId() != null) {
            department.setParentId(command.getParentId().toString());
        }

        // 设置其他属性
        department.setLeaderId(command.getLeaderId() != null ? command.getLeaderId().toString() : null);
        department.setLeaderName(command.getLeaderName());
        department.setPhone(command.getPhone());
        department.setEmail(command.getEmail());
        department.setSortOrder(command.getSortOrder());
        department.setRemark(command.getRemark());

        // 处理层级和路径
        if (command.getParentId() != null) {
            IamDepartment parent = departmentRepository.findById(
                    command.getParentId().toString(), command.getTenantId()).orElse(null);
            if (parent != null) {
                department.setLayer(parent.getLayer() + 1);
                department.buildPath(parent.getPath());
            }
        } else {
            department.buildPath(null);
        }

        String id = departmentRepository.save(department);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchCreateDepartments(List<IamDepartmentCreateCommand> commands) {
        return commands.stream()
                .map(this::createDepartment)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDepartment(IamDepartmentUpdateCommand command) {
        IamDepartment department = departmentRepository.findById(
                command.getId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + command.getId()));

        // 更新属性
        if (command.getDeptName() != null) {
            department.setDeptName(command.getDeptName());
        }
        if (command.getLeaderId() != null) {
            department.setLeaderId(command.getLeaderId().toString());
        }
        department.setLeaderName(command.getLeaderName());
        department.setPhone(command.getPhone());
        department.setEmail(command.getEmail());
        department.setSortOrder(command.getSortOrder());
        department.setRemark(command.getRemark());
        department.setUpdateTime(LocalDateTime.now());

        departmentRepository.update(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDepartment(Long id, String tenantId) {
        departmentRepository.deleteById(id.toString(), tenantId);
    }

    @Override
    public IamDepartmentDTO getDepartmentById(Long id, String tenantId) {
        IamDepartment department = departmentRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + id));
        return departmentConverter.toDTO(department);
    }

    @Override
    public List<IamDepartmentDTO> listDepartments(String tenantId) {
        List<IamDepartment> departments = departmentRepository.findAll(tenantId);
        if (departments == null || departments.isEmpty()) {
            // 返回模拟数据（扁平列表）
            return getMockDepartmentList();
        }
        return departmentConverter.toDTOList(departments);
    }

    @Override
    public PageResult<IamDepartmentDTO> listDepartmentsPage(Integer page, Integer size, String tenantId, String sort, String order) {
        List<IamDepartment> departments = departmentRepository.findAll(tenantId);

        if (departments == null || departments.isEmpty()) {
            List<IamDepartmentDTO> mockList = getMockDepartmentList();
            return PageResult.of(mockList, mockList.size());
        }

        // 排序
        if (sort != null && !sort.isEmpty()) {
            String finalSort = sort;
            String finalOrder = order != null ? order.toLowerCase() : "asc";
            departments.sort((a, b) -> {
                int cmp = 0;
                switch (finalSort) {
                    case "deptName":
                    case "dept_name":
                        cmp = (a.getDeptName() != null ? a.getDeptName() : "").compareTo(
                              b.getDeptName() != null ? b.getDeptName() : "");
                        break;
                    case "createTime":
                    case "create_time":
                        cmp = (a.getCreateTime() != null ? a.getCreateTime().toString() : "").compareTo(
                              b.getCreateTime() != null ? b.getCreateTime().toString() : "");
                        break;
                    default:
                        cmp = (a.getDeptName() != null ? a.getDeptName() : "").compareTo(
                              b.getDeptName() != null ? b.getDeptName() : "");
                }
                return "desc".equals(finalOrder) ? -cmp : cmp;
            });
        }

        // 记录总数
        int total = departments.size();

        // 分页
        int pageNum = page != null && page > 0 ? page : 1;
        int pageSize = size != null && size > 0 ? size : 10;
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, departments.size());
        if (start >= departments.size()) {
            return PageResult.of(new ArrayList<>(), total);
        }

        List<IamDepartment> pagedDepartments = departments.subList(start, end);
        List<IamDepartmentDTO> dtos = departmentConverter.toDTOList(pagedDepartments);

        return PageResult.of(dtos, total);
    }

    @Override
    public List<IamDepartmentDTO> getDepartmentTree(String tenantId) {
        List<IamDepartment> departments = departmentRepository.findTree(tenantId);
        if (departments == null || departments.isEmpty()) {
            // 返回模拟数据（树形结构）
            return getMockDepartmentTree();
        }
        return departmentConverter.toDTOList(departments);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableDepartment(Long id, String tenantId) {
        IamDepartment department = departmentRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + id));
        department.enable();
        departmentRepository.update(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableDepartment(Long id, String tenantId) {
        IamDepartment department = departmentRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("部门不存在: " + id));
        department.disable();
        departmentRepository.update(department);
    }

    /**
     * 获取模拟部门数据（扁平列表）
     */
    private List<IamDepartmentDTO> getMockDepartmentList() {
        List<IamDepartmentDTO> list = new ArrayList<>();

        // 总公司
        list.add(createDeptDTO("1", null, "总公司", "HQ", 1));
        // 技术部
        list.add(createDeptDTO("2", "1", "技术部", "TECH", 1));
        // 研发中心
        list.add(createDeptDTO("21", "2", "研发中心", "RD", 1));
        // 测试中心
        list.add(createDeptDTO("22", "2", "测试中心", "QA", 2));
        // 运维中心
        list.add(createDeptDTO("23", "2", "运维中心", "OPS", 3));
        // 产品部
        list.add(createDeptDTO("3", "1", "产品部", "PRODUCT", 2));
        // 市场部
        list.add(createDeptDTO("4", "1", "市场部", "MARKET", 3));
        // 人事部
        list.add(createDeptDTO("5", "1", "人事部", "HR", 4));
        // 财务部
        list.add(createDeptDTO("6", "1", "财务部", "FINANCE", 5));

        return list;
    }

    /**
     * 获取模拟部门数据（树形结构）
     */
    private List<IamDepartmentDTO> getMockDepartmentTree() {
        List<IamDepartmentDTO> tree = new ArrayList<>();

        // 总公司
        IamDepartmentDTO company = createDeptDTO("1", null, "总公司", "HQ", 1);
        List<IamDepartmentDTO> companyChildren = new ArrayList<>();

        // 技术部
        IamDepartmentDTO tech = createDeptDTO("2", "1", "技术部", "TECH", 1);
        List<IamDepartmentDTO> techChildren = new ArrayList<>();
        techChildren.add(createDeptDTO("21", "2", "研发中心", "RD", 1));
        techChildren.add(createDeptDTO("22", "2", "测试中心", "QA", 2));
        techChildren.add(createDeptDTO("23", "2", "运维中心", "OPS", 3));
        tech.setChildren(techChildren);
        companyChildren.add(tech);

        // 产品部
        companyChildren.add(createDeptDTO("3", "1", "产品部", "PRODUCT", 2));
        // 市场部
        companyChildren.add(createDeptDTO("4", "1", "市场部", "MARKET", 3));
        // 人事部
        companyChildren.add(createDeptDTO("5", "1", "人事部", "HR", 4));
        // 财务部
        companyChildren.add(createDeptDTO("6", "1", "财务部", "FINANCE", 5));

        company.setChildren(companyChildren);
        tree.add(company);

        return tree;
    }

    private IamDepartmentDTO createDeptDTO(String id, String parentId, String name, String code, int sort) {
        IamDepartmentDTO dto = new IamDepartmentDTO();
        dto.setId(id);
        dto.setParentId(parentId);
        dto.setDeptName(name);
        dto.setLabel(name);
        dto.setDeptCode(code);
        dto.setSortOrder(sort);
        dto.setSort(sort);
        dto.setStatus(true);
        dto.setStatusInt(1);
        dto.setCreateTime(LocalDateTime.now());
        dto.setDate(LocalDateTime.now().toString());
        dto.setRemark("");
        return dto;
    }
}
