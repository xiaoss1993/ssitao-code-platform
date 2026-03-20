package com.ssitao.code.frame.aggregate.sample.quickstart.command.service;

import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity.PricedOrder;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.factory.OrderFactory;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by changming.xie on 4/7/16.
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public PricedOrder placeOrder(int productId, int price, int i) {
        PricedOrder pricedOrder = OrderFactory.buildOrder(productId, price, i);
        return orderRepository.save(pricedOrder);
    }

    @Transactional
    public void confirm(long orderId, int statusId) {
        PricedOrder pricedOrder = orderRepository.findOne(orderId);
        pricedOrder.confirm(statusId);
        pricedOrder.getOrderLines().get(0).setPrice(statusId);
        orderRepository.save(pricedOrder);
    }

    public PricedOrder find(long orderId) {
        return orderRepository.findOne(orderId);
    }
}
