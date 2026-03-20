package com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.repository;

import com.ssitao.code.frame.aggregate.cache.L2Cache;
import com.ssitao.code.frame.aggregate.repository.DaoAwareAggregateRepository;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity.Payment;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.infrastructure.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by changming.xie on 4/13/16.
 */
@Repository
public class PaymentRepository extends DaoAwareAggregateRepository<Payment, Long> {

    @Autowired
    PaymentDao paymentDao;

    public PaymentRepository() {
        this(Payment.class);
    }

    protected PaymentRepository(Class<Payment> aggregateType) {
        super(aggregateType);
    }

    @Autowired(required = false)
    @Qualifier("paymentL2Cache")
    public void setL2Cacher(L2Cache<Payment, Long> l2Cache) {
        this.l2Cache = l2Cache;
    }

    public Payment findByPaymentNo(String paymentNo) {
        return paymentDao.findByPaymentNo(paymentNo);
    }

    @Transactional
    public void saveWithTransactional(Payment payment) {
        this.save(payment);
    }
}
