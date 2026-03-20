package com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.repository;

import com.ssitao.code.frame.aggregate.cache.L2Cache;
import com.ssitao.code.frame.aggregate.repository.DaoAwareAggregateRepository;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity.PricedOrder;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.infrastructure.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by changming.xie on 4/7/16.
 */
@Repository
public class OrderRepository extends DaoAwareAggregateRepository<PricedOrder, Long> {

    @Autowired
    OrderDao orderDao;

    protected OrderRepository(Class<PricedOrder> aggregateType) {
        super(aggregateType);
    }

    public OrderRepository() {
        this(PricedOrder.class);
    }

    @Autowired(required = false)
    @Qualifier("pricedOrderMultiLevelL2Cache")
    public void setL2Cacher(L2Cache<PricedOrder, Long> l2Cache) {
        super.setL2Cache(l2Cache);
    }

    public PricedOrder findByMerchantOrderNo(String no) {
        PricedOrder order = null;

        order = orderDao.findByNo(no);
        return findOne(order.getId());

    }
}
