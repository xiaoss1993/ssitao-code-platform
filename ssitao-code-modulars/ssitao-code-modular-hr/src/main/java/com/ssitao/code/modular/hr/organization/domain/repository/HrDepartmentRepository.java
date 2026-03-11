package com.ssitao.code.modular.hr.organization.domain.repository;

import com.ssitao.code.modular.hr.organization.domain.model.HrDepartment;

import java.util.List;
import java.util.Optional;

/**
 * 部门仓储接口
 *
 * @author ssitao
 */
public interface HrDepartmentRepository {

    /**
     * 根据ID查询
     */
    Optional<HrDepartment> findById(String id, String tenantId);

    /**
     * 查询所有
     */
    List<HrDepartment> findAll(String tenantId);

    /**
     * 查询树形结构
     */
    List<HrDepartment> findTree(String tenantId);

    /**
     * 根据编码查询
     */
    boolean existsByDeptCode(String deptCode, String tenantId, String excludeId);

    /**
     * 保存
     */
    String save(HrDepartment department);

    /**
     * 更新
     */
    void update(HrDepartment department);

    /**
     * 删除
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据父ID查询
     */
    List<HrDepartment> findByParentId(String parentId, String tenantId);
}
