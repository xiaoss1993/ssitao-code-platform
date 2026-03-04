package com.ssitao.code.modular.iam.identity.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.identity.dal.dataobject.IamLoginLogDO;
import com.ssitao.code.modular.iam.identity.dal.mapper.IamLoginLogMapper;
import com.ssitao.code.modular.iam.identity.domain.model.IamLoginLog;
import com.ssitao.code.modular.iam.identity.domain.repository.IamLoginLogRepository;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamLoginLogConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private IamLoginLogMapper loginLogMapper;

    @Resource
    private IamLoginLogConverter loginLogConverter;

    @Override
    public String save(IamLoginLog loginLog) {
        IamLoginLogDO loginLogDO = loginLogConverter.toDO(loginLog);
        loginLogDO.setLoginTime(LocalDateTime.now());
        loginLogMapper.insert(loginLogDO);
        return loginLogDO.getLogId();
    }

    @Override
    public Optional<IamLoginLog> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("log_id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
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
        LocalDateTime now = LocalDateTime.now();
        for (IamLoginLogDO loginLogDO : loginLogDOList) {
            loginLogDO.setLoginTime(now);
        }
        loginLogMapper.insertBatch(loginLogDOList);
    }

    @Override
    public List<IamLoginLog> findByAccountId(String accountId, Integer limit) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_id", accountId)
                .orderBy("login_time", false);
        if (limit != null && limit > 0) {
            query.limit(limit);
        }
        List<IamLoginLogDO> list = loginLogMapper.selectListByQuery(query);
        return loginLogConverter.toDomainList(list);
    }

    @Override
    public List<IamLoginLog> findByUserId(String userId, Integer limit) {
        // 登录日志表中存储的是accountId，这里需要通过账号关联查询
        QueryWrapper query = QueryWrapper.create()
                .orderBy("login_time", false);
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
        QueryWrapper query = QueryWrapper.create();

        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        if (username != null && !username.isEmpty()) {
            query.like("account_name", "%" + username + "%");
        }
        if (loginType != null && !loginType.isEmpty()) {
            query.eq("login_type", loginType);
        }
        if (loginStatus != null) {
            query.eq("login_status", loginStatus ? "SUCCESS" : "FAIL");
        }
        if (startTime != null) {
            query.ge("login_time", startTime);
        }
        if (endTime != null) {
            query.le("login_time", endTime);
        }

        query.orderBy("login_time", false);
        List<IamLoginLogDO> list = loginLogMapper.selectListByQuery(query);
        return loginLogConverter.toDomainList(list);
    }

    @Override
    public Long countFailedLogin(String accountId, LocalDateTime afterTime) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_id", accountId)
                .ge("login_time", afterTime)
                .eq("login_status", "FAIL");
        return loginLogMapper.selectCountByQuery(query);
    }

    @Override
    public void deleteExpired(LocalDateTime beforeTime) {
        QueryWrapper query = QueryWrapper.create()
                .lt("login_time", beforeTime);
        loginLogMapper.deleteByQuery(query);
    }
}
