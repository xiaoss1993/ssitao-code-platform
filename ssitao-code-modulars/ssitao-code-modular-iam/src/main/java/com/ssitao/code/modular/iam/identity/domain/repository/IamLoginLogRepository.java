package com.ssitao.code.modular.iam.identity.domain.repository;

import com.ssitao.code.modular.iam.identity.domain.model.IamLoginLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * IAM登录日志仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamLoginLogRepository {

    /**
     * 保存登录日志
     *
     * @param loginLog 登录日志实体
     * @return 保存后的日志ID
     */
    String save(IamLoginLog loginLog);

    /**
     * 根据ID查找登录日志
     *
     * @param id       日志ID
     * @param tenantId 租户ID
     * @return 登录日志
     */
    Optional<IamLoginLog> findById(String id, String tenantId);

    /**
     * 批量保存登录日志
     *
     * @param loginLogs 登录日志列表
     */
    void saveAll(List<IamLoginLog> loginLogs);

    /**
     * 根据账号ID查询登录日志列表
     *
     * @param accountId 账号ID
     * @param limit     数量限制
     * @return 登录日志列表
     */
    List<IamLoginLog> findByAccountId(String accountId, Integer limit);

    /**
     * 根据用户ID查询登录日志列表
     *
     * @param userId 用户ID
     * @param limit  数量限制
     * @return 登录日志列表
     */
    List<IamLoginLog> findByUserId(String userId, Integer limit);

    /**
     * 根据条件查询登录日志列表
     *
     * @param username   用户名(模糊查询)
     * @param loginType  登录类型
     * @param loginStatus 登录结果
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param tenantId   租户ID
     * @return 登录日志列表
     */
    List<IamLoginLog> findByConditions(String username, String loginType, Boolean loginStatus,
                                       LocalDateTime startTime, LocalDateTime endTime,
                                       String tenantId);

    /**
     * 统计登录失败次数
     *
     * @param accountId  账号ID
     * @param afterTime  统计该时间之后的数据
     * @return 失败次数
     */
    Long countFailedLogin(String accountId, LocalDateTime afterTime);

    /**
     * 删除过期的登录日志
     *
     * @param beforeTime 删除该时间之前的日志
     */
    void deleteExpired(LocalDateTime beforeTime);

}
