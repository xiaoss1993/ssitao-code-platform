package com.ssitao.code.modular.iam.organization.application.service;

import com.ssitao.code.modular.iam.organization.application.command.IamDepartmentCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamDepartmentUpdateCommand;
import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;

import java.util.List;

/**
 * IAM部门应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamDepartmentAppService {

    /**
     * 创建部门
     *
     * @param command 创建命令
     * @return 部门ID
     */
    Long createDepartment(IamDepartmentCreateCommand command);

    /**
     * 批量创建部门
     *
     * @param commands 创建命令列表
     * @return 部门ID列表
     */
    List<Long> batchCreateDepartments(List<IamDepartmentCreateCommand> commands);

    /**
     * 更新部门
     *
     * @param command 更新命令
     */
    void updateDepartment(IamDepartmentUpdateCommand command);

    /**
     * 删除部门
     *
     * @param id       部门ID
     * @param tenantId 租户ID
     */
    void deleteDepartment(Long id, String tenantId);

    /**
     * 根据ID获取部门
     *
     * @param id       部门ID
     * @param tenantId 租户ID
     * @return 部门DTO
     */
    IamDepartmentDTO getDepartmentById(Long id, String tenantId);

    /**
     * 获取部门列表
     *
     * @param tenantId 租户ID
     * @return 部门列表
     */
    List<IamDepartmentDTO> listDepartments(String tenantId);

    /**
     * 获取部门树
     *
     * @param tenantId 租户ID
     * @return 部门树列表
     */
    List<IamDepartmentDTO> getDepartmentTree(String tenantId);

    /**
     * 启用部门
     *
     * @param id       部门ID
     * @param tenantId 租户ID
     */
    void enableDepartment(Long id, String tenantId);

    /**
     * 禁用部门
     *
     * @param id       部门ID
     * @param tenantId 租户ID
     */
    void disableDepartment(Long id, String tenantId);

}
