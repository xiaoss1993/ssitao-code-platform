package com.ssitao.code.modular.hr.organization.application.service;

import com.ssitao.code.modular.hr.organization.api.dto.HrDepartmentDTO;
import com.ssitao.code.modular.hr.organization.application.command.HrDepartmentCreateCommand;
import com.ssitao.code.modular.hr.organization.application.command.HrDepartmentUpdateCommand;

import java.util.List;

/**
 * 部门应用服务接口
 *
 * @author ssitao
 */
public interface HrDepartmentAppService {

    /**
     * 创建部门
     */
    String createDepartment(HrDepartmentCreateCommand command);

    /**
     * 更新部门
     */
    void updateDepartment(HrDepartmentUpdateCommand command);

    /**
     * 删除部门
     */
    void deleteDepartment(String id, String tenantId);

    /**
     * 获取部门详情
     */
    HrDepartmentDTO getDepartmentById(String id, String tenantId);

    /**
     * 获取部门列表
     */
    List<HrDepartmentDTO> listDepartments(String tenantId);

    /**
     * 获取部门树
     */
    List<HrDepartmentDTO> getDepartmentTree(String tenantId);

    /**
     * 启用部门
     */
    void enableDepartment(String id, String tenantId);

    /**
     * 禁用部门
     */
    void disableDepartment(String id, String tenantId);
}
