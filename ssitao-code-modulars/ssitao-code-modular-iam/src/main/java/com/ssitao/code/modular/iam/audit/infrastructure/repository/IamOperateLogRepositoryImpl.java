package com.ssitao.code.modular.iam.audit.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.audit.dal.dataobject.IamOperateLogDO;
import com.ssitao.code.modular.iam.audit.dal.mapper.IamOperateLogMapper;
import com.ssitao.code.modular.iam.audit.domain.model.IamOperateLog;
import com.ssitao.code.modular.iam.audit.domain.repository.IamOperateLogRepository;
import com.ssitao.code.modular.iam.audit.infrastructure.converter.IamOperateLogConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * IAM操作日志仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamOperateLogRepositoryImpl implements IamOperateLogRepository {

    @Resource
    private IamOperateLogMapper operateLogMapper;

    @Resource
    private IamOperateLogConverter operateLogConverter;

    @Override
    public String save(IamOperateLog log) {
        IamOperateLogDO operateLogDO = operateLogConverter.toDO(log);
        if (operateLogDO.getLogId() == null || operateLogDO.getLogId().isEmpty()) {
            operateLogDO.setLogId(UUID.randomUUID().toString().replace("-", ""));
        }
        operateLogDO.setOperateTime(LocalDateTime.now());
        operateLogMapper.insert(operateLogDO);
        return operateLogDO.getLogId();
    }

    @Override
    public List<String> saveBatch(List<IamOperateLog> logs) {
        List<String> ids = new ArrayList<>();
        if (logs == null || logs.isEmpty()) {
            return ids;
        }
        LocalDateTime now = LocalDateTime.now();
        for (IamOperateLog log : logs) {
            IamOperateLogDO operateLogDO = operateLogConverter.toDO(log);
            if (operateLogDO.getLogId() == null || operateLogDO.getLogId().isEmpty()) {
                operateLogDO.setLogId(UUID.randomUUID().toString().replace("-", ""));
            }
            operateLogDO.setOperateTime(now);
            operateLogMapper.insert(operateLogDO);
            ids.add(operateLogDO.getLogId());
        }
        return ids;
    }

    @Override
    public IamOperateLog findById(String id) {
        IamOperateLogDO operateLogDO = operateLogMapper.selectOneById(id);
        return operateLogConverter.toDomain(operateLogDO);
    }

    @Override
    public List<IamOperateLog> findByOperatorId(String operatorId, int page, int size) {
        QueryWrapper query = QueryWrapper.create()
                .eq("operator_id", operatorId)
                .orderBy("operate_time", false)
                .offset((page - 1) * size)
                .limit(size);
        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public List<IamOperateLog> findByOperateType(String operateType, String tenantId, int page, int size) {
        QueryWrapper query = QueryWrapper.create()
                .eq("operate_type", operateType);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.orderBy("operate_time", false)
             .offset((page - 1) * size)
             .limit(size);
        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public List<IamOperateLog> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime,
                                                String tenantId, int page, int size) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.ge("operate_time", startTime)
             .le("operate_time", endTime)
             .orderBy("operate_time", false)
             .offset((page - 1) * size)
             .limit(size);
        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public List<IamOperateLog> findByOperateModule(String operateModule, String tenantId, int page, int size) {
        QueryWrapper query = QueryWrapper.create()
                .eq("module_name", operateModule);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.orderBy("operate_time", false)
             .offset((page - 1) * size)
             .limit(size);
        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public long count(String operatorId, String operateType, String operateModule,
                      LocalDateTime startTime, LocalDateTime endTime, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        if (operatorId != null && !operatorId.isEmpty()) {
            query.eq("operator_id", operatorId);
        }
        if (operateType != null && !operateType.isEmpty()) {
            query.eq("operate_type", operateType);
        }
        if (operateModule != null && !operateModule.isEmpty()) {
            query.eq("module_name", operateModule);
        }
        if (startTime != null) {
            query.ge("operate_time", startTime);
        }
        if (endTime != null) {
            query.le("operate_time", endTime);
        }

        return operateLogMapper.selectCountByQuery(query);
    }

    @Override
    public List<IamOperateLog> find(String operatorId, String operateType, String operateModule,
                                     LocalDateTime startTime, LocalDateTime endTime, String tenantId,
                                     int page, int size) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        if (operatorId != null && !operatorId.isEmpty()) {
            query.eq("operator_id", operatorId);
        }
        if (operateType != null && !operateType.isEmpty()) {
            query.eq("operate_type", operateType);
        }
        if (operateModule != null && !operateModule.isEmpty()) {
            query.eq("module_name", operateModule);
        }
        if (startTime != null) {
            query.ge("operate_time", startTime);
        }
        if (endTime != null) {
            query.le("operate_time", endTime);
        }

        query.orderBy("operate_time", false)
             .offset((page - 1) * size)
             .limit(size);

        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public void deleteBeforeTime(LocalDateTime beforeTime, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.lt("operate_time", beforeTime);
        operateLogMapper.deleteByQuery(query);
    }
}
