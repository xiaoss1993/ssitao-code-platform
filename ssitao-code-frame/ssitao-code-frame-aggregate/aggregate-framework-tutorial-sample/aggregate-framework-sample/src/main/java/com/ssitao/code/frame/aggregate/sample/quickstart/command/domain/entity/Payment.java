package com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.entity;

import com.ssitao.code.frame.aggregate.entity.AbstractSimpleAggregateRoot;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.event.PaymentConfirmedEvent;

import java.math.BigDecimal;

/**
 * Created by changming.xie on 4/7/16.
 */
public class Payment extends AbstractSimpleAggregateRoot<Long> {
    private static final long serialVersionUID = -563655006765498113L;

    private int statusId;

    private BigDecimal totalAmount;

    private Long orderId;

    private String paymentNo;

    private Long id;

    public Payment() {

    }

    public Payment(Long orderId, String paymentNo, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.paymentNo = paymentNo;
        this.totalAmount = totalAmount;
        this.apply(new PaymentConfirmedEvent(paymentNo));
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void confirm() {
        this.statusId = 1;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
