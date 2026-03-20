package com.ssitao.code.frame.aggregate.sample.quickstart.command.infrastructure.dao;

import com.ssitao.code.frame.aggregate.dao.CollectiveAggregateRootDao;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity.Payment;

import java.util.List;

/**
 * Created by changming.xie on 4/8/16.
 */
public interface PaymentDao extends CollectiveAggregateRootDao<Payment, Long> {

    Payment findByPaymentNo(String paymentNo);

    List<Payment> findAll();
}
