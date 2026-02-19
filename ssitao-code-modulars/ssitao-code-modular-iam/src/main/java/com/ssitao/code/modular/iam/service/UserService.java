package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.user.UserCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.user.UserListReqVO;
import com.ssitao.code.modular.iam.controller.vo.user.UserUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.UserDO;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param createReqVO 创建请求
     * @return 用户ID
     */
    Long createUser(UserCreateReqVO createReqVO);

    /**
     * 更新用户
     *
     * @param updateReqVO 更新请求
     */
    void updateUser(UserUpdateReqVO updateReqVO);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void deleteUser(Long id);

    /**
     * 获取用户详情
     *
     * @param id 用户ID
     * @return 用户信息
     */
    UserDO getUser(Long id);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserDO getUserByUsername(String username);

    /**
     * 获取用户列表
     *
     * @param reqVO 查询请求
     * @return 用户列表
     */
    List<UserDO> getUserList(UserListReqVO reqVO);

    /**
     * 更新用户状态
     *
     * @param id     用户ID
     * @param status 状态
     */
    void updateUserStatus(Long id, Integer status);

    /**
     * 更新用户密码
     *
     * @param id       用户ID
     * @param password 密码
     */
    void updateUserPassword(Long id, String password);

    /**
     * 分配角色给用户
     *
     * @param userId  用户ID
     * @param roleIds 角色ID列表
     */
    void assignRoles(Long userId, List<Long> roleIds);

    /**
     * 获取用户的角色列表
     *
     * @param userId 用户ID
     * @return 角色ID列表
     */
    List<Long> getUserRoles(Long userId);
}
