package com.ssitao.code.frame.aggregate.discovery.loadbalance;

/**
 * @author Nervose.Wu
 * @date 2022/5/19 14:47
 */
public interface LoadBalanceProvider {

    LoadBalanceServcie provide();
}
