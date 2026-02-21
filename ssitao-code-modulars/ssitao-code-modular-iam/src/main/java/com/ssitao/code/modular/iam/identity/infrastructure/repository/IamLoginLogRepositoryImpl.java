package com.ssitao.code.modular.iam.identity.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamLoginLogDO;
import com.ssitao.code.modular.iam.dal.mapper.IamLoginLogMapper;
import com.ssitao.code.modular.iam.identity.domain.model.IamLoginLog;
import com.ssitao.code.modular.iam.identity.domain.repository.IamLoginLogRepository;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamLoginLogConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * IAM登录日志仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamLoginLogRepositoryImpl implements IamLoginLogRepository {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamLoginLogMapper loginLogMapper;

    @Resource
    private IamLoginLogConverter loginLogConverter;

    @Override
    public String save(IamLoginLog loginLog) {
        IamLoginLogDO loginLogDO = loginLogConverter.toDO(loginLog);
        loginLogDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        loginLogMapper.insert(loginLogDO);
        return loginLogDO.getTbIamLoginlogId();
    }

    @Override
    public Optional<IamLoginLog> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .where("tb_iam_loginlog_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.and("sy_tenant_id", tenantId);
        }
        IamLoginLogDO loginLogDO = loginLogMapper.selectOneByQuery(query);
        if (loginLogDO == null) {
            return Optional.empty();
        }
        return Optional.of(loginLogConverter.toDomain(loginLogDO));
    }

    @Override
    public void saveAll(List<IamLoginLog> loginLogs) {
        if (loginLogs == null || loginLogs.isEmpty()) {
            return;
        }
        List<IamLoginLogDO> loginLogDOList = loginLogConverter.toDOList(loginLogs);
        String now = LocalDateTime.now().format(DATE_FORMATTER);
        for (IamLoginLogDO loginLogDO : loginLogDOList) {
            loginLogDO.setSyCreatetime(now);
        }
        loginLogMapper.insertBatch(loginLogDOList);
    }

    @Override
    public List<IamLoginLog> findByAccountId(String accountId, Integer limit) {
        QueryWrapper query = QueryWrapper.create()
                .where("sy_account_id", accountId)
                .orderBy("sy_createtime", false);
        if (limit != null && limit > 0) {
            query.limit(limit);
        }
        List<IamLoginLogDO> list = loginLogMapper.selectListByQuery(query);
        return loginLogConverter.toDomainList(list);
    }

    @Override
    public List<IamLoginLog> findByUserId(String userId, Integer limit) {
        // 登录日志表中存储的是accountId，这里需要通过账号关联查询
        // 如果需要支持userId查询，需要关联账号表
        QueryWrapper query = QueryWrapper.create()
                .orderBy("sy_createtime", false);
        if (limit != null && limit > 0) {
            query.limit(limit);
        }
        List<IamLoginLogDO> list = loginLogMapper.selectListByQuery(query);
        return loginLogConverter.toDomainList(list);
    }

    @Override
    public List<IamLoginLog> findByConditions(String username, String loginType, Boolean loginStatus,
                                              LocalDateTime startTime, LocalDateTime endTime,
                                              String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .where("sy_status", "1");

        if (tenantId != null && !tenantId.isEmpty()) {
            query.and("sy_tenant_id", tenantId);
        }
        if (username != null && !username.isEmpty()) {
            query.and("loginlog_account_name", "like", "%" + username + "%");
        }
        if (loginType != null && !loginType.isEmpty()) {
            query.and("loginlog_type_code", loginType);
        }
        if (startTime != null) {
            query.and("sy_createtime", ">=", startTime.format(DATE_FORMATTER));
        }
        if (endTime != null) {
            query.and("sy_createtime", "<=", endTime.format(DATE_FORMATTER));
        }

        query.orderBy("sy_createtime", false);
        List<IamLoginLogDO> list = loginLogMapper.selectListByQuery(query);
        return loginLogConverter.toDomainList(list);
    }

    @Override
    public Long countFailedLogin(String accountId, LocalDateTime afterTime) {
        QueryWrapper query = QueryWrapper.create()
                .where("sy_account_id", accountId)
                .and("sy_createtime", ">=", afterTime.format(DATE_FORMATTER));
        // 登录失败的记录可能需要通过特定字段判断，这里假设通过sy_status=0或特定字段
        // 根据实际业务逻辑调整
        query.and("sy_status", "0");
        return loginLogMapper.selectCountByQuery(query);
    }

    @Override
    public void deleteExpired(LocalDateTime beforeTime) {
        QueryWrapper query = QueryWrapper.create()
                .where("sy_createtime", "<", beforeTime.format(DATE_FORMATTER));
        loginLogMapper.deleteByQuery(query);
    }
}
