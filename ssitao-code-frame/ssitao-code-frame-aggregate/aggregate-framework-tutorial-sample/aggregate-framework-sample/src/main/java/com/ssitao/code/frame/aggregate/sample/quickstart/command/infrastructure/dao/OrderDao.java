package com.ssitao.code.frame.aggregate.sample.quickstart.command.infrastructure.dao;

import com.ssitao.code.frame.aggregate.dao.CollectiveAggregateRootDao;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity.PricedOrder;

import java.util.List;

/**
 * Created by changming.xie on 4/8/16.
 */
public interface OrderDao extends CollectiveAggregateRootDao<PricedOrder, Long> {
    List<PricedOrder> findByPrice();

    PricedOrder findByNo(String no);
}
