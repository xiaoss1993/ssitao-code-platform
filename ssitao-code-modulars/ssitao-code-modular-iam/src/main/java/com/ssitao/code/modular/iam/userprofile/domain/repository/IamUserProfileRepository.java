package com.ssitao.code.modular.iam.userprofile.domain.repository;

import com.ssitao.code.modular.iam.userprofile.domain.model.IamUserProfile;

import java.util.List;
import java.util.Optional;

/**
 * IAM用户档案仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamUserProfileRepository {

    /**
     * 保存用户档案
     */
    String save(IamUserProfile userProfile);

    /**
     * 批量保存用户档案
     */
    List<String> saveBatch(List<IamUserProfile> userProfiles);

    /**
     * 更新用户档案
     */
    void update(IamUserProfile userProfile);

    /**
     * 根据ID删除用户档案
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据ID获取用户档案
     */
    Optional<IamUserProfile> findById(String id, String tenantId);

    /**
     * 根据用户编码获取用户档案
     */
    Optional<IamUserProfile> findByCode(String userCode, String tenantId);

    /**
     * 根据手机号获取用户档案
     */
    Optional<IamUserProfile> findByPhone(String phone, String tenantId);

    /**
     * 根据邮箱获取用户档案
     */
    Optional<IamUserProfile> findByEmail(String email, String tenantId);

    /**
     * 获取所有用户档案
     */
    List<IamUserProfile> findAll(String tenantId);

    /**
     * 根据部门ID获取用户档案
     */
    List<IamUserProfile> findByDepartmentId(String deptId, String tenantId);

    /**
     * 根据岗位代码获取用户档案
     */
    List<IamUserProfile> findByPostCode(String postCode, String tenantId);

    /**
     * 根据角色ID获取用户档案
     */
    List<IamUserProfile> findByRoleId(String roleId, String tenantId);

    /**
     * 分页查询用户档案
     */
    List<IamUserProfile> findPage(String tenantId, String keyword, String deptId, int page, int size);

    /**
     * 统计用户档案数量
     */
    long count(String tenantId, String keyword, String deptId);

    /**
     * 检查用户编码是否存在
     */
    boolean existsByCode(String userCode, String tenantId, String excludeId);

    /**
     * 检查手机号是否存在
     */
    boolean existsByPhone(String phone, String tenantId, String excludeId);

    /**
     * 检查邮箱是否存在
     */
    boolean existsByEmail(String email, String tenantId, String excludeId);

}
