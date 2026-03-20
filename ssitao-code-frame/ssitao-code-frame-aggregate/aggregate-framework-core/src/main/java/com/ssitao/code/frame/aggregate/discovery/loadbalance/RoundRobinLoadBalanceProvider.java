package com.ssitao.code.frame.aggregate.discovery.loadbalance;

import com.ssitao.code.frame.aggregate.load.LoadInfo;

/**
 * @author Nervose.Wu
 * @date 2022/5/19 14:50
 */
@LoadInfo(name = "RoundRobin")
public class RoundRobinLoadBalanceProvider implements LoadBalanceProvider {
    @Override
    public LoadBalanceServcie provide() {
        return new RoundRobinLoadBalanceServcieImpl();
    }
}
