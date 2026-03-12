package com.ssitao.code.modular.iam.audit.domain.repository;

import com.ssitao.code.modular.iam.audit.domain.model.IamOperateLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IAM操作日志仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamOperateLogRepository {

    /**
     * 保存操作日志
     *
     * @param log 操作日志聚合根
     * @return 日志ID
     */
    String save(IamOperateLog log);

    /**
     * 批量保存操作日志
     *
     * @param logs 操作日志列表
     * @return 日志ID列表
     */
    List<String> saveBatch(List<IamOperateLog> logs);

    /**
     * 根据ID查找操作日志
     *
     * @param id 日志ID
     * @return 操作日志聚合根
     */
    IamOperateLog findById(String id);

    /**
     * 根据操作人ID查找日志
     *
     * @param operatorId 操作人ID
     * @param page       页码
     * @param size       每页大小
     * @return 操作日志列表
     */
    List<IamOperateLog> findByOperatorId(String operatorId, int page, int size);

    /**
     * 根据操作类型查找日志
     *
     * @param operateType 操作类型
     * @param tenantId    租户ID
     * @param page        页码
     * @param size        每页大小
     * @return 操作日志列表
     */
    List<IamOperateLog> findByOperateType(String operateType, String tenantId, int page, int size);

    /**
     * 根据时间范围查找日志
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param tenantId  租户ID
     * @param page      页码
     * @param size      每页大小
     * @return 操作日志列表
     */
    List<IamOperateLog> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime,
                                         String tenantId, int page, int size);

    /**
     * 根据模块查找日志
     *
     * @param operateModule 操作模块
     * @param tenantId      租户ID
     * @param page          页码
     * @param size          每页大小
     * @return 操作日志列表
     */
    List<IamOperateLog> findByOperateModule(String operateModule, String tenantId, int page, int size);

    /**
     * 统计操作日志数量
     *
     * @param operatorId    操作人ID
     * @param operateType   操作类型
     * @param operateModule 操作模块
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param tenantId      租户ID
     * @return 数量
     */
    long count(String operatorId, String operateType, String operateModule,
               LocalDateTime startTime, LocalDateTime endTime, String tenantId);

    /**
     * 查询操作日志
     *
     * @param operatorId    操作人ID
     * @param operateType   操作类型
     * @param operateModule 操作模块
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param tenantId      租户ID
     * @param page          页码
     * @param size          每页大小
     * @return 操作日志列表
     */
    List<IamOperateLog> find(String operatorId, String operateType, String operateModule,
                             LocalDateTime startTime, LocalDateTime endTime, String tenantId,
                             int page, int size);

    /**
     * 删除指定时间之前的日志
     *
     * @param beforeTime 时间
     * @param tenantId   租户ID
     */
    void deleteBeforeTime(LocalDateTime beforeTime, String tenantId);

}
