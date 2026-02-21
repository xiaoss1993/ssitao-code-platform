package com.ssitao.code.modular.iam.audit.application.service.impl;

import com.ssitao.code.modular.iam.audit.api.dto.IamOperateLogDTO;
import com.ssitao.code.modular.iam.audit.application.query.IamOperateLogQuery;
import com.ssitao.code.modular.iam.audit.application.service.IamOperateLogAppService;
import com.ssitao.code.modular.iam.audit.domain.model.IamOperateLog;
import com.ssitao.code.modular.iam.audit.domain.repository.IamOperateLogRepository;
import com.ssitao.code.modular.iam.audit.infrastructure.converter.IamOperateLogConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM操作日志应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamOperateLogAppServiceImpl implements IamOperateLogAppService {

    @Resource
    private IamOperateLogRepository operateLogRepository;

    @Resource
    private IamOperateLogConverter operateLogConverter;

    @Override
    public Long recordLog(IamOperateLogQuery query) {
        if (query == null) {
            return null;
        }

        IamOperateLog log = IamOperateLog.create(
                query.getOperatorId() != null ? query.getOperatorId().toString() : null,
                query.getOperatorAccount(),
                query.getOperatorName(),
                query.getOperateModule(),
                query.getOperateType(),
                query.getOperateDesc(),
                query.getTenantId()
        );

        // 设置请求信息
        log.setRequestInfo(query.getRequestMethod(), query.getRequestUrl(), query.getRequestParams());

        // 设置响应信息
        log.setResponseInfo(query.getResponseResult(), query.getExecuteTime());

        // 设置环境信息
        log.setEnvironmentInfo(query.getOperateIp(), query.getOperateLocation(),
                query.getBrowserType(), query.getOsType());

        // 处理失败状态
        if ("FAIL".equals(query.getOperateStatus())) {
            log.markAsFail(query.getErrorMsg());
        }

        String logId = operateLogRepository.save(log);
        return logId != null ? Long.valueOf(logId) : null;
    }

    @Override
    public List<Long> recordLogBatch(List<IamOperateLogQuery> queries) {
        if (queries == null || queries.isEmpty()) {
            return new ArrayList<>();
        }

        List<IamOperateLog> logs = new ArrayList<>();
        for (IamOperateLogQuery query : queries) {
            IamOperateLog log = IamOperateLog.create(
                    query.getOperatorId() != null ? query.getOperatorId().toString() : null,
                    query.getOperatorAccount(),
                    query.getOperatorName(),
                    query.getOperateModule(),
                    query.getOperateType(),
                    query.getOperateDesc(),
                    query.getTenantId()
            );

            log.setRequestInfo(query.getRequestMethod(), query.getRequestUrl(), query.getRequestParams());
            log.setResponseInfo(query.getResponseResult(), query.getExecuteTime());
            log.setEnvironmentInfo(query.getOperateIp(), query.getOperateLocation(),
                    query.getBrowserType(), query.getOsType());

            if ("FAIL".equals(query.getOperateStatus())) {
                log.markAsFail(query.getErrorMsg());
            }

            logs.add(log);
        }

        List<String> logIds = operateLogRepository.saveBatch(logs);
        return logIds.stream()
                .map(id -> id != null ? Long.valueOf(id) : null)
                .collect(Collectors.toList());
    }

    @Override
    public IamOperateLogDTO getLogById(Long id) {
        if (id == null) {
            return null;
        }
        IamOperateLog log = operateLogRepository.findById(id.toString());
        return operateLogConverter.toDTO(log);
    }

    @Override
    public List<IamOperateLogDTO> getLogsByOperatorId(Long operatorId, int page, int size) {
        if (operatorId == null) {
            return new ArrayList<>();
        }
        List<IamOperateLog> logs = operateLogRepository.findByOperatorId(operatorId.toString(), page, size);
        return operateLogConverter.toDTOList(logs);
    }

    @Override
    public List<IamOperateLogDTO> getLogsByOperateType(String operateType, String tenantId, int page, int size) {
        List<IamOperateLog> logs = operateLogRepository.findByOperateType(operateType, tenantId, page, size);
        return operateLogConverter.toDTOList(logs);
    }

    @Override
    public List<IamOperateLogDTO> getLogsByOperateModule(String operateModule, String tenantId, int page, int size) {
        List<IamOperateLog> logs = operateLogRepository.findByOperateModule(operateModule, tenantId, page, size);
        return operateLogConverter.toDTOList(logs);
    }

    @Override
    public List<IamOperateLogDTO> queryLogs(IamOperateLogQuery query, int page, int size) {
        String operatorId = query != null && query.getOperatorId() != null
                ? query.getOperatorId().toString() : null;
        String operateType = query != null ? query.getOperateType() : null;
        String operateModule = query != null ? query.getOperateModule() : null;
        LocalDateTime startTime = query != null ? query.getStartTime() : null;
        LocalDateTime endTime = query != null ? query.getEndTime() : null;
        String tenantId = query != null ? query.getTenantId() : null;

        List<IamOperateLog> logs = operateLogRepository.find(
                operatorId, operateType, operateModule,
                startTime, endTime, tenantId, page, size
        );

        return operateLogConverter.toDTOList(logs);
    }

    @Override
    public long countLogs(IamOperateLogQuery query) {
        String operatorId = query != null && query.getOperatorId() != null
                ? query.getOperatorId().toString() : null;
        String operateType = query != null ? query.getOperateType() : null;
        String operateModule = query != null ? query.getOperateModule() : null;
        LocalDateTime startTime = query != null ? query.getStartTime() : null;
        LocalDateTime endTime = query != null ? query.getEndTime() : null;
        String tenantId = query != null ? query.getTenantId() : null;

        return operateLogRepository.count(
                operatorId, operateType, operateModule,
                startTime, endTime, tenantId
        );
    }

    @Override
    public void deleteLogsBeforeDays(int days, String tenantId) {
        LocalDateTime beforeTime = LocalDateTime.now().minusDays(days);
        operateLogRepository.deleteBeforeTime(beforeTime, tenantId);
    }
}
