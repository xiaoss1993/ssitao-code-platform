package com.ssitao.code.modular.iam.identity.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamAccountDO;
import com.ssitao.code.modular.iam.dal.mapper.IamAccountMapper;
import com.ssitao.code.modular.iam.identity.domain.model.IamAccount;
import com.ssitao.code.modular.iam.identity.domain.repository.IamAccountRepository;
import com.ssitao.code.modular.iam.identity.infrastructure.converter.IamAccountConverter;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamAccountMapper accountMapper;

    @Resource
    private IamAccountConverter accountConverter;

    @Override
    public String save(IamAccount account) {
        IamAccountDO accountDO = accountConverter.toDO(account);
        accountDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        accountMapper.insert(accountDO);
        return accountDO.getTbIamAccountId();
    }

    @Override
    public void update(IamAccount account) {
        IamAccountDO accountDO = accountConverter.toDO(account);
        accountDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        accountMapper.update(accountDO);
    }

    @Override
    public Optional<IamAccount> findById(String id) {
        IamAccountDO accountDO = accountMapper.selectOneById(id);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByAccountCode(String accountCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_code", accountCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByPhone(String phone, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_phone", phone);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByEmail(String email, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_mail", email);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByOpenId(String openId, String openType, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_openid", openId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public Optional<IamAccount> findByUserId(String userId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_association_id", userId)
                .eq("sy_status", "1");
        IamAccountDO accountDO = accountMapper.selectOneByQuery(query);
        return Optional.ofNullable(accountConverter.toDomain(accountDO));
    }

    @Override
    public boolean existsByAccountCode(String accountCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_code", accountCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_account_id", excludeId);
        }
        return accountMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public boolean existsByPhone(String phone, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("account_phone", phone);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_account_id", excludeId);
        }
        return accountMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public List<IamAccount> findByConditions(String accountName, String phone, Boolean status, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (accountName != null && !accountName.isEmpty()) {
            query.like("account_name", accountName);
        }
        if (phone != null && !phone.isEmpty()) {
            query.eq("account_phone", phone);
        }
        if (status != null) {
            query.eq("user_status", status ? "1" : "0");
        }
        query.orderBy("sy_createtime", false);
        List<IamAccountDO> list = accountMapper.selectListByQuery(query);
        return accountConverter.toDomainList(list);
    }

    @Override
    public void delete(String id) {
        accountMapper.deleteById(id);
    }

}
