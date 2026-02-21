package com.ssitao.code.modular.iam.audit.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.audit.domain.model.IamOperateLog;
import com.ssitao.code.modular.iam.audit.domain.repository.IamOperateLogRepository;
import com.ssitao.code.modular.iam.audit.infrastructure.converter.IamOperateLogConverter;
import com.ssitao.code.modular.iam.dal.dataobject.IamOperateLogDO;
import com.ssitao.code.modular.iam.dal.mapper.IamOperateLogMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * IAM操作日志仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamOperateLogRepositoryImpl implements IamOperateLogRepository {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamOperateLogMapper operateLogMapper;

    @Resource
    private IamOperateLogConverter operateLogConverter;

    @Override
    public String save(IamOperateLog log) {
        IamOperateLogDO operateLogDO = operateLogConverter.toDO(log);
        operateLogDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        operateLogMapper.insert(operateLogDO);
        return operateLogDO.getTbIamOperatelogId();
    }

    @Override
    public List<String> saveBatch(List<IamOperateLog> logs) {
        List<String> ids = new ArrayList<>();
        if (logs == null || logs.isEmpty()) {
            return ids;
        }
        String now = LocalDateTime.now().format(DATE_FORMATTER);
        for (IamOperateLog log : logs) {
            IamOperateLogDO operateLogDO = operateLogConverter.toDO(log);
            operateLogDO.setSyCreatetime(now);
            operateLogMapper.insert(operateLogDO);
            ids.add(operateLogDO.getTbIamOperatelogId());
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
                .orderBy("sy_createtime", false)
                .offset((page - 1) * size)
                .limit(size);
        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public List<IamOperateLog> findByOperateType(String operateType, String tenantId, int page, int size) {
        QueryWrapper query = QueryWrapper.create()
                .eq("operatelog_type", operateType);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.orderBy("sy_createtime", false)
                .offset((page - 1) * size)
                .limit(size);
        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public List<IamOperateLog> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime,
                                                 String tenantId, int page, int size) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.ge("sy_createtime", startTime.format(DATE_FORMATTER))
                .le("sy_createtime", endTime.format(DATE_FORMATTER))
                .orderBy("sy_createtime", false)
                .offset((page - 1) * size)
                .limit(size);
        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public List<IamOperateLog> findByOperateModule(String operateModule, String tenantId, int page, int size) {
        QueryWrapper query = QueryWrapper.create()
                .eq("operatelog_module", operateModule);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.orderBy("sy_createtime", false)
                .offset((page - 1) * size)
                .limit(size);
        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public long count(String operatorId, String operateType, String operateModule,
                      LocalDateTime startTime, LocalDateTime endTime, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        if (operatorId != null && !operatorId.isEmpty()) {
            query.eq("operator_id", operatorId);
        }
        if (operateType != null && !operateType.isEmpty()) {
            query.eq("operatelog_type", operateType);
        }
        if (operateModule != null && !operateModule.isEmpty()) {
            query.eq("operatelog_module", operateModule);
        }
        if (startTime != null) {
            query.ge("sy_createtime", startTime.format(DATE_FORMATTER));
        }
        if (endTime != null) {
            query.le("sy_createtime", endTime.format(DATE_FORMATTER));
        }

        return operateLogMapper.selectCountByQuery(query);
    }

    @Override
    public List<IamOperateLog> find(String operatorId, String operateType, String operateModule,
                                     LocalDateTime startTime, LocalDateTime endTime, String tenantId,
                                     int page, int size) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        if (operatorId != null && !operatorId.isEmpty()) {
            query.eq("operator_id", operatorId);
        }
        if (operateType != null && !operateType.isEmpty()) {
            query.eq("operatelog_type", operateType);
        }
        if (operateModule != null && !operateModule.isEmpty()) {
            query.eq("operatelog_module", operateModule);
        }
        if (startTime != null) {
            query.ge("sy_createtime", startTime.format(DATE_FORMATTER));
        }
        if (endTime != null) {
            query.le("sy_createtime", endTime.format(DATE_FORMATTER));
        }

        query.orderBy("sy_createtime", false)
                .offset((page - 1) * size)
                .limit(size);

        List<IamOperateLogDO> list = operateLogMapper.selectListByQuery(query);
        return operateLogConverter.toDomainList(list);
    }

    @Override
    public void deleteBeforeTime(LocalDateTime beforeTime, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.lt("sy_createtime", beforeTime.format(DATE_FORMATTER));
        operateLogMapper.deleteByQuery(query);
    }
}
