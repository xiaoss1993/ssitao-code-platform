package com.ssitao.code.modular.iam.userprofile.application.service;

import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileCreateCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileQueryCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileUpdateCommand;

import java.util.List;

/**
 * IAM用户档案应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamUserProfileAppService {

    // ==================== CRUD 操作 ====================

    /**
     * 创建用户档案
     */
    String createUserProfile(IamUserProfileCreateCommand command);

    /**
     * 批量创建用户档案
     */
    List<String> batchCreateUserProfiles(List<IamUserProfileCreateCommand> commands);

    /**
     * 更新用户档案
     */
    void updateUserProfile(IamUserProfileUpdateCommand command);

    /**
     * 删除用户档案
     */
    void deleteUserProfile(String id, String tenantId);

    /**
     * 根据ID获取用户档案
     */
    IamUserProfileDTO getUserProfileById(String id, String tenantId);

    /**
     * 根据用户编码获取用户档案
     */
    IamUserProfileDTO getUserProfileByCode(String userCode, String tenantId);

    /**
     * 获取用户档案列表
     */
    List<IamUserProfileDTO> listUserProfiles(IamUserProfileQueryCommand command);

    /**
     * 分页查询用户档案
     */
    List<IamUserProfileDTO> pageUserProfiles(IamUserProfileQueryCommand command, int page, int size);

    // ==================== 状态管理 ====================

    /**
     * 启用用户档案
     */
    void enableUserProfile(String id, String tenantId);

    /**
     * 禁用用户档案
     */
    void disableUserProfile(String id, String tenantId);

    // ==================== 组织关系管理 ====================

    /**
     * 分配部门
     */
    void assignDepartment(String id, String deptId, String deptName, String tenantId);

    /**
     * 分配岗位
     */
    void assignPost(String id, String postCode, String postName, String tenantId);

    /**
     * 分配角色
     */
    void assignRole(String id, String roleId, String roleName, String tenantId);

    // ==================== 信息更新 ====================

    /**
     * 更新基本信息
     */
    void updateBasicInfo(String id, IamUserProfileUpdateCommand command);

    /**
     * 更新联系信息
     */
    void updateContactInfo(String id, IamUserProfileUpdateCommand command);

    /**
     * 更新工作信息
     */
    void updateJobInfo(String id, IamUserProfileUpdateCommand command);

    // ==================== 查询接口 ====================

    /**
     * 根据部门获取用户档案
     */
    List<IamUserProfileDTO> getUserProfilesByDepartment(String deptId, String tenantId);

    /**
     * 根据岗位获取用户档案
     */
    List<IamUserProfileDTO> getUserProfilesByPost(String postCode, String tenantId);

    /**
     * 搜索用户档案
     */
    List<IamUserProfileDTO> searchUserProfiles(String keyword, String tenantId);

}
