package com.ssitao.code.modular.iam.organization.domain.repository;

import com.ssitao.code.modular.iam.organization.domain.model.IamDepartment;

import java.util.List;
import java.util.Optional;

/**
 * IAM部门仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamDepartmentRepository {

    /**
     * 保存部门
     *
     * @param department 部门聚合根
     * @return 部门ID
     */
    String save(IamDepartment department);

    /**
     * 批量保存部门
     *
     * @param departments 部门列表
     * @return 部门ID列表
     */
    List<String> saveBatch(List<IamDepartment> departments);

    /**
     * 更新部门
     *
     * @param department 部门聚合根
     */
    void update(IamDepartment department);

    /**
     * 根据ID删除部门
     *
     * @param id       部门ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据ID查找部门
     *
     * @param id       部门ID
     * @param tenantId 租户ID
     * @return 部门聚合根
     */
    Optional<IamDepartment> findById(String id, String tenantId);

    /**
     * 根据部门编码查找部门
     *
     * @param deptCode 部门编码
     * @param tenantId 租户ID
     * @return 部门聚合根
     */
    Optional<IamDepartment> findByDeptCode(String deptCode, String tenantId);

    /**
     * 查找所有部门
     *
     * @param tenantId 租户ID
     * @return 部门列表
     */
    List<IamDepartment> findAll(String tenantId);

    /**
     * 查找部门树
     *
     * @param tenantId 租户ID
     * @return 部门树列表
     */
    List<IamDepartment> findTree(String tenantId);

    /**
     * 根据父ID查找子部门
     *
     * @param parentId 父部门ID
     * @param tenantId 租户ID
     * @return 部门列表
     */
    List<IamDepartment> findByParentId(String parentId, String tenantId);

    /**
     * 检查部门编码是否存在
     *
     * @param deptCode  部门编码
     * @param tenantId  租户ID
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByDeptCode(String deptCode, String tenantId, String excludeId);

    /**
     * 根据负责人ID查找部门
     *
     * @param leaderId 负责人ID
     * @param tenantId 租户ID
     * @return 部门列表
     */
    List<IamDepartment> findByLeaderId(String leaderId, String tenantId);

}
