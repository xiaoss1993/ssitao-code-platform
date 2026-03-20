package com.ssitao.code.frame.aggregate.sample.quickstart.command.infrastructure.dao;

import com.ssitao.code.frame.aggregate.dao.CollectiveDomainObjectDao;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity.OrderLine;

import java.util.List;

/**
 * Created by changming.xie on 4/8/16.
 */
public interface OrderLineDao extends CollectiveDomainObjectDao<OrderLine, Long> {

    public List<OrderLine> findByOrderId(Long orderId);

    public List<OrderLine> findAll();
}
