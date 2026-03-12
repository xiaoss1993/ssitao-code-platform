package com.ssitao.code.modular.iam.userprofile.domain.repository;

import com.ssitao.code.modular.iam.userprofile.domain.model.IamUser;

import java.util.List;
import java.util.Optional;

/**
 * IAM用户仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamUserRepository {

    /**
     * 保存用户
     *
     * @param user 用户聚合根
     * @return 用户ID
     */
    String save(IamUser user);

    /**
     * 批量保存用户
     *
     * @param users 用户列表
     * @return 用户ID列表
     */
    List<String> saveBatch(List<IamUser> users);

    /**
     * 更新用户
     *
     * @param user 用户聚合根
     */
    void update(IamUser user);

    /**
     * 根据ID删除用户
     *
     * @param id       用户ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据ID查找用户
     *
     * @param id       用户ID
     * @param tenantId 租户ID
     * @return 用户聚合根
     */
    Optional<IamUser> findById(String id, String tenantId);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @param tenantId 租户ID
     * @return 用户聚合根
     */
    Optional<IamUser> findByUsername(String username, String tenantId);

    /**
     * 根据邮箱查找用户
     *
     * @param email    邮箱
     * @param tenantId 租户ID
     * @return 用户聚合根
     */
    Optional<IamUser> findByEmail(String email, String tenantId);

    /**
     * 根据手机号查找用户
     *
     * @param phone    手机号
     * @param tenantId 租户ID
     * @return 用户聚合根
     */
    Optional<IamUser> findByPhone(String phone, String tenantId);

    /**
     * 查找所有用户
     *
     * @param tenantId 租户ID
     * @return 用户列表
     */
    List<IamUser> findAll(String tenantId);

    /**
     * 根据部门ID查找用户
     *
     * @param deptId   部门ID
     * @param tenantId 租户ID
     * @return 用户列表
     */
    List<IamUser> findByDeptId(String deptId, String tenantId);

    /**
     * 根据岗位ID查找用户
     *
     * @param postId   岗位ID
     * @param tenantId 租户ID
     * @return 用户列表
     */
    List<IamUser> findByPostId(String postId, String tenantId);

    /**
     * 检查用户名是否存在
     *
     * @param username  用户名
     * @param tenantId  租户ID
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByUsername(String username, String tenantId, String excludeId);

    /**
     * 检查邮箱是否存在
     *
     * @param email     邮箱
     * @param tenantId  租户ID
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByEmail(String email, String tenantId, String excludeId);

    /**
     * 检查手机号是否存在
     *
     * @param phone     手机号
     * @param tenantId  租户ID
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByPhone(String phone, String tenantId, String excludeId);

    /**
     * 分页查询用户
     *
     * @param tenantId 租户ID
     * @param keyword  关键词
     * @param deptId   部门ID
     * @param page     页码
     * @param size     每页大小
     * @return 用户列表
     */
    List<IamUser> findPage(String tenantId, String keyword, String deptId, int page, int size);

    /**
     * 统计用户数量
     *
     * @param tenantId 租户ID
     * @param keyword  关键词
     * @param deptId   部门ID
     * @return 用户数量
     */
    long count(String tenantId, String keyword, String deptId);

}
