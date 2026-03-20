package com.ssitao.code.frame.aggregate.discovery.loadbalance;

import java.util.List;

/**
 * @author Nervose.Wu
 * @date 2022/5/12 16:39
 */
public interface LoadBalanceServcie {

    <T> T select(List<T> invokers);
}
