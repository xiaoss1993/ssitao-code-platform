package com.ssitao.code.modular.iam.organization.application.service;

import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;

import java.util.List;

/**
 * IAM部门用户关联服务接口
 * 管理 tb_iam_department_user 表
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamDepartmentUserService {

    /**
     * 添加用户到部门
     */
    void addUsersToDepartment(String departmentId, List<String> userIds, String tenantId);

    /**
     * 从部门移除用户
     */
    void removeUsersFromDepartment(String departmentId, List<String> userIds, String tenantId);

    /**
     * 转移用户到其他部门
     */
    void transferUsers(String fromDepartmentId, String toDepartmentId, List<String> userIds, String tenantId);

    /**
     * 获取部门用户列表
     */
    List<IamUserProfileDTO> getDepartmentUsers(String departmentId, String tenantId);

    /**
     * 获取用户的部门列表
     */
    List<IamDepartmentDTO> getUserDepartments(String userId, String tenantId);

    /**
     * 获取用户的主部门
     */
    IamDepartmentDTO getUserMainDepartment(String userId, String tenantId);

    /**
     * 设置用户的主部门
     */
    void setUserMainDepartment(String userId, String departmentId, String tenantId);

}
