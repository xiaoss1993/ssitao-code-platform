package com.ssitao.code.frame.aggregate.discovery.registry;

/**
 * @author Nervose.Wu
 * @date 2022/7/7 17:34
 */
public interface ClientRegistryConfig extends RegistryConfig {

    RegistryRole getRegistryRole();

    String getLoadBalanceType();
}
