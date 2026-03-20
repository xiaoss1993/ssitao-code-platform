package com.ssitao.code.frame.aggregate.server.service;

import com.ssitao.code.frame.aggregate.AggServer;
import com.ssitao.code.frame.aggregate.dashboard.service.impl.BaseDomainServiceImpl;
import com.ssitao.code.frame.aggregate.storage.TransactionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author huabao.fang
 * @Date 2022/6/14 12:18
 **/
@Service
public class DomainServiceImpl extends BaseDomainServiceImpl {

    @Autowired
    private AggServer aggServer;


    @Override
    public TransactionStorage getTransactionStorage() {
        return aggServer.getTransactionStorage();
    }
}
