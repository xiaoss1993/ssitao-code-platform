package com.ssitao.code.frame.aggregate.sample.complexmodel.command.service;

import com.ssitao.code.frame.aggregate.sample.complexmodel.command.domain.entity.BookingOrder;

/**
 * User: changming.xie
 * Date: 14-6-3
 * Time: 下午3:44
 */
public interface OrderService {

    public void add(BookingOrder bookingOrder);

    public void add2(String as);
}
