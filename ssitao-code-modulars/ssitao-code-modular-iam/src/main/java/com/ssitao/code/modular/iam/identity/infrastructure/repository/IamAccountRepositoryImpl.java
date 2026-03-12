package com.ssitao.code.modular.iam.identity.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.identity.dal.dataobject.IamAccountDO;
import com.ssitao.code.modular.iam.identity.dal.mapper.IamAccountMapper;
import com.ssitao.code.modular.iam.identity.domain.model.IamAccount;
import com.ssitao.code.modular.iam.identity.domain.repository.IamAccountRepository;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamAccountConverter;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * IAM账号仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamAccountRepositoryImpl implements IamAccountRepository {

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamAccountMapper accountMapper;

    @Resource
    private IamAccountConverter accountConverter;

    @Override
    public String save(IamAccount account) {
        IamAccountDO accountDO = accountConverter.toDO(account);
        accountDO.setCreateTime(LocalDateTime.now());
        accountDO.setIsDeleted(NOT_DELETED);
        accountMapper.insert(accountDO);
        return accountDO.getAccountId();
    }

    @Override
    public void update(IamAccount account) {
        IamAccountDO accountDO = accountConverter.toDO(account);
        accountDO.setModifyTime(LocalDateTime.now());
        accountMapper.update(accountDO);
    }

    @Override
    public Optional<IamAccount> findById(String id) {
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }
        IamAccountDO accountDO = accountMapper.selectOneById(id);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByAccountCode(String accountCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_code", accountCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("account_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByPhone(String phone, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_phone", phone);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("account_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByEmail(String email, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_mail", email);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("account_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByOpenId(String openId, String openType, String tenantId) {
        // 简化实现，需要根据实际业务调整
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("account_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByUserId(String userId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_status", STATUS_ACTIVE)
                .eq("is_deleted", NOT_DELETED);
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public boolean existsByAccountCode(String accountCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_code", accountCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("account_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        if (excludeId != null && !excludeId.isEmpty()) {
            query.ne("account_id", excludeId);
        }
        return accountMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public boolean existsByPhone(String phone, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_phone", phone);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("account_status", STATUS_ACTIVE)
             .eq("is_deleted", NOT_DELETED);
        if (excludeId != null && !excludeId.isEmpty()) {
            query.ne("account_id", excludeId);
        }
        return accountMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public List<IamAccount> findByConditions(String accountName, String phone, Boolean status, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        if (accountName != null && !accountName.isEmpty()) {
            query.like("account_name", accountName);
        }
        if (phone != null && !phone.isEmpty()) {
            query.eq("account_phone", phone);
        }
        if (status != null) {
            query.eq("account_status", status ? STATUS_ACTIVE : 0);
        }
        query.orderBy("create_time", false);
        List<IamAccountDO> list = accountMapper.selectListByQuery(query);
        return accountConverter.toDomainList(list);
    }

    @Override
    public void delete(String id) {
        accountMapper.deleteById(id);
    }

}
