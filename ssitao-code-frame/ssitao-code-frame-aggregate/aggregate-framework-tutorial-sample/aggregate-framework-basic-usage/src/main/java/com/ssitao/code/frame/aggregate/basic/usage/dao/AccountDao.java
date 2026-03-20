package com.ssitao.code.frame.aggregate.basic.usage.dao;


import com.ssitao.code.frame.aggregate.basic.usage.entity.Account;
import com.ssitao.code.frame.aggregate.dao.AggregateRootDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Nervose.Wu
 * @date 2023/6/26 14:33
 */

public interface AccountDao extends AggregateRootDao<Account,Long> {

    Account findByAccountId(@Param("accountId") String accountId);

    List<Account> findByAccountIds(List<String> accountIds);
}
