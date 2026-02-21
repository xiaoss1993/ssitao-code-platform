package com.ssitao.code.modular.iam.audit.application.service;

import com.ssitao.code.modular.iam.audit.application.query.IamOperateLogQuery;
import com.ssitao.code.modular.iam.audit.api.dto.IamOperateLogDTO;

import java.util.List;

/**
 * IAM操作日志应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamOperateLogAppService {

    /**
     * 记录操作日志
     *
     * @param query 操作日志查询对象
     * @return 日志ID
     */
    Long recordLog(IamOperateLogQuery query);

    /**
     * 批量记录操作日志
     *
     * @param queries 操作日志查询对象列表
     * @return 日志ID列表
     */
    List<Long> recordLogBatch(List<IamOperateLogQuery> queries);

    /**
     * 根据ID获取操作日志
     *
     * @param id 日志ID
     * @return 操作日志DTO
     */
    IamOperateLogDTO getLogById(Long id);

    /**
     * 根据操作人ID获取日志
     *
     * @param operatorId 操作人ID
     * @param page       页码
     * @param size       每页大小
     * @return 操作日志列表
     */
    List<IamOperateLogDTO> getLogsByOperatorId(Long operatorId, int page, int size);

    /**
     * 根据操作类型获取日志
     *
     * @param operateType 操作类型
     * @param tenantId    租户ID
     * @param page        页码
     * @param size        每页大小
     * @return 操作日志列表
     */
    List<IamOperateLogDTO> getLogsByOperateType(String operateType, String tenantId, int page, int size);

    /**
     * 根据操作模块获取日志
     *
     * @param operateModule 操作模块
     * @param tenantId      租户ID
     * @param page          页码
     * @param size          每页大小
     * @return 操作日志列表
     */
    List<IamOperateLogDTO> getLogsByOperateModule(String operateModule, String tenantId, int page, int size);

    /**
     * 查询操作日志
     *
     * @param query 查询条件
     * @param page  页码
     * @param size  每页大小
     * @return 操作日志列表
     */
    List<IamOperateLogDTO> queryLogs(IamOperateLogQuery query, int page, int size);

    /**
     * 统计操作日志数量
     *
     * @param query 查询条件
     * @return 数量
     */
    long countLogs(IamOperateLogQuery query);

    /**
     * 删除指定天数之前的日志
     *
     * @param days    天数
     * @param tenantId 租户ID
     */
    void deleteLogsBeforeDays(int days, String tenantId);

}
