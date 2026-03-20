package com.ssitao.code.frame.aggregate.basic.usage.repository;


import com.ssitao.code.frame.aggregate.basic.usage.dao.AccountDao;
import com.ssitao.code.frame.aggregate.basic.usage.entity.Account;
import com.ssitao.code.frame.aggregate.repository.DaoAwareAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nervose.Wu
 * @date 2023/6/26 14:43
 */
@Repository
public class AccountRepository extends DaoAwareAggregateRepository<Account,Long> {

    @Autowired
    private AccountDao accountDao;

    public AccountRepository() {
        super(Account.class);
    }

    public Account findByAccountId(String accountId) {
        return fetchAllComponents(accountDao.findByAccountId(accountId));
    }

    public List<Account> findByAccountIds(List<String> accountIds) {
        return fetchAllComponents(accountDao.findByAccountIds(accountIds));
    }
}
