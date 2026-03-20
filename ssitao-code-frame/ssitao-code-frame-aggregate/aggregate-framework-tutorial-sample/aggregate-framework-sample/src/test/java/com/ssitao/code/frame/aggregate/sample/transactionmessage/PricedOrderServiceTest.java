package com.ssitao.code.frame.aggregate.sample.transactionmessage;

import com.ssitao.code.frame.aggregate.sample.AbstractTestCase;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity.PricedOrder;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.repository.OrderRepository;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by changming.xie on 4/13/16.
 */
public class PricedOrderServiceTest extends AbstractTestCase {


    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void given_new_priced_order_when_confirm_and_place_order_then_all_called_suceed() {

        List<PricedOrder> pricedOrders = new ArrayList<PricedOrder>();


        PricedOrder pricedOrder = orderService.placeOrder(1, 10, 1);

        pricedOrder.confirm(2);

        pricedOrder.confirm(2);

        pricedOrders.add(pricedOrder);

        pricedOrder = orderService.placeOrder(1, 20, 1);

        pricedOrder.confirm(2);

        pricedOrder.confirm(2);

        pricedOrders.add(pricedOrder);

        orderRepository.save(pricedOrders);
    }
}
