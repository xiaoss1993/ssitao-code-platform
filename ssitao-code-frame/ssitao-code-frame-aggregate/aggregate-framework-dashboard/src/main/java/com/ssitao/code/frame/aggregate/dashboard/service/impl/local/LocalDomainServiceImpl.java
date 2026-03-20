package com.ssitao.code.frame.aggregate.dashboard.service.impl.local;


import com.ssitao.code.frame.aggregate.AggClient;
import com.ssitao.code.frame.aggregate.dashboard.service.condition.LocalStorageCondition;
import com.ssitao.code.frame.aggregate.dashboard.service.impl.BaseDomainServiceImpl;
import com.ssitao.code.frame.aggregate.storage.TransactionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * @Author huabao.fang
 * @Date 2022/6/9 17:03
 **/
@Conditional(LocalStorageCondition.class)
@Service
public class LocalDomainServiceImpl extends BaseDomainServiceImpl {

    @Autowired
    private AggClient aggClient;

    @Override
    public TransactionStorage getTransactionStorage() {
        return aggClient.getTransactionStorage();
    }
}
