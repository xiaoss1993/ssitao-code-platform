package com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.event;

import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity.PricedOrder;

import java.io.Serializable;

/**
 * Created by changming.xie on 11/28/17.
 */
public class OrderConfirmedEvent implements Serializable {
    private static final long serialVersionUID = 5747983401748068456L;

    private String no;

    public OrderConfirmedEvent(PricedOrder pricedOrder) {

        this.no = pricedOrder.getMerchantOrderNo();
    }
}
