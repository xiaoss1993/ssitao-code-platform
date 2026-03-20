package com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.factory;

import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity.Payment;

import java.math.BigDecimal;

/**
 * Created by changming.xie on 4/13/16.
 */
public class PaymentFactory {


    public static Payment buildPayment(Long orderId, String paymentNo, BigDecimal totalAmount) {
        return new Payment(orderId, paymentNo, totalAmount);
    }
}
