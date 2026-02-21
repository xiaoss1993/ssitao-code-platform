package com.ssitao.code.modular.iam.userprofile.application.service;

import com.ssitao.code.modular.iam.userprofile.application.command.IamUserCreateCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserUpdateCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserQueryCommand;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserDTO;

import java.util.List;

/**
 * IAM用户应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamUserAppService {

    /**
     * 创建用户
     *
     * @param command 创建命令
     * @return 用户ID
     */
    Long createUser(IamUserCreateCommand command);

    /**
     * 批量创建用户
     *
     * @param commands 创建命令列表
     * @return 用户ID列表
     */
    List<Long> batchCreateUsers(List<IamUserCreateCommand> commands);

    /**
     * 更新用户
     *
     * @param command 更新命令
     */
    void updateUser(IamUserUpdateCommand command);

    /**
     * 删除用户
     *
     * @param id       用户ID
     * @param tenantId 租户ID
     */
    void deleteUser(Long id, String tenantId);

    /**
     * 根据ID获取用户
     *
     * @param id       用户ID
     * @param tenantId 租户ID
     * @return 用户DTO
     */
    IamUserDTO getUserById(Long id, String tenantId);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @param tenantId 租户ID
     * @return 用户DTO
     */
    IamUserDTO getUserByUsername(String username, String tenantId);

    /**
     * 获取用户列表
     *
     * @param command 查询命令
     * @return 用户列表
     */
    List<IamUserDTO> listUsers(IamUserQueryCommand command);

    /**
     * 分页查询用户
     *
     * @param command 查询命令
     * @param page    页码
     * @param size    每页大小
     * @return 用户列表
     */
    List<IamUserDTO> pageUsers(IamUserQueryCommand command, int page, int size);

    /**
     * 启用用户
     *
     * @param id       用户ID
     * @param tenantId 租户ID
     */
    void enableUser(Long id, String tenantId);

    /**
     * 禁用用户
     *
     * @param id       用户ID
     * @param tenantId 租户ID
     */
    void disableUser(Long id, String tenantId);

    /**
     * 锁定用户
     *
     * @param id       用户ID
     * @param tenantId 租户ID
     */
    void lockUser(Long id, String tenantId);

    /**
     * 解锁用户
     *
     * @param id       用户ID
     * @param tenantId 租户ID
     */
    void unlockUser(Long id, String tenantId);

    /**
     * 重置密码
     *
     * @param id          用户ID
     * @param newPassword 新密码
     * @param tenantId    租户ID
     */
    void resetPassword(Long id, String newPassword, String tenantId);

    /**
     * 分配部门
     *
     * @param id       用户ID
     * @param deptId   部门ID
     * @param deptName 部门名称
     * @param tenantId 租户ID
     */
    void assignDepartment(Long id, Long deptId, String deptName, String tenantId);

    /**
     * 分配岗位
     *
     * @param id       用户ID
     * @param postId   岗位ID
     * @param postName 岗位名称
     * @param tenantId 租户ID
     */
    void assignPost(Long id, Long postId, String postName, String tenantId);

    /**
     * 分配角色
     *
     * @param id       用户ID
     * @param roleIds  角色ID列表
     * @param tenantId 租户ID
     */
    void assignRoles(Long id, List<Long> roleIds, String tenantId);

}
