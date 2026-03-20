package com.ssitao.code.frame.aggregate.eventhandling.annotation;

/**
 * Created by changming.xie on 12/19/17.
 */
public @interface TransactionCheck {

    String checkTransactionStatusMethod() default "";
}
