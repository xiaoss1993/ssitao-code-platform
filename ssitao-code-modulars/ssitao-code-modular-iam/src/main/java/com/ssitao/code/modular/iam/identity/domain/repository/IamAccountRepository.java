package com.ssitao.code.modular.iam.identity.domain.repository;

import com.ssitao.code.modular.iam.identity.domain.model.IamAccount;

import java.util.List;
import java.util.Optional;

/**
 * IAM账号仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamAccountRepository {

    /**
     * 保存账号
     *
     * @param account 账号聚合根
     * @return 保存后的账号ID
     */
    String save(IamAccount account);

    /**
     * 更新账号
     *
     * @param account 账号聚合根
     */
    void update(IamAccount account);

    /**
     * 根据ID获取账号
     *
     * @param id 账号ID
     * @return 账号聚合根
     */
    Optional<IamAccount> findById(String id);

    /**
     * 根据账号编码获取账号
     *
     * @param accountCode 账号编码
     * @param tenantId    租户ID
     * @return 账号聚合根
     */
    Optional<IamAccount> findByAccountCode(String accountCode, String tenantId);

    /**
     * 根据手机号获取账号
     *
     * @param phone    手机号
     * @param tenantId 租户ID
     * @return 账号聚合根
     */
    Optional<IamAccount> findByPhone(String phone, String tenantId);

    /**
     * 根据邮箱获取账号
     *
     * @param email    邮箱
     * @param tenantId 租户ID
     * @return 账号聚合根
     */
    Optional<IamAccount> findByEmail(String email, String tenantId);

    /**
     * 根据OpenID获取账号
     *
     * @param openId   OpenID
     * @param openType 第三方类型
     * @param tenantId 租户ID
     * @return 账号聚合根
     */
    Optional<IamAccount> findByOpenId(String openId, String openType, String tenantId);

    /**
     * 根据用户ID获取账号
     *
     * @param userId 用户ID
     * @return 账号聚合根
     */
    Optional<IamAccount> findByUserId(String userId);

    /**
     * 检查账号编码是否存在
     *
     * @param accountCode 账号编码
     * @param tenantId    租户ID
     * @param excludeId   排除的账号ID
     * @return true-存在，false-不存在
     */
    boolean existsByAccountCode(String accountCode, String tenantId, String excludeId);

    /**
     * 检查手机号是否存在
     *
     * @param phone    手机号
     * @param tenantId 租户ID
     * @param excludeId 排除的账号ID
     * @return true-存在，false-不存在
     */
    boolean existsByPhone(String phone, String tenantId, String excludeId);

    /**
     * 根据条件查询账号列表
     *
     * @param accountName 账号名称(模糊查询)
     * @param phone       手机号
     * @param status      状态
     * @param tenantId    租户ID
     * @return 账号列表
     */
    List<IamAccount> findByConditions(String accountName, String phone, Boolean status, String tenantId);

    /**
     * 删除账号(逻辑删除)
     *
     * @param id 账号ID
     */
    void delete(String id);

}
